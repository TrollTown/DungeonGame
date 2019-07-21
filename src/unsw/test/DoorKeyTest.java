package unsw.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;
import unsw.dungeon.Player;
import unsw.dungeon.Boulder;
import unsw.dungeon.Door;
import unsw.dungeon.ClosedDoor;
import unsw.dungeon.UnlockedDoor;

class DoorKeyTest {

	@Test
	void PlayerCanWalkThroughDoorTest() throws FileNotFoundException {
		JSONReader jsonReader;
		jsonReader = new JSONReader("advanced2.json");
		Dungeon dungeon = jsonReader.load();
		Player player = dungeon.getPlayer();
		assert player != null;
		Door door = null;
		for (Entity entity: dungeon.getEntityAtCoord(3, 1)) {
			if (entity instanceof Door) {
				door = (Door) entity;
			}
		}
		assert door != null;
		assert door.getType() instanceof ClosedDoor;
		player.moveRight();
		player.moveRight();
		assert player.getX() == 3 && player.getY() == 1;
		assert door.getType() instanceof UnlockedDoor;
		player.moveRight();
		assert player.getX() == 4 && player.getY() == 1;
		
	}
	
	@Test
	void PlayerCannotWalkThroughDoorWithoutKeyTest() throws FileNotFoundException {
		JSONReader jsonReader;
		jsonReader = new JSONReader("advanced2.json");
		Dungeon dungeon = jsonReader.load();
		Player player = dungeon.getPlayer();
		assert player != null;
		Door door = null;
		for (Entity entity: dungeon.getEntityAtCoord(1, 2)) {
			if (entity instanceof Door) {
				door = (Door) entity;
			}
		}
		assert door != null;
		assert door.getType() instanceof ClosedDoor;
		player.moveDown();
		assert player.getX() == 1 && player.getY() == 1;
		assert door.getType() instanceof ClosedDoor;
		
	}
	
}
