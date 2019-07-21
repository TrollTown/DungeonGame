package unsw.dungeon;

// State pattern for different stages of exploding
public interface LitBombState {
	void next(LitBomb bomb);
}
