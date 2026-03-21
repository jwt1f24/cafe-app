package uosm.jwt1f24_36400092;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;

public class Controls {
    @FXML
    private TextField nameField;
    @FXML
    private PasswordField passField;
    @FXML
    private Label loginLabel;
    @FXML
    private Button submitBtn;
    @FXML
    private Button toSignUp;
    @FXML
    private Button toLogin;

    // login event handling
    @FXML
    private void login() {
        String username = nameField.getText();
        String password = passField.getText();

        if (username.isEmpty()) {
            loginLabel.setText("Please fill in username!");
            return;
        } else if (password.isEmpty()) {
            loginLabel.setText("Please fill in password!");
            return;
        } else {
            loginLabel.setText(" ");
        }
    }
    // switch to sign up page
    @FXML
    private void signUpPage(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("signup.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Sign Up");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // switch to login page
    @FXML
    private void loginPage(ActionEvent event) {
        try {
            FXMLLoader loadWindow = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root = loadWindow.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
