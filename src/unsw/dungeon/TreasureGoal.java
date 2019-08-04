package unsw.dungeon;

/**
 * Reprents the goal to collect all treasure
 * @author Edward Webb and William Shen
 *
 */
public class TreasureGoal implements GoalInterface {

	/**
	 * If number of treasure in inventory equals number of treasure in dungeon
	 */
	@Override
	public boolean hasMetGoal(Dungeon dungeon, Player player, Direction directionMovingTowards) {
		if (player.getInventory().getNumTreasure() >= dungeon.getTreasureCount()) {
			
			return true;
		}
		return false;
	}

	/**
	 * Prints the class
	 */
	@Override
	public String toString() {
		return "Collect all the treasure";
	}
}
