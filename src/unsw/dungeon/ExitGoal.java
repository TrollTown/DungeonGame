package unsw.dungeon;

public class ExitGoal implements GoalInterface {

	
	@Override
	public boolean hasMetGoal(Dungeon dungeon, Player player, Direction directionMovingTowards) {
		if (directionMovingTowards == null) {
			return false;
		}
		int futureX = player.getX();
		int futureY = player.getY();
		if (directionMovingTowards.equals(Direction.UP)) {
			futureY += -1;
		} else if (directionMovingTowards.equals(Direction.RIGHT)) {
			futureX += 1;
		} else if (directionMovingTowards.equals(Direction.DOWN)) {
			futureY += 1;
		} else {
			futureX += 1;
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
