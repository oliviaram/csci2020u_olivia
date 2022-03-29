module com.example.lab08 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires java.desktop;


    opens com.example.lab08 to javafx.fxml;
    exports com.example.lab08;
}