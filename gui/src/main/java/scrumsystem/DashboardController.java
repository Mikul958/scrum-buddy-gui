package scrumsystem;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.Initializable;
import model.*;

public class DashboardController implements Initializable {

    @FXML
    private Label lbl_username;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        ScrumSystem system = ScrumSystem.getInstance();
        lbl_username.setText(system.getCurrentAccount().getUsername());
    }
    
    @FXML
    private void logout() throws IOException
    {
        ScrumSystem.getInstance().saveData();
        ScrumSystem.getInstance().logout();
        App.setRoot("primary");
    }
}
