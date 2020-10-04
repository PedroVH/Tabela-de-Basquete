module org.pedrovh {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.desktop;

    opens org.pedrovh.gui to javafx.fxml;
    exports org.pedrovh;
    exports org.pedrovh.app;
}