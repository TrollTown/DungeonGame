package unsw.dungeon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
//        this.loadNextDungeon();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    public void loadMainMenu() {
    	try {
    		this.loadNewRoot("main_menu");
    	} catch (Exception e) {
    		e.printStackTrace();
    	}	
    }
    
    private void loadDungeonStage(String dungeonFile) {
    	try {
    		this.loadNewRoot(dungeonFile);
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
    
    public void resetLevelProgress(){
    	this.currentLevel = -1;
    	this.loadNextDungeon();
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
        	FXMLLoader loader = new FXMLLoader(getClass().getResource("app.fxml"));
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
