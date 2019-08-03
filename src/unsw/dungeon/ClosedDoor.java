package unsw.dungeon;

/**
 * Represents a locked door
 * @author William Shen and Edward Webb
 *
 */
public class ClosedDoor implements TypeDoor {
	private int id;
	
	/**
	 * Constructor for the closed door class
	 * @param id The id of the door
	 */
	public ClosedDoor(int id) {
		this.id = id;
	}
	
	/**
	 * Cannot move through a closed door
	 */
	@Override
	public boolean getImmovable() {
		return true;
	}

	/**
	 * If key's id matches door's id
	 */
	@Override
	public boolean unlockDoor(Key key) {
		if (key.getId() == this.id) {
			return true;
		}
		return false;
	}
	
	/**
	 * Gets the ID
	 */
	@Override
	public int getID() {
		return this.id;
	}
	
	/**
	 * Gets whether the door is locked or not
	 */
	@Override
	public boolean doorLocked() {
		return true;
	}
	
	
}
