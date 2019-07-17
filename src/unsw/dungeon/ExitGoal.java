package unsw.dungeon;

public class ExitGoal implements GoalInterface {

	@Override
	public boolean hasMetGoal(Dungeon dungeon, Player player) {
		return false;
	}

}
