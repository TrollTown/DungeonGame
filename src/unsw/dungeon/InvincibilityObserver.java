package unsw.dungeon;

public class InvincibilityObserver extends Observer {
	public Inventory inventory;
	
	public InvincibilityObserver(Inventory inventory) {
		this.inventory = inventory;
		this.inventory.attach(this);
		
	}
	@Override
	public void update() {
		int invincibilitySeconds = this.inventory.getInvincibilitySeconds();
		this.inventory.getPlayer().getDungeon().getMainController().updateInvincibilityTimer(invincibilitySeconds);
	}

}
