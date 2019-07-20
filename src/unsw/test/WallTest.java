package unsw.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import unsw.dungeon.*;

class WallTest {

	@Test
	void MoveIntoWallTest() throws FileNotFoundException {
		JSONReader jsonReader;
		jsonReader = new JSONReader("maze.json");
		Dungeon dungeon = jsonReader.load();
		Player player = dungeon.getPlayer();
		assert player != null;
		assert player.getX() == 1 && player.getY() == 1;
		Wall wall = null;
		for (Entity entity: dungeon.getEntityAtCoord(1, 4)) {
			if (entity instanceof Wall) {
				wall = (Wall) entity;
			}
		}
		assert wall != null;
		player.moveDown();
		player.moveDown();
		player.moveDown();
		assert player.getX() == 1 && player.getY() == 3;
		assert wall.getX() == 1 && wall.getY() == 4;
	}
	void MoveIntoWallTwiceTest() throws FileNotFoundException {
		JSONReader jsonReader;
		jsonReader = new JSONReader("maze.json");
		Dungeon dungeon = jsonReader.load();
		Player player = dungeon.getPlayer();
		assert player != null;
		assert player.getX() == 1 && player.getY() == 1;
		Wall wall = null;
		for (Entity entity: dungeon.getEntityAtCoord(1, 4)) {
			if (entity instanceof Wall) {
				wall = (Wall) entity;
			}
		}
		assert wall != null;
		player.moveDown();
		player.moveDown();
		player.moveDown();
		player.moveDown();
		assert player.getX() == 1 && player.getY() == 3;
		assert wall.getX() == 1 && wall.getY() == 4;
	}
	void MoveIntoWallThenMoveAwayFromWallTest() throws FileNotFoundException {
		JSONReader jsonReader;
		jsonReader = new JSONReader("maze.json");
		Dungeon dungeon = jsonReader.load();
		Player player = dungeon.getPlayer();
		assert player != null;
		assert player.getX() == 1 && player.getY() == 1;
		Wall wall = null;
		for (Entity entity: dungeon.getEntityAtCoord(1, 4)) {
			if (entity instanceof Wall) {
				wall = (Wall) entity;
			}
		}
		assert wall != null;
		player.moveDown();
		player.moveDown();
		player.moveDown();
		player.moveUp();
		assert player.getX() == 1 && player.getY() == 2;
		assert wall.getX() == 1 && wall.getY() == 4;
	}

}
