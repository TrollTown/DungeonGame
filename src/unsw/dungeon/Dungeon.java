/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon {

    private int width, height;
    private List<Entity> entities;
    private Player player;

    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.player = null;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void addEntity(Entity entity) {
    	if (entity != null) {
    		entity.setDungeon(this);
    	}
        entities.add(entity);
    }
    
    public Entity getEntityAtCoord(int x, int y) {
    	for (Entity entity : this.entities) {
    		if (entity != null && entity.getX() == x &&
    			entity.getY() == y) {
    			return entity;
    		}
    	}
    	return null;
    }
    
    public boolean isImmovableAtCoord(int x, int y) {
    	Entity entity = getEntityAtCoord(x, y);
    	if (entity == null) {
    		
    		return false;
    	}
    	if (entity.isImmovableObject() == true) {
    		return true;
    	}
    	return false;
    }
}
