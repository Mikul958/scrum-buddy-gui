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
    HBox columnsHBox;

    ScrumSystem system = ScrumSystem.getInstance();

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
            columnsHBox.getChildren().add(columnVBox);

            // Add title to column.
            Label columnTitle = new Label();
            columnTitle.setText(column.getTitle());
            columnVBox.getChildren().add(columnTitle);

            // Todo add task HBoxes to column, use inner for-loop.
        }
    }

    @FXML
    void returnToDashboard(ActionEvent event) throws IOException {
        App.setRoot("dashboard");
    }
}
