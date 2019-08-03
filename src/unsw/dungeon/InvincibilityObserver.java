package unsw.dungeon;

/**
 * Observers an invincibility potion for any changes
 * @author Edward Webb and William Shen
 *
 */
public class InvincibilityObserver extends Observer {
	private Inventory inventory;
	
	/**
	 * Constructor for the invicibility observer
	 * @param inventory The player's inventory
	 */
	public InvincibilityObserver(Inventory inventory) {
		this.inventory = inventory;
		this.inventory.attach(this);
		
	}
	
	/**
	 * Updates the inventory ui when the number of potions in the inventory changes
	 */
	@Override
	public void update() {
		int invincibilitySeconds = this.inventory.getInvincibilitySeconds();
		this.inventory.getPlayer().getDungeon().getMainController().updateInvincibilityTimer(invincibilitySeconds);
	}

}
