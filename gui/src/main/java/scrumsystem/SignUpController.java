package scrumsystem;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.*;

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
    void createAccount() throws IOException
    {
        ScrumSystem system = ScrumSystem.getInstance();
        
        String firstName = textFirstName.getText();
        String lastName = textLastName.getText();
        String email = textEmail.getText();
        String username = textUsername.getText();
        String password = textPassword.getText();
        String passwordConfirm = textPasswordConfirm.getText();

        System.out.println(firstName + " " + lastName + " " + email + " " + username + " " + password + " " + passwordConfirm);
    }
}
