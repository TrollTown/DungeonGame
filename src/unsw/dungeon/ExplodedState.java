package unsw.dungeon;

public class ExplodedState implements LitBombState {

	// Bomb has exploded
	@Override
	public void next(LitBomb bomb) {
		bomb.setState(new StopExplodedState());
	}
}
