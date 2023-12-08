package scrumsystem;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.ScrumSystem;

public class AddColumnController implements Initializable
{

    @FXML
    private Label labelProjectName;

    ScrumSystem system = ScrumSystem.getInstance();

    @FXML
    public void initialize(URL arg0, ResourceBundle arg1){

        labelProjectName.setText("Current Project: "+system.getCurrentProject().getTitle());
        

    }

    @FXML
    void cancelAddColumn(ActionEvent event) throws IOException{
        App.setRoot("project");
    }
}
