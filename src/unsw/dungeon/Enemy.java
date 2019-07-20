package unsw.dungeon;

public class Enemy extends Entity {
	private boolean isAlive;
	public Enemy(int x, int y) {
		super(x, y);
		isAlive = true;
	}
	public void kill() {
		this.isAlive = false;
	}
	public boolean isDead() {
		return !this.isAlive;
	}
	
	public void moveTowardsPlayer(Player player) {
		int distanceToPlayer = this.computePathLength(player, this.getX(), this.getY());
		
		// Debugging
		if (distanceToPlayer == 0) {
			System.out.println("Player has been reached by enemy");
			return;
		}
		
		int distanceAfterMoveUp = this.computePathLength(player, this.getX(), this.getY()-1);
		int distanceAfterMoveDown = this.computePathLength(player, this.getX(), this.getY()+1);
		int distanceAfterMoveLeft = this.computePathLength(player, this.getX()-1, this.getY());
		int distanceAfterMoveRight = this.computePathLength(player, this.getX()+1, this.getY());
		int distances[] = new int[] {distanceAfterMoveUp, distanceAfterMoveDown, distanceAfterMoveLeft, distanceAfterMoveRight};
		int move = -1;
		for (int i = 0; i < distances.length; i++) {
			if (distances[i] < distanceToPlayer && distances[i] != -1) {
				move = i;
				break;
			}
		}
		//System.out.println(move);
		switch(move) {
			case 0:
				this.setY(this.getY()-1);
				//this.reportPosition();
				break;
			case 1:
				this.setY(this.getY()+1);
				//this.reportPosition();
				break;
			case 2:
				this.setX(this.getX()-1);
				//this.reportPosition();
				break;
			case 3:
				this.setX(this.getX()+1);
				//this.reportPosition();
				break;
			// Debugging
			default:
				// System.out.println("Enemy not moved");
				break;
		}
		
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
	
	// For debugging only
	private void reportPosition() {
		System.out.println("Enemy position is now " + this.getX() +"," + this.getY());
	}
	
    public boolean moveEntityCheck(int x, int y, Direction direction, Inventory inventory) {
    	return true;
    }
}
