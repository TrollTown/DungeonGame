package unsw.dungeon;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class InvincibilityStatus {
	private boolean status;
	private Timeline mainTimeline;
	private int invincibilitySeconds;
	private Player player;
	
	public InvincibilityStatus(Player player) {
		this.status = false;
		this.invincibilitySeconds = 0;
		this.player = player;
		this.mainTimeline = new Timeline(
			    new KeyFrame(Duration.millis(1000), e -> {
			        this.decrementInvincibilitySeconds();
			    })
			);
	}
	
	
	// Start invincibility effect
	public void refreshInvincibility() {
		this.status = true;
		this.invincibilitySeconds = 7;
		this.player.getInventory().notifyInvincibilityObserver();
		this.runTimeline();
	}
	
	public boolean getStatus() {
		return this.status;
	}
	
	public int getInvincibilitySeconds() {
		return this.invincibilitySeconds;
	}
	
	private void decrementInvincibilitySeconds() {
		this.invincibilitySeconds--;
		this.player.getInventory().notifyInvincibilityObserver();
		if (this.invincibilitySeconds == 0) {
			this.status = false;
		}
	}
	
    private void runTimeline() {
    	this.mainTimeline.stop();
    	this.mainTimeline.setCycleCount(7);
    	this.mainTimeline.play();
    }
}
