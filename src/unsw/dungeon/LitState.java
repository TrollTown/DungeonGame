package unsw.dungeon;

public class LitState implements LitBombState{

	@Override
	public void next(LitBomb bomb) {
		bomb.setState(new ExplodedState());
	}
}
