package unsw.dungeon;

/**
 * Represents the goal to kill all enemies
 * @author William Shen and Edward Webb
 *
 */
public class EnemyGoal implements GoalInterface {

	/**
	 * If all enemies are dead, then goal has been met
	 */
	@Override
	public boolean hasMetGoal(Dungeon dungeon, Player player, Direction directionMovingTowards) {
		for (Enemy enemy : dungeon.getEnemies()) {
			if (!(enemy.isDead())) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		return "Kill all enemies";
	}
	
	
}
