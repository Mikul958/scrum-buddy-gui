package scrumsystem;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.ScrumSystem;

public class AddColumnController
{
    @FXML
    private TextField titleBox;
    
    @FXML
    private Label errorBox;

    @FXML
    void backToProject(ActionEvent event) throws IOException
    {
        App.setRoot("project");
    }

    @FXML
    private void addColumn() throws IOException
    {
        String columnTitle = titleBox.getText();
        
        if (columnTitle == null || columnTitle.equals(""))
        {
            errorBox.setText("*Please enter a column title");
            errorBox.setVisible(true);
            return;
        }
        ScrumSystem.getInstance().addProjectColumn(columnTitle);
        App.setRoot("project");
    }
}
