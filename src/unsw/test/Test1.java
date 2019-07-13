package unsw.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;
import unsw.dungeon.Player;

class Test1 {

	@BeforeEach
	void setUp() throws Exception {	
	}
	
	@Test
	void test() throws Exception {
		JSONReader jsonReader;
		jsonReader = new JSONReader("maze.json");
		Dungeon dungeon = jsonReader.load();
		Player testEntity = (Player) dungeon.getEntityAtCoord(1,1);
		assert(testEntity != null);
		// Does nothing
		testEntity.moveUp();
		assert(dungeon.getEntityAtCoord(1,1) == testEntity);
		// Moves down 1 tile
		testEntity.moveDown();
		assert(dungeon.getEntityAtCoord(1,2) == testEntity);
	}
}
