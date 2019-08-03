package unsw.dungeon;

/**
 * Represents a bomb exploded state
 * @author Edward Webb and William Shen
 *
 */
public class ExplodedState implements LitBombState {

	/**
	 * Bomb has exploded
	 * Get next state
	 */
	@Override
	public void next(LitBomb bomb) {
		bomb.setState(new StopExplodedState());
	}
}
