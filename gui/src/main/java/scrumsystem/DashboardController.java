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
    
    ScrumSystem system = ScrumSystem.getInstance();
    ArrayList<Project> currentProjects = system.getCurrentAccountProjects();

    @FXML
    private Label lbl_username;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        ScrumSystem system = ScrumSystem.getInstance();
        lbl_username.setText("Dashboard");
        for (int i=0; i<currentProjects.size(); i++)
        {
            HBox project = new HBox();
            projectVBox.getChildren().add(project);

            Label label = new Label();
            label.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    System.out.println(label.getText() + " is clicked");
                }
                
            });
            label.setText(currentProjects.get(i).getTitle());
            project.getChildren().add(label);
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
