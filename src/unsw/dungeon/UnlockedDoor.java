package unsw.dungeon;

public class UnlockedDoor implements TypeDoor {

	@Override
	public boolean getImmovable() {
		return false;
	}

	@Override
	public boolean unlockDoor(Key key) {
		return true;
	}

	@Override
	public int getID() {
		return 0;
	}

	@Override
	public boolean doorLocked() {
		return false;
	}

}
