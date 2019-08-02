package unsw.dungeon;

public class BoulderGoal implements GoalInterface {
	
	// If all switches are activated, then goal has been met
	@Override
	public boolean hasMetGoal(Dungeon dungeon, Player player, Direction directionMovingTowards) {
		for (Entity entity: dungeon.getEntities()) {
			if (entity instanceof FloorSwitch && ((FloorSwitch) entity).isActivated() == false) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public String toString() {
		return "Activate all floor switches with boulders";
	}
}
