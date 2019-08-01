package unsw.dungeon;

/**
 * Used to display the bomb on the inventory UI
 * @author Edward Webb and William Shen
 *
 */
public class BombObserver extends Observer {
	private Inventory inventory;
	
	/**
	 * Constructor for the BombObserver class
	 * @param inventory The player's inventory
	 */
	public BombObserver(Inventory inventory) {
		this.inventory = inventory;
		this.inventory.attach(this);
	}

	/**
	 * Update the bomb's UI when the number of bombs the player has is updated
	 */
	@Override
	public void update() {
		int bombCount = this.inventory.getBombCount();
		this.inventory.getPlayer().getDungeon().getMainController().changeBombCount(bombCount);
	}

}
