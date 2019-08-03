package unsw.dungeon;

/**
 * Observes a sword
 * @author William Shen and Edward Webb
 *
 */
public class SwordObserver extends Observer {
	private Inventory inventory;
	
	/**
	 * Constructor for Sword observer class
	 * @param inventory The inventory
	 */
	public SwordObserver(Inventory inventory) {
		this.inventory = inventory;
		this.inventory.attach(this);
	}

	/**
	 * Updates a sword's view when the sword is used
	 */
	@Override
	public void update() {
		int durability = this.inventory.getSwordDurability();
		this.inventory.getPlayer().getDungeon().getMainController().changeSwordDurability(durability);
	}

}
