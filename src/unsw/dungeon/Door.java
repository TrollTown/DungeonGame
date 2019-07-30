package unsw.dungeon;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Door extends PhysicalObject {
	private ObjectProperty<TypeDoor> type;
	public Door (int x, int y, int id) {
		super(x, y, true);
		this.type = new SimpleObjectProperty<TypeDoor>(new ClosedDoor(id));
		// By Default
	}
	public ObjectProperty<TypeDoor> getTypeProperty() {
		return this.type;
	}
	public TypeDoor getType() {
		return type.get();
	}
	public void setType(TypeDoor type) {
		this.type.set(type);;
	}
	
	public int getId() {
		return this.type.get().getID();
	}
	
	// Unlocks the door by setting a new type and allowing door to be moved through
	public void unlockDoor() {
		this.setType(new UnlockedDoor());
		this.setImmovableObject(false);
		
	}
	
	// Is door locked?
	public boolean Locked() {
		return this.type.get().doorLocked();
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
