package unsw.dungeon;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * An entity in the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public abstract class Entity {

    // IntegerProperty is used so that changes to the entities position can be
    // externally observed.
    private IntegerProperty x, y;
    private Dungeon dungeon;
    /**
     * Create an entity positioned in square (x,y)
     * @param x
     * @param y
     */
    public Entity(int x, int y) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
    }

    public Dungeon getDungeon() {
		return dungeon;
	}

	public void setDungeon(Dungeon dungeon) {
		this.dungeon = dungeon;
	}

	public IntegerProperty x() {
        return x;
    }

    public IntegerProperty y() {
        return y;
    }

    public int getY() {
        return this.y().get();
    }

    public int getX() {
        return this.x().get();
    }
    
    public void setX(int x) {
        this.x().set(x);
    }
    
    public void setY(int y) {
    	this.y().set(y);
    }
    

	public boolean isImmovableAtCoord(int x, int y) {
		return dungeon.isImmovableAtCoord(x, y);
	}
	
    public int getDungeonWidth() {
    	return dungeon.getWidth();
    }
    
    public int getDungeonHeight() {
    	return dungeon.getHeight();
    }
    
    public void causeDamage() {
    	System.out.println("Called");
    	assertNotNull(this.dungeon);
    	System.out.println(this.dungeon);
    	this.dungeon.causeDamage(this.getX(), this.getY());
    }
    
    public boolean checkGoal() {
    	return dungeon.checkGoal();
    }
    
    public boolean checkBoulderOnTop() {
    	List<Entity> entities = dungeon.getEntityAtCoord(getX(), getY());
    	for (Entity entity: entities) {
    		if (entity instanceof Boulder) {
    			return true;
    		}
    	}
    	return false;
    }
    
    public FloorSwitch getFloorSwitch(int x, int y) {
    	System.out.println(x + " " + y);
    	List<Entity> entities = dungeon.getEntityAtCoord(getX(), getY());
    	for (Entity entity: entities) {
    		System.out.println("found: " + entity);
    		if (entity instanceof FloorSwitch) {
    			return (FloorSwitch) entity;
    		}
    	}
    	return null;
    }
    
    public abstract boolean moveEntityCheck(int x, int y, Direction direction, Inventory inventory);
}
