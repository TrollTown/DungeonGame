package unsw.dungeon;

import java.util.ArrayList;

public class Inventory {
	private ArrayList<Key> keys;
	private ArrayList<UnlitBomb> bombs;
	private ArrayList<Treasure> treasure;
	
	public Inventory() {
		this.keys = new ArrayList<Key>();
		this.bombs = new ArrayList<UnlitBomb>();
		this.treasure = new ArrayList<Treasure>();
	}
	public void addKey(Key key) {
		this.keys.add(key);
	}
	
	public void addUnlitBomb(UnlitBomb bomb) {
		this.bombs.add(bomb);
	}
	
	public void addTreasure(Treasure treasure) {
		this.treasure.add(treasure);
	}
	
	public int getNumTreasure() {
		return this.treasure.size();
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
		else if (item instanceof UnlitBomb) {
			this.addUnlitBomb((UnlitBomb) item);
		}
	}
	
	public boolean useBomb() {
		if (this.bombs.size() > 0) {
			this.bombs.remove(0);
			return true;
		}
		return false;
	}
}
