package unsw.dungeon;

public interface GoalInterface {
	
	// Composite Pattern interface
	public boolean hasMetGoal(Dungeon dungeon, Player player, Direction directionMovingTowards);
}
