package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Time;

public class Main extends Application{
    Game game = new Game();
    public void start(Stage primaryStage ) throws Exception {
        Group root = new Group();
        Scene startScene = new Scene(root,900,660);
        MenuStart menuStart = new MenuStart(root);
        primaryStage.setScene(startScene);
        primaryStage.show();


                game = new Game();
                game.start(primaryStage);




    }
    public static void main(String[] args) {
        launch(args);
    }
}