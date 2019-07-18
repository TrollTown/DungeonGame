package unsw.dungeon;

import org.json.JSONArray;

// Composite pattern
// This is a "group" of goals
public class AndGoal implements GoalInterface {
	private GoalInterface goal1;
	private GoalInterface goal2;
	
	public AndGoal(GoalInterface goal1, GoalInterface goal2) {
		this.goal1 = goal1;
		this.goal2 = goal2;
	}
	
	public AndGoal(JSONArray subgoals) {
		this(new ExitGoal(), new EnemyGoal());
	}

	@Override
	public boolean hasMetGoal(Dungeon dungeon, Player player) {
		if (goal1.hasMetGoal(dungeon, player) && goal2.hasMetGoal(dungeon, player)) {
			return true;
		}
		return false;
	}

}
