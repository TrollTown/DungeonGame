package unsw.dungeon;

/**
 * Represents a wall
 * @author Edward Webb and William Shen
 *
 */
public class Wall extends PhysicalObject {
	
	/**
	 * Constructor for wall class
	 * @param x x-coord
	 * @param y y-coord
	 */
    public Wall(int x, int y) {
        super(x, y, true);
    }
    
    /**
     * Run every time player attempts to move to a wall
     * Return true, as is immovable so player cannot move through it anyway
     */
	public boolean moveEntityCheck(int x, int y, Direction direction, Inventory inventory) {
		return true;
	}
}
