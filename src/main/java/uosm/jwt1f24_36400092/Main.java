package uosm.jwt1f24_36400092;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    // setup gui
    public void start(Stage primaryStage) throws Exception {
        // get GUI elements from fxml file
        FXMLLoader source = new FXMLLoader(getClass().getResource("/main-view.fxml"));
        // setup scene + stage
        Scene scene = new Scene(source.load(), 800, 600);
        primaryStage.setTitle("Cafe App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    // instantiate the program
    public static void main(String[] args) {
        launch(args);
    }
}
