package unsw.dungeon;

public class ClosedDoor implements TypeDoor {
	private int id;
	public ClosedDoor(int id) {
		this.id = id;
	}
	@Override
	public boolean getImmovable() {
		return true;
	}

	@Override
	public boolean unlockDoor(Key key) {
		if (key.getId() == this.id) {
			return true;
		}
		return false;
	}
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return this.id;
	}
	@Override
	public boolean doorLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
}
