package scrumsystem;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.*;

public class PrimaryController {


     @FXML
    private PasswordField txt_password_box;

    @FXML
    private TextField txt_username_box;

    @FXML
    private void goToDashboard() throws IOException {

        ScrumSystem system = ScrumSystem.getInstance();
        String userName = txt_username_box.getText();
        String password = txt_password_box.getText();

        if(!system.login(userName, password)){
            System.out.println("Error logging in");
            return;
        }

        App.setRoot("dashboard");
    }

   
}
