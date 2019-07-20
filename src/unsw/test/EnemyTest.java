package unsw.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import unsw.dungeon.*;

class EnemyTest {

	@Test
	void test() throws FileNotFoundException, InterruptedException {
		JSONReader jsonReader;
		jsonReader = new JSONReader("enemy.json");
		Dungeon dungeon = jsonReader.load();
		Player player = dungeon.getPlayer();
		assert player != null;
		System.out.println(dungeon.getEnemies());
		Enemy enemy = dungeon.getEnemies().get(0);
		System.out.println("Enemy: " + enemy.getX() + ", " + enemy.getY());
		assert enemy.getX() == 1 && enemy.getY() == 10;
		assert player.isAlive() == true;
		Thread.sleep(10000);
		assert player.isAlive() == false;
	}

}
