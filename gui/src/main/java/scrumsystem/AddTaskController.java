package scrumsystem;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import model.*;

public class AddTaskController implements Initializable
{
    ScrumSystem system = ScrumSystem.getInstance();



    private Column currentCol = ProjectController.selectedColumn;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1)
    {
        
    }
    
    @FXML
    void backToProject() throws IOException
    {
        App.setRoot("project");
    }
}
