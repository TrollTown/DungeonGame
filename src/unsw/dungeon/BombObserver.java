package unsw.dungeon;

public class BombObserver extends Observer {
	
	public BombObserver(Inventory inventory) {
		this.inventory = inventory;
		this.inventory.attach(this);
	}

	@Override
	public void update() {
		int bombCount = this.inventory.getBombCount();
		this.inventory.getPlayer().getDungeon().getMainController().changeBombCount(bombCount);
	}

}
