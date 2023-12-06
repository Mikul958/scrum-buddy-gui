package scrumsystem;

import java.io.IOException;
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
}
