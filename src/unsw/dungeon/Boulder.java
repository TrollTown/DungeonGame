package unsw.dungeon;

/**
 * Represents a boulder
 * @author William Shen and Edward Webb
 *
 */
public class Boulder extends PhysicalObject {
	
	/**
	 * constructor for the Boulder class
	 * @param x The x-coordinate of the boulder
	 * @param y The y-coordinate of the boulder
	 */
	public Boulder(int x, int y) {
		super(x, y, true);
	}
	
	/**
	 * Move boulder to these coordinates
	 * @param x The x-coordinate
	 * @param y The y-coordinate
	 * @return Whether the boulder was successful in moving to these coords
	 */
	private boolean moveToCoords(int x, int y) {
		if (!super.isImmovableAtCoord(x, y)) {
			checkFloorSwitch(x, y);
			super.setX(x);
			super.setY(y);
			
			return true;
		}
		return false;
	}
	
	/**
	 * Given coords, activates floor switch if it is at these coords
	 * @param x the x-coord
	 * @param y the y-coord
	 */
	private void checkFloorSwitch(int x, int y) {
		FloorSwitch floorswitch = super.getFloorSwitch(x, y);
		if (floorswitch != null) { // If there is a floor switch then activate it
			floorswitch.setActivated(true);
		}
	}
	
	/**
	 * Moves the boulder left
	 * @return Whether the boulder moved left or not
	 */
	public boolean moveLeft() {
		if (super.getX() > 0) {
			// Check if can move to these coords
			return moveToCoords(super.getX() - 1, super.getY());
		}
		return false;
	}
	
	/**
	 * Moves the boulder right
	 * @return Whether the boulder moved right or not
	 */
	public boolean moveRight() {
		if (super.getX() < super.getDungeonWidth()) {
			return moveToCoords(super.getX() + 1, super.getY());
		}
		return false;
	}
	
	/**
	 * Moves the boulder up
	 * @return Whether the boulder moved up or not
	 */
	public boolean moveUp() {
		if (super.getY() > 0) {
			return moveToCoords(super.getX(), super.getY() - 1);
		}
		return false;
	}
	
	/**
	 * Moves the boulder down
	 * @return Whether the boulder moved down or not
	 */
	public boolean moveDown() {
		if (super.getY() < super.getDungeonHeight()) {
			return moveToCoords(super.getX(), super.getY() + 1);
		}
		return false;
	}

	
	/**
	 * Player wants to move to square where boulder is
	 * Return whether the player can move to this square not
	 * Run some checks and move the boulder in response
	 */
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
