package unsw.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import unsw.dungeon.*;

class KeyTest {

	@Test
	void pickUpKey() throws FileNotFoundException {
		JSONReader jsonReader;
		jsonReader = new JSONReader("advanced2.json");
		Dungeon dungeon = jsonReader.load();
		Player player = dungeon.getPlayer();
		assert player != null;
		Key key = null;
		for (Entity entity: dungeon.getEntityAtCoord(2, 1)) {
			if (entity instanceof Key) {
				key = (Key) entity;
			}
		}
		assert key != null;
		player.moveRight();
		Key key2 = null;
		for (Entity entity: dungeon.getEntityAtCoord(2, 1)) {
			if (entity instanceof Key) {
				key = (Key) entity;
			}
		}
		assert key2 == null;
		// Check that player has a key that is same id as 
		// key on the square that the player moved to
		assert player.getInventory().checkKeys(key.getId());
	}

}
