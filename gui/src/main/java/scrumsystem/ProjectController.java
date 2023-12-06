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
import model.Column;
import model.Project;
import model.ScrumSystem;

public class ProjectController implements Initializable
{
    @FXML
    private Label totalTasks;
    @FXML
    private Label projectName;
    @FXML
    VBox columnVBox;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        ScrumSystem system = ScrumSystem.getInstance();
        totalTasks.setText("Number of tasks: " + system.getCurrentProject().getTotalTasks());
        projectName.setText("Project: " + system.getCurrentProject().getTitle());

        for(int i = 0; i < system.getCurrentProject().getColumns().size(); i++){
            Column column = system.getCurrentProject().getColumns().get(i);
            HBox columnHBox = new HBox();
            columnVBox.getChildren().add(columnHBox);

            Label label = new Label();

            label.setOnMouseClicked(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    //TODO
                }
                
            });
            label.setText(system.getCurrentProject().getColumns().get(i).getTitle());
            columnHBox.getChildren().add(label);
        }

    }

    @FXML
    void returnToDashboard(ActionEvent event) throws IOException {
        App.setRoot("dashboard");
    }
}
