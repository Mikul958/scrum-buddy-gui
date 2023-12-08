package scrumsystem;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import model.*;

public class ViewTaskController implements Initializable
{
    @FXML
    private Label taskName, columnName, priority;

    private Column currentCol = ProjectController.selectedColumn;
    private Task currentTask = ProjectController.selectedTask;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1)
    {
        taskName.setText(currentTask.getName());
        columnName.setText(currentCol.getTitle());
        priority.setText("" + currentTask.getPriority());
    }

    @FXML
    private void backToProject(ActionEvent event) throws IOException
    {
        App.setRoot("project");
    }

    @FXML
    private void deleteTask(ActionEvent event) throws IOException
    {
        ScrumSystem.getInstance().removeProjectTask(currentCol, currentTask);
        App.setRoot("project");
    }
}
