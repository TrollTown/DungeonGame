package unsw.dungeon;

public class Enemy extends Entity {
	private boolean isAlive;
	
	public Enemy(int x, int y) {
		super(x, y);
		isAlive = true;
		
	}
	// Kill the enemy
	public void kill() {
		super.setShow(false);
		this.isAlive = false;
	}
	public boolean isDead() {
		return !this.isAlive;
	}
	
	// Move enemy towards player
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
	
	// Simple path length calculator, using Manhattan Distance (planning to implement proper pathfinding algorithm later)
	private int computePathLength(Player player, int x, int y) {
		if (isImmovableAtCoord(x, y)) {
			return -1;
		}
		int playerX = player.getX();
		int playerY = player.getY();
		int manhattanDistance = Math.abs(playerX - x) + Math.abs(playerY - y);
		return manhattanDistance;
	}
	
	// Can move through enemy
    public boolean moveEntityCheck(int x, int y, Direction direction, Inventory inventory) {
    	return true;
    }
    
    // Get the distance betwene enemy and player
    private int getDistanceToPlayer(Player player) {
    	int distanceToPlayer = this.computePathLength(player, this.getX(), this.getY());
    	if (distanceToPlayer == 0) {
			player.getDungeon().processPlayerEnemyCollision(player, this);
		}
    	return distanceToPlayer;
    }
}
