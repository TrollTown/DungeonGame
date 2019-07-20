package unsw.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Player;

class SetUpTest {

	@BeforeEach
	void setUp() throws Exception {	
	}
	
	@Test
	void test() throws Exception {
		JSONReader jsonReader;
		jsonReader = new JSONReader("maze.json");
		Dungeon dungeon = jsonReader.load();
		Player testEntity = dungeon.getPlayer();
		assert(testEntity != null);
		// Does nothing
		testEntity.moveUp();
		assert(dungeon.getEntityAtCoord(1,1) == testEntity);
		// Moves down 1 tile
		testEntity.moveDown();
		assert(dungeon.getEntityAtCoord(1,2) == testEntity);
	}
}
