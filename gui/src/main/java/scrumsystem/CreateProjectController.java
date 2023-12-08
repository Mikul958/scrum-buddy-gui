package scrumsystem;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.fxml.Initializable;

import model.*;

public class CreateProjectController implements Initializable
{
    @FXML
    private TextField titleBox;

    @FXML
    private ChoiceBox<Category> categoryBox;

    @FXML
    private Label errorBox;
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1)
    {
        // Populate ChoiceBox with options.
        for (Category c : Category.values())
            categoryBox.getItems().add(c);
    }
    
    @FXML
    private void backToDashboard() throws IOException
    {
        App.setRoot("dashboard");
    }

    @FXML
    private void createProject() throws IOException
    {
        String title = titleBox.getText();
        Category category = (Category)categoryBox.getValue();

        if (title == null || title.equals(""))
        {
            errorBox.setText("*Please enter a title");
            errorBox.setVisible(true);
            return;
        }
        if (category == null)
        {
            errorBox.setText("*Please select a category");
            errorBox.setVisible(true);
            return;
        }

        ScrumSystem.getInstance().createProject(title, category);
        App.setRoot("dashboard");
    }
}
