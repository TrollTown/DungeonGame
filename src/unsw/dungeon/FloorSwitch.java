package unsw.dungeon;

public class FloorSwitch extends PhysicalObject {
	public FloorSwitch (int x, int y) {
		super(x, y, false);
	}
	public boolean moveEntityCheck(int x, int y, Direction direction, Inventory inventory) {
		return true;
	}
}
