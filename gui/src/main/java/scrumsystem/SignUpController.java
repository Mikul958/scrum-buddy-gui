package scrumsystem;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import model.*;

public class SignUpController
{
    @FXML
    private TextField textFirstName, textLastName, textEmail, textUsername;

    @FXML
    private PasswordField textPassword, textPasswordConfirm;

    @FXML
    void backToLogin() throws IOException
    {
        App.setRoot("primary");
    }

    @FXML
    void signUp() throws IOException
    {
        String password = textPassword.getText();
        String passwordConfirm = textPasswordConfirm.getText();
        if (!password.equals(passwordConfirm))
        {
            // TODO make text box with confirm password error.
            return;
        }
        String username = textUsername.getText();
        String email = textEmail.getText();
        String firstName = textFirstName.getText();
        String lastName = textLastName.getText();
        boolean success = ScrumSystem.getInstance().createAccount(username, password, email, firstName, lastName);

        if (success)
        {
            ScrumSystem.getInstance().login(username, password);
            App.setRoot("dashboard");
        }
        else
        {
            // TODO make text box with general error
        }
    }
}
