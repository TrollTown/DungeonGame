package unsw.dungeon;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

/**
 * A JavaFX controller for the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController {

    @FXML
    private GridPane squares;

    private List<ImageView> initialEntities;

    private Player player;

    private Dungeon dungeon;

    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities) {
        this.dungeon = dungeon;
        this.dungeon.setMainController(this);
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
    }

    @FXML
    public void initialize() {
    	
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
        default:
            break;
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

}

