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
    private Label projectTitle, totalTasks, columnStatus;

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
        columnStatus.setText("No column selected");

        for(int i = 0; i < currentProject.getColumns().size(); i++)
        {
            // Create VBox for current column and add it to outer HBox
            Column column = currentProject.getColumns().get(i);
            VBox columnVBox = new VBox();
            columnVBox.setPrefWidth(200);
            columnsHBox.getChildren().add(columnVBox);

            // Column Title VBox
            Label columnTitle = new Label();
            columnTitle.setPrefWidth(400);
            columnTitle.getStyleClass().add("columnTitle");             // Changes Title text to black.
            // columnTitle.getStyleClass().add("columnTitleBackground");   // Changes background behind title text to white
            columnTitle.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event)
                {
                    // Select a column to edit or add to and update status.
                    selectedColumn = column;
                    columnStatus.setText("Column \"" + selectedColumn.getTitle() + "\" selected");
                }
            });
            columnTitle.setText(column.getTitle());
            columnVBox.getChildren().add(columnTitle);

            // Tasks VBox
            for (int j=0; j<column.getTasks().size(); j++)
            {
                // Create HBox for current Task and add it to Column's VBox
                Task task = column.getTasks().get(j);
                HBox taskHBox = new HBox();
                taskHBox.getStyleClass().add("taskListBackground"); 
                columnVBox.getChildren().add(taskHBox);

                // Add title to task box.
                Label taskName = new Label();
                taskName.setWrapText(true); //here
                taskName.getStyleClass().add("taskTitle");
                taskName.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event)
                    {
                        // TODO add code to change screen and view a task
                        selectedColumn = column;
                        selectedTask = task;
                        System.out.println("\nSELECTED TASK:\n" + selectedTask.getName() + "\nIN COLUMN:\n" + selectedColumn.getTitle());
                        try {
                            App.setRoot("viewtask");
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                taskName.setText(task.getName());
                taskHBox.getChildren().add(taskName);
            }
        }
    }

    @FXML
    void addColumn(ActionEvent event) throws IOException
    {
        // TODO switch to add column screen
        System.out.println("Clicked add column");
        App.setRoot("addcolumn");
    }

    @FXML
    void addTaskToColumn(ActionEvent event) throws IOException
    {
        // TODO switch to add task screen (using selected column).
        System.out.println("Clicked add task");
        if (selectedColumn == null)
        {
            columnStatus.setText("Please select a column first");
            return;
        }
        App.setRoot("addtask");
    }

    @FXML
    void returnToDashboard(ActionEvent event) throws IOException {
        App.setRoot("dashboard");
    }
}
