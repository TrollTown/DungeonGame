package unsw.dungeon;

/**
 * Represents an unlocked door
 * @author Edward Webb and William Shen
 *
 */
public class UnlockedDoor implements TypeDoor {

	/**
	 * Get whether door is immovable
	 */
	@Override
	public boolean getImmovable() {
		return false;
	}

	/**
	 * Unlock the door
	 * Door is already unlocked, so return true
	 */
	@Override
	public boolean unlockDoor(Key key) {
		return true;
	}

	/**
	 * Get the door's ID
	 * Does not matter, so return 0
	 */
	@Override
	public int getID() {
		return 0;
	}

	/**
	 * Get whether door is locked
	 */
	@Override
	public boolean doorLocked() {
		return false;
	}

}
