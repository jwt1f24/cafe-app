package uosm.jwt1f24_36400092;
import javafx.application.Application;
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
    public void start(Stage primaryStage) {
        // labels + input fields
        Label nameLabel = new Label("Username: ");
        TextField nameField = new TextField();
        Label passLabel = new Label("Password: ");
        TextField passField = new TextField();

        // buttons
        Button submitBtn = new Button("Submit");
        submitBtn.setPrefHeight(30);
        submitBtn.setPrefWidth(200);
        StackPane btnPane = new StackPane(submitBtn);

        // gridPane
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);
        grid.add(nameLabel, 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(passLabel, 0, 1);
        grid.add(passField, 1, 1);

        // setup column constraints
        ColumnConstraints c1 = new ColumnConstraints();
        ColumnConstraints c2 = new ColumnConstraints();
        c1.setPercentWidth(20);
        c2.setPercentWidth(30);
        grid.getColumnConstraints().addAll(c1, c2);

        // vbox to stack gridPane & stackPane
        VBox vbox = new VBox(30, grid, btnPane);
        vbox.setMaxHeight(400);
        vbox.setAlignment(Pos.CENTER);

        // borderPane for flexible resizing
        BorderPane layout = new BorderPane();
        layout.setCenter(vbox);

        // setup scene + stage
        Scene scene = new Scene(layout, 800, 600);
        primaryStage.setTitle("Cafe App");
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(500);
        primaryStage.setMinHeight(400);
        primaryStage.show();
    }
    // instantiate the program
    public static void main(String[] args) {
        launch(args);
    }
}
