package unsw.dungeon;

public interface TypeDoor {
	boolean getImmovable();
	
	boolean unlockDoor(Key key);
	
	int getID();
	
	boolean doorLocked();
}
