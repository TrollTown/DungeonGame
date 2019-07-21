package unsw.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import unsw.dungeon.*;

class InvincibilityTest {

	@Test
	void test() throws FileNotFoundException {
		JSONReader jsonReader;
		jsonReader = new JSONReader("enemy.json");
		Dungeon dungeon = jsonReader.load();
		Player player = dungeon.getPlayer();
		assert player != null;
		Invincibility invincibility = null;
		for (Entity entity: dungeon.getEntityAtCoord(1, 3)) {
			if (entity instanceof Invincibility) {
				invincibility = (Invincibility) entity;
			}
		}
		assert invincibility != null;
		Enemy enemy = null;
		for (Entity entity: dungeon.getEntityAtCoord(1, 10)) {
			if (entity instanceof Enemy) {
				enemy = (Enemy) entity;
			}
		}
		assert enemy != null;
		
		player.moveDown();
		player.moveDown();
		assert player.getInvincibilityStatus() == true;
		
		assert player.getX() == 1 && player.getY() == 3;
		int count = 0;
		while (enemy.isDead() != true && count < 10) {
			player.moveDown();
			count++;
		}
		assert enemy.isDead() == true;
	}

}
