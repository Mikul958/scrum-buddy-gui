module scrumsystem {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;

    opens scrumsystem to javafx.fxml;
    exports scrumsystem;

    opens model to javafx.xml;
    exports model;
}
