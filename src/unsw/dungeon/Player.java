package unsw.dungeon;

enum Direction {
	LEFT,
	RIGHT,
	UP,
	DOWN,
}
/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity {

    private Dungeon dungeon;

    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
    }
    
    private boolean moveBoulderCheck(int x, int y, Direction direction) {
    	
    	Entity coordEntity = dungeon.getEntityAtCoord(x, y);
    	if (coordEntity instanceof Boulder) {
    		System.out.println("Running boulder check");
    		System.out.println("Player currently at: " + getX() + ", " + getY());
    		System.out.println("Move Boulder from " + x + ", " + y);
    		Boulder boulderEntity = (Boulder) coordEntity;
    		if (direction == Direction.UP && boulderEntity.moveUp()) {
    			return true;
    		}
    		else if (direction == Direction.RIGHT && boulderEntity.moveRight()) {
    			return true;
    		}
    		else if (direction == Direction.DOWN && boulderEntity.moveDown()) {
    			return true;
    		}
    		else if (direction == Direction.LEFT && boulderEntity.moveLeft()) {
    			return true;
    		}
    		return false;
    	}
    	return true;
    }

    public void moveUp() {
        if (getY() > 0 && (!dungeon.isImmovableAtCoord(getX(), getY() - 1) ||
        		moveBoulderCheck(getX(), getY() - 1, Direction.UP)))
            y().set(getY() - 1);
    }

    public void moveDown() {
        if (getY() < dungeon.getHeight() - 1 && (!dungeon.isImmovableAtCoord(getX(), getY() + 1)
        		|| moveBoulderCheck(getX(), getY() + 1, Direction.DOWN)))
            y().set(getY() + 1);
    }

    public void moveLeft() {
        if (getX() > 0 && (!dungeon.isImmovableAtCoord(getX() - 1, getY()) ||
        		moveBoulderCheck(getX() -1, getY(), Direction.LEFT)))
            x().set(getX() - 1);
    }

    public void moveRight() {
        if (getX() < dungeon.getWidth() - 1 && (!dungeon.isImmovableAtCoord(getX() + 1, getY())
        		|| moveBoulderCheck(getX() + 1, getY(), Direction.RIGHT)))
            x().set(getX() + 1);
    }
    
    
}
