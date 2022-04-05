/**
 * Part 3: Creates a bar chart that displays two bars (i.e., two series') for each airline.
 * The first series displays the sum of fatal incidents/fatalities (col[3], col[4]) from 1985-1999;
 * the second series is for the sum of fatal incidents/fatalities (col[6], col[7]) from 2000-2014.
 */
package com.example.assignment02;

import com.opencsv.CSVReader;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import com.opencsv.CSVReaderBuilder;
import java.io.FileReader;

public class PlotApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        final NumberAxis yAxis = new NumberAxis(0, 600, 1);
        final CategoryAxis xAxis = new CategoryAxis();
        final BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);

        yAxis.setLabel("Number of Fatal Incidents");
        xAxis.setLabel("Airline");
        barChart.setTitle("Fatal Incidents by Airline");
        barChart.setCategoryGap(0);
        barChart.setBarGap(2);

        XYChart.Series series1 = new XYChart.Series();
        XYChart.Series series2 = new XYChart.Series();

        series1.setName("1985-1999");
        series2.setName("2000-2014");

        CSVReader data = new CSVReaderBuilder(new FileReader("C:\\Users\\olivi\\OneDrive\\Desktop\\csci2020u_" +
                "olivia\\assignment02\\src\\main\\resources\\com\\example\\assignment02\\airline_safety.csv")).withSkipLines(1).build();
        String[] nextLine;
        while ((nextLine = data.readNext()) != null) {
            // Extracting the first column data "airline"
            String airline = nextLine[0];

            // Compute the sum of columns "fatal_accidents_85_99" and "fatalities_85_99"
            int fatal90s = (Integer.parseInt(nextLine[3]) + Integer.parseInt(nextLine[4]));
            series1.getData().add(new XYChart.Data(airline, fatal90s));

            // Compute the sum of columns "fatal_accidents_00_14" and "fatalities_00_14"
            int fatalY2K = (Integer.parseInt(nextLine[6]) + Integer.parseInt(nextLine[7]));
            series2.getData().add(new XYChart.Data(airline, fatalY2K));
        }

        barChart.getData().addAll(series1, series2);
        Scene scene = new Scene(barChart, 1200, 700);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Part 3");
        primaryStage.show();
    }
}
