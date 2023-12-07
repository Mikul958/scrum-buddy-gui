package scrumsystem;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AddColumnController
{

    @FXML
    private Label labelProjectName;

    @FXML
    void cancelAddColumn(ActionEvent event) throws IOException{
        App.setRoot("project");
    }
}
