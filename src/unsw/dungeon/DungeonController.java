package unsw.dungeon;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 * A JavaFX controller for the dungeon.
 * @author Edward Webb and William Shen
 *
 */
public class DungeonController {
	
	@FXML
	private AnchorPane anchor;
	
	@FXML
	private StackPane stack;

    @FXML
    private GridPane squares;
    
    @FXML
    private FlowPane menu;
    
    @FXML
    private Button BackToGameButton;
    
    @FXML
    private Button ResetProgressButton;
    
    @FXML
    private Button SaveGameButton;
    
    @FXML
    private Button QuitToTitleButton;
    
    @FXML
    private FlowPane inventory;
    
    @FXML
    private Label bombCount;
    
    @FXML
    private Label swordDurability;
    
    @FXML
    private Label invincibilityTimer;
    
    @FXML
    private FlowPane inventoryKeys;
    
    @FXML
    private Pane saveGamePanel;
    
    @FXML
    private StackPane saveGameStack;
    
    @FXML
    private Label saveGameLabel;
    
    @FXML
    private GridPane saveTable;
    
    @FXML
    private TextField saveNameField;
    
    @FXML
    private Label textfieldInstructions;
    
    @FXML
    private Button confirmSaveButton;
    
    @FXML
    private Label gameSavedLabel;
    
    @FXML
    private Button backToInGameMenu;
    
    @FXML
    private Label goalString;
    
    private List<ImageView> initialEntities;

    private Player player;

    private Dungeon dungeon;
    
    private DungeonApplication application;
    
    private int chosenSaveFile;
    
    private int savedFileIndex;
    
    ArrayList<GameSave> saves;
	ArrayList<String> saveNames;
	ArrayList<String> saveLevels;
	ArrayList<String> saveTimestamps;

    /**
     * Constructor for the dungeon controller
     * @param dungeon the dungeon
     * @param initialEntities the dungeon's entities
     */
    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities) {
        this.dungeon = dungeon;
        this.dungeon.setMainController(this);
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
        this.saveNames = new ArrayList<String>();
        this.saveLevels = new ArrayList<String>();
        this.saveTimestamps = new ArrayList<String>();
    }

    /**
     * Initialises the controller
     */
    @FXML
    public void initialize() {
    	// Initialise the menu popup
    	//this.initialiseMenu();
    	
        Image ground = new Image("/dirt_0_new.png");

        // Add the ground first so it is below all other entities
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                squares.add(new ImageView(ground), x, y);
            }
        }

        for (ImageView entity : initialEntities)
            squares.getChildren().add(entity);
        
        this.BackToGameButton.setOnAction(event -> {
        	this.toggleMenu();
        	this.anchor.requestFocus();
        });
        
        this.ResetProgressButton.setOnAction(event -> {
        	this.resetProgress();
        	this.anchor.requestFocus();
        });
        
        this.SaveGameButton.setOnAction(event -> {
        	this.toggleSaveGamePanel();
        	this.anchor.requestFocus();
        });
        
        this.QuitToTitleButton.setOnAction(event -> {
        	this.application.loadMainMenu();
        });
        
        this.anchor.setOnKeyPressed(event -> {
        	this.handleKeyPress(event);
        	this.anchor.requestFocus();
        });
        
        this.backToInGameMenu.setOnAction(event -> {
        	this.backToMenu();
        	this.anchor.requestFocus();
        });
        
        this.confirmSaveButton.setOnAction(event -> {
        	this.saveGame();
        	this.anchor.requestFocus();
        });
        
        // Make the in-game menu fill up the entire stack pane
        this.menu.prefWidthProperty().bind(this.stack.widthProperty());
        this.menu.prefHeightProperty().bind(this.stack.heightProperty());
        
        
        // Make the save game menu also fill up the entire stack pane
        this.saveGameStack.prefWidthProperty().bind(this.stack.widthProperty());
        this.saveGameStack.prefHeightProperty().bind(this.stack.heightProperty());
        
        this.displayGoals();
    }

    /**
     * Handles a key press
     * @param event an event eg. a key press
     */
    @FXML
    public void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
        case UP:
            player.moveUp();
            break;
        case DOWN:
            player.moveDown();
            break;
        case LEFT:
            player.moveLeft();
            break;
        case RIGHT:
            player.moveRight();
            break;
        case E:
        	player.placeBomb();
        	break;
        case K:
        	player.useSword();
        	break;
        case ESCAPE:
        	this.toggleMenu();
        	break;
        default:
            break;
        }
    }
    
    /**
     * Toggles the menu to show/hide
     */
    @FXML
    private void toggleMenu() {
    	if (this.menu.isVisible()) {
    		if (this.saveGameStack.isVisible()) {
    			this.saveGameStack.setVisible(false);
    		}
    		else {
    			this.menu.setVisible(false);
    		}
    	}
    	else {
    		this.menu.setVisible(true);
    	}
    }
    
    /**
     * Updates the bomb count in the inventory UI
     * @param count
     */
    @FXML
    private void toggleSaveGamePanel() {
    	if (this.saveGameStack.isVisible()) {
    		this.saveGameStack.setVisible(false);
    		
    	}
    	else {
    		this.populateSavePanel();
    		this.saveGameStack.setVisible(true);
    	}
    }
    
    @FXML
    private void backToMenu() {
    	this.saveGameStack.setVisible(false);
    	this.saveNameField.setVisible(false);
    	this.textfieldInstructions.setVisible(false);
    	this.confirmSaveButton.setVisible(false);
    	this.gameSavedLabel.setVisible(false);
    }
    
    public void changeBombCount(int count) {
    	this.bombCount.setText(Integer.toString(count));
    }
    
    /**
     * Updates the number of hits the sword has remaining
     * @param durability The durability of the sword
     */
    public void changeSwordDurability(int durability) {
    	this.swordDurability.setText(Integer.toString(durability));
    }
    
    /**
     * Updates the number of seconds remaining for the invincibility effect
     * @param seconds The number of seconds remaining
     */
    public void updateInvincibilityTimer(int seconds) {
    	this.invincibilityTimer.setText(Integer.toString(seconds) + " s");
    }
    
    /**
     * Updates the number of keys remaining
     * @param keyCount number of keys remaining
     */
    public void updateInventoryKeys(int keyCount) {
    	int existingKeyCount = this.inventoryKeys.getChildren().size();
		while (existingKeyCount < keyCount) {
			Image keyImage = new Image("/key.png");
			this.inventoryKeys.getChildren().add(new ImageView(keyImage));
			existingKeyCount++;
		}
		while (existingKeyCount > keyCount) {
			this.inventoryKeys.getChildren().remove(0);
			existingKeyCount--;
		}
    }
    
    /**
     * Updates the bomb's view when a bomb is placed
     * @param entity The bomb
     */
    public void updateView(Entity entity) {
    	if (entity instanceof LitBomb) {
    		LitBomb bomb = (LitBomb) entity;
    		if (bomb.getState() instanceof LitState) {
    			ImageView newView = new ImageView("/bomb_lit_1.png");
    			squares.add(newView, bomb.getX(), bomb.getY());
    	        bomb.getStateProperty().addListener(new ChangeListener<LitBombState>() {
    	            @Override
    	            public void changed(ObservableValue<? extends LitBombState> observable,
    	                    LitBombState oldValue, LitBombState newValue) {
    	            	if (newValue instanceof ExplodedState) {
    	            		newView.setImage(new Image("/bomb_lit_4.png"));
    	            	} else {
    	            		newView.setVisible(false);
    	            	}               
    	            }
    	        });			
    		}
    	} 
    }
    
    /**
     * Sets the dungeon application property
     * @param app The dungeon application
     */
    public void setApplication(DungeonApplication app) {
    	this.application = app;
    }
    
    /**
     * Gets the dungeon application
     * @return The dungeon application
     */
    public DungeonApplication getApplication() {
    	return this.application;
    }
    
    /**
     * Resets the dungeon progress
     */
    public void resetProgress() {
    	this.application.resetLevelProgress();
    }
    
    private void populateSavePanel() {
    	this.savedFileIndex = -1;
    	this.saves = this.application.getSaveManager().getSavesList();
    	this.chosenSaveFile = -1;
    	this.refreshGridValues();
    }
    
    private void clearHighlighting() {
    	this.saveTable.getChildren().forEach(cell -> {
    		if (GridPane.getRowIndex(cell) != this.chosenSaveFile) {
    			cell.setStyle("-fx-background-color: transparent;");
    		}
    	});
    }
    
    private void showConfirmSaveButton() {
    	this.confirmSaveButton.setVisible(true);
    }
    
    private void showSaveNameField() {
    	this.saveNameField.setVisible(true);
    	this.saveNameField.setText(saveNames.get(this.chosenSaveFile - 1));
    	this.textfieldInstructions.setVisible(true);
    	this.saveNameField.requestFocus();
    	// Arbitrary value - to the right of existing text
    	this.saveNameField.positionCaret(1000);
    }
    
    private void saveGame() {
    	String saveName = this.saveNameField.getText();
    	int currentLevel = this.application.getCurrentLevel();
    	int saveSlot = this.chosenSaveFile - 1;
    	this.application.getSaveManager().save(saveName, currentLevel, saveSlot);
    	this.saveNameField.setVisible(false);
    	this.textfieldInstructions.setVisible(false);
    	this.confirmSaveButton.setVisible(false);
    	this.saveNames.set(this.chosenSaveFile - 1, saveName);
    	this.saveLevels.set(this.chosenSaveFile - 1, Integer.toString(currentLevel));
    	final DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    	Date date = new Date();
		String newTimeStamp = sdf.format(date);
    	this.saveTimestamps.set(this.chosenSaveFile - 1, newTimeStamp);
    	this.refreshGridValues();
    	this.savedFileIndex = this.chosenSaveFile - 1;
    	this.highlightSavedSlot();
    	this.gameSavedLabel.setVisible(true);
    	this.saveTable.requestFocus();
    }
    
    private void refreshGridValues() {
    	this.saveTable.getChildren().clear();
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
    		
    		saveTable.add(newNameLabel, 0, i);
    		this.saveTable.add(newLevelLabel, 1, i);
    		this.saveTable.add(newTimestampLabel , 2, i);
    	}
    	this.refreshGridMouseListeners();
    }
    
    private void refreshGridMouseListeners() {
    	for (Node node : this.saveTable.getChildren()) {
    		node.setOnMouseEntered(e -> this.saveTable.getChildren().forEach(cell -> {
    			Integer targetIndex = GridPane.getRowIndex(node);
    			if ((GridPane.getRowIndex(cell) == targetIndex) && (GridPane.getRowIndex(cell) != this.chosenSaveFile) && (targetIndex != 0)) {
    				cell.setStyle("-fx-background-color: #ffb61e;");
    			}
    		}));
    		node.setOnMouseExited(e -> this.saveTable.getChildren().forEach(cell -> {
    	        Integer targetIndex = GridPane.getRowIndex(node);
    	        if ((GridPane.getRowIndex(cell) == targetIndex) && (GridPane.getRowIndex(cell) != this.chosenSaveFile) && (targetIndex != 0)) {
    	            cell.setStyle("-fx-background-color: transparent;");
    	        }
    	    }));
    		
    		node.setOnMouseClicked(e -> this.saveTable.getChildren().forEach(cell -> {
    			Integer targetIndex = GridPane.getRowIndex(node);
    			if ((GridPane.getRowIndex(cell) == targetIndex) && (targetIndex != 0)) {
    				cell.setStyle("-fx-background-color: #ffb61e;");
    				this.gameSavedLabel.setVisible(false);
    				this.showConfirmSaveButton();
    				this.chosenSaveFile = targetIndex;
    				this.showSaveNameField();
    				this.clearHighlighting();
    			}
    		}));
    	}
    }
    
    private void highlightSavedSlot() {
    	this.saveTable.getChildren().forEach(cell -> {
    		Integer rowIndex = GridPane.getRowIndex(cell);
    		if (rowIndex == this.savedFileIndex + 1) {
    			cell.setStyle("-fx-background-color: #26a65b;");
    		}
    	});
    }
    
    public void reloadDungeon() {
    	this.getApplication().reloadCurrentDungeon();
    }
    
    private void displayGoals() {
    	String string = this.dungeon.getGoal().toString();
    	System.out.println(string);
    	this.goalString.setText(string);
    }
}

