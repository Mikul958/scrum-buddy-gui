package scrumsystem;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import model.ScrumSystem;

public class AddTaskController implements Initializable
{

    ScrumSystem system = ScrumSystem.getInstance();

    @FXML
    private Button cancelAddTask;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    
        //TODO

    }
    
    @FXML
    void cancelAddTask(ActionEvent event) throws IOException{
        App.setRoot("project");
    }

}
