package unsw.dungeon;

public class LitState implements LitBombState{
	// Bomb is lit
	@Override
	public void next(LitBomb bomb) {
		bomb.setState(new ExplodedState());
	}
}
