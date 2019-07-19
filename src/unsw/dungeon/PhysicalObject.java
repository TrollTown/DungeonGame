package unsw.dungeon;

public abstract class PhysicalObject extends Entity {
	private boolean isImmovableObject;
	public PhysicalObject(int x, int y, boolean isImmovableObject) {
		super(x, y);
		this.setImmovableObject(isImmovableObject);
	}
	public boolean isImmovableObject() {
		return isImmovableObject;
	}
	public void setImmovableObject(boolean isImmovableObject) {
		this.isImmovableObject = isImmovableObject;
	}
	
	public boolean isImmovableAtCoord(int x, int y) {
		return super.isImmovableAtCoord(x, y);
	}
	
	
	public void setX(int x) {
		super.setX(x);
	}
	
	public void setY(int y) {
		super.setY(y);
	}
	
	public int getX() {
		return super.getX();
	}
	public int getY() {
		return super.getY();
	}
	
	public boolean checkGoal() {
		return super.checkGoal();
	}
	
    public abstract boolean moveEntityCheck(int x, int y, Direction direction, Inventory inventory);
    

}
