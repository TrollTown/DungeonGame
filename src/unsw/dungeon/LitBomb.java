package unsw.dungeon;

import java.util.Timer;
import java.util.TimerTask;

public class LitBomb extends Bomb {
	
	private LitBombState state = new LitState();
	
	public LitBomb(int x, int y) {
		super(x, y);
	}
	
	public void nextState() {
		state.next(this);
	}
	
	public void setState(LitBombState state) {
		this.state = state;
	}
	
	public LitBombState getState() {
		return this.state;
	}
	
	// Detonate a bomb
	public void detonateBomb() {
		new Timer().schedule( 
		        new TimerTask() {
		            @Override
		            public void run() {
		            	state.next(LitBomb.this);
		            	LitBomb.this.causeDamage();
		            }
		        }, 
		        5000 
		);
	}
	
	public void causeDamage() {
		super.causeDamage();
	}
}
