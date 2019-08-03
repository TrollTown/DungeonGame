package unsw.dungeon;

/**
 * Represents an exit
 * @author Edward Webb and William Shen
 *
 */
public class Exit extends PhysicalObject {
	/**
	 * Constructor for the exit class
	 * @param x The x-coord
	 * @param y The y-coord
	 */
	public Exit (int x, int y) {
		super(x, y, false);
	}
	
	/**
	 * Can move through exit by default
	 */
	public boolean moveEntityCheck(int x, int y, Direction direction, Inventory inventory) {
		
		return true;
	}
}
