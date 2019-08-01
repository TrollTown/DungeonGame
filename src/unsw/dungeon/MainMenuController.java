package unsw.dungeon;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class MainMenuController {
	private DungeonApplication application;
	
	@FXML
	private AnchorPane anchor;
	
	@FXML 
	private Label mainLabel;
	
	@FXML
	private Button startGameButton;
	
	@FXML
	private Button loadGameButton;
	
	@FXML
	private Button quitGameButton;
	
	@FXML
	private Pane loadGamePanel;
	
	@FXML
	public void initialize() {
		 this.startGameButton.setOnAction(event -> {
        	this.startGame();
	     });
		 this.loadGameButton.setOnAction(event -> {
			 this.toggleLoadGamePanel();
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
	
	private void toggleLoadGamePanel() {
		if (this.loadGamePanel.isVisible()) {
			this.loadGamePanel.setVisible(false);
			this.anchor.requestFocus();
		}
		else {
			this.loadGamePanel.setVisible(true);
			this.loadGamePanel.requestFocus();
		}
	}
}
