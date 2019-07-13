package unsw.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unsw.dungeon.DungeonController;
import unsw.dungeon.DungeonControllerLoader;

class Test1 {

	@BeforeEach
	void setUp() throws Exception {
		DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("maze.json");
		DungeonController controller = dungeonLoader.loadController();
	}
	
	@Test
	void test() {
		fail("Not yet implemented");
	}
}
