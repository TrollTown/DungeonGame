package unsw.dungeon;

import org.json.JSONArray;

public class OrGoal implements GoalInterface {
	private GoalInterface goal1;
	private GoalInterface goal2;
	public OrGoal(GoalInterface goal1, GoalInterface goal2) {
		this.goal1 = goal1;
		this.goal2 = goal2;
	}
	
	public OrGoal(JSONArray subgoals) {
		this(new EnemyGoal(), new ExitGoal());
	}

	@Override
	public boolean hasMetGoal(Dungeon dungeon, Player player) {
		if (goal1.hasMetGoal(dungeon, player) || goal2.hasMetGoal(dungeon, player)) {
			return true;
		}
		return false;
	}

}
