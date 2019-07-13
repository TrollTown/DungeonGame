package unsw.dungeon;

public class Boulder extends Entity {

	public Boulder(int x, int y) {
		super(x, y, false);
	}
	
	private boolean moveToCoords(int x, int y) {
		if (!super.isImmovableAtCoord(x, y)) {
			super.setX(x);
			super.setY(y);
			
			System.out.println("Move to coords boulder: " + x + ", " + y);
			return true;
		}
		return false;
	}
	
	public boolean moveLeft() {
		if (super.getX() > 0) {
			return moveToCoords(super.getX() - 1, super.getY());
		}
		return false;
	}
	
	public boolean moveRight() {
		if (super.getX() < super.getDungeonWidth()) {
			return moveToCoords(super.getX() + 1, super.getY());
		}
		return false;
	}
	
	public boolean moveUp() {
		if (super.getY() > 0) {
			return moveToCoords(super.getX(), super.getY() - 1);
		}
		return false;
	}
	
	public boolean moveDown() {
		if (super.getY() < super.getDungeonHeight()) {
			return moveToCoords(super.getX(), super.getY() + 1);
		}
		return false;
	}

}
