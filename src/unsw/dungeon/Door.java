package unsw.dungeon;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * Represents a door
 * @author William Shen and Edward Webb
 *
 */
public class Door extends PhysicalObject {
	private ObjectProperty<TypeDoor> type;
	
	/**
	 * Constructor for the door class
	 * @param x The door's x-coord
	 * @param y The door's y-coord
	 * @param id The door's ID
	 */
	public Door (int x, int y, int id) {
		super(x, y, true);
		this.type = new SimpleObjectProperty<TypeDoor>(new ClosedDoor(id));
		// By Default
	}
	
	/**
	 * Gets the type of the door i.e. whether it is locked or not
	 * @return The door's type property for binding
	 */
	public ObjectProperty<TypeDoor> getTypeProperty() {
		return this.type;
	}
	
	/**
	 * Gets the type of the door
	 * @return Whether the door is a locked or unlocked door
	 */
	public TypeDoor getType() {
		return type.get();
	}
	public void setType(TypeDoor type) {
		this.type.set(type);;
	}
	
	public int getId() {
		return this.type.get().getID();
	}
	
	/**
	 * Unlocks the door by setting a new type and allowing door to be moved through
	 */
	public void unlockDoor() {
		this.setType(new UnlockedDoor());
		this.setImmovableObject(false);
		
	}
	
	/**
	 * Is door locked?
	 * @return whether the door is locked or not
	 */
	public boolean Locked() {
		return this.type.get().doorLocked();
	}
	
	/**
	 * If door is locked, check if player's key matches door id
	 * if it does, then unlock door
	 */
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
