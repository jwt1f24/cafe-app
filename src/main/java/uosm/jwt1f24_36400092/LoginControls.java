package uosm.jwt1f24_36400092;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.sql.*;

public class LoginControls {
    @FXML
    private TextField nameField;
    @FXML
    private PasswordField passField;
    @FXML
    private Label alert;

    // login event handling
    @FXML
    private void login(ActionEvent event) {
        String username = nameField.getText();
        String password = passField.getText();
        // input validation
        if (username.isEmpty()) {
            alert.setText("Please fill in username!");
            return;
        } else if (password.isEmpty()) {
            alert.setText("Please fill in password!");
            return;
        } else {
            alert.setText(" ");
        }
        // search if input matches any database entry in users table
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?;";
        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/cafe", "root", "");
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setString(2, password);
            // if credentials match an entry, login is successful
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                String role = result.getString("role");
                alert.setText("Login successful!");
                if (role.equals("admin")) {
                    try {
                        // if account is an admin
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/admindashboard.fxml"));
                        Parent root = loader.load();
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(new Scene(root));
                        stage.setTitle("Admin Dashboard");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        // if account is a user (default)
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/userdashboard.fxml"));
                        Parent root = loader.load();
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(new Scene(root));
                        stage.setTitle("User Dashboard");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                alert.setText("Invalid! Either incorrect credentials or account does not exist.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // switch to sign up page
    @FXML
    private void signUpPage(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/signup.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Sign Up");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
