package unsw.dungeon;

/**
 * Observes whether the dungeon is completed or not
 * @author William Shen and Edward Webb
 *
 */
public class DungeonCompletionObserver extends Observer {
	private Dungeon dungeon;
	
	/**
	 * Constructor for Dungeon Completion Observer
	 * @param dungeon The dungeon
	 */
	public DungeonCompletionObserver(Dungeon dungeon) {
		this.dungeon = dungeon;
		this.dungeon.attach(this);
	}
	
	/**
	 * Updates the dungeon when the dungeon is completed
	 */
	@Override
	public void update() {
		if (this.dungeon.isCompletedDungeon()) {
			this.dungeon.getMainController().getApplication().loadNextDungeon();
		}
	}
}
