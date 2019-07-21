package unsw.dungeon;

public class ExplodedState implements LitBombState {

	// Bomb has exploded
	@Override
	public void next(LitBomb bomb) {
		System.out.println("Bomb detonated");
	}
}
