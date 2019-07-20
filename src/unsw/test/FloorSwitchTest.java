package unsw.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import unsw.dungeon.*;

class FloorSwitchTest {

	@Test
	void test() throws FileNotFoundException {
		JSONReader jsonReader;
		jsonReader = new JSONReader("floorswitch.json");
		Dungeon dungeon = jsonReader.load();
		Player player = dungeon.getPlayer();
		assert player != null;
		FloorSwitch floorswitch = null;
		for (Entity entity: dungeon.getEntityAtCoord(1, 3)) {
			if (entity instanceof FloorSwitch) {
				floorswitch = (FloorSwitch) entity;
			}
		}
		assert floorswitch != null;
		Boulder boulder = null;
		for (Entity entity: dungeon.getEntityAtCoord(1, 2)) {
			if (entity instanceof Boulder) {
				boulder = (Boulder) entity;
			}
		}
		assert boulder != null;
	
		// Move boulder down
		player.moveDown();
		assert player.getX() == 1 && player.getY() == 2;
		assert boulder.getX() == 1 && boulder.getY() == 3;
		assert floorswitch.isActivated() == true;
		assert dungeon.isCompletedDungeon() == true;
	}

}
