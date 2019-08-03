package unsw.dungeon;

/**
 * State pattern for different states of exploding
 * @author Edward Webb and William Shen
 *
 */
public interface LitBombState {
	void next(LitBomb bomb);
}
