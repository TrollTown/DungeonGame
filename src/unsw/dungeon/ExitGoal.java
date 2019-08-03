package unsw.dungeon;

/**
 * Represents the goal of reaching the exit
 * @author William Shen and Edward Webb
 *
 */
public class ExitGoal implements GoalInterface {

	/**
	 * If Player is at Exit
	 * Goal has been met
	 */
	@Override
	public boolean hasMetGoal(Dungeon dungeon, Player player, Direction directionMovingTowards) {
		if (directionMovingTowards == null) {
			return false;
		}
		for (Entity entity: dungeon.getEntityAtCoord(player.getX(), player.getY())) {
			if (entity instanceof Exit) {
				return true;
			}
		}
		return false;
		
	}

	/**
	 * For printing out this goal
	 */
	@Override
	public String toString() {
		return "Reach the exit";
	}

}
