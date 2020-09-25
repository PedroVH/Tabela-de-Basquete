module com.pedrovh {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.pedrovh to javafx.fxml;
    exports org.pedrovh;
}