package unsw.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import unsw.dungeon.*;

class TreasureTest {

	@Test
	void pickUpTreasure() throws FileNotFoundException {
		JSONReader jsonReader;
		jsonReader = new JSONReader("treasure.json");
		Dungeon dungeon = jsonReader.load();
		Player player = dungeon.getPlayer();
		assert player.getX() == 1 && player.getY() == 1;
		assert player.getInventory().getNumTreasure() == 0;
		Treasure treasure = null;
		for (Entity entity: dungeon.getEntityAtCoord(1, 2)) {
			if (entity instanceof Treasure) {
				treasure = (Treasure) entity;
			}
		}
		assert treasure != null;
		player.moveDown();
		Treasure treasure2 = null;
		for (Entity entity: dungeon.getEntityAtCoord(1, 2)) {
			if (entity instanceof Treasure) {
				treasure2 = (Treasure) entity;
			}
		}
		assert treasure2 == null;
		assert player.getInventory().getNumTreasure() == 1;
	}

}
