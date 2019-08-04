package unsw.dungeon;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

/**
 * Represents an enemy
 * @author Edward Webb and William Shen
 *
 */
public class Enemy extends Entity {
	private boolean isAlive;
	private Timeline mainTimeline;
	
	/**
	 * Constructor for the enemy class
	 * @param x The enemy's x-coord
	 * @param y The enemy's y-coord
	 */
	public Enemy(int x, int y) {
		super(x, y);
		isAlive = true;
		
	}
	
	/**
	 * Kill the enemy
	 */
	public void kill() {
		super.setShow(false);
		this.mainTimeline.stop();
		this.isAlive = false;
	}
	
	/**
	 * Get whether the enemy is dead or not
	 * @return Whether the enemy is dead or not
	 */
	public boolean isDead() {
		return !this.isAlive;
	}
	
	/**
	 * Move enemy towards player
	 * @param player The player
	 */
	public void moveTowardsPlayer(Player player) {
		
		// Get distances in all four directions
		int distanceAfterMoveUp = this.computePathLength(player, this.getX(), this.getY()-1);
		int distanceAfterMoveDown = this.computePathLength(player, this.getX(), this.getY()+1);
		int distanceAfterMoveLeft = this.computePathLength(player, this.getX()-1, this.getY());
		int distanceAfterMoveRight = this.computePathLength(player, this.getX()+1, this.getY());
		int distances[] = new int[] {distanceAfterMoveUp, distanceAfterMoveDown, distanceAfterMoveLeft, distanceAfterMoveRight};
		int move = -1;
		// Check if player is invincible
		if (player.getInvincibilityStatus()) {
			for (int i = 0; i < distances.length; i++){
				if (distances[i] > this.getDistanceToPlayer(player) && distances[i] != -1) {
					move = i;
					break;
				}
			}
		}
		else {
			// Very basic move towards player AI with no pathfinding (will get stuck behind obstacles)
			for (int i = 0; i < distances.length; i++) {
				if (distances[i] < this.getDistanceToPlayer(player) && distances[i] != -1) {
					move = i;
					break;
				}
			}
		}
		
		// Decide where to move
		switch(move) {
			case 0:
				this.setY(this.getY()-1);
				break;
			case 1:
				this.setY(this.getY()+1);
				break;
			case 2:
				this.setX(this.getX()-1);
				break;
			case 3:
				this.setX(this.getX()+1);
				break;
		}
		// Check player not reached by enemy
		getDistanceToPlayer(player);
	}
	
	/**
	 * Simple path length calculator, using Manhattan Distance
	 * @param player The player
	 * @param x The x-coord where the enemy might move to
	 * @param y The y-coord where the enemy might move to
	 * @return The length of the path
	 */
	private int computePathLength(Player player, int x, int y) {
		if (isImmovableAtCoord(x, y)) {
			return -1;
		}
		if (x != this.getX() || y != this.getY()) {
			if (isEnemy(x, y)) {
				return -1;
			}
		}
		int playerX = player.getX();
		int playerY = player.getY();
		int manhattanDistance = Math.abs(playerX - x) + Math.abs(playerY - y);
		return manhattanDistance;
	}
	
	/**
	 * A player can move through enemy
	 */
    public boolean moveEntityCheck(int x, int y, Direction direction, Inventory inventory) {
    	return true;
    }
    
    public Timeline getMainTimeline() {
		return mainTimeline;
	}

	public void setMainTimeline(Timeline mainTimeline) {
		this.mainTimeline = mainTimeline;
	}

	/**
     * Get the distance betwene enemy and player
     * @param player The player
     * @return The distance
     */
    private int getDistanceToPlayer(Player player) {
    	int distanceToPlayer = this.computePathLength(player, this.getX(), this.getY());
    	if (distanceToPlayer == 0) {
			player.getDungeon().processPlayerEnemyCollision(player, this);
		}
    	return distanceToPlayer;
    }
    
    /**
     * Move towards the player every second
     * i.e. run moveTowardsPlayer every second
     * @param player The player
     */
    public void runTimeline(Player player) {
		mainTimeline = new Timeline(
		    new KeyFrame(Duration.millis(1000), e -> {
		        this.moveTowardsPlayer(player);
		    })
		);
    	mainTimeline.setCycleCount(Timeline.INDEFINITE);
    	mainTimeline.play();

    }
	
}
