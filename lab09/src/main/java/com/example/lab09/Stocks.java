package com.example.lab09;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Stocks extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException, ParseException {
        final String symbol = "GOOG";
        String interval = "1mo";

        // period 1
        Calendar calender1 = Calendar.getInstance();
        calender1.set(Calendar.YEAR, 2010);
        calender1.set(Calendar.MONTH, Calendar.JANUARY);
        calender1.set(Calendar.DAY_OF_MONTH, 1);

        // period 2
        Calendar calender2 = Calendar.getInstance();
        calender2.set(Calendar.YEAR, 2015);
        calender2.set(Calendar.MONTH, Calendar.DECEMBER);
        calender2.set(Calendar.DAY_OF_MONTH, 31);

        // call method to get date
        Date period1 = setDate(calender1);
        Date period2 = setDate(calender2);

        long date1 = (period1.getTime());
        date1 = date1 / 1000;

        long date2 = (period2.getTime());
        date2 = date2 / 1000;

        String inputURL = "https://query1.finance.yahoo.com/v7/finance/download/" + symbol + "?period1=" + date1 + "&period2=" + date2 + "&interval=" + interval + "&events=history";
        URL url = new URL(inputURL);
        URLConnection conn = url.openConnection();
        InputStreamReader stream = new InputStreamReader(conn.getInputStream());
        BufferedReader br = new BufferedReader(stream);

        // loading the data into a 2D array
        String line;
        List<List<String>> result = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            System.out.println(line);
            String[] values = line.split(",");
            result.add(Arrays.asList(values));
        }

        // parsing the 2D array list to get the index of 'Close' values to add to array
        ArrayList<String> numbers = new ArrayList<>();
        for (int i = 1; i < result.size(); i++) {
            numbers.add(result.get(i).get(4));
        }
        //System.out.println(numbers.get(1));

        // adding the String array of 'Close' column values to a Float array
        ArrayList<Float> numbers2 = new ArrayList<>();
        for (int i = 1; i < numbers.size(); i++) {
            numbers2.add(Float.parseFloat(numbers.get(i)));
        }

        // setting up line chart
        ObservableList<XYChart.Data<Number, Number>> list = FXCollections.observableArrayList();
        LineChart<Number, Number> lineChart = new LineChart<>(new NumberAxis(), new NumberAxis());

        // add array values into list
        for (float values: numbers2) {
            list.add(new XYChart.Data<>(values, values));
        }

        // make list a series
        XYChart.Series<Number, Number> series = new XYChart.Series<>(list);
        series.setName("GOOG");

        lineChart.getData().add(series);

        VBox vbox = new VBox(lineChart);
        Scene scene = new Scene(vbox, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setHeight(500);
        primaryStage.setWidth(1000);
        primaryStage.setTitle("Lab 9");
        primaryStage.show();
    }

    public static Date setDate(Calendar calender) throws ParseException {
        Date date = calender.getTime();

        String str = date.toString();
        String arr[] = str.split(" ");
        String cat = arr[1] + " " + arr[2] + " " + arr[5];

        SimpleDateFormat formatDate = new SimpleDateFormat("MMM dd yyyy", Locale.ENGLISH);

        formatDate.setTimeZone(TimeZone.getTimeZone("GMT"));
        calender.setTime(formatDate.parse(cat));
        date = calender.getTime();
        formatDate.format(date);

        return date;
    }
}
