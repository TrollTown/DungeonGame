package unsw.dungeon;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * An entity in the dungeon.
 * @author Edward Webb and William Shen
 *
 */
public abstract class Entity {

    // IntegerProperty is used so that changes to the entities position can be
    // externally observed.
    private IntegerProperty x, y;
    private BooleanProperty show;
    private Dungeon dungeon;
    /**
     * Create an entity positioned in square (x,y)
     * @param x
     * @param y
     */
    public Entity(int x, int y) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.show = new SimpleBooleanProperty(true);
    }

    /**
     * Gets the dungeon
     * @return The dungeon
     */
    public Dungeon getDungeon() {
		return dungeon;
	}

    /**
     * Sets the dungeon
     * @param dungeon the dungeon
     */
	public void setDungeon(Dungeon dungeon) {
		this.dungeon = dungeon;
	}

	/**
	 * Returns the x-coord property
	 * @return x-coord property
	 */
	public IntegerProperty x() {
        return x;
    }

	/**
	 * Returns the y-coord property
	 * @return y-coord property
	 */
    public IntegerProperty y() {
        return y;
    }
    
    /**
     * Gets the x-coord
     * @return the x-coord
     */
    public int getX() {
        return this.x().get();
    }
    
    /**
     * Gets the y-coord
     * @return the y-coord
     */
    public int getY() {
        return this.y().get();
    }

    /**
     * Sets the x-coord
     * @param x the x-coord
     */
    public void setX(int x) {
        this.x().set(x);
    }
    
    /**
     * Sets the y-coord
     * @param y the y-coord
     */
    public void setY(int y) {
    	this.y().set(y);
    }
    
    /**
     * Method forwarding for method in dungeon
     * Gets whether can move through entity at these coords
     * @param x The x-coord
     * @param y The y-coord
     * @return Whether can move through entity
     */
	public boolean isImmovableAtCoord(int x, int y) {
		return dungeon.isImmovableAtCoord(x, y);
	}
	
	/**
	 * Get if enemy is at this coord
	 * @param x the x-coord
	 * @param y the y-coord
	 * @return whether enemy is here
	 */
	public boolean isEnemy(int x, int y) {
		return dungeon.isEnemy(x, y);
	}
	
	/**
	 * Gets the dungeon's width
	 * @return The dungeon width
	 */
    public int getDungeonWidth() {
    	return dungeon.getWidth();
    }
    
    /**
     * Gets the dungeon's height
     * @return The dungeon height
     */
    public int getDungeonHeight() {
    	return dungeon.getHeight();
    }
    
    /**
     * Causes damage around the entity
     */
    public void causeDamage() {
    	assertNotNull(this.dungeon);
    	this.dungeon.causeDamage(this.getX(), this.getY());
    }
    
    /**
     * Checks whether the dungeon goal has been met
     * @return Whether the dungeon goal has been met
     */
    public boolean checkGoal() {
    	return dungeon.checkGoal();
    }
    
    /**
     * Updates the view of the entity
     */
    public void updateView() {
    	dungeon.getMainController().updateView(this);
    }
    
    /**
     * Check if boulder is on top of this entity
     * @return whether boulder is on top of this entity
     */
    public boolean checkBoulderOnTop() {
    	List<Entity> entities = dungeon.getEntityAtCoord(getX(), getY());
    	for (Entity entity: entities) {
    		if (entity instanceof Boulder) {
    			return true;
    		}
    	}
    	return false;
    }
    
    /**
     * Get floor switch at given coords
     * @param x The x-coord
     * @param y The y-coord
     * @return The floor switch
     */
    public FloorSwitch getFloorSwitch(int x, int y) {
    	List<Entity> entities = dungeon.getEntityAtCoord(x, y);
    	for (Entity entity: entities) {
    		if (entity instanceof FloorSwitch) {
    			return (FloorSwitch) entity;
    		}
    	}
    	return null;
    }
    
    /**
     * Must be implemented by all Entity classes
     * Run every time the player moves, does any processing required and returns whether the player
     * can move to the square the entity is on
     * @param x The x-coord the player is attempting to move to
     * @param y The y-coord
     * @param direction The direction the player is moving in
     * @param inventory The player's inventory
     * @return Whether the player can move to this square
     */
    public abstract boolean moveEntityCheck(int x, int y, Direction direction, Inventory inventory);

    /**
     * Returns the boolean property of whether the entity is visible
     * @return The boolean property
     */
	public BooleanProperty getShow() {
		return show;
	}
	
	/**
	 * Sets whether the entity is visible or not
	 * @param show The visible property
	 */
	public void setShow(boolean show) {
		this.show.set(show);
	}
}
