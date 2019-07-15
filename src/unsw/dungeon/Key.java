package unsw.dungeon;

public class Key extends Item {
	private int id;
	public Key(int x, int y, int id) {
		super(x, y);
		this.setId(id);
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}
