package unsw.dungeon;

import java.util.ArrayList;

public class Inventory {
	private ArrayList<Key> keys;
	public Inventory() {
		this.keys = new ArrayList<Key>();
	}
	public void addKey(Key key) {
		this.keys.add(key);
	}
	
	public ArrayList<Key> getKeys() {
		return this.keys;
	}
	
	public boolean checkKeys(int id) {
		for (Key key: this.keys) {
			if (key.getId() == id) {
				return true;
			}
		}
		return false;
	}
	
	public void addItem(Item item) {
		if (item instanceof Key) {
			this.addKey((Key) item);
		}
	}
}
