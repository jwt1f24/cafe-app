package uosm.jwt1f24_36400092;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.event.ActionEvent;
import java.io.*;
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
                if (role.equals("admin")) {
                    try {
                        dashboardPage(event, "/admindashboard.fxml", "Admin");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        dashboardPage(event, "/userdashboard.fxml", "User");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return;
            } else {
                alert.setText("Invalid! Either incorrect credentials or account does not exist.");
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // search account in txt file for input validation
        File file = new File("database/users/" + username + ".txt");
        // check if input matches a file name in database directory
        if (file.exists()) {
            try (BufferedReader read = new BufferedReader(new FileReader(file))) {
                // reads each line of the txt file
                read.readLine();
                String filePass = read.readLine().split(": ")[1];
                String fileRole = read.readLine().split(": ")[1];
                // login if input matches credentials of an account in database
                if (password.equals(filePass)) {
                    alert.setText("Login successful!");
                    // check if account is an admin or user, then switch to respective dashboards
                    if (fileRole.equals("admin")) {
                        dashboardPage(event, "/admindashboard.fxml", "Admin");
                    } else {
                        dashboardPage(event, "/userdashboard.fxml", "User");
                    }
                } else {
                    // file name exists, but incorrect password
                    alert.setText("Invalid! Password is incorrect.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            alert.setText("Invalid! Either incorrect credentials or account does not exist.");
        }
    }
    // switch to dashboard page if login is successful
    @FXML
    private void dashboardPage(ActionEvent event, String path, String title) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle(title + " Dashboard");
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
