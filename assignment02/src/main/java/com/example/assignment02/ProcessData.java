/**
 * Part 1: Program will read in the csv file data and add a column that represents
 * the total number of incidents between 1985 and 2014 (using the incident columns).
 * The data will then use the CSVtoXML class to write the newly generated csv file
 * to an xml file.
 */
package com.example.assignment02;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ProcessData {

    public static void main(String[] args) throws IOException, ParserConfigurationException, TransformerException {

        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\olivi\\OneDrive\\Desktop\\csci2020u_olivia\\assignment02\\src\\main\\resources\\com\\example\\assignment02\\airline_safety.csv"));
        CSVParser parser = CSVFormat.DEFAULT.withDelimiter(',').withHeader().parse(br);

        ArrayList<Integer> numbers = new ArrayList<>();

        // Parse CSV using Apache and find the sum of specified column elements ('incident' columns)
        for (CSVRecord record : parser) {
            numbers.add(Integer.parseInt(record.get("incidents_85_99")) + Integer.parseInt(record.get("incidents_00_14")));
        }

        // Adding an element to the array list to simply avoid an indexing error
        numbers.add(0,666);

        // Converting the [][] numbers array to an array of integers
        Integer temp[] = new Integer[numbers.size()];
        for (int i = 0; i < numbers.size(); i++) {
            temp[i] = numbers.get(i);
        }

        // Converting the array of integers to an array of strings
        String[] strCol = new String[temp.length];
        for (int i = 0; i < temp.length; i++) {
            strCol[i] = String.valueOf(temp[i]);
        }

        /*
        Now replacing the previously added element of type int (666) to a string;
        '666' was a temporary placeholder to lengthen the arraylist
        The index [0] will be used as our header string when I add the list as a column to the CSV
         */
        strCol[0] = "incidents_85_14";
        System.out.println(Arrays.toString(strCol)); // New array to be added as sum column in CSV

        // Initialize line number for the string array to be added to
        int numLines = 0;
        String line = "";

        // Write original csv data to new csv file
        BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\olivi\\OneDrive\\Desktop\\csci2020u_olivia\\assignment02\\src\\main\\resources\\com\\example\\assignment02\\temp.csv"));

        // Read from the original file
        BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\olivi\\OneDrive\\Desktop\\csci2020u_olivia\\assignment02\\src\\main\\resources\\com\\example\\assignment02\\airline_safety.csv"));

        // Write lines of original csv file then add the string array to each line
        while ((line = reader.readLine()) != null) {
            String newCSVLines = line + "," + strCol[numLines++];
            writer.write(newCSVLines);
            writer.newLine();
        }
        writer.close();

        // Call method to convert temp.csv into an XML file
        CSVtoXML createIt = new CSVtoXML();
        createIt.convertCSV("C:\\Users\\olivi\\OneDrive\\Desktop\\csci2020u_olivia\\assignment02\\src\\main\\resources\\com\\example\\assignment02\\temp.csv","C:\\Users\\olivi\\OneDrive\\Desktop\\csci2020u_olivia\\assignment02\\src\\main\\resources\\com\\example\\assignment02\\converted_airline_safety.xml");
    }
}



