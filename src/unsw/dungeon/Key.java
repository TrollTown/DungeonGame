package unsw.dungeon;

/**
 * Represents a key
 * @author William Shen and Edward Webb
 *
 */
public class Key extends Item {
	private int id;
	
	/**
	 * Constructor for the key class
	 * @param x The x-coord of the key
	 * @param y The y-coord
	 * @param id The key's id
	 */
	public Key(int x, int y, int id) {
		super(x, y);
		this.setId(id);
		
	}
	/**
	 * Get the key's id
	 * @return The id
	 */
	public int getId() {
		return id;
	}
	/**
	 * Sets the key's id
	 * @param id The id
	 */
	public void setId(int id) {
		this.id = id;
	}

}
