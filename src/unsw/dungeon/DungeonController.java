package unsw.dungeon;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * A JavaFX controller for the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController {
	
	private Stage primaryStage;
	
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
    
    private List<ImageView> initialEntities;

    private Player player;

    private Dungeon dungeon;

    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities) {
        this.dungeon = dungeon;
        this.dungeon.setMainController(this);
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
    }
    
    public void setPrimaryStage(Stage primaryStage) {
    	this.primaryStage = primaryStage;
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
        	;
        });
        
        this.SaveGameButton.setOnAction(event -> {
        	;
        });
        
        this.QuitToTitleButton.setOnAction(event -> {
        	;
        });
        
        this.anchor.setOnKeyPressed(event -> {
        	this.handleKeyPress(event);
        });
    }

    @FXML
    public void handleKeyPress(KeyEvent event) {
    	System.out.println(event.getCode());
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
    	if (this.flow.isVisible()) {
    		this.flow.setVisible(false);
    	}
    	else {
    		this.flow.setVisible(true);
    	}
    }
    
    public void changeBombCount(int count) {
    	this.bombCount.setText(Integer.toString(count));
    }
    
    public void changeSwordDurability(int durability) {
    	this.swordDurability.setText(Integer.toString(durability));
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
}

