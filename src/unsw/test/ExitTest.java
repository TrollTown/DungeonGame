package unsw.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import unsw.dungeon.*;

import org.junit.jupiter.api.Test;


class ExitTest {

	@Test
	void ReachExit() throws FileNotFoundException {
		JSONReader jsonReader;
		jsonReader = new JSONReader("exitMaze.json");
		Dungeon dungeon = jsonReader.load();
		Player player = dungeon.getPlayer();
		assert player != null;
		assert dungeon.isCompletedDungeon() == false;
		Exit exit = null;
		for (Entity entity: dungeon.getEntityAtCoord(18, 16)) {
			if (entity instanceof Exit) {
				exit = (Exit) entity;
			}
		}
		assert exit != null;
		assert player.getX() == 18 && player.getY() == 15;
		player.moveDown();
		assert dungeon.isCompletedDungeon() == true;
		assert player.getX() == 18 && player.getY() == 16;
	}

}
