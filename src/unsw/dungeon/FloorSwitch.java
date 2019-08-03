package unsw.dungeon;

/**
 * Represents a floor switch
 * @author Edward Webb and William Shen
 *
 */
public class FloorSwitch extends PhysicalObject {
	private boolean activated;
	
	/**
	 * Represents a floor switch
	 * @param x The x-coord of the switch
	 * @param y The y-coord
	 */
	public FloorSwitch (int x, int y) {
		super(x, y, false);
	}
	
	/**
	 * If boulder is on top of floor switch, then activate switch
	 */
	public boolean moveEntityCheck(int x, int y, Direction direction, Inventory inventory) {
		
		if(super.checkBoulderOnTop()) {
			this.activated = true;
		} else {
			this.activated = false;
		}

		return true;
	}
	
	/**
	 * Get whether the switch is activated
	 * @return whether the switch is activated
	 */
	public boolean isActivated() {
		return activated;
	}
	/**
	 * Set the activated status
	 * @param activated Whether the switch is activated or not
	 */
	public void setActivated(boolean activated) {
		this.activated = activated;
	}
}
