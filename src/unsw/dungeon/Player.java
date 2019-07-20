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
    
    public void moveUp() {
        if (getY() > 0 && dungeon.moveEntityCheck(getX(), getY() - 1, Direction.UP)) {

            y().set(getY() - 1);
        	if (dungeon.getGoal().hasMetGoal(this.dungeon, this, Direction.UP)) {
        		dungeon.setCompletedDungeon(true);
        	}
        }
    }

    public void moveDown() {
        if (getY() < dungeon.getHeight() - 1 && dungeon.moveEntityCheck(getX(), getY() + 1, Direction.DOWN)) {

        	y().set(getY() + 1);
        	if (dungeon.getGoal().hasMetGoal(this.dungeon, this, Direction.DOWN)) {
        		dungeon.setCompletedDungeon(true);
        	}
        }
    }

    public void moveLeft() {
        if (getX() > 0 && dungeon.moveEntityCheck(getX() -1, getY(), Direction.LEFT)) {

            x().set(getX() - 1);
        	if (dungeon.getGoal().hasMetGoal(this.dungeon, this, Direction.UP)) {
        		dungeon.setCompletedDungeon(true);
        	}
        }
    }

    public void moveRight() {
        if (getX() < dungeon.getWidth() - 1 && dungeon.moveEntityCheck(getX() + 1, getY(), Direction.RIGHT)) {

        	x().set(getX() + 1);
        	if (dungeon.getGoal().hasMetGoal(this.dungeon, this, Direction.UP)) {
        		dungeon.setCompletedDungeon(true);
        	}
        }
    }
    
    public boolean checkKeys(int id) {
    	return this.inventory.checkKeys(id);
    }


	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	
	public void addItem(Item item) {
		if (item instanceof Invincibility) {
			this.invincibility.refreshInvincibility(); 
		}
		this.inventory.addItem(item);
	}
	
	public void placeBomb() {
		if (this.inventory.useBomb() == true) {
			LitBomb bomb = new LitBomb(this.getX(), this.getY());
			bomb.setDungeon(this.dungeon);
			bomb.detonateBomb();
		}
	}

    public boolean moveEntityCheck(int x, int y, Direction direction, Inventory inventory) {
    	return true;
    }

	
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
		if (this.inventory.containsSword()) {
			dungeon.killEnemies(getX(), getY());
		}
	}
	
	public boolean getInvincibilityStatus() {
		return this.invincibility.getStatus();
	}
}
