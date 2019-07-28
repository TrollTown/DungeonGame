package unsw.dungeon;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class LitBomb extends Bomb {
	private ObjectProperty<LitBombState> state = new SimpleObjectProperty<LitBombState>(new LitState());
	public LitBomb(int x, int y) {
		super(x, y);
		
	}
	
	public void nextState() {
		state.get().next(this);
	}
	
	public void setState(LitBombState state) {
		this.state.set(state);
	}
	
	public ObjectProperty<LitBombState> getStateProperty() {
		return this.state;
	}
	
	public LitBombState getState() {
		return this.state.get();
	}
	
	// Detonate a bomb
	public void detonateBomb() {
		
		Timeline timeline = new Timeline(
		    new KeyFrame(Duration.millis(5000), e -> {
		        state.get().next(LitBomb.this);
		        LitBomb.this.causeDamage();
		        removeFromView();
		    })
		);
    	timeline.setCycleCount(1);
    	timeline.play();
	    	
	}
	
	public void removeFromView() {
		Timeline timeline = new Timeline(
		    new KeyFrame(Duration.millis(1000), e -> {
		    	state.get().next(LitBomb.this);
		    	
		    })
		);
		timeline.setCycleCount(1);
		timeline.play();
	}
	
	public void causeDamage() {
		super.causeDamage();
	}

}
