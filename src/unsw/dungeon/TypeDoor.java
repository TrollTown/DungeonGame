package unsw.dungeon;

// State pattern for ClosedDoor/UnlockedDoor
public interface TypeDoor {
	boolean getImmovable();
	
	boolean unlockDoor(Key key);
	
	int getID();
	
	boolean doorLocked();
}
