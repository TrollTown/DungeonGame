package unsw.dungeon;

public class TreasureGoal implements GoalInterface {

	@Override
	public boolean hasMetGoal(Dungeon dungeon, Player player) {
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
