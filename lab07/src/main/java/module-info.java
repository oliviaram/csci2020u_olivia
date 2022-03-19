module com.example.lab07 {
    requires javafx.controls;
    requires javafx.fxml;
    requires opencsv;
    requires commons.csv;


    opens com.example.lab07 to javafx.fxml;
    exports com.example.lab07;
}