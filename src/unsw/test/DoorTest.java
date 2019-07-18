package unsw.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Player;
import unsw.dungeon.Boulder;
import unsw.dungeon.Door;
import unsw.dungeon.ClosedDoor;
import unsw.dungeon.UnlockedDoor;

class DoorTest {

	@Test
	void PlayerCanWalkThroughDoorTest() throws FileNotFoundException {
		JSONReader jsonReader;
		jsonReader = new JSONReader("advanced2.json");
		Dungeon dungeon = jsonReader.load();
		Player player = (Player) dungeon.getEntityAtCoord(1,1);
		assert player != null;
		assert dungeon.getEntityAtCoord(3,1) instanceof Door;
		Door door = (Door) dungeon.getEntityAtCoord(3, 1);
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
		Player player = (Player) dungeon.getEntityAtCoord(1,1);
		assert player != null;
		assert dungeon.getEntityAtCoord(1,2) instanceof Door;
		Door door = (Door) dungeon.getEntityAtCoord(1, 2);
		assert door.getType() instanceof ClosedDoor;
		player.moveDown();
		assert player.getX() == 1 && player.getY() == 1;
		assert door.getType() instanceof ClosedDoor;
		
	}
	
}
