package unsw.dungeon;

public class Item extends Entity {

	public Item(int x, int y) {
		super(x, y);
	}
	
	
	public void causeDamage() {
		super.causeDamage();
	}
	
	// Item will not block player from moving onto square
    public boolean moveEntityCheck(int x, int y, Direction direction, Inventory inventory) {
    	return true;
    }
}
