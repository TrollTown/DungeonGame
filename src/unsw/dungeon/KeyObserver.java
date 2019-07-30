package unsw.dungeon;

public class KeyObserver extends Observer {
	
	public KeyObserver(Inventory inventory) {
		this.inventory = inventory;
		this.inventory.attach(this);
	}
	
	@Override
	public void update() {
		int keyCount = this.inventory.getKeyCount();
		this.inventory.getPlayer().getDungeon().getMainController().updateInventoryKeys(keyCount);
		System.out.println("OBSERVER CALLED");
	}

}
