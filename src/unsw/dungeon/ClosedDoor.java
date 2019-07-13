package unsw.dungeon;

public class ClosedDoor implements TypeDoor {

	@Override
	public boolean getImmovable() {
		return true;
	}

}
