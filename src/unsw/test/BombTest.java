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
		assert enemy.getX() == 1 && enemy.getY() == 7;
		Bomb bomb = null;
		
		for (Entity entity: dungeon.getEntityAtCoord(1, 3)) {
			if (entity instanceof Bomb) {
				bomb = (Bomb) entity;
			}
		}
		assert bomb != null;
		player.moveDown();
		player.moveDown();
		assert player.getInventory().getBombs().isEmpty() == false;
		// Player now has bomb
		player.placeBomb();
		LitBomb placedBomb = null;
		for (Entity entity: dungeon.getEntityAtCoord(1, 3)) {
			System.out.println(entity);
			if (entity instanceof LitBomb) {
				placedBomb = (LitBomb) entity;
			}
		}
		assert placedBomb != null;
		assert player.getInventory().getBombs().isEmpty() == true;
		player.moveUp();
		player.moveUp();
		Thread.sleep(5000);
		
		assert player.isAlive() == true;
		assert enemy.isDead() == true;
	}

}
