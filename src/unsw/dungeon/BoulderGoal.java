package unsw.dungeon;

/**
 * Represents the goal where all floor switches are activated
 * @author Edward Webb and William Shen
 *
 */
public class BoulderGoal implements GoalInterface {
	
	/**
	 * If all switches have been activated, then this function will return true
	 */
	@Override
	public boolean hasMetGoal(Dungeon dungeon, Player player, Direction directionMovingTowards) {
		for (Entity entity: dungeon.getEntities()) {
			if (entity instanceof FloorSwitch && ((FloorSwitch) entity).isActivated() == false) {
				return false;
			}
		}
		return true;
	}

}
