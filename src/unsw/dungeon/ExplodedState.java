package unsw.dungeon;

public class ExplodedState implements LitBombState {

	@Override
	public void next(LitBomb bomb) {
		System.out.println("Bomb detonated");
	}
}
