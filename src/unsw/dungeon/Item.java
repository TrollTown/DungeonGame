package unsw.dungeon;

public class Item extends Entity {

	public Item(int x, int y) {
		super(x, y);
	}
	
	public void causeDamage() {
		super.causeDamage();
	}
    public boolean moveEntityCheck(int x, int y, Direction direction) {
    	return true;
    }
}
