package unsw.dungeon;

/**
 * State pattern for ClosedDoor/UnlockedDoor
 * @author William Shen and Edward Webb
 *
 */
public interface TypeDoor {
	
	/**
	 * Gets whether the door is immovable
	 * @return whether the door is immovable
	 */
	boolean getImmovable();
	/**
	 * Unlock a door
	 * @param key The key
	 * @return Whether the door has been unlocked
	 */
	boolean unlockDoor(Key key);
	
	/**
	 * Get the door's ID
	 * @return the door's ID
	 */
	int getID();
	
	/**
	 * Get whether door has been locked
	 * @return whether door is locked
	 */
	boolean doorLocked();
}
