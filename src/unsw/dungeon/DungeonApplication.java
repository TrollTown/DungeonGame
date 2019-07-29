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
	
    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Dungeon");

        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("bomb.json");

        DungeonController controller = dungeonLoader.loadController();
        //AnchorPane root = new AnchorPane();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("app.fxml"));
        loader.setController(controller);
        Parent root = loader.load();
        //root.getChildren().add(dungeonGridPane);
        controller.setPrimaryStage(primaryStage);
        
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("menustyles.css").toString());
        root.requestFocus();
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
