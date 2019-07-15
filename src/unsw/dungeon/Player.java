package unsw.dungeon;

enum Direction {
	LEFT,
	RIGHT,
	UP,
	DOWN,
}
/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity {

    private Dungeon dungeon;
    private Inventory inventory;
    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        this.setInventory(new Inventory());
    }
    
    

    public void moveUp() {
        if (getY() > 0 && dungeon.moveEntityCheck(getX(), getY() - 1, Direction.UP))
            y().set(getY() - 1);
    }

    public void moveDown() {
        if (getY() < dungeon.getHeight() - 1 && dungeon.moveEntityCheck(getX(), getY() + 1, Direction.DOWN))
            y().set(getY() + 1);
    }

    public void moveLeft() {
        if (getX() > 0 && dungeon.moveEntityCheck(getX() -1, getY(), Direction.LEFT))
            x().set(getX() - 1);
    }

    public void moveRight() {
        if (getX() < dungeon.getWidth() - 1 && dungeon.moveEntityCheck(getX() + 1, getY(), Direction.RIGHT))
            x().set(getX() + 1);
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
    
    
}
