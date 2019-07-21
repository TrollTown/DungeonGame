package unsw.test;

import static org.junit.jupiter.api.Assertions.*;


import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import unsw.dungeon.*;

class AndOrGoalTest {

	@Test
	void ExitAndTreasureOrEnemyGoalTest() throws FileNotFoundException {
		JSONReader jsonReader;
		jsonReader = new JSONReader("treasure.json");
		Dungeon dungeon = jsonReader.load();
		Player player = dungeon.getPlayer();
		assert dungeon.isCompletedDungeon() == false;
		assert player.getX() == 1 && player.getY() == 1;
		assert player.getInventory().getNumTreasure() == 0;
		Treasure treasure = null;
		for (Entity entity: dungeon.getEntityAtCoord(1, 2)) {
			if (entity instanceof Treasure) {
				treasure = (Treasure) entity;
			}
		}
		assert treasure != null;
		player.moveDown();
		Treasure treasure2 = null;
		for (Entity entity: dungeon.getEntityAtCoord(1, 2)) {
			if (entity instanceof Treasure) {
				treasure2 = (Treasure) entity;
			}
		}
		assert treasure2 == null;
		assert player.getInventory().getNumTreasure() == 1;
		
		player.moveDown();
		assert dungeon.isCompletedDungeon() == true;
	}
	
	@Test
	void OrGoalTreasureOrExitTest() throws FileNotFoundException {
		JSONReader jsonReader;
		jsonReader = new JSONReader("OrGoal.json");
		Dungeon dungeon = jsonReader.load();
		Player player = dungeon.getPlayer();
		assert dungeon.isCompletedDungeon() == false;
		
		Treasure treasure = null;
		for (Entity entity: dungeon.getEntityAtCoord(1, 2)) {
			if (entity instanceof Treasure) {
				treasure = (Treasure) entity;
			}
		}
		assert treasure != null;
		
		Exit exit = null;
		for (Entity entity: dungeon.getEntityAtCoord(2, 1)) {
			if (entity instanceof Exit) {
				exit = (Exit) entity;
			}
		}
		assert exit != null;
		
		player.moveDown();
		assert dungeon.isCompletedDungeon() == true;
		player.moveUp();
		dungeon.setCompletedDungeon(false);
		player.moveRight();
		assert dungeon.isCompletedDungeon() == true;
	}
	
	@Test
	void failAndGoalTest() throws FileNotFoundException {
		JSONReader jsonReader;
		jsonReader = new JSONReader("FailAndGoal.json");
		Dungeon dungeon = jsonReader.load();
		Player player = dungeon.getPlayer();
		assert dungeon.isCompletedDungeon() == false;
		
		Treasure treasure = null;
		for (Entity entity: dungeon.getEntityAtCoord(1, 2)) {
			if (entity instanceof Treasure) {
				treasure = (Treasure) entity;
			}
		}
		assert treasure != null;
		
		Exit exit = null;
		for (Entity entity: dungeon.getEntityAtCoord(2, 1)) {
			if (entity instanceof Exit) {
				exit = (Exit) entity;
			}
		}
		assert exit != null;
		
		player.moveDown();
		assert dungeon.isCompletedDungeon() == false;
		player.moveUp();
		player.moveRight();
		assert dungeon.isCompletedDungeon() == false;
	}

}
