package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
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
    private GridPane squares;
    
    private Popup menu;

    private List<ImageView> initialEntities;

    private Player player;

    private Dungeon dungeon;

    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
    }
    
    public void setPrimaryStage(Stage primaryStage) {
    	this.primaryStage = primaryStage;
    }

    @FXML
    public void initialize() {
    	// Initialise the menu popup
    	this.initialiseMenu();
    	
        Image ground = new Image("/dirt_0_new.png");

        // Add the ground first so it is below all other entities
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                squares.add(new ImageView(ground), x, y);
            }
        }

        for (ImageView entity : initialEntities)
            squares.getChildren().add(entity);
        
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
    
    private void initialiseMenu() {
    	FlowPane pane = new FlowPane(Orientation.VERTICAL);
    	pane.setVgap(10);
    	Popup popup = new Popup();
    	Label label = new Label("Game Menu");
    	Button backToGame = new Button("Back to Game");
    	label.setStyle(" -fx-background-color: green");
    	pane.getChildren().addAll(label, backToGame);
    	popup.getContent().add(pane);
    	popup.setAutoHide(true);
    	this.menu = popup;
    }
    
    private void toggleMenu() {
    	if (!this.menu.isShowing()) {
    		menu.show(this.primaryStage);
    		// TODO: implement pausing enemy AI movement (pause game) - using javafx timeline?
    	}
    	else {
    		menu.hide();
    	}
    	
    }

}

