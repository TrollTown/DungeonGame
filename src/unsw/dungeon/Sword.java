package unsw.dungeon;

public class Sword extends Item {
	private int numHits;
	public Sword(int x, int y) {
		super(x, y);
		numHits = 0;
		
	}
	
	// Get number of times sword has been used
	public int getNumHits() {
		return numHits;
	}
	public void increaseNumHits() {
		this.numHits += 1;
	}
	

}
