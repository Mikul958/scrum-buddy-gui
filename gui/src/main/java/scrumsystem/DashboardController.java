package scrumsystem;

import java.util.ArrayList;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.fxml.Initializable;
import model.*;

public class DashboardController implements Initializable
{   
    @FXML
    VBox ownedProjectsVBox, sharedProjectsVBox;

    @FXML
    private Label dashboardHeader;

    ScrumSystem system = ScrumSystem.getInstance();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1)
    {
        ArrayList<Project> accountProjects = system.getCurrentAccountProjects();
        ArrayList<Project> ownedProjects = new ArrayList<Project>();
        ArrayList<Project> sharedProjects = new ArrayList<Project>();

        // Populate owned list and shared with me list.
        for (int i=0; i<accountProjects.size(); i++)
        {
            Project currentProject = accountProjects.get(i);

            if (currentProject.getOwner().equals(system.getCurrentAccount()))
                ownedProjects.add(currentProject);
            else
                sharedProjects.add(currentProject);
        }

        dashboardHeader.setText("Welcome, " + system.getCurrentAccount().getFirstName() + "!");
        buildProjectVBox(ownedProjects, ownedProjectsVBox);
        buildProjectVBox(sharedProjects, sharedProjectsVBox);
    }

    private void buildProjectVBox(ArrayList<Project> projectList, VBox targetVBox)
    {
        for (int i=0; i<projectList.size(); i++)
        {
            Project project = projectList.get(i);
            HBox projectBox = new HBox();
            targetVBox.getChildren().add(projectBox);

            Label projectTitle = new Label();
            projectTitle.getStyleClass().add("projectTitle"); //here
            projectTitle.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event)
                {
                    system.openProject(project);
                    try {
                        App.setRoot("project");
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            projectTitle.setText(project.getTitle());
            projectBox.getChildren().add(projectTitle);
        }
    }

    @FXML
    private void createProject() throws IOException
    {
        App.setRoot("createproject");
    }

    @FXML
    private void logout() throws IOException
    {
        ScrumSystem.getInstance().saveData();
        ScrumSystem.getInstance().logout();
        App.setRoot("primary");
    }
}
