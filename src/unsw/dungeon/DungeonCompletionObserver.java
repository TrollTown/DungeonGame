package unsw.dungeon;

/**
 * Observes whether the dungeon is completed or not
 * @author William Shen and Edward Webb
 *
 */
public class DungeonCompletionObserver extends Observer {
	private Dungeon dungeon;
	
	/**
	 * 
	 * @param dungeon
	 */
	public DungeonCompletionObserver(Dungeon dungeon) {
		this.dungeon = dungeon;
		this.dungeon.attach(this);
	}
	
	@Override
	public void update() {
		if (this.dungeon.isCompletedDungeon()) {
			this.dungeon.getMainController().getApplication().loadNextDungeon();
			System.out.println("TRIPPED");
		}
	}
}
