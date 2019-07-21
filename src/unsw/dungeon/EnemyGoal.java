package unsw.dungeon;

public class EnemyGoal implements GoalInterface {

	// If all enemies are dead, then goal has been met
	@Override
	public boolean hasMetGoal(Dungeon dungeon, Player player, Direction directionMovingTowards) {
		for (Enemy enemy : dungeon.getEnemies()) {
			if (!(enemy.isDead())) {
				return false;
			}
		}
		return true;
	}
}
