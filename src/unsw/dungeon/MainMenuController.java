package unsw.dungeon;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
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
	private GridPane loadTable;
	
	@FXML
	private Button confirmLoadButton;
	
	@FXML
	private Button backToMainMenuButton;
	
	private int chosenSaveFile;
	
	private int chosenLevelToLoad;
    
    ArrayList<GameSave> saves;
	ArrayList<String> saveNames;
	ArrayList<String> saveLevels;
	ArrayList<String> saveTimestamps;
	
	@FXML
	public void initialize() {
		 this.saveNames = new ArrayList<String>();
         this.saveLevels = new ArrayList<String>();
         this.saveTimestamps = new ArrayList<String>();
		 this.startGameButton.setOnAction(event -> {
        	this.startGame();
	     });
		 this.loadGameButton.setOnAction(event -> {
			 this.toggleLoadGamePanel();
	     });
		 this.quitGameButton.setOnAction(event -> {
	        System.exit(0);
	     });
		 this.backToMainMenuButton.setOnAction(event -> {
			 this.toggleLoadGamePanel();
		 });
		 this.confirmLoadButton.setOnAction(event -> {
			 this.loadSavedDungeon();
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
			this.populateSavePanel();
			this.loadGamePanel.requestFocus();
		}
	}
	
	private void populateSavePanel() {
    	this.saves = this.application.getSaveManager().getSavesList();
    	this.chosenSaveFile = -1;
    	this.refreshGridValues();
    }
	
    private void refreshGridValues() {
    	this.loadTable.getChildren().clear();
    	for (int i = 0; i < 10; i++) {
    		if (i < this.saves.size()) {
    			this.saveNames.add(this.saves.get(i).getSaveName());
    			this.saveLevels.add(Integer.toString(this.saves.get(i).getSaveLevel()));
    			this.saveTimestamps.add(this.saves.get(i).getTimeStamp());
    		}
    		else {
    			this.saveNames.add("<<New Save>>");
    			this.saveLevels.add("-");
    			this.saveTimestamps.add("-");
    		}
    	}
    	for (int i = 0; i < 10; i++) {
    		this.loadTable.add(new Label(this.saveNames.get(i)), 0, i);
    		this.loadTable.add(new Label(this.saveLevels.get(i)), 1, i);
    		this.loadTable.add(new Label(this.saveTimestamps.get(i)), 2, i);
    	}
    	this.refreshGridMouseListeners();
    }
    
    private void refreshGridMouseListeners() {
    	for (Node node : this.loadTable.getChildren()) {
    		node.setOnMouseEntered(e -> this.loadTable.getChildren().forEach(cell -> {
    			Integer targetIndex = GridPane.getRowIndex(node);
    			if ((GridPane.getRowIndex(cell) == targetIndex) && (GridPane.getRowIndex(cell) != this.chosenSaveFile)) {
    				cell.setStyle("-fx-background-color: #ffb61e;");
    			}
    		}));
    		node.setOnMouseExited(e -> this.loadTable.getChildren().forEach(cell -> {
    	        Integer targetIndex = GridPane.getRowIndex(node);
    	        if ((GridPane.getRowIndex(cell) == targetIndex) && (GridPane.getRowIndex(cell) != this.chosenSaveFile)) {
    	            cell.setStyle("-fx-background-color: transparent;");
    	        }
    	    }));
    		
    		node.setOnMouseClicked(e -> this.loadTable.getChildren().forEach(cell -> {
    			Integer targetIndex = GridPane.getRowIndex(node);
    			if (GridPane.getRowIndex(cell) == targetIndex) {
    				this.confirmLoadButton.setVisible(true);
    				cell.setStyle("-fx-background-color: #ffb61e;");
    				this.chosenSaveFile = targetIndex;
    				this.chosenLevelToLoad = Integer.parseInt(this.saveLevels.get(this.chosenSaveFile));
    				this.clearHighlighting();
    			}
    		}));
    	}
    }
    
    private void clearHighlighting() {
    	this.loadTable.getChildren().forEach(cell -> {
    		if (GridPane.getRowIndex(cell) != this.chosenSaveFile) {
    			cell.setStyle("-fx-background-color: transparent;");
    		}
    	});
    }
    
    private void loadSavedDungeon() {
    	this.application.setCurrentLevel(this.chosenLevelToLoad - 1);
    	this.application.loadNextDungeon();
    }
}
