package unsw.dungeon;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MainMenuController {
	private DungeonApplication application;
	
	@FXML 
	private Label mainLabel;
	
	@FXML
	private Button startGameButton;
	
	@FXML
	private Button loadGameButton;
	
	@FXML
	private Button quitGameButton;
	
	@FXML
	public void initialise() {
		 this.startGameButton.setOnAction(event -> {
	        	;
	        });
		 this.loadGameButton.setOnAction(event -> {
	        	;
	        });
		 this.quitGameButton.setOnAction(event -> {
	        	System.exit(0);
	        });
	}
	
	public void setApplication(DungeonApplication application) {
		this.application = application;
	}
}
