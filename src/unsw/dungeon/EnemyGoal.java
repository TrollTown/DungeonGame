package unsw.dungeon;

public class EnemyGoal implements GoalInterface {

	@Override
	public boolean hasMetGoal(Dungeon dungeon, Player player) {
		for (Enemy enemy : dungeon.getEnemies()) {
			if (!(enemy.isDead())) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		return "EnemyGoal []";
	}

}
