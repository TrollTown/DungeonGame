package unsw.dungeon;

public class TreasureGoal implements GoalInterface {

	// If number of treasure in inventory equals number of treasure in dungeon
	@Override
	public boolean hasMetGoal(Dungeon dungeon, Player player, Direction directionMovingTowards) {
		int countTreasure = 0;
		for (Entity entity: dungeon.getEntities()) {
			if (entity instanceof Treasure) {
				countTreasure++;
			}
		}
		if (player.getInventory().getNumTreasure() == countTreasure) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "TreasureGoal []";
	}

}
