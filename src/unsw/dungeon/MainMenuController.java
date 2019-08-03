package unsw.dungeon;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 * Controller for the main menu
 * @author William Shen and Edward Webb
 *
 */
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
	private StackPane loadGameStack;
	
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
	
	/**
	 * Initialise the scene
	 */
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
			 this.anchor.requestFocus();
	     });
		 this.quitGameButton.setOnAction(event -> {
	        System.exit(0);
	     });
		 this.backToMainMenuButton.setOnAction(event -> {
			 this.toggleLoadGamePanel();
			 this.anchor.requestFocus();
		 });
		 this.confirmLoadButton.setOnAction(event -> {
			 this.loadSavedDungeon();
		 });
	}
	
	/**
	 * Set the application so this controller can access it
	 * @param application
	 */
	public void setApplication(DungeonApplication application) {
		this.application = application;
	}
	
	/**
	 * Start the game
	 */
	private void startGame() {
		this.application.loadNextDungeon();
		System.out.println("BUTTON PRESSED");
	}
	
	/**
	 * Toggle the game panel
	 */
	private void toggleLoadGamePanel() {
		if (this.loadGameStack.isVisible()) {
			this.loadGameStack.setVisible(false);
		}
		else {
			this.loadGameStack.setVisible(true);
			this.populateSavePanel();
		}
	}
	
	/**
	 * Fill the save panel with saves
	 */
	private void populateSavePanel() {
    	this.saves = this.application.getSaveManager().getSavesList();
    	this.chosenSaveFile = -1;
    	this.refreshGridValues();
    }
	
	/**
	 * Update the view with save/levels/other values
	 */
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
    		
    	for (int i = 0; i < 11; i++) {
    		Label newNameLabel;
    		Label newLevelLabel;
    		Label newTimestampLabel;
    		if (i == 0) {
    			newNameLabel = new Label("Save Name");
        		newLevelLabel = new Label("Level");
        		newTimestampLabel = new Label("Timestamp");
        		newNameLabel.getStyleClass().add("saveTableHeading");
        		newLevelLabel.getStyleClass().add("saveTableHeading");
        		newTimestampLabel.getStyleClass().add("saveTableHeading");
    		}
    		else {
    			newNameLabel = new Label(this.saveNames.get(i-1));
        		newLevelLabel = new Label(this.saveLevels.get(i-1));
        		newTimestampLabel = new Label(this.saveTimestamps.get(i-1));
        		newNameLabel.getStyleClass().add("saveTableLabel");
        		newLevelLabel.getStyleClass().add("saveTableLabel");
        		newTimestampLabel.getStyleClass().add("saveTableLabel");
    		}
    		
    		this.loadTable.add(newNameLabel, 0, i);
    		this.loadTable.add(newLevelLabel, 1, i);
    		this.loadTable.add(newTimestampLabel , 2, i);
    	}
    	this.refreshGridMouseListeners();
    }
    
	/**
	 * Refresh the mouse listener functions
	 */
	private void refreshGridMouseListeners() {
    	for (Node node : this.loadTable.getChildren()) {
    		node.setOnMouseEntered(e -> this.loadTable.getChildren().forEach(cell -> {
    			Integer targetIndex = GridPane.getRowIndex(node);
    			if ((GridPane.getRowIndex(cell) == targetIndex) && (GridPane.getRowIndex(cell) != this.chosenSaveFile) && (targetIndex != 0)) {
    				cell.setStyle("-fx-background-color: #ffb61e;");
    			}
    		}));
    		node.setOnMouseExited(e -> this.loadTable.getChildren().forEach(cell -> {
    	        Integer targetIndex = GridPane.getRowIndex(node);
    	        if ((GridPane.getRowIndex(cell) == targetIndex) && (GridPane.getRowIndex(cell) != this.chosenSaveFile) && (targetIndex != 0)) {
    	            cell.setStyle("-fx-background-color: transparent;");
    	        }
    	    }));
    		
    		node.setOnMouseClicked(e -> this.loadTable.getChildren().forEach(cell -> {
    			Integer targetIndex = GridPane.getRowIndex(node);
    			if ((GridPane.getRowIndex(cell) == targetIndex) && (targetIndex != 0)) {
    				cell.setStyle("-fx-background-color: #ffb61e;");
    				this.confirmLoadButton.setVisible(true);
    				this.chosenSaveFile = targetIndex;
    				this.chosenLevelToLoad = Integer.parseInt(this.saveLevels.get(this.chosenSaveFile - 1));
    				this.clearHighlighting();
    			}
    		}));
    	}
    }
    
	/**
	 * Clear highlighting
	 */
    private void clearHighlighting() {
    	this.loadTable.getChildren().forEach(cell -> {
    		if (GridPane.getRowIndex(cell) != this.chosenSaveFile) {
    			cell.setStyle("-fx-background-color: transparent;");
    		}
    	});
    }
    
    /**
     * Load the saved dungeon
     */
    private void loadSavedDungeon() {
    	this.application.setCurrentLevel(this.chosenLevelToLoad - 1);
    	this.application.loadNextDungeon();
    }
}
