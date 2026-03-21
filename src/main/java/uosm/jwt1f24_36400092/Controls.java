package uosm.jwt1f24_36400092;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

public class Controls {
    @FXML
    private TextField nameField;
    @FXML
    private PasswordField passField;
    @FXML
    private Button submitBtn;
    @FXML
    private Label loginLabel;

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
}
