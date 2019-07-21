package unsw.dungeon;

import java.util.ArrayList;

public class Inventory {
	private ArrayList<Key> keys;
	private ArrayList<UnlitBomb> bombs;
	private ArrayList<Treasure> treasure;
	private Sword sword;
	
	public Inventory() {
		this.keys = new ArrayList<Key>();
		this.bombs = new ArrayList<UnlitBomb>();
		this.treasure = new ArrayList<Treasure>();
		this.sword = null;
	}
	public void addKey(Key key) {
		this.keys.add(key);
	}
	public ArrayList<UnlitBomb> getBombs() {
		return this.bombs;
	}
	
	public void addUnlitBomb(UnlitBomb bomb) {
		this.bombs.add(bomb);
	}
	
	public void addTreasure(Treasure treasure) {
		this.treasure.add(treasure);
	}
	
	public void addSword(Sword sword) {
		this.sword = sword;
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
		else if (item instanceof Treasure) {
			this.addTreasure((Treasure) item);
		}
		else if (item instanceof Sword) {
			this.addSword((Sword) item);
		}
	}
	
	public boolean useBomb() {
		if (this.bombs.size() > 0) {
			this.bombs.remove(0);
			return true;
		}
		return false;
	}
	
	public Sword containsSword() {
		if (this.sword == null) {
			return null;
		}
		if (sword.getNumHits() < 5) {
			return sword;
		}
		
		return null;
	}
}
