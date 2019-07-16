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

    public List<Entity> getEntities() {
    	return this.entities;
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
    	System.out.println(entity);
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
    	if (entity instanceof PhysicalObject) {
    		PhysicalObject object = (PhysicalObject) entity;
    		if (object.isImmovableObject() == true) {
        		return true;
        	}
    	}
    	
    	
    	
    	return false;
    }
    
    public void pickUpItem(Item item) {
    	player.addItem(item);
    }
    
    public boolean moveEntityCheck(int x, int y, Direction direction) {
    	Entity entityAtCoord = this.getEntityAtCoord(x, y);
    	System.out.println(x);
    	System.out.println(y);

    	// If boulder in square
    	if (entityAtCoord instanceof Boulder) {
    		// Convert to boulder object
    		Boulder boulderEntity = (Boulder) entityAtCoord;
    		
    		if (direction == Direction.UP && boulderEntity.moveUp()) {
    			// Checks if boulder can move as well, if can move then it will move
    			return true;
    		}
    		else if (direction == Direction.RIGHT && boulderEntity.moveRight()) {
    			return true;
    		}
    		else if (direction == Direction.DOWN && boulderEntity.moveDown()) {
    			return true;
    		}
    		else if (direction == Direction.LEFT && boulderEntity.moveLeft()) {
    			return true;
    		}
    		// Boulder cannot move, so player will not move
    		return false;
    	}
    	if (entityAtCoord instanceof Door) {
    		Door door = (Door) entityAtCoord;
    		if (door.Locked() == true) {
        		if (player.checkKeys(door.getId())) {
        			door.unlockDoor();
        			return true;
        		}
        		return false;
    		}
    		return true;

    	}
    	if (entityAtCoord instanceof Item) {
    		pickUpItem((Item) entityAtCoord);
    		this.entities.remove(entityAtCoord);
    		return true;
    	}
    	if (entityAtCoord instanceof Exit) {
    		
    	}
    	// If wall or other immovable object in square that player
    	// wants to walk to
    	if (isImmovableAtCoord(x, y)) {
    		return false;
    	}
    	return true;
    }
    
    public void causeDamage(int x, int y) {
    	System.out.println("INNER CALLED");
    	if (tileInDungeon(x, y-1)) {
    		processDamage(x, y-1);
    	}
    	if (tileInDungeon(x-1,y)) {
    		processDamage(x-1,y);
    		
    	}
    	if (tileInDungeon(x+1,y+1)) {
    		processDamage(x+1,y+1);
    	}
    	if (tileInDungeon(x+1,y)) {
    		processDamage(x+1,y);
    	}
    }

    private boolean tileInDungeon(int x, int y) {
    	if (x < 0 || y < 0 || x >= width || y >= width) {
    		return false;
    	}
    	return true;
    }
    
    public void processDamage(int x, int y) {
    	Entity entity = getEntityAtCoord(x,y);
    	if (entity instanceof Player) {
    		// kill the player
    		;
    	}
//    	else if (entity instanceof Enemy) {
//    		;
//    	}
    	else if (entity instanceof Boulder) {
    		// destroy boulder
    		System.out.println("Attempts to destroy boulder at: " + x + "," + "y");
    	}
    }
    
}
