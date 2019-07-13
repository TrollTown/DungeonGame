package unsw.dungeon;

public class Door extends Entity {
	private TypeDoor type;
	public Door (int x, int y) {
		super(x, y, true);
		this.setType(new ClosedDoor());
		// By Default
	}
	public TypeDoor getType() {
		return type;
	}
	public void setType(TypeDoor type) {
		this.type = type;
	}
}
