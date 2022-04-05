module com.example.assignment02 {
    requires javafx.controls;
    requires javafx.fxml;
    requires opencsv;
    requires java.xml;
    requires commons.csv;


    opens com.example.assignment02 to javafx.fxml;
    exports com.example.assignment02;
}