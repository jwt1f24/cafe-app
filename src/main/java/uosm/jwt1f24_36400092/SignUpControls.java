package uosm.jwt1f24_36400092;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.sql.*;

public class SignUpControls {
    @FXML
    private TextField nameField;
    @FXML
    private PasswordField passField;
    @FXML
    private Label alert;

    // sign up event handling
    @FXML
    private void signUp() {
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
        // create account & insert into users table
        String sql = "INSERT INTO users(username, password, role) VALUES (?, ?, ?);";
        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/cafe", "root", "");
             PreparedStatement statement = connection.prepareStatement(sql)) {
            String role = "user";
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, role);
            statement.executeUpdate();
            // if creation is successful
            alert.setText("Account created! Please go to login page to log in.");
            nameField.clear();
            passField.clear();
        } catch (SQLException e) {
            // check for duplicate usernames
            if (e.getErrorCode() == 1062) {
                alert.setText("Invalid! Username already exists.");
            } else {
                alert.setText("Error: " + e.getMessage());
            }
            e.printStackTrace();
        }
    }
    // switch to login page
    @FXML
    private void loginPage(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
