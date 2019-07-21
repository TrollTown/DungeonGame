package unsw.dungeon;

public class Door extends PhysicalObject {
	private TypeDoor type;
	public Door (int x, int y, int id) {
		super(x, y, true);
		this.setType(new ClosedDoor(id));
		// By Default
	}
	public TypeDoor getType() {
		return type;
	}
	public void setType(TypeDoor type) {
		this.type = type;
	}
	
	public int getId() {
		return this.type.getID();
	}
	
	// Unlocks the door by setting a new type and allowing door to be moved through
	public void unlockDoor() {
		this.setType(new UnlockedDoor());
		this.setImmovableObject(false);
		
	}
	
	// Is door locked?
	public boolean Locked() {
		return this.type.doorLocked();
	}
	
	// If door is locked, check if player's key matches door id
	// if it does, then unlock door
	public boolean moveEntityCheck(int x, int y, Direction direction, Inventory inventory) {
		if (this.Locked() == true) {
    		if (inventory.checkKeys(this.getId())) {
    			
    			this.unlockDoor();
    			return true;
    		}
    		return false;
		}
		return true;
	}
}
