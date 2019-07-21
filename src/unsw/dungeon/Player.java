package unsw.dungeon;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity {

    private Dungeon dungeon;
    private Inventory inventory;
    private boolean isAlive;
    private InvincibilityStatus invincibility;
    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        this.setInventory(new Inventory());
        this.setAlive(true);
        this.invincibility = new InvincibilityStatus();
    }
    
    // Player moves up
    public void moveUp() {
    	// Checks if within dungeon height and width and if it can move to the square
        if (getY() > 0 && dungeon.moveEntityCheck(getX(), getY() - 1, Direction.UP) && this.isAlive) {

            y().set(getY() - 1);
            // If the goal has been met
        	if (dungeon.getGoal().hasMetGoal(this.dungeon, this, Direction.UP)) {
        		dungeon.setCompletedDungeon(true);
        	}
        }
    }
    // Below functions are similar to above
    public void moveDown() {
        if (getY() < dungeon.getHeight() - 1 && dungeon.moveEntityCheck(getX(), getY() + 1, Direction.DOWN) && this.isAlive) {

        	y().set(getY() + 1);
        	if (dungeon.getGoal().hasMetGoal(this.dungeon, this, Direction.DOWN)) {
        		dungeon.setCompletedDungeon(true);
        	}
        }
    }

    public void moveLeft() {
        if (getX() > 0 && dungeon.moveEntityCheck(getX() -1, getY(), Direction.LEFT) && this.isAlive) {

            x().set(getX() - 1);
        	if (dungeon.getGoal().hasMetGoal(this.dungeon, this, Direction.UP)) {
        		dungeon.setCompletedDungeon(true);
        	}
        }
    }

    public void moveRight() {
        if (getX() < dungeon.getWidth() - 1 && dungeon.moveEntityCheck(getX() + 1, getY(), Direction.RIGHT) && this.isAlive) {

        	x().set(getX() + 1);
        	if (dungeon.getGoal().hasMetGoal(this.dungeon, this, Direction.UP)) {
        		dungeon.setCompletedDungeon(true);
        	}
        }
    }
    
    // Check if any keys in inventory match the id given
    public boolean checkKeys(int id) {
    	return this.inventory.checkKeys(id);
    }


	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	
	// Add an item to the inventory
	public void addItem(Item item) {
		// If invincibility potion
		if (item instanceof Invincibility) {
			this.invincibility.refreshInvincibility(); 
		}
		this.inventory.addItem(item);
	}
	// Places a bomb
	public void placeBomb() {
		if (this.inventory.useBomb() == true) {
			LitBomb bomb = new LitBomb(this.getX(), this.getY());
			this.dungeon.addEntity(bomb);
			bomb.setDungeon(this.dungeon);
			bomb.detonateBomb();
		}
	}

	// This function is implemented due to polymorphism and how Player inherits from Entity
    public boolean moveEntityCheck(int x, int y, Direction direction, Inventory inventory) {
    	return true;
    }

	// Kill the player
	public void killPlayer() {
		this.setAlive(false);
		this.dungeon.reloadDungeon();
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	
	public void useSword() {
		
		Sword sword = this.inventory.containsSword();
		if (sword != null) { // If there is a sword
			sword.increaseNumHits(); // increase number of hits
			dungeon.killEnemies(getX(), getY());
		}
	}
	
	// Get whether player is still invincible
	public boolean getInvincibilityStatus() {
		return this.invincibility.getStatus();
	}
}
