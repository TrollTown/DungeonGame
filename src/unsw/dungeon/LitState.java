package unsw.dungeon;

/**
 * State of bomb when it is lit
 * @author Edward Webb and William Shen
 *
 */
public class LitState implements LitBombState{
	/**
	 * Call this function when bomb is about to explode
	 * Go to exploded state
	 */
	@Override
	public void next(LitBomb bomb) {
		bomb.setState(new ExplodedState());
	}
}
