package uosm.jwt1f24_36400092;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // create database if it doesn't exist, otherwise ignore
        SetupDB.setup();
        // get GUI elements from fxml file
        FXMLLoader loadWindow = new FXMLLoader(getClass().getResource("/login.fxml"));
        // setup scene + stage + resizing limits
        Scene loginScene = new Scene(loadWindow.load(), 800, 600);
        primaryStage.setTitle("Login");
        primaryStage.setMinHeight(400);
        primaryStage.setMinWidth(500);
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }
    // instantiate the program
    public static void main(String[] args) {
        launch(args);
    }
}
