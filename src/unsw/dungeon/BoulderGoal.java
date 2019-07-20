package unsw.dungeon;

public class BoulderGoal implements GoalInterface {
	
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
