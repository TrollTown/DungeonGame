package unsw.dungeon;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * A DungeonLoader that also creates the necessary ImageViews for the UI,
 * connects them via listeners to the model, and creates a controller.
 * @author William Shen and Edward Webb
 *
 */
public class DungeonControllerLoader extends DungeonLoader {
	
    private List<ImageView> entities;

    //Images
    private Image playerImage;
    private Image wallImage;
    private Image closedDoorImage;
    private Image boulderImage;
    private Image keyImage;
    //private Image openDoorImage;
    private Image treasureImage;
    private Image invincibilityImage;
    private Image swordImage;
    private Image unlitBombImage;
    private Image enemyImage;
    private Image exitImage;
    private Image switchImage;

    /**
     * Constructor for the dungeon controller loader class
     * @param filename The filename of the dungeon
     * @throws FileNotFoundException
     */
    public DungeonControllerLoader(String filename)
            throws FileNotFoundException {
        super(filename);
        entities = new ArrayList<>();
        playerImage = new Image("/human_new.png");
        wallImage = new Image("/brick_brown_0.png");
        closedDoorImage = new Image("/closed_door.png");
        //openDoorImage = new Image("/open_door.png");
        boulderImage = new Image("/boulder.png");
        keyImage = new Image("/key.png");
        treasureImage = new Image("/gold_pile.png");
        invincibilityImage = new Image("/brilliant_blue_new.png");
        swordImage = new Image("/greatsword_1_new.png");
        unlitBombImage = new Image("/bomb_unlit.png");
        enemyImage = new Image("/gnome.png");
        exitImage = new Image("/exit.png");
        switchImage = new Image("/pressure_plate.png");
    }
    
   
    /**
     * Load the player
     */
    @Override
    public void onLoad(Entity player) {
        ImageView view = new ImageView(playerImage);
        addEntity(player, view);
    }

    /**
     * Load a wall
     */
    @Override
    public void onLoad(Wall wall) {
        ImageView view = new ImageView(wallImage);
        addEntity(wall, view);
    }
    @Override
    public void onLoad(Door door) {
        ImageView view = new ImageView(closedDoorImage);
        changeDoorListener(door, view);
        addEntity(door, view);
    }
    
    /**
     * Load a boulder
     */
    @Override
    public void onLoad(Boulder boulder) {
    	ImageView view = new ImageView(boulderImage);
    	view.toFront();
    	addEntity(boulder, view);
    	setToFront(boulder, view);
    }
    
    /**
     * Load a treasure object
     */
    @Override
    public void onLoad(Treasure treasure) {
    	ImageView view = new ImageView(treasureImage);
    	addEntity(treasure, view);
    }
    /**
     * Load an invincibility potion
     */
    @Override
	public void onLoad(Invincibility invincibility) {
    	ImageView view = new ImageView(invincibilityImage);
    	addEntity(invincibility, view);
    }
	
    /**
     * Load a sword
     */
    @Override
	public void onLoad(Sword sword) {
    	ImageView view = new ImageView(swordImage);
    	addEntity(sword, view);
    }
	
    @Override
    public void onLoad(Key key) {
    	ImageView view = new ImageView(keyImage);
    	addEntity(key, view);
    }
    
    /**
     * Load a bomb
     */
	@Override
	public void onLoad(UnlitBomb bomb) {
		ImageView view = new ImageView(unlitBombImage);
		addEntity(bomb, view);
	}

	/**
	 * Load an enemy
	 */
	@Override
	public void onLoad(Enemy enemy) {
		ImageView view = new ImageView(enemyImage);
		addEntity(enemy, view);
		
	}
	
	/**
	 * Load an exit
	 */
	@Override
	public void onLoad(Exit exit) {
		ImageView view = new ImageView(exitImage);
		addEntity(exit, view);
	}
	
	/**
	 * Load a floor switch
	 */
	@Override
	public void onLoad(FloorSwitch floorswitch) {
		ImageView view = new ImageView(switchImage);
		addEntity(floorswitch, view);
	}

	/**
	 * Connect an entity and view and add the view
	 * @param entity the entity
	 * @param view the view
	 */
    private void addEntity(Entity entity, ImageView view) {
        trackPosition(entity, view);
        hideEntity(entity, view);
        entities.add(view);
    }
    
    /**
     * Add a listener to the door so the view changes when unlocked
     * @param door The door
     * @param node The door's image
     */
    private void changeDoorListener(Door door, ImageView node) {
    	door.getTypeProperty().addListener(new ChangeListener<TypeDoor>() {
    		@Override
    		public void changed(ObservableValue<? extends TypeDoor> observable,
    				TypeDoor oldValue, TypeDoor newValue) {
    			node.setImage(new Image("/open_door.png"));
    		}
    	});
    }
    
    /**
     * Hides an entity from the ui
     * @param entity The entity
     * @param node The entity's view
     */
    private void hideEntity(Entity entity, Node node) {
    	entity.getShow().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable,
                    Boolean oldValue, Boolean newValue) {
                node.setVisible(false);
            }
    	});
    }

    /**
     * Set a node in a GridPane to have its position track the position of an
     * entity in the dungeon.
     *
     * By connecting the model with the view in this way, the model requires no
     * knowledge of the view and changes to the position of entities in the
     * model will automatically be reflected in the view.
     * @param entity
     * @param node
     */
    private void trackPosition(Entity entity, Node node) {
        GridPane.setColumnIndex(node, entity.getX());
        GridPane.setRowIndex(node, entity.getY());
        entity.x().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setColumnIndex(node, newValue.intValue());
            }
        });
        entity.y().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setRowIndex(node, newValue.intValue());
            }
        });
    }
    
    private void setToFront(Entity entity, ImageView view) {
    	entity.x().addListener(new ChangeListener<Number>() {
    		@Override
    		public void changed(ObservableValue<? extends Number> observable,
    				Number oldValue, Number newValue) {
    			view.toFront();
    		}
    		
    	});
    	entity.y().addListener(new ChangeListener<Number>() {
    		@Override
    		public void changed(ObservableValue<? extends Number> observable,
    				Number oldValue, Number newValue) {
    			view.toFront();
    		}
    		
    	});
    }

    /**
     * Create a controller that can be attached to the DungeonView with all the
     * loaded entities.
     * @return The dungeon controller
     * @throws FileNotFoundException
     */
    public DungeonController loadController() throws FileNotFoundException {
        return new DungeonController(load(), entities);
    }
}
