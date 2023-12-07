package scrumsystem;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import model.*;

public class ProjectController implements Initializable
{
    @FXML
    private Label projectTitle, totalTasks;

    @FXML
    private HBox columnsHBox;

    private ScrumSystem system = ScrumSystem.getInstance();
    private Column selectedColumn = null;
    private Task selectedTask = null;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1)
    {
        Project currentProject = system.getCurrentProject();
        projectTitle.setText("Project: " + currentProject.getTitle());
        totalTasks.setText("Number of tasks: " + currentProject.getTotalTasks());

        for(int i = 0; i < currentProject.getColumns().size(); i++)
        {
            // Create VBox for current column and add it to outer HBox
            Column column = currentProject.getColumns().get(i);
            VBox columnVBox = new VBox();
            columnVBox.setMaxWidth(200);
            columnsHBox.getChildren().add(columnVBox);

            // Add title to column.
            Label columnTitle = new Label();
            columnTitle.getStyleClass().add("columnTitle"); //here0
            columnTitle.setText(column.getTitle());
            columnVBox.getChildren().add(columnTitle);

            // Add task HBoxes to column.
            for (int j=0; j<column.getTasks().size(); j++)
            {
                // Create HBox for current Task and add it to Column's VBox
                Task task = column.getTasks().get(j);
                HBox taskHBox = new HBox();
                columnVBox.getChildren().add(taskHBox);

                // Add title to task box.
                Label taskName = new Label();
                taskName.getStyleClass().add("taskTitle");
                taskName.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event)
                    {
                        // TODO add code to change screen and view a task
                        selectedColumn = column;
                        selectedTask = task;
                        System.out.println("selected task\n" + selectedTask + "\nin column\n" + selectedColumn);
                    }
                });
                taskName.setText(task.getName());
                taskHBox.getChildren().add(taskName);
            }
        }
    }

    @FXML
    void returnToDashboard(ActionEvent event) throws IOException {
        App.setRoot("dashboard");
    }
}
