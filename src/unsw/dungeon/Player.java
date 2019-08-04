package unsw.dungeon;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

/**
 * The player entity
 * @author Edward Webb and William Shen
 *
 */
public class Player extends Entity {

    private Dungeon dungeon;
    private Inventory inventory;
    private boolean isAlive;
    private InvincibilityStatus invincibility;
    private Timeline mainTimeline;
    private boolean delayMovement;
    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        this.setInventory(new Inventory(this));
        this.setAlive(true);
        this.invincibility = new InvincibilityStatus(this);
        this.delayMovement = false;
        this.mainTimeline = new Timeline(
		    new KeyFrame(Duration.millis(100), e -> {
		    	delayMovement = false;
		    })
		);
        this.mainTimeline.setCycleCount(1);
    	
    }
    
    /**
     * Delays the player's movement
     */
    private void delayMovement() {
		this.delayMovement = true;
		this.mainTimeline.play();
    }
    
    /**
     * Player moves up
     */
    public void moveUp() {
    	// Checks if within dungeon height and width and if it can move to the square
        if (this.delayMovement == false && getY() > 0 && dungeon.moveEntityCheck(getX(), getY() - 1, Direction.UP) 
        		&& this.isAlive) {
        	this.delayMovement();
            y().set(getY() - 1);
            // If the goal has been met
        	if (dungeon.getGoal().hasMetGoal(this.dungeon, this, Direction.UP)) {
        		dungeon.setCompletedDungeon(true);
        	}
        }
    }
    
    /**
     * Player moves down
     */
    public void moveDown() {
        if (this.delayMovement == false && getY() < dungeon.getHeight() - 1 && dungeon.moveEntityCheck(getX(), getY() + 1, Direction.DOWN) 
        		&& this.isAlive) {
        	this.delayMovement();
        	y().set(getY() + 1);
        	if (dungeon.getGoal().hasMetGoal(this.dungeon, this, Direction.DOWN)) {
        		dungeon.setCompletedDungeon(true);
        	}
        }
    }
    
    
    /**
     * Player moves left
     */
    public void moveLeft() {
        if (this.delayMovement == false && getX() > 0 && 
        		dungeon.moveEntityCheck(getX() -1, getY(), Direction.LEFT) 
        		&& this.isAlive) {
        	this.delayMovement();
            x().set(getX() - 1);
        	if (dungeon.getGoal().hasMetGoal(this.dungeon, this, Direction.UP)) {
        		dungeon.setCompletedDungeon(true);
        	}
        }
    }

    /**
     * Player moves right
     */
    public void moveRight() {
        if (this.delayMovement == false && getX() < dungeon.getWidth() - 1
        		&& dungeon.moveEntityCheck(getX() + 1, getY(), Direction.RIGHT) && this.isAlive) {
        	
        	this.delayMovement();
        	x().set(getX() + 1);
        	if (dungeon.getGoal().hasMetGoal(this.dungeon, this, Direction.UP)) {
        		dungeon.setCompletedDungeon(true);
        	}
        }
    }
    
    /**
     * Check if any keys in inventory match the id given
     * @param id The id
     * @return Whether any keys match
     */
    public boolean checkKeys(int id) {
    	return this.inventory.checkKeys(id);
    }
    
    /**
     * Gets the inventory
     * @return the inventory
     */
	public Inventory getInventory() {
		return inventory;
	}

	/**
	 * Sets the inventory
	 * @param inventory The inventory
	 */
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
		new BombObserver(this.inventory);
		new SwordObserver(this.inventory);
		new InvincibilityObserver(this.inventory);
		new KeyObserver(this.inventory);
	}
	
	/**
	 * Add an item to the inventory
	 * @param item The item
	 */
	public void addItem(Item item) {
		// If invincibility potion
		if (item instanceof Invincibility) {
			this.invincibility.refreshInvincibility(); 
		}
		this.inventory.addItem(item);
	}
	
	/**
	 * Places a bomb
	 */
	public void placeBomb() {
		if (this.inventory.useBomb() == true) {
			LitBomb bomb = new LitBomb(this.getX(), this.getY());
			this.dungeon.addEntity(bomb);
			bomb.setDungeon(this.dungeon);
			bomb.detonateBomb();
		}
	}

	/**
	 * This function is implemented due to polymorphism and how Player inherits from Entity
	 */
    public boolean moveEntityCheck(int x, int y, Direction direction, Inventory inventory) {
    	return true;
    }

	/**
	 * Kill the player
	 */
	public void killPlayer() {
		super.setShow(false);
		this.setAlive(false);
		for (Enemy enemy: dungeon.getEnemies()) {
			enemy.getMainTimeline().stop();
		}
		this.dungeon.reloadDungeon();
	}

	/**
	 * Is the player alive?
	 * @return whether the player is alive
	 */
	public boolean isAlive() {
		return isAlive;
	}
	/**
	 * Set whether the player is alive
	 * @param isAlive Alive property
	 */
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	
	/**
	 * Use a sword
	 */
	public void useSword() {
		
		Sword sword = this.inventory.containsSword();
		if (sword != null) { // If there is a sword
			sword.increaseNumHits(); // increase number of hits
			dungeon.killEnemies(getX(), getY());
			this.inventory.notifySwordObserver();
		}
	}
	
	/**
	 * Get whether player is still invincible
	 * @return Whether the player is invincible
	 */
	public boolean getInvincibilityStatus() {
		return this.invincibility.getStatus();
	}
	
	/**
	 * Get number of seconds remaining for invincibility effect
	 * @return Number of seconds
	 */
	public int getInvincibilitySeconds() {
		return this.invincibility.getInvincibilitySeconds();
	}
	
	/**
	 * Get number of treasure in dungeon
	 * @return Number of treasure
	 */
	public int getTreasureCount() {
		int count = 0;
		for (Entity entity: dungeon.getEntities()) {
			if (entity instanceof Treasure) {
				count++;
			}
			
		}
		return count;
	}
}
