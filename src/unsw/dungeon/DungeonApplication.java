package unsw.dungeon;

import java.io.IOException;

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
	
    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Dungeon");
        this.stage = primaryStage;
        loadDungeonStage();
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
    
    private void loadDungeonStage() {
    	try {
    		this.loadDungeonRoot("advanced2.json");
    	} catch (Exception e) {
    		e.printStackTrace();
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
}
