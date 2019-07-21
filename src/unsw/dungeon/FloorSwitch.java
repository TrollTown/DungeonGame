package unsw.dungeon;

public class FloorSwitch extends PhysicalObject {
	private boolean activated;
	public FloorSwitch (int x, int y) {
		super(x, y, false);
	}
	
	// If boulder is on top of floor switch, then activate switch
	public boolean moveEntityCheck(int x, int y, Direction direction, Inventory inventory) {
		
		if(super.checkBoulderOnTop()) {
			this.activated = true;
		} else {
			this.activated = false;
		}

		return true;
	}
	
	public boolean isActivated() {
		return activated;
	}
	public void setActivated(boolean activated) {
		this.activated = activated;
	}
}
