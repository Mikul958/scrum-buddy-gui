module scrumsystem {
    requires javafx.controls;
    requires javafx.fxml;

    opens scrumsystem to javafx.fxml;
    exports scrumsystem;
}
