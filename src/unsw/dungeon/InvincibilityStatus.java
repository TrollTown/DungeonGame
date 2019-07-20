package unsw.dungeon;

import java.util.Timer;
import java.util.TimerTask;

public class InvincibilityStatus {
	private boolean status;
	private Timer timer;
	
	public InvincibilityStatus() {
		this.status = false;
		this.timer = new Timer();
	}
	
	public void refreshInvincibility() {
		this.status = true;
		this.timer.cancel();
		this.timer = new Timer();
		System.out.println("Player is invincible");
		this.timer.schedule(newTimerTask(), 7000L);
	}
	
	public boolean getStatus() {
		return this.status;
	}
	
	private TimerTask newTimerTask() {
		TimerTask task = new TimerTask() {
			public void run() {
				InvincibilityStatus.this.status = false;
				System.out.println("Player is no longer invincible");
			}
		};
		return task;
	}
	
}
