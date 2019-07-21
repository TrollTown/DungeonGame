package unsw.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;
import unsw.dungeon.Player;

class SetUpTest {
	
	@Test
	void test() throws Exception {
		JSONReader jsonReader;
		jsonReader = new JSONReader("maze.json");
		Dungeon dungeon = jsonReader.load();
		Player testEntity = dungeon.getPlayer();
		assert(testEntity != null);
		// Does nothing
		testEntity.moveUp();
		boolean found = false;
		for (Entity entity: dungeon.getEntityAtCoord(1, 1)) {
			if (entity == testEntity) {
				found = true;
			}
		}
		assert found == true;
		// Moves down 1 tile
		testEntity.moveDown();
		found = false;
		for (Entity entity: dungeon.getEntityAtCoord(1, 2)) {
			if (entity == testEntity) {
				found = true;
			}
		}
		assert found == true;
	}
}
