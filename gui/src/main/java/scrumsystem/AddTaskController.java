package scrumsystem;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.ScrumSystem;

public class AddTaskController implements Initializable
{

    ScrumSystem system = ScrumSystem.getInstance();

    @FXML
    private Button cancelAddTask;

    @FXML
    private Label labelColumnName;

                                    //Selected column is null
    // private String selectColumn = ProjectController.selectedColumn.getTitle();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    
        labelColumnName.setText("Current Column: ");

    }
    
    @FXML
    void cancelAddTask(ActionEvent event) throws IOException{
        App.setRoot("project");
    }

}
