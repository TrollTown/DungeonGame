package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

/**
 * Composite pattern which contains two goals,
 * these two goals must both be met for the whole goal to be met
 * @author Edward Webb
 *
 */
public class AndGoal implements GoalInterface {
	private List<GoalInterface> goals;
	
	/**
	 * Constructor for the And Goal class
	 * @param goal1 The first goal
	 * @param goal2 The second goal
	 */
	public AndGoal(GoalInterface goal1, GoalInterface goal2) {
		this.goals = new ArrayList<GoalInterface>();
		this.goals.add(goal1);
		this.goals.add(goal2);
	}
	
	/**
	 * Second constructor for the AndGoal class,
	 * given a json array, will read the subgoals from it
	 * @param subgoals the JSONArray
	 */
	public AndGoal(JSONArray subgoals) {
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
	 * Prints out each goal in the class
	 */
	@Override
	public String toString() {
		return "AndGoal [goals=" + goals + "]";
	}

	/**
	 * Returns whether the goal has been met by checking
	 * if the goals within the class have been met
	 */
	@Override
	public boolean hasMetGoal(Dungeon dungeon, Player player, Direction directionMovingTowards) {
		for (GoalInterface goal: this.goals) {
			if (goal.hasMetGoal(dungeon, player, directionMovingTowards) == false) {
				return false;
			}
		}
		return true;
	}

}
