package unsw.dungeon;

public class ClosedDoor implements TypeDoor {
	private int id;
	public ClosedDoor(int id) {
		this.id = id;
	}
	
	// Cannot move through a closed door
	@Override
	public boolean getImmovable() {
		return true;
	}

	// If key given matches id, then unlock door
	@Override
	public boolean unlockDoor(Key key) {
		if (key.getId() == this.id) {
			return true;
		}
		return false;
	}
	
	// Gets ID
	@Override
	public int getID() {
		return this.id;
	}
	@Override
	public boolean doorLocked() {
		return true;
	}
	
	
}
