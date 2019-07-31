package unsw.dungeon;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class DungeonApplication extends Application {
	private Stage stage;
	private ArrayList<String> levels;
	private int currentLevel;
	private int levelCount;
	
	public DungeonApplication() {
		this.levels = this.readLevels();
		this.currentLevel = -1;
	}
	
    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Main Menu");
        this.stage = primaryStage;
        this.loadMainMenu();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    public void loadMainMenu() {
    	try {
    		this.loadMainMenuRoot();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}	
    }
    
    private void loadDungeonStage(String dungeonFile) {
    	try {
    		this.loadDungeonRoot(dungeonFile);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    public void loadNextDungeon() {
    	if (this.currentLevel == -1) {
            this.stage.setTitle("Dungeon");
    		this.loadDungeonStage(this.levels.get(0));
    		this.currentLevel++;
    	}
    	else {
    		this.currentLevel++;
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
    
    private Parent loadDungeonRoot(String dungeon) throws IOException {
		DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(dungeon);
    	DungeonController controller = dungeonLoader.loadController();
    	controller.setApplication(this);
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("app.fxml"));
    	loader.setController(controller);
    	Parent newRoot = loader.load();
    	controller.setPrimaryStage(this.stage);
    	Scene scene = this.stage.getScene();
    	if (scene == null) {
    		scene = new Scene(newRoot);
    		scene.getStylesheets().add(getClass().getResource("menustyles.css").toString());
    		newRoot.requestFocus();
    		this.stage.setScene(scene);
    	}
    	else {
    		stage.getScene().setRoot(newRoot);
    		newRoot.requestFocus();
    	}
    	stage.sizeToScene();
    	return newRoot;
    }
    
    private Parent loadMainMenuRoot() throws IOException {
    	Parent newRoot = (Parent) FXMLLoader.load(getClass().getResource("main_menu.fxml"));
    	Scene scene = this.stage.getScene();
    	if (scene == null) {
    		scene = new Scene(newRoot);
    		scene.getStylesheets().add(getClass().getResource("main_menu_styles.css").toString());
    		this.stage.setScene(scene);
    		newRoot.requestFocus();
    	}
    	else {
    		this.stage.getScene().setRoot(newRoot);
    		newRoot.requestFocus();
    	}
    	this.stage.sizeToScene();
    	return newRoot;
    }
    
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
}
