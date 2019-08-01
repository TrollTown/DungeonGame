package unsw.dungeon;

/**
 * Represents a generic goal
 * @author Edward Webb and William Shen
 *
 */
public interface GoalInterface {
	
	/**
	 * Composite Pattern interface
	 * Gets whether the goal has been met
	 * @param dungeon The dungeon
	 * @param player The player
	 * @param directionMovingTowards The direction the player is moving towards
	 * @return Whether the goal has been met
	 */
	public boolean hasMetGoal(Dungeon dungeon, Player player, Direction directionMovingTowards);
}
