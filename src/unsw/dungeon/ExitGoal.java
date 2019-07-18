package unsw.dungeon;

public class ExitGoal implements GoalInterface {

	@Override
	public boolean hasMetGoal(Dungeon dungeon, Player player) {
		if (dungeon.getEntityAtCoord(player.getX(), player.getY()) instanceof Exit) {
			return true;
		}
		return false;
		
	}

	@Override
	public String toString() {
		return "ExitGoal []";
	}

}
