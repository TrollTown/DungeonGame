package unsw.dungeon;

public class DungeonCompletionObserver extends Observer {
	private Dungeon dungeon;
	
	public DungeonCompletionObserver(Dungeon dungeon) {
		this.dungeon = dungeon;
		this.dungeon.attach(this);
	}
	
	@Override
	public void update() {
		if (this.dungeon.isCompletedDungeon()) {
			this.dungeon.getMainController().getApplication().loadNextDungeon();
		}
	}
}
