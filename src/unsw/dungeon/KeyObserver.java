package unsw.dungeon;

/**
 * Observes a key for any changes and updates the UI
 * @author Edward Webb and William Shen
 *
 */
public class KeyObserver extends Observer {
	private Inventory inventory;
	
	/**
	 * Constructor for the key observer class
	 * @param inventory The player's inventory
	 */
	public KeyObserver(Inventory inventory) {
		this.inventory = inventory;
		this.inventory.attach(this);
	}
	
	/**
	 * Updates the UI when number of keys in inventory changes
	 */
	@Override
	public void update() {
		int keyCount = this.inventory.getKeyCount();
		this.inventory.getPlayer().getDungeon().getMainController().updateInventoryKeys(keyCount);
		System.out.println("OBSERVER CALLED");
	}

}
