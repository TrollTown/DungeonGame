package unsw.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import unsw.dungeon.*;

class SwordTest {

	@Test
	void KillEnemyTest() throws FileNotFoundException, InterruptedException {
		JSONReader jsonReader;
		jsonReader = new JSONReader("enemy.json");
		Dungeon dungeon = jsonReader.load();
		Player player = dungeon.getPlayer();
		assert player != null;
		Sword sword = null;
		for (Entity entity: dungeon.getEntityAtCoord(1, 2)) {
			if (entity instanceof Sword) {
				sword = (Sword) entity;
			}
		}
		assert sword != null;
		Enemy enemy = null;
		for (Entity entity: dungeon.getEntityAtCoord(1, 10)) {
			if (entity instanceof Enemy) {
				enemy = (Enemy) entity;
			}
		}
		assert enemy != null;
		player.moveDown();
		Thread.sleep(7000); // Enemy takes a second to move a square
		player.useSword();
		assert enemy.isDead() == true;
		assert player.isAlive() == true;
	}
}
