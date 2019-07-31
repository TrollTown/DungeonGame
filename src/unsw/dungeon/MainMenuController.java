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
	public void initialize() {
		 this.startGameButton.setOnAction(event -> {
        	this.startGame();
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
	
	private void startGame() {
		this.application.loadNextDungeon();
		System.out.println("BUTTON PRESSED");
	}
}
