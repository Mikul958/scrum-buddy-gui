package scrumsystem;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.ScrumSystem;

public class ProjectsController implements Initializable
{
    @FXML
    private Label totalTasks;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        ScrumSystem project = ScrumSystem.getInstance();
        System.out.println("in here");
        totalTasks.setText("" + project.getCurrentProject().getTotalTasks());
    }
}
