package unsw.dungeon;

/**
 * Represents actual physical objects like Wall
 * @author Edward Webb and William Shen
 *
 */
public abstract class PhysicalObject extends Entity {
	private boolean isImmovableObject;
	
	/**
	 * Constructor for Physical Object class
	 * @param x The x-coord
	 * @param y The y-coord
	 * @param isImmovableObject Is this object immovable?
	 */
	public PhysicalObject(int x, int y, boolean isImmovableObject) {
		super(x, y);
		this.setImmovableObject(isImmovableObject);
	}
	
	/**
	 * Gets isImmovableObject property
	 * @return the property
	 */
	public boolean isImmovableObject() {
		return isImmovableObject;
	}
	
	/**
	 * Sets isImmovableObject property
	 * @param isImmovableObject the property
	 */
	public void setImmovableObject(boolean isImmovableObject) {
		this.isImmovableObject = isImmovableObject;
	}
	
	/**
	 * Is object at coords immovable?
	 */
	public boolean isImmovableAtCoord(int x, int y) {
		return super.isImmovableAtCoord(x, y);
	}
	
	/**
	 * Method forwarding
	 */
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
	
	/**
	 * Must be implemented by all physical entities
	 */
    public abstract boolean moveEntityCheck(int x, int y, Direction direction, Inventory inventory);
    

}
