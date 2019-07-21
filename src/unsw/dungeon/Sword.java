package unsw.dungeon;

public class Sword extends Item {
	private int numHits;
	public Sword(int x, int y) {
		super(x, y);
		numHits = 0;
		
	}
	public int getNumHits() {
		return numHits;
	}
	public void increaseNumHits() {
		this.numHits += 1;
	}
	

}
