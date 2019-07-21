/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
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
    private List<Enemy> enemies;
    private Player player;
    private GoalInterface goal;
    private boolean completedDungeon;

    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.setEnemies(new ArrayList<>());
        this.player = null;
        this.goal = null;
        this.setCompletedDungeon(false);
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
    	if (entity != null) {
    		entity.setDungeon(this);
    	}
        entities.add(entity);
    }
    
    private ArrayList<Entity> copyEntityList(List<Entity> entityList) {
    	ArrayList<Entity> newEntityList = new ArrayList<Entity>(entityList.size());
    	for (Entity entity: entityList) {
    		newEntityList.add(entity);
    	}
    	return newEntityList;
    }
    
    public List<Entity> getEntityAtCoord(int x, int y) {
    	List<Entity> listEntity = new ArrayList<Entity>();
    	
    	List<Entity> copyOfEntities = copyEntityList(this.entities); // Avoid co-modification error
    	Iterator it = copyOfEntities.iterator();
    	while (it.hasNext()) {
    		Entity entity = (Entity) it.next();
    		if (entity != null && entity.getX() == x &&
    			entity.getY() == y) {
    			listEntity.add(entity);
    		}
    	}
    	return listEntity;
    }
    public boolean isImmovableAtCoord(int x, int y) {
    	List<Entity> listOfEntities = getEntityAtCoord(x, y);
    	if (listOfEntities.isEmpty()) {
    		return false;
    	}
    	for (Entity entity: listOfEntities) {
        	if (entity instanceof PhysicalObject) {
        		PhysicalObject object = (PhysicalObject) entity;
        		if (object.isImmovableObject() == true) {
            		return true;
            	}
        	}
    	}
    	
    	return false;
    }
    
    public void pickUpItem(Item item) {
    	player.addItem(item);
    }
    
    public boolean moveEntityCheck(int x, int y, Direction direction) {
    	
    	List<Entity> entitiesAtCoord = this.getEntityAtCoord(x, y);
    	for (Entity entityAtCoord: entitiesAtCoord) {
    		if (entityAtCoord == null) {
    			continue;
    		}
    		if (!entityAtCoord.moveEntityCheck(x, y, direction, player.getInventory())) {
    			return false;
    		}
    		if (entityAtCoord instanceof Enemy) {
    			processPlayerEnemyCollision(this.player, (Enemy) entityAtCoord);
    			return true;
    		}
    		if (entityAtCoord instanceof Item) {
        		pickUpItem((Item) entityAtCoord);
        		this.entities.remove(entityAtCoord);
        	}
        	if (isImmovableAtCoord(x, y)) {
        		return false;
        	}
    	}
    	return true;
    }
    
    public void causeDamage(int x, int y) {
    	if (tileInDungeon(x, y-1)) {
    		processDamage(x, y-1);
    	}
    	if (tileInDungeon(x-1,y)) {
    		processDamage(x-1,y);
    		
    	}
    	if (tileInDungeon(x,y+1)) {
    		processDamage(x,y+1);
    	}
    	if (tileInDungeon(x+1,y)) {
    		processDamage(x+1,y);
    	}
    	processDamage(x,y);
    }

    private boolean tileInDungeon(int x, int y) {
    	if (x < 0 || y < 0 || x >= width || y >= width) {
    		return false;
    	}
    	return true;
    }
    
    public void processDamage(int x, int y) {
    	List<Entity> entities = getEntityAtCoord(x,y);
    	for (Entity entity: entities) {
        	if (entity instanceof Player) {
        		if (((Player) entity).getInvincibilityStatus() == false) {
        			this.player.killPlayer();
        		}
        	}
        	if (entity instanceof Enemy) {
        		((Enemy) entity).kill();
        	}
        	if (entity instanceof Boulder) {
        		// destroy boulder
        		getEntities().remove(entity);
        	}
    	}

    }

    public GoalInterface getGoal() {
    	return this.goal;
    }
	public void setGoal(GoalInterface goal) {
		this.goal = goal;
	}
	
	public List<Enemy> getEnemies() {
		return this.enemies;
	}
	
	public boolean checkGoal() {
		if (this.isCompletedDungeon()) {
			return true;
		} else {
			return this.goal.hasMetGoal(this, player, null);
		}
	}

	public void setEnemies(List<Enemy> enemies) {
		this.enemies = enemies;
	}


	public boolean isCompletedDungeon() {
		return completedDungeon;
	}

	public void setCompletedDungeon(boolean completedDungeon) {
		this.completedDungeon = completedDungeon;
	}

	
	public void addEnemy(Enemy enemy) {
		this.enemies.add(enemy);
	}
	
	public void reloadDungeon() {
		System.out.println("Reloading Dungeon");
//		System.exit(0);
	}
	
	public void initiateEnemyAI() {
		EnemyAITimer ai = new EnemyAITimer(1, this.getEnemies(), this.getPlayer());
	}
	
	public void killEnemies(int x, int y) {
		for (int i = x - 1; i <= x + 1; i++) {
			for (Enemy enemy: enemies) {
				if (enemy.getX() == i && enemy.getY() == y)
					enemy.kill();
			}
		}
		for (int k = y - 1; k <= y + 1; k++) {
			for (Enemy enemy: enemies) {
				if (enemy.getX() == x && enemy.getY() == k)
					enemy.kill();
			}
		}
	}

	public void processPlayerEnemyCollision(Player player, Enemy enemy) {
		if (!enemy.isDead()) {
			if (this.player.getInvincibilityStatus() == true) {
				enemy.kill();
			}
			else {
				this.player.killPlayer();
			}
		}
	}
}
