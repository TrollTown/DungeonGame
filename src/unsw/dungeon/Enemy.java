package unsw.dungeon;

public class Enemy extends Entity {
	private boolean isAlive;
	public Enemy(int x, int y) {
		super(x, y);
		isAlive = true;
	}
	public void kill() {
		this.isAlive = false;
	}
	public boolean isDead() {
		return !this.isAlive;
	}
    public boolean moveEntityCheck(int x, int y, Direction direction, Inventory inventory) {
    	return true;
    }
}
