package unsw.dungeon;

import java.awt.Toolkit;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class EnemyAITimer {
	Toolkit toolkit;
	Timer timer;
	
	public EnemyAITimer(int seconds, List<Enemy> enemies, Player player) {
		this.timer = new Timer();
		this.timer.schedule(new MakeMove(enemies, player), 0, seconds * 1000);
	}
}
