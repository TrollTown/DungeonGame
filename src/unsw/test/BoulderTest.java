package unsw.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;
import unsw.dungeon.Player;
import unsw.dungeon.Boulder;

class BoulderTest {

	@Test
	void MoveBoulderTest() throws FileNotFoundException {
		JSONReader jsonReader;
		jsonReader = new JSONReader("boulders.json");
		Dungeon dungeon = jsonReader.load();
		Player player = dungeon.getPlayer();
		assert player != null;
		player.moveRight();
		assert player.getX() == 3 && player.getY() == 2;
		boolean foundBoulder = false;
		for (Entity entity: dungeon.getEntityAtCoord(4, 2)) {
			if (entity instanceof Boulder) {
				foundBoulder = true;
			}
		}
		assert foundBoulder;

	}
	
	@Test
	void unableToMoveBoulderTest() throws FileNotFoundException {
		JSONReader jsonReader;
		jsonReader = new JSONReader("boulders.json");
		Dungeon dungeon = jsonReader.load();
		Player player = dungeon.getPlayer();
		assert player != null;
		player.moveRight();
		player.moveRight();
		player.moveRight();
		assert player.getX() == 4 && player.getY() == 2;
		boolean foundBoulder = false;
		for (Entity entity: dungeon.getEntityAtCoord(5, 2)) {
			if (entity instanceof Boulder) {
				foundBoulder = true;
			}
		}
		assert foundBoulder;
	}
	
}
