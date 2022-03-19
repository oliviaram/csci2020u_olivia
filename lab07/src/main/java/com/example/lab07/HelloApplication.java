package com.example.lab07;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        // parsing the csv file to count the cases that match the string type in column 6
        try {
            Reader csvData = new FileReader("C:\\Users\\olivi\\OneDrive\\Desktop\\csci2020u_olivia\\lab07\\src\\main\\java\\com\\example\\lab07\\weatherwarnings-2015.csv");
            CSVParser parser = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(csvData);

            int count = 0;
            int count2 = 0;
            int count3 = 0;
            int count4 = 0;

            for (CSVRecord csvRecord : parser) {
                if(csvRecord.get(5).equalsIgnoreCase("FlASH FLOOD")){
                    count++;
                }
                else if(csvRecord.get(5).equalsIgnoreCase("SEVERE THUNDERSTORM")){
                    count2++;
                }
                else if(csvRecord.get(5).equalsIgnoreCase("SPECIAL MARINE")){
                    count3++;
                }
                else if(csvRecord.get(5).equalsIgnoreCase("TORNADO")){
                    count4++;
                }
            }
            System.out.println("[Warning Type Count]");
            System.out.println("Flash flood: " + count + "\nSevere Thunderstorm: " + count2 +
                    "\nSpecial Marine: " + count3 + "\nSevere Thunderstorm: " + count4);

            PieChart applePie = new PieChart();
            PieChart.Data slice1 = new PieChart.Data("FLASH FLOOD", count);
            PieChart.Data slice2 = new PieChart.Data("SEVERE THUNDERSTORM", count2);
            PieChart.Data slice3 = new PieChart.Data("SPECIAL MARINE", count3);
            PieChart.Data slice4 = new PieChart.Data("TORNADO", count4);
            applePie.getData().addAll(slice1, slice2, slice3, slice4);

            VBox vbox = new VBox(applePie);
            Scene scene = new Scene(vbox, 400, 350);

            stage.setTitle("Lab 07");
            stage.setScene(scene);
            stage.setHeight(400);
            stage.setWidth(700);
            stage.show();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) { launch(); }
}

// csv_objectList.forEach((n) -> System.out.println(n));