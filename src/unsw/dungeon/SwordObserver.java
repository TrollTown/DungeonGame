package unsw.dungeon;

public class SwordObserver extends Observer {
	
	public SwordObserver(Inventory inventory) {
		this.inventory = inventory;
		this.inventory.attach(this);
	}

	@Override
	public void update() {
		int durability = this.inventory.getSwordDurability();
		this.inventory.getPlayer().getDungeon().getMainController().changeSwordDurability(durability);
	}

}
