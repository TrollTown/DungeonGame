package unsw.dungeon;

public class Exit extends PhysicalObject {
	public Exit (int x, int y) {
		super(x, y, false);
	}
	public boolean moveEntityCheck(int x, int y, Direction direction, Inventory inventory) {
		
		return true;
	}
}
