package unsw.dungeon;

public class Boulder extends PhysicalObject {

	public Boulder(int x, int y) {
		super(x, y, true);
	}
	
	private boolean moveToCoords(int x, int y) {
		if (!super.isImmovableAtCoord(x, y)) {
			checkFloorSwitch(x, y);
			super.setX(x);
			super.setY(y);
			
			return true;
		}
		return false;
	}
	
	private void checkFloorSwitch(int x, int y) {
		FloorSwitch floorswitch = super.getFloorSwitch(x, y);
		if (floorswitch != null) {
			floorswitch.setActivated(true);
		}
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

	
	 public boolean moveEntityCheck(int x, int y, Direction direction, Inventory inventory) {
 		
 		
 		if (direction == Direction.UP) {
 			// Checks if boulder can move as well, if can move then it will move
 			return this.moveUp();
 		}
 		else if (direction == Direction.RIGHT) {
 			return this.moveRight();
 		}
 		else if (direction == Direction.DOWN) {
 			return this.moveDown();
 		}
 		else if (direction == Direction.LEFT) {
 			return this.moveLeft();
 		}
 		// Boulder cannot move, so player will not move
 		return false;
	 }

}
