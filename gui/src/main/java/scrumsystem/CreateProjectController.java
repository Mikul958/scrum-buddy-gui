package scrumsystem;

import java.util.ArrayList;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.fxml.Initializable;

import model.*;

public class CreateProjectController implements Initializable
{
    @FXML
    private TextField titleBox;

    @FXML
    private ChoiceBox<Category> categoryBox;
    
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
}
