package unsw.dungeon;

import java.util.List;
import java.util.TimerTask;

// Used for multithreading for enemy to move
class MakeMove extends TimerTask {
	List<Enemy> enemies;
	Player player;
	
	public MakeMove(List<Enemy> enemies, Player player) {
		this.enemies = enemies;
		this.player = player;
	}
	
	@Override
	public void run() {
		// Insert code to run repeatedly
		for (Enemy enemy : this.enemies) {
			if (enemy.isDead() == false) {
				enemy.moveTowardsPlayer(player);
			}
			else {
				this.cancel(); // Kill task
			}
		}
	}
}