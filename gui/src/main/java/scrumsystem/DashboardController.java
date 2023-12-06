package scrumsystem;

import java.util.ArrayList;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.fxml.Initializable;
import model.*;

public class DashboardController implements Initializable
{   
    @FXML
    VBox projectVBox;

    @FXML
    private Label dashboardHeader;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1)
    {
        ScrumSystem system = ScrumSystem.getInstance();
        ArrayList<Project> currentProjects = system.getCurrentAccountProjects();
        dashboardHeader.setText("Welcome, " + system.getCurrentAccount().getFirstName() + "!");
        for (int i=0; i<currentProjects.size(); i++)
        {
            Project project = currentProjects.get(i);
            HBox projectBox = new HBox();
            projectVBox.getChildren().add(projectBox);

            Label label = new Label();
            label.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event)
                {
                    system.openProject(project);
                    System.out.println("Opened " + system.getCurrentProject().getTitle());  // TODO App.setRoot
                }
            });
            label.setText(currentProjects.get(i).getTitle());
            projectBox.getChildren().add(label);
        }
    }
    
    @FXML
    private void logout() throws IOException
    {
        ScrumSystem.getInstance().saveData();
        ScrumSystem.getInstance().logout();
        App.setRoot("primary");
    }
}
