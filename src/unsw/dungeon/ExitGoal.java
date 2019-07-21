package unsw.dungeon;

public class ExitGoal implements GoalInterface {

	// If Player is at Exit
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

	@Override
	public String toString() {
		return "ExitGoal []";
	}

}
