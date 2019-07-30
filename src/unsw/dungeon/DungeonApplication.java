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
        primaryStage.setTitle("Dungeon");
        this.stage = primaryStage;
        loadNextDungeon();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
//    private void loadMainMenu() {
//    	try {
//    		this.changeScene("main_menu.fxml");
//    	} catch (Exception e) {
//    		e.printStackTrace();
//    	}	
//    }
    
    private void loadDungeonStage(String dungeonFile) {
    	try {
    		this.loadDungeonRoot(dungeonFile);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    private void loadNextDungeon() {
    	if (this.currentLevel == -1) {
    		this.loadDungeonStage(this.levels.get(0));
    		this.currentLevel++;
    	}
    	else {
    		this.currentLevel++;
    		if (this.currentLevel < this.levelCount) {
    			this.loadDungeonStage(this.levels.get(this.currentLevel));
    		}
    	}
    }
    
    private Parent loadDungeonRoot(String dungeon) throws IOException {
		DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(dungeon);
    	DungeonController controller = dungeonLoader.loadController();
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
    	}
    	stage.sizeToScene();
    	return newRoot;
    }
    
    private ArrayList<String> readLevels(){
    	ArrayList<String> levels = new ArrayList<String>();
    	BufferedReader reader;
    	try {
    		int levelCount = 0;
    		System.out.println(new File(".").getAbsoluteFile());
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
