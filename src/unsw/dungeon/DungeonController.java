package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * A JavaFX controller for the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController {
	
	@FXML
	private AnchorPane anchor;

    @FXML
    private GridPane squares;
    
    @FXML
    private FlowPane menu;
    
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
    
    @FXML
    private Pane saveGamePanel;
    
    @FXML
    private Label saveGameLabel;
    
    @FXML
    private GridPane saveTable;
    
    
    @FXML
    private Button backToInGameMenu;
    
    private List<ImageView> initialEntities;

    private Player player;

    private Dungeon dungeon;
    
    private DungeonApplication application;

    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities) {
        this.dungeon = dungeon;
        this.dungeon.setMainController(this);
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
    }

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
        	this.toggleSaveGamePanel();
        });
        
        this.QuitToTitleButton.setOnAction(event -> {
        	this.application.loadMainMenu();
        });
        
        this.anchor.setOnKeyPressed(event -> {
        	this.handleKeyPress(event);
        	this.anchor.requestFocus();
        });
        
        this.backToInGameMenu.setOnAction(event -> {
        	this.backToMenu();
        	this.menu.requestFocus();
        	System.out.println("This worked");
        });
    }

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
    
    @FXML
    private void toggleMenu() {
    	if (this.menu.isVisible()) {
    		this.menu.setVisible(false);
    		this.saveGamePanel.setVisible(false);
    	}
    	else {
    		this.menu.setVisible(true);
    		this.menu.requestFocus();
    	}
    }
    
    @FXML
    private void toggleSaveGamePanel() {
    	if (this.saveGamePanel.isVisible()) {
    		this.saveGamePanel.setVisible(false);
    	}
    	else {
    		this.populateSavePanel();
    		this.saveGamePanel.setVisible(true);
    	}
    }
    
    @FXML
    private void backToMenu() {
    	this.saveGamePanel.setVisible(false);
    }
    
    public void changeBombCount(int count) {
    	this.bombCount.setText(Integer.toString(count));
    }
    
    public void changeSwordDurability(int durability) {
    	this.swordDurability.setText(Integer.toString(durability));
    }
    
    public void updateInvincibilityTimer(int seconds) {
    	this.invincibilityTimer.setText(Integer.toString(seconds) + " s");
    }
    
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
    
    public void setApplication(DungeonApplication app) {
    	this.application = app;
    }
    
    public DungeonApplication getApplication() {
    	return this.application;
    }
    
    public void resetProgress() {
    	this.application.resetLevelProgress();
    }
    
    private void populateSavePanel() {
    	ArrayList<GameSave> saves = this.application.getSaveManager().getSavesList();
    	ArrayList<String> saveNames = new ArrayList<String>();
    	ArrayList<String> saveLevels = new ArrayList<String>();
    	ArrayList<String> saveTimestamps = new ArrayList<String>();
    	for (int i = 0; i < 10; i++) {
    		if (i < saves.size()) {
    			saveNames.add(saves.get(i).getSaveName());
    			saveLevels.add(Integer.toString(saves.get(i).getSaveLevel()));
    			saveTimestamps.add(saves.get(i).getTimeStamp());
    		}
    		else {
    			saveNames.add("");
    			saveLevels.add("");
    			saveTimestamps.add("");
    		}
    	}
    	for (int i = 0; i < 10; i++) {
    		this.saveTable.add(new Label(saveNames.get(i)), 0, i);
    		this.saveTable.add(new Label(saveLevels.get(i)), 1, i);
    		this.saveTable.add(new Label(saveTimestamps.get(i)), 2, i);
    		
    	}
    }
}

