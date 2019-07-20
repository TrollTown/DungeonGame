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
    	System.out.println(entity);
    	if (entity != null) {
    		entity.setDungeon(this);
    	}
        entities.add(entity);
    }
    
    public List<Entity> getEntityAtCoord(int x, int y) {
    	List<Entity> listEntity = new ArrayList<Entity>();
    	for (Entity entity : this.entities) {
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
    	System.out.println("Running Entity Check");
    	if (this.getGoal().hasMetGoal(this, this.player)) {
    		this.completedDungeon = true;
    	}
    	System.out.println("Checked goal");
    	
    	List<Entity> entitiesAtCoord = this.getEntityAtCoord(x, y);
    	for (Entity entityAtCoord: entitiesAtCoord) {
    		System.out.println("entityAtCoord");
    		System.out.println(entityAtCoord);
    		if (entityAtCoord == null) {
    			continue;
    		}
    		if (!entityAtCoord.moveEntityCheck(x, y, direction, player.getInventory())) {
    			return false;
    		}
    		if (entityAtCoord instanceof Item) {
    			System.out.println(entityAtCoord);
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
        		// kill the player
        		this.player.killPlayer();
        	}
        	else if (entity instanceof Enemy) {
        		
        	}
        	else if (entity instanceof Boulder) {
        		// destroy boulder
        		System.out.println("Destroys boulder at: " + x + "," + y);
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
			return this.goal.hasMetGoal(this, player);
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
		System.exit(0);
	}
	
	public void initiateEnemyAI() {
		EnemyAITimer ai = new EnemyAITimer(1, this.getEnemies(), this.getPlayer());
	}


    
}
