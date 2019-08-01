package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;

/**
 * A JavaFX controller for the dungeon.
 * @author Edward Webb and William Shen
 *
 */
public class DungeonController {
	
	@FXML
	private AnchorPane anchor;

    @FXML
    private GridPane squares;
    
    @FXML
    private FlowPane flow;
    
    @FXML
    private Button BackToGameButton;
    
    @FXML
    private Button ResetProgressButton;
    
    @FXML
    private Button SaveGameButton;
    
    @FXML
    private Button QuitToTitleButton;
    
    @FXML
    private FlowPane inventory;
    
    @FXML
    private Label bombCount;
    
    @FXML
    private Label swordDurability;
    
    @FXML
    private Label invincibilityTimer;
    
    @FXML
    private FlowPane inventoryKeys;
    
    private List<ImageView> initialEntities;

    private Player player;

    private Dungeon dungeon;
    
    private DungeonApplication application;

    /**
     * Constructor for the dungeon controller
     * @param dungeon the dungeon
     * @param initialEntities the dungeon's entities
     */
    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities) {
        this.dungeon = dungeon;
        this.dungeon.setMainController(this);
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
    }

    /**
     * Initialises the controller
     */
    @FXML
    public void initialize() {
    	// Initialise the menu popup
    	//this.initialiseMenu();
    	
        Image ground = new Image("/dirt_0_new.png");

        // Add the ground first so it is below all other entities
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                squares.add(new ImageView(ground), x, y);
            }
        }

        for (ImageView entity : initialEntities)
            squares.getChildren().add(entity);
        
        this.BackToGameButton.setOnAction(event -> {
        	this.toggleMenu();
        	this.anchor.requestFocus();
        });
        
        this.ResetProgressButton.setOnAction(event -> {
        	this.resetProgress();
        	this.anchor.requestFocus();
        });
        
        this.SaveGameButton.setOnAction(event -> {
        	this.anchor.requestFocus();
        });
        
        this.QuitToTitleButton.setOnAction(event -> {
        	this.application.loadMainMenu();
        });
        
        this.anchor.setOnKeyPressed(event -> {
        	this.handleKeyPress(event);
        });
    }

    /**
     * Handles a key press
     * @param event an event eg. a key press
     */
    @FXML
    public void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
        case UP:
            player.moveUp();
            break;
        case DOWN:
            player.moveDown();
            break;
        case LEFT:
            player.moveLeft();
            break;
        case RIGHT:
            player.moveRight();
            break;
        case E:
        	player.placeBomb();
        	break;
        case K:
        	player.useSword();
        	break;
        case ESCAPE:
        	this.toggleMenu();
        	break;
        default:
            break;
        }
    }
    
    /**
     * Toggles the menu to show/hide
     */
    @FXML
    private void toggleMenu() {
    	if (this.flow.isVisible()) {
    		this.flow.setVisible(false);
    	}
    	else {
    		this.flow.setVisible(true);
    	}
    }
    
    /**
     * Updates the bomb count in the inventory UI
     * @param count
     */
    public void changeBombCount(int count) {
    	this.bombCount.setText(Integer.toString(count));
    }
    
    /**
     * Updates the number of hits the sword has remaining
     * @param durability The durability of the sword
     */
    public void changeSwordDurability(int durability) {
    	this.swordDurability.setText(Integer.toString(durability));
    }
    
    /**
     * Updates the number of seconds remaining for the invincibility effect
     * @param seconds The number of seconds remaining
     */
    public void updateInvincibilityTimer(int seconds) {
    	this.invincibilityTimer.setText(Integer.toString(seconds) + " s");
    }
    
    /**
     * Updates the number of keys remaining
     * @param keyCount number of keys remaining
     */
    public void updateInventoryKeys(int keyCount) {
    	int existingKeyCount = this.inventoryKeys.getChildren().size();
		while (existingKeyCount < keyCount) {
			Image keyImage = new Image("/key.png");
			this.inventoryKeys.getChildren().add(new ImageView(keyImage));
			existingKeyCount++;
		}
		while (existingKeyCount > keyCount) {
			this.inventoryKeys.getChildren().remove(0);
			existingKeyCount--;
		}
    }
    
    /**
     * Updates the bomb's view when a bomb is placed
     * @param entity The bomb
     */
    public void updateView(Entity entity) {
    	if (entity instanceof LitBomb) {
    		LitBomb bomb = (LitBomb) entity;
    		if (bomb.getState() instanceof LitState) {
    			ImageView newView = new ImageView("/bomb_lit_1.png");
    			squares.add(newView, bomb.getX(), bomb.getY());
    	        bomb.getStateProperty().addListener(new ChangeListener<LitBombState>() {
    	            @Override
    	            public void changed(ObservableValue<? extends LitBombState> observable,
    	                    LitBombState oldValue, LitBombState newValue) {
    	            	if (newValue instanceof ExplodedState) {
    	            		newView.setImage(new Image("/bomb_lit_4.png"));
    	            	} else {
    	            		newView.setVisible(false);
    	            	}               
    	            }
    	        });			
    		}
    	} 
    }
    
    /**
     * Sets the dungeon application property
     * @param app The dungeon application
     */
    public void setApplication(DungeonApplication app) {
    	this.application = app;
    }
    
    /**
     * Gets the dungeon application
     * @return The dungeon application
     */
    public DungeonApplication getApplication() {
    	return this.application;
    }
    
    /**
     * Resets the dungeon progress
     */
    public void resetProgress() {
    	this.application.resetLevelProgress();
    }
}

