package unsw.dungeon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Represents the dungeon application, used by JavaFX
 * @author Edward Webb and William Shen
 *
 */
public class DungeonApplication extends Application {
	private Stage stage;
	private ArrayList<String> levels;
	private int currentLevel;
	private int levelCount;
	private SaveManager saveManager;
	
	/**
	 * The constructor for the dungeon application
	 */
	public DungeonApplication() {
		this.levels = this.readLevels();
		this.currentLevel = -1;
		this.saveManager = new SaveManager();
	}
	
	/**
	 * Function used to start the dungeon UI
	 * Used by JavaFX
	 */
    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Main Menu");
        this.stage = primaryStage;
        this.loadMainMenu();
//        this.loadNextDungeon();
        primaryStage.show();
    }

    /**
     * The main function
     * Launch is used by javafx
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    /**
     * Loads the main menu
     */
    public void loadMainMenu() {
    	try {
    		this.loadNewRoot("main_menu");
    	} catch (Exception e) {
    		e.printStackTrace();
    	}	
    }
    
    /**
     * Given the dungeon file, loads the stage
     * @param dungeonFile
     */
    private void loadDungeonStage(String dungeonFile) {
    	try {
    		this.loadNewRoot(dungeonFile);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    /**
     * Load the next dungeon in the levels.txt file
     */
    public void loadNextDungeon() {
    	if (this.currentLevel == -1) {
            this.stage.setTitle("Dungeon");
    		this.loadDungeonStage(this.levels.get(0));
    		this.currentLevel++;
    		System.out.println(this.currentLevel);
    	}
    	else {
    		this.currentLevel++;
    		System.out.println(this.currentLevel);
    		if (this.currentLevel < this.levelCount) {
    			this.loadDungeonStage(this.levels.get(this.currentLevel));
    		}
    		else {
    			// Game completion
    			System.out.println("Game Completed!");
    			System.exit(0);
    		}
    	}
    }
    
    /**
     * Reset the level progress
     */
    public void resetLevelProgress(){
    	this.currentLevel = -1;
    	this.loadNextDungeon();
    }
    
    /**
     * Loads the scene
     * @param dungeon A file to be loaded
     * @return The parent root
     * @throws IOException
     */
    public int getCurrentLevel() {
    	return this.currentLevel;
    }
    
    public void reloadCurrentDungeon() {
    	this.currentLevel--;
    	this.loadNextDungeon();
    }
    
    public void setCurrentLevel(int currentLevel) {
    	this.currentLevel = currentLevel;
    }
    
    private Parent loadNewRoot(String dungeon) throws IOException {
    	Parent newRoot;
    	if (dungeon == "main_menu") {
    		MainMenuController controller = new MainMenuController();
        	controller.setApplication(this);
        	FXMLLoader loader = new FXMLLoader(getClass().getResource("main_menu.fxml"));
        	loader.setController(controller);
        	newRoot = loader.load();
    	}
    	else {
    		DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(dungeon);
        	DungeonController controller = dungeonLoader.loadController();
        	controller.setApplication(this);
        	FXMLLoader loader = new FXMLLoader(getClass().getResource("app - Copy.fxml"));
        	loader.setController(controller);
        	newRoot = loader.load();
    	}
    	Scene scene = this.stage.getScene();
    	if (scene == null) {
    		scene = new Scene(newRoot);
    		this.stage.setScene(scene);
    	}
    	else {
    		this.stage.getScene().setRoot(newRoot);
    	}
    	newRoot.requestFocus();
    	this.stage.sizeToScene();
    	this.stage.setResizable(Boolean.FALSE);
    	return newRoot;
    }
    
    /**
     * Reads the levels from levels.txt
     * @return The list of dungeon names
     */
    private ArrayList<String> readLevels(){
    	ArrayList<String> levels = new ArrayList<String>();
    	BufferedReader reader;
    	try {
    		int levelCount = 0;
    		reader = new BufferedReader(new FileReader("src/unsw/dungeon/levels.txt"));
    		String line = reader.readLine();
    		while (line != null) {
    			levels.add(line);
    			levelCount++;
    			line = reader.readLine();
    		}
    		this.levelCount = levelCount;
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	return levels;
    }
    
    public SaveManager getSaveManager() {
    	return this.saveManager;
    }
}
