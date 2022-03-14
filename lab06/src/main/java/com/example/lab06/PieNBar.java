package com.example.lab06;

import javafx.application.Application;
import javafx.scene.chart.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.canvas.GraphicsContext;

public class PieNBar extends Application {
    // bar chart data
    private static double[] avgHousingPricesByYear = {
            247381.0,264171.4,287715.3,294736.1,
            308431.4,322635.9,340253.0,363153.7
    };
    private static double[] avgCommercialPricesByYear = {
            1121585.3,1219479.5,1246354.2,1295364.8,
            1335932.6,1472362.0,1583521.9,1613246.3
    };
    // pie chart data
    private static String[] ageGroups = {
            "18-25", "26-35", "36-45", "46-55", "56-65", "65+"
    };
    private static int[] purchasesByAgeGroup = {
            648, 1021, 2453, 3173, 1868, 2247
    };
    //private static Color[] pieColours = {
     //       Color.AQUA, Color.GOLD, Color.DARKORANGE,
     //       Color.DARKSALMON, Color.LAWNGREEN, Color.PLUM
    //};

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Lab06");

        CategoryAxis xaxis = new CategoryAxis();
        NumberAxis yaxis = new NumberAxis();

        BarChart barChart = new BarChart(xaxis, yaxis);

        XYChart.Series series1 = new XYChart.Series();
        series1.getData().add(new XYChart.Data("1", avgHousingPricesByYear[0]));
        series1.getData().add(new XYChart.Data("2", avgHousingPricesByYear[1]));
        series1.getData().add(new XYChart.Data("3", avgHousingPricesByYear[2]));
        series1.getData().add(new XYChart.Data("4", avgHousingPricesByYear[3]));
        series1.getData().add(new XYChart.Data("5", avgHousingPricesByYear[4]));
        series1.getData().add(new XYChart.Data("6", avgHousingPricesByYear[5]));
        series1.getData().add(new XYChart.Data("7", avgHousingPricesByYear[6]));
        series1.getData().add(new XYChart.Data("8", avgHousingPricesByYear[7]));
        barChart.getData().add(series1);

        XYChart.Series series2 = new XYChart.Series();
        series2.getData().add(new XYChart.Data("1", avgCommercialPricesByYear[0]));
        series2.getData().add(new XYChart.Data("2", avgCommercialPricesByYear[1]));
        series2.getData().add(new XYChart.Data("3", avgCommercialPricesByYear[2]));
        series2.getData().add(new XYChart.Data("4", avgCommercialPricesByYear[3]));
        series2.getData().add(new XYChart.Data("5", avgCommercialPricesByYear[4]));
        series2.getData().add(new XYChart.Data("6", avgCommercialPricesByYear[5]));
        series2.getData().add(new XYChart.Data("7", avgCommercialPricesByYear[6]));
        series2.getData().add(new XYChart.Data("8", avgCommercialPricesByYear[7]));
        barChart.getData().add(series2);

        // -------------------PIE-----------------------------------------------------------
        PieChart pecanPie = new PieChart();

        PieChart.Data slice1 = new PieChart.Data(ageGroups[0], purchasesByAgeGroup[0]);
        PieChart.Data slice2 = new PieChart.Data(ageGroups[1], purchasesByAgeGroup[1]);
        PieChart.Data slice3 = new PieChart.Data(ageGroups[2], purchasesByAgeGroup[2]);
        PieChart.Data slice4 = new PieChart.Data(ageGroups[3], purchasesByAgeGroup[3]);
        PieChart.Data slice5 = new PieChart.Data(ageGroups[4], purchasesByAgeGroup[4]);
        PieChart.Data slice6 = new PieChart.Data(ageGroups[5], purchasesByAgeGroup[5]);

        pecanPie.getData().add(slice1);
        pecanPie.getData().add(slice2);
        pecanPie.getData().add(slice3);
        pecanPie.getData().add(slice4);
        pecanPie.getData().add(slice5);
        pecanPie.getData().add(slice6);

        VBox vbox = new VBox(barChart, pecanPie);
        Scene scene = new Scene(vbox, 400, 350);
        primaryStage.setScene(scene);
        primaryStage.setHeight(700);
        primaryStage.setWidth(800);
        primaryStage.show();
    }
}
