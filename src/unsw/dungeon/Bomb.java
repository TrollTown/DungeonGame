package unsw.dungeon;

/**
 * Bomb class that represents a bomb in the game
 * @author William Shen and Edward Webb
 *
 */
public class Bomb extends Item {

	/**
	 * Constructor for the bomb class
	 * @param x x-coordinate
	 * @param y y-coordinate
	 */
	public Bomb(int x, int y) {
		super(x, y);
		
	}
	
	/**
	 * Cause damage on entities on squares around bomb
	 */
	public void causeDamage() {
		super.causeDamage();
	}
	
	/**
	 * Updates the bomb's image
	 */
	public void updateView() {
		super.updateView();
	}
}
