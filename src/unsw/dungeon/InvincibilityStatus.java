package unsw.dungeon;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

/**
 * Status of an invincibility potion
 * @author Edward Webb and William Shen
 *
 */
public class InvincibilityStatus {
	private boolean status;
	private Timeline mainTimeline;
	private int invincibilitySeconds;
	private Player player;
	
	/**
	 * Constructor for the invincibility status class
	 * @param player the player
	 */
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
	
	
	/**
	 * Start invincibility effect
	 */
	public void refreshInvincibility() {
		this.status = true;
		this.invincibilitySeconds = 7;
		this.player.getInventory().notifyInvincibilityObserver();
		this.runTimeline();
	}
	
	/**
	 * Gets the status of the invincibility potion
	 * @return The status
	 */
	public boolean getStatus() {
		return this.status;
	}
	
	/**
	 * Get number of seconds remaining
	 * @return The number of seconds
	 */
	public int getInvincibilitySeconds() {
		return this.invincibilitySeconds;
	}
	
	/**
	 * Decrease the seconds the invincibility potion is in effect
	 */
	private void decrementInvincibilitySeconds() {
		this.invincibilitySeconds--;
		this.player.getInventory().notifyInvincibilityObserver();
		if (this.invincibilitySeconds == 0) {
			this.status = false;
		}
	}
	
	/**
	 * Run a timer for the potion to run out using JavaFX timeline
	 */
    private void runTimeline() {
    	this.mainTimeline.stop();
    	this.mainTimeline.setCycleCount(7);
    	this.mainTimeline.play();
    }
}
