package scrumsystem;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class AddColumnController
{
    @FXML
    void cancelAddColumn(ActionEvent event) throws IOException{
        App.setRoot("project");
    }
}
