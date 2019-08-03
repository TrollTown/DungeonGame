package unsw.dungeon;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * Represents a lit bomb
 * @author William Shen and Edward Webb
 *
 */
public class LitBomb extends Bomb {
	private ObjectProperty<LitBombState> state = new SimpleObjectProperty<LitBombState>(new LitState());
	
	/**
	 * Constructor for lit bomb class
	 * @param x The x-coord
	 * @param y The y-coord
	 */
	public LitBomb(int x, int y) {
		super(x, y);
		
	}
	
	/**
	 * Gets the next state of the bomb
	 */
	public void nextState() {
		state.get().next(this);
	}
	
	/**
	 * Sets the state of the bomb
	 * @param state The state
	 */
	public void setState(LitBombState state) {
		this.state.set(state);
	}
	
	/**
	 * Gets the state property for JavaFX
	 * @return The property
	 */
	public ObjectProperty<LitBombState> getStateProperty() {
		return this.state;
	}
	
	/**
	 * Gets the bomb's state
	 * @return The bomb's state
	 */
	public LitBombState getState() {
		return this.state.get();
	}
	
	/**
	 * Detonate a bomb
	 */
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
	
	
	/**
	 * Removes a bomb from the view
	 */
	public void removeFromView() {
		Timeline timeline = new Timeline(
		    new KeyFrame(Duration.millis(1000), e -> {
		    	state.get().next(LitBomb.this);
		    	
		    })
		);
		timeline.setCycleCount(1);
		timeline.play();
	}
	
	/**
	 * Causes damage around the bomb
	 */
	public void causeDamage() {
		super.causeDamage();
	}

}
