package unsw.dungeon;

public class Bomb extends Item {

	public Bomb(int x, int y) {
		super(x, y);
		
	}
	// Cause damage on squares around bomb
	public void causeDamage() {
		super.causeDamage();
	}
}
