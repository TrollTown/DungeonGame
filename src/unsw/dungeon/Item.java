package unsw.dungeon;

/**
 * Represents an item
 * @author Edward Webb and William Shen
 *
 */
public class Item extends Entity {
	
	/**
	 * Constructor for the item class
	 * @param x The x-coord
	 * @param y The y-coord
	 */
	public Item(int x, int y) {
		super(x, y);
	}
	
	
	/**
	 * Causes damage
	 */
	public void causeDamage() {
		super.causeDamage();
	}
	
	/**
	 * Item will not block player from moving onto square
	 */
    public boolean moveEntityCheck(int x, int y, Direction direction, Inventory inventory) {
    	return true;
    }
    
    /**
     * Updates the item's view
     */
    public void updateView() {
    	super.updateView();
    }
}
