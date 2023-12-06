package scrumsystem;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.ScrumSystem;

public class ProjectController implements Initializable
{
    @FXML
    private Label totalTasks;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        ScrumSystem project = ScrumSystem.getInstance();
        System.out.println("in here");
        totalTasks.setText("Number of tasks: " + project.getCurrentProject().getTotalTasks());
    }

    @FXML
    void returnToDashboard(ActionEvent event) throws IOException {
        App.setRoot("dashboard");
    }
}
