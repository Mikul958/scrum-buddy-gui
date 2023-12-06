package scrumsystem;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import model.*;

public class PrimaryController
{
    @FXML
    private PasswordField txt_password_box;

    @FXML
    private TextField txt_username_box;

    @FXML
    private Label errorBox;

    @FXML
    private void goToDashboard() throws IOException {

        ScrumSystem system = ScrumSystem.getInstance();
        String userName = txt_username_box.getText();
        String password = txt_password_box.getText();

        if (!system.login(userName, password))
        {
            errorBox.setText("*Incorrect username or password");
            errorBox.setVisible(true);
            return;
        }
        App.setRoot("dashboard");
    }

    @FXML
    private void goToSignUp() throws IOException
    {
        App.setRoot("signup");
    }
}
