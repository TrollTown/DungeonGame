package unsw.dungeon;

public class Wall extends PhysicalObject {

    public Wall(int x, int y) {
        super(x, y, true);
    }
	public boolean moveEntityCheck(int x, int y, Direction direction, Inventory inventory) {
		return true;
	}
}
