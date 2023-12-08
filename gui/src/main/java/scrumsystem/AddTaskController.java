package scrumsystem;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import model.*;

public class AddTaskController implements Initializable
{
    @FXML
    private Label headerBox, errorBox;

    @FXML
    private TextField nameBox, priorityBox;

    ScrumSystem system = ScrumSystem.getInstance();
    private Column currentCol = ProjectController.selectedColumn;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1)
    {
        headerBox.setText("Add Task to " + currentCol.getTitle());
    }
    
    @FXML
    private void backToProject() throws IOException
    {
        App.setRoot("project");
    }

    @FXML
    private void addTask() throws IOException
    {
        String name = nameBox.getText();
        String priorityString = priorityBox.getText();
        int priority = 0;

        // Check entered name and priority were entered.
        if (name == null || name.equals(""))
        {
            errorBox.setText("*Please enter a title");
            errorBox.setVisible(true);
            return;
        }
        if (priorityString == null || priorityString.equals(""))
        {
            errorBox.setText("*Please enter a priority");
            errorBox.setVisible(true);
            return;
        }

        // If priorityString is a number, parse it, else set priority to 0.
        try
        {
            priority = Integer.parseInt(priorityString);
        }
        catch (Exception e)
        {
            priority = 0;
        }

        if (priority < 1 || priority > 5)
        {
            errorBox.setText("*Priority must be a number 1-5");
            errorBox.setVisible(true);
            return;
        }

        ScrumSystem.getInstance().addProjectTask(currentCol, name, priority);
        App.setRoot("project");
    }
}
