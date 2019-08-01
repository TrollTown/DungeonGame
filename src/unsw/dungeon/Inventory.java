package unsw.dungeon;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Represents the player's inventory
 * @author William Shen and Edward Webb
 *
 */
public class Inventory {
	private Player player;
	private ArrayList<Observer> observers = new ArrayList<Observer>();
	private ArrayList<Key> keys;
	private ArrayList<UnlitBomb> bombs;
	private ArrayList<Treasure> treasure;
	private Sword sword;
	private int bombCount;
	private int keyCount;
	
	/**
	 * The constructor for the inventory class
	 * @param player The player
	 */
	public Inventory(Player player) {
		this.keys = new ArrayList<Key>();
		this.bombs = new ArrayList<UnlitBomb>();
		this.treasure = new ArrayList<Treasure>();
		this.sword = null;
		this.bombCount = 0;
		this.player = player;
		this.keyCount = 0;
	}
	
	/**
	 * Gets the bomb count
	 * @return The bomb count
	 */
	public int getBombCount() {
		return this.bombCount;
	}
	
	/**
	 * Gets the sword's durability
	 * @return The sword's durability
	 */
	public int getSwordDurability() {
		if (this.sword == null) {
			return 0;
		}
		else {
			return 5 - this.sword.getNumHits();
		}
	}
	
	/**
	 * Gets the number of seconds the invincibility effect will continue
	 * @return The number of seconds
	 */
	public int getInvincibilitySeconds() {
		return this.player.getInvincibilitySeconds();
	}
	
	/**
	 * Returns the number of keys
	 * @return The number of keys
	 */
	public int getKeyCount() {
		return this.keyCount;
	}
	
	/**
	 * Adds the key to the inventory
	 * @param key The key
	 */
	public void addKey(Key key) {
		this.keys.add(key);
		this.keyCount++;
		this.notifyKeyObserver();
	}
	
	/**
	 * Gets the list of bombs
	 * @return The list of bombs
	 */
	public ArrayList<UnlitBomb> getBombs() {
		return this.bombs;
	}
	
	/**
	 * Adds an unlit bomb to the inventory
	 * @param bomb The bomb
	 */
	public void addUnlitBomb(UnlitBomb bomb) {
		this.bombs.add(bomb);
		this.bombCount++;
		this.notifyBombObserver();
	}
	
	/**
	 * Add treasure object
	 * @param treasure The treasure object
	 */
	public void addTreasure(Treasure treasure) {
		this.treasure.add(treasure);
	}
	
	/**
	 * Adds a sword to the inventory
	 * @param sword The sword
	 */
	public void addSword(Sword sword) {
		this.sword = sword;
		this.notifySwordObserver();
	}
	
	/**
	 * Gets the number of treasure in the inventory
	 * @return The number of treasure
	 */
	public int getNumTreasure() {
		return this.treasure.size();
	}
	
	/**
	 * Gets the list of keys
	 * @return The list of keys
	 */
	public ArrayList<Key> getKeys() {
		return this.keys;
	}
	
	/**
	 * Check if keys match given id
	 * @param id The id
	 * @return Whether any keys match the id
	 */
	public boolean checkKeys(int id) {
		Iterator<Key> iter = this.keys.iterator();
		while (iter.hasNext()) {
			Key key = iter.next();
			if (key.getId() == id) {
				iter.remove();
				this.keyCount--;
				this.notifyKeyObserver();
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Adds an item to the inventory
	 * @param item The item
	 */
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
	
	/**
	 * If there are bombs in inventory, remove bomb when using one
	 * @return Whether a bomb was used
	 */
	public boolean useBomb() {
		if (this.bombs.size() > 0) {
			this.bombs.remove(0);
			this.bombCount--;
			this.notifyBombObserver();
			return true;
		}
		return false;
	}
	
	/**
	 * If sword has been used less than 5 times and there is a sword, return true
	 * @return The sword
	 */
	public Sword containsSword() {
		if (this.sword == null) {
			return null;
		}
		if (sword.getNumHits() < 5) {
			return sword;
		}
		
		return null;
	}
	
	/**
	 * Attach an observer
	 * @param observer The observer
	 */
	public void attach(Observer observer) {
		this.observers.add(observer);
	}
	
	/**
	 * Update the bomb view
	 */
	public void notifyBombObserver() {
		for (Observer observer : this.observers) {
			if (observer instanceof BombObserver) {
				observer.update();
			}
		}
	}
	
	/**
	 * Update the sword view
	 */
	public void notifySwordObserver() {
		for (Observer observer : this.observers) {
			if (observer instanceof SwordObserver) {
				observer.update();
			}
		}
	}
	
	/**
	 * Update the invincibility observer
	 */
	public void notifyInvincibilityObserver() {
		for (Observer observer: this.observers) {
			if (observer instanceof InvincibilityObserver) {
				observer.update();
			}
		}
	}
	
	/**
	 * Update the key observer
	 */
	public void notifyKeyObserver() {
		for (Observer observer : this.observers) {
			if (observer instanceof KeyObserver) {
				observer.update();
			}
		}
	}
		
	/**
	 * Gets the player
	 * @return The player
	 */
	public Player getPlayer() {
		return this.player;
	}	
}
