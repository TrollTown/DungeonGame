package unsw.dungeon;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * An entity in the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class Entity {

    // IntegerProperty is used so that changes to the entities position can be
    // externally observed.
    private IntegerProperty x, y;
    private boolean immovableObject;
    private Dungeon dungeon;
    /**
     * Create an entity positioned in square (x,y)
     * @param x
     * @param y
     */
    public Entity(int x, int y, boolean immovableObject) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.immovableObject = immovableObject;
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
        return y().get();
    }

    public int getX() {
        return x().get();
    }
    
    public void setX(int x) {
        x().set(x);
    }
    
    public void setY(int y) {
    	y().set(y);
    }

	public boolean isImmovableObject() {
		return immovableObject;
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
}
