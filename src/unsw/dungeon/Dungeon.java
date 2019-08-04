/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Edward Webb and William Shen
 *
 */
public class Dungeon {

    private int width, height;
    private List<Entity> entities;
    private List<Enemy> enemies;
    private Player player;
    private GoalInterface goal;
    private boolean completedDungeon;
    private DungeonController mainController;
    private ArrayList<Observer> observers = new ArrayList<Observer>();
    private int treasureCount;
    
    /**
     * The Dungeon constructor
     * @param width The dungeon's width
     * @param height The dungeon's height
     */
    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.setEnemies(new ArrayList<>());
        this.player = null;
        this.goal = null;
        this.setCompletedDungeon(false);
        this.treasureCount = 0;
        
    }

    /**
     * Gets the list of entities
     * @return The list of entities
     */
    public List<Entity> getEntities() {
    	return this.entities;
    }
    
    /**
     * Gets the dungeon's width
     * @return The dungeon's width
     */
    public int getWidth() {
        return width;
    }
    
    /**
     * Gets the dungeon's height
     * @return the dungeon's height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Gets the dungeon's main player
     * @return The player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Sets the dungeon's main player
     * @param player The player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }
    
    /**
     * Adds an entity to the dungeon
     * @param entity
     */
    public void addEntity(Entity entity) {
    	if (entity != null) {
    		entity.setDungeon(this);
    	}
    	if (mainController != null) {
    		mainController.updateView(entity);
    	}
        entities.add(entity);
    }
    
    /**
     * Makes a copy of an entity list
     * Used to avoid the co-modification error that the Timer class kept producing
     * @param entityList The entity list
     * @return A copy of the entity list
     */
    private ArrayList<Entity> copyEntityList(List<Entity> entityList) {
    	ArrayList<Entity> newEntityList = new ArrayList<Entity>(entityList.size());
    	for (Entity entity: entityList) {
    		newEntityList.add(entity);
    	}
    	return newEntityList;
    }
    
    /**
     * Gets a list of all the entities at this coordinate
     * @param x The x-coord
     * @param y The y-coord
     * @return The list of entities at this coord
     */
    public List<Entity> getEntityAtCoord(int x, int y) {
    	List<Entity> listEntity = new ArrayList<Entity>();
    	
    	List<Entity> copyOfEntities = copyEntityList(this.entities); // Avoid co-modification error
    	// Iterator used to avoid co-modification error
    	Iterator<Entity> it = copyOfEntities.iterator();
    	while (it.hasNext()) {
    		Entity entity = (Entity) it.next();
    		if (entity != null && entity.getX() == x &&
    			entity.getY() == y) {
    			listEntity.add(entity);
    		}
    	}
    	return listEntity;
    }
    
    /**
     * Checks if the player can move on the same square as the entity on this square
     * @param x The x-coord
     * @param y The y-coord
     * @return Whether the player can move to this square
     */
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
    
    public boolean isEnemy(int x, int y) {
    	for (Enemy enemy: this.getEnemies()) {
    		if (enemy.getX() == x && enemy.getY() == y) {
    			return true;
    		}
    	}
    	return false;
    }
    /**
     * Pick up an entity
     * @param item The item to pick up
     */
    public void pickUpItem(Item item) {
    	item.setShow(false);
    	player.addItem(item);
    }
    
    /**
     * Run every time the player moves
     * Checks if the player can move to the square it wants to go to
     * @param x the x-coord
     * @param y the y-coord
     * @param direction The player's direction it wants to move towards
     * @return Whether the player can move to this square or not
     */
    public boolean moveEntityCheck(int x, int y, Direction direction) {
    	
    	List<Entity> entitiesAtCoord = this.getEntityAtCoord(x, y);
    	for (Entity entityAtCoord: entitiesAtCoord) {
    		if (entityAtCoord == null) {
    			continue;
    		}
    		// Polymorphism
    		// Every single entity has this function
    		if (!entityAtCoord.moveEntityCheck(x, y, direction, player.getInventory())) {
    			return false;
    		}
    		// If the player encounters an enemy
    		if (entityAtCoord instanceof Enemy) {
    			processPlayerEnemyCollision(this.player, (Enemy) entityAtCoord);
    			return true;
    		}
    		// If the player encounters an item
    		if (entityAtCoord instanceof Item) {
        		pickUpItem((Item) entityAtCoord);
        		this.entities.remove(entityAtCoord);
        	}
    		// If the object at this square is immovable or not
        	if (isImmovableAtCoord(x, y)) {
        		return false;
        	}
    	}
    	return true;
    }
    
    /**
     * Given coords, causes damage on everything around that coordinate
     * Bombs use this
     * @param x The x-coord
     * @param y The y-coord
     */
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
    
    /**
     * If the tile exists within the dungeon's width and height
     * @param x The x-coord
     * @param y The y-coord
     * @return Whether the tile exists within the dungeon's width and height
     */
    private boolean tileInDungeon(int x, int y) {
    	if (x < 0 || y < 0 || x >= width || y >= width) {
    		return false;
    	}
    	return true;
    }
    
    /**
     * Causes damage at a specific coordinate
     * @param x The x-coord
     * @param y The y-coord
     */
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
        		((Boulder) entity).setShow(false);
        	}
    	}

    }

    /**
     * Gets dungeon's goal
     * @return The dungeon's goal
     */
    public GoalInterface getGoal() {
    	return this.goal;
    }
    
    /**
     * Sets dungeon's goal
     * @param goal The goal
     */
	public void setGoal(GoalInterface goal) {
		this.goal = goal;
	}
	
	/**
	 * Gets dungeon's enemies
	 * @return The dungeon's enemies
	 */
	public List<Enemy> getEnemies() {
		return this.enemies;
	}
	
	/**
	 * Checks if dungeon's goal has been met
	 * @return if goal has been met or not
	 */
	public boolean checkGoal() {
		if (this.isCompletedDungeon()) {
			return true;
		} else {
			return this.goal.hasMetGoal(this, player, null);
		}
	}

	/**
	 * Set the dungeon's enemies
	 * @param enemies The enemies
	 */
	public void setEnemies(List<Enemy> enemies) {
		this.enemies = enemies;
	}

	public boolean isCompletedDungeon() {
		return completedDungeon;
	}

	public void setCompletedDungeon(boolean completedDungeon) {
		this.completedDungeon = completedDungeon;
		this.notifyDungeonCompletionObservers();
	}

	/**
	 * Add an enemy to the dungeon
	 * @param enemy The enemy
	 */
	public void addEnemy(Enemy enemy) {
		this.enemies.add(enemy);
	}
	
	/**
	 * Reloads the dungeon
	 */
	public void reloadDungeon() {
		this.getMainController().reloadDungeon();
	}
	
	/**
	 * Start the enemy AI
	 */
	public void initiateEnemyAI() {
		for (Enemy enemy: this.enemies) {
			enemy.runTimeline(player);
		}
		
	}
	
	/**
	 * 	// Kill enemies around these coordinates
	 * This is why there are two for loops:
	 *    X
     * X  X  X
     *    X
	 * The middle coordinate is the coordinate given, and it kills everything at these coordinates
	 * @param x The x-coord
	 * @param y The y-coord
	 */
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

	/**
	 * Process player and enemy colliding
	 * @param player The player
	 * @param enemy The enemy
	 */
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

	/**
	 * Gets the dungeon's main controller
	 * @return The controller
	 */
	public DungeonController getMainController() {
		return mainController;
	}
	
	/**
	 * Sets the dungeon's main controller
	 * @param mainController The main controller
	 */
	public void setMainController(DungeonController mainController) {
		this.mainController = mainController;
	}
	
	/**
	 * Attach an observer to the dungeon
	 * @param observer The observer
	 */
	public void attach(Observer observer) {
		this.observers.add(observer);
	}
	
	/**
	 * Notify observers that dungeon has been completed
	 */
	public void notifyDungeonCompletionObservers() {
		for (Observer observer : observers) {
			if (observer instanceof DungeonCompletionObserver) {
				observer.update();
			}
		}
	}

	public int getTreasureCount() {
		return treasureCount;
	}

	public void increaseTreasureCount() {
		this.treasureCount++;
	}
}
