package unsw.dungeon;

/**
 * Represents a sword
 * @author Edward Webb and William Shen
 *
 */
public class Sword extends Item {
	private int numHits;
	
	/**
	 * Constructor for sword class
	 * @param x The x-coord
	 * @param y The y-coord
	 */
	public Sword(int x, int y) {
		super(x, y);
		numHits = 0;
		
	}
	
	/**
	 * Get number of times sword has been used
	 * @return number of times sword has been used
	 */
	public int getNumHits() {
		return numHits;
	}
	
	/**
	 * Increase the number of hits on the sword
	 */
	public void increaseNumHits() {
		this.numHits += 1;
	}
	

}
