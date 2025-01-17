package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

/**
 * Composite pattern
 * This is a "group" of goals
 * @author Edward Webb and William Shen
 *
 */
public class OrGoal implements GoalInterface {
	private List<GoalInterface> goals;
	
	/**
	 * Represents a group of goals
	 * AT LEAST one goal must be met for this goal to be met
	 * @param goal1 The first goal
	 * @param goal2 The second goal
	 */
	public OrGoal(GoalInterface goal1, GoalInterface goal2) {
		this.goals = new ArrayList<GoalInterface>();
		this.goals.add(goal1);
		this.goals.add(goal2);
	}
	
	/**
	 * Converts JSON array to an OrGoal
	 * @param subgoals
	 */
	public OrGoal(JSONArray subgoals) {
		this.goals = new ArrayList<GoalInterface>();
		for (int i = 0; i < subgoals.length(); i++) {
			String goal = subgoals.getJSONObject(i).getString("goal");
	    	switch (goal) {
	    	case "exit":
	    		this.goals.add(new ExitGoal());
	    		break;
	    	case "enemies":
	    		this.goals.add(new EnemyGoal());
	    		break;
	    	case "treasure":
	    		this.goals.add(new TreasureGoal());
	    		break;
	    	case "boulders":
	    		this.goals.add(new BoulderGoal());
	    		break;
	    	case "AND":
	    		this.goals.add(new AndGoal(subgoals.getJSONObject(i).getJSONArray("subgoals")));
	    		break;
	    	case "OR":
	    		this.goals.add(new OrGoal(subgoals.getJSONObject(i).getJSONArray("subgoals")));
	    		break;
	    	}
		}
		
		
	}

	/**
	 * If either goal has been met, return true
	 */
	@Override
	public boolean hasMetGoal(Dungeon dungeon, Player player, Direction directionMovingTowards) {
		for (GoalInterface goal: this.goals) {
			if (goal.hasMetGoal(dungeon, player, directionMovingTowards) == true) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Print out the goal
	 */
	@Override
	public String toString() {
		return "(" + goals.get(0) + " OR " + goals.get(1) + ")";
	}

}
