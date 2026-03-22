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

public class LoginControls {
    @FXML
    private TextField nameField;
    @FXML
    private PasswordField passField;
    @FXML
    private Label alert;
    @FXML
    private Button submitBtn;
    @FXML
    private Button toSignUp;

    // login event handling
    @FXML
    private void login() {
        String username = nameField.getText();
        String password = passField.getText();

        if (username.isEmpty()) {
            alert.setText("Please fill in username!");
            return;
        } else if (password.isEmpty()) {
            alert.setText("Please fill in password!");
            return;
        } else {
            alert.setText(" ");
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
