package unsw.dungeon;

import java.util.ArrayList;

public class Inventory {
	private Player player;
	private ArrayList<Observer> observers = new ArrayList<Observer>();
	private ArrayList<Key> keys;
	private ArrayList<UnlitBomb> bombs;
	private ArrayList<Treasure> treasure;
	private Sword sword;
	private int bombCount;
	
	public Inventory(Player player) {
		this.keys = new ArrayList<Key>();
		this.bombs = new ArrayList<UnlitBomb>();
		this.treasure = new ArrayList<Treasure>();
		this.sword = null;
		this.bombCount = 0;
		this.player = player;
	}
	
	public int getBombCount() {
		return this.bombCount;
	}
	
	public int getSwordDurability() {
		if (this.sword == null) {
			return 0;
		}
		else {
			return 5 - this.sword.getNumHits();
		}
	}
	
	public int getInvincibilitySeconds() {
		return this.player.getInvincibilitySeconds();
	}
	
	public void addKey(Key key) {
		this.keys.add(key);
	}
	public ArrayList<UnlitBomb> getBombs() {
		return this.bombs;
	}
	
	public void addUnlitBomb(UnlitBomb bomb) {
		this.bombs.add(bomb);
		this.bombCount++;
		this.notifyBombObserver();
	}
	
	// Add treasure object
	public void addTreasure(Treasure treasure) {
		this.treasure.add(treasure);
	}
	
	public void addSword(Sword sword) {
		this.sword = sword;
		this.notifySwordObserver();
	}
	
	public int getNumTreasure() {
		return this.treasure.size();
	}
	public ArrayList<Key> getKeys() {
		return this.keys;
	}
	
	// Check if keys match given id
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
	
	// If there are bombs in inventory, remove bomb when using one
	public boolean useBomb() {
		if (this.bombs.size() > 0) {
			this.bombs.remove(0);
			this.bombCount--;
			this.notifyBombObserver();
			return true;
		}
		return false;
	}
	
	// If sword has been used less than 5 times and there is a sword, return true
	public Sword containsSword() {
		if (this.sword == null) {
			return null;
		}
		if (sword.getNumHits() < 5) {
			return sword;
		}
		
		return null;
	}
	
	public void attach(Observer observer) {
		this.observers.add(observer);
	}
	
	public void notifyBombObserver() {
		for (Observer observer : this.observers) {
			if (observer instanceof BombObserver) {
				observer.update();
			}
		}
	}
	
	public void notifySwordObserver() {
		for (Observer observer : this.observers) {
			if (observer instanceof SwordObserver) {
				observer.update();
			}
		}
	}
	
	public void notifyInvincibilityObserver() {
		for (Observer observer: this.observers) {
			if (observer instanceof InvincibilityObserver) {
				observer.update();
			}
		}
	}
		
	
	public Player getPlayer() {
		return this.player;
	}
}
