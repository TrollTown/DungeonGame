package unsw.test;

import static org.junit.jupiter.api.Assertions.*;


import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import unsw.dungeon.*;

class BombTest {

	@Test
	void ExplodeBouldersTest() throws FileNotFoundException, InterruptedException {
		JSONReader jsonReader;
		jsonReader = new JSONReader("bomb.json");
		Dungeon dungeon = jsonReader.load();
		Player player = dungeon.getPlayer();
		assert player.getX() == 1 && player.getY() == 1;
		Bomb bomb = null;
		for (Entity entity: dungeon.getEntityAtCoord(1, 2)) {
			if (entity instanceof Bomb) {
				bomb = (Bomb) entity;
			}
		}
		assert bomb != null;
		assert bomb instanceof UnlitBomb;
		Boulder boulder = null;
		for (Entity entity: dungeon.getEntityAtCoord(1, 3)) {
			if (entity instanceof Boulder) {
				boulder = (Boulder) entity;
			}
		}
		assert boulder != null;
		player.moveDown();
		player.placeBomb();
		Thread.sleep(6000);
		boulder = null;
		for (Entity entity: dungeon.getEntityAtCoord(1, 3)) {
			if (entity instanceof Boulder) {
				boulder = (Boulder) entity;
			}
		}
		assert boulder == null;
		assert player.isAlive() == false;
	}
	
	@Test
	void ExplodeEnemyTest() throws FileNotFoundException, InterruptedException {
		JSONReader jsonReader;
		jsonReader = new JSONReader("ExplodeEnemy.json");
		Dungeon dungeon = jsonReader.load();
		Player player = dungeon.getPlayer();
		assert player != null;
		Enemy enemy = dungeon.getEnemies().get(0);
		assert enemy.getX() == 1 && enemy.getY() == 10;
		Bomb bomb = null;
		
		for (Entity entity: dungeon.getEntityAtCoord(1, 3)) {
			if (entity instanceof Bomb) {
				bomb = (Bomb) entity;
			}
		}
		assert bomb != null;
		Thread.sleep(10000);
		assert player.isAlive() == false;
	}

}
