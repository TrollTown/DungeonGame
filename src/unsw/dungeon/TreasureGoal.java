package unsw.dungeon;

public class TreasureGoal implements GoalInterface {

	// If number of treasure in inventory equals number of treasure in dungeon
	@Override
	public boolean hasMetGoal(Dungeon dungeon, Player player, Direction directionMovingTowards) {
		System.out.println("playTreasure: " + player.getInventory().getNumTreasure());
		System.out.println("dungTreasure: " + dungeon.getTreasureCount());
		if (player.getInventory().getNumTreasure() >= dungeon.getTreasureCount()) {
			
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Collect all the treasure";
	}
}
