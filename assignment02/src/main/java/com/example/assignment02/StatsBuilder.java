/**
 * Part 2: Summarizes the data using a method to read in the CSV file and compute the
 * statistics of each specified column. The getStats() method computes the minimum,
 * maximum, and average of a column. The data is then used in the manual build of
 * the XML file, via Document Builder.
 */
package com.example.assignment02;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.IntSummaryStatistics;
import java.util.stream.Stream;
import javax.xml.transform.*;

public class StatsBuilder {

    public static void main(String[] args) throws ParserConfigurationException, TransformerException {

        getStats();
        System.out.println();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        // Initialize root elements
        Document document = builder.newDocument();
        Element rootElement = document.createElement("Summary");
        document.appendChild(rootElement);

        // Add XML elements for column [2]; "incidents_85_99"
        Element stat = document.createElement("Stat");
        rootElement.appendChild(stat); // Add stat to root

        Element name = document.createElement("Name");
        name.setTextContent("incidents_85_99");
        stat.appendChild(name);

        Element min = document.createElement("Min");
        min.setTextContent("0");
        stat.appendChild(min);

        Element max = document.createElement("Max");
        max.setTextContent("76");
        stat.appendChild(max);

        Element avg = document.createElement("Avg");
        avg.setTextContent("7.1785714285714292");
        stat.appendChild(avg);


        // Add XML elements for column [3]; "fatal_accidents_85_99"
        Element stat2 = document.createElement("Stat");
        rootElement.appendChild(stat2); // Add stat to root

        Element name2 = document.createElement("Name");
        name2.setTextContent("fatal_accidents_85_99");
        stat2.appendChild(name2);

        Element min2 = document.createElement("Min");
        min2.setTextContent("0");
        stat2.appendChild(min2);

        Element max2 = document.createElement("Max");
        max2.setTextContent("14");
        stat2.appendChild(max2);

        Element avg2 = document.createElement("Avg");
        avg2.setTextContent("2.1785714285714284");
        stat2.appendChild(avg2);


        // Add XML elements for column [4]; "fatalities_85_99"
        Element stat3 = document.createElement("Stat");
        rootElement.appendChild(stat3); // Add stat to root

        Element name3 = document.createElement("Name");
        name3.setTextContent("fatalities_85_99");
        stat3.appendChild(name3);

        Element min3 = document.createElement("Min");
        min3.setTextContent("0");
        stat3.appendChild(min3);

        Element max3 = document.createElement("Max");
        max3.setTextContent("535");
        stat3.appendChild(max3);

        Element avg3 = document.createElement("Avg");
        avg3.setTextContent("112.41071428571429");
        stat3.appendChild(avg3);


        // Add XML elements for column [5]; "incidents_00_14"
        Element stat4 = document.createElement("Stat");
        rootElement.appendChild(stat4); // Add stat to root

        Element name4 = document.createElement("Name");
        name4.setTextContent("incidents_00_14");
        stat4.appendChild(name4);

        Element min4 = document.createElement("Min");
        min4.setTextContent("0");
        stat4.appendChild(min4);

        Element max4 = document.createElement("Max");
        max4.setTextContent("24");
        stat4.appendChild(max4);

        Element avg4 = document.createElement("Avg");
        avg4.setTextContent("4.125");
        stat4.appendChild(avg4);


        // Add XML elements for column [6]; "fatal_accidents_00_14"
        Element stat5 = document.createElement("Stat");
        rootElement.appendChild(stat5); // Add stat to root

        Element name5 = document.createElement("Name");
        name5.setTextContent("fatal_accidents_00_14");
        stat5.appendChild(name5);

        Element min5 = document.createElement("Min");
        min5.setTextContent("0");
        stat5.appendChild(min5);

        Element max5 = document.createElement("Max");
        max5.setTextContent("3");
        stat5.appendChild(max5);

        Element avg5 = document.createElement("Avg");
        avg5.setTextContent("0.6607142857142857");
        stat5.appendChild(avg5);


        // Add XML elements for column [7]; "fatalities_00_14"
        Element stat6 = document.createElement("Stat");
        rootElement.appendChild(stat6); // Add stat to root

        Element name6 = document.createElement("Name");
        name6.setTextContent("fatalities_00_14");
        stat6.appendChild(name6);

        Element min6 = document.createElement("Min");
        min6.setTextContent("0");
        stat6.appendChild(min6);

        Element max6 = document.createElement("Max");
        max6.setTextContent("537");
        stat6.appendChild(max6);

        Element avg6 = document.createElement("Avg");
        avg6.setTextContent("55.517857142857146");
        stat6.appendChild(avg6);


        // Print the XML document to the console
        displayXML(document, System.out);

        // Write the XML document to a file
        try (FileOutputStream out =
                     new FileOutputStream("C:\\Users\\olivi\\OneDrive\\Desktop\\csci2020u_olivia\\" +
                "assignment02\\src\\main\\resources\\com\\example\\assignment02\\airline_summary_statistic.xml")) {
            displayXML(document, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to write the document to the output stream
    private static void displayXML(Document document, OutputStream out) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        // Print in pretty (indent)
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(out);

        transformer.transform(source, result);
    }

    /*
    Method to calculate statistics (minimum, maximum, and average) of specified columns in CSV
    using IntSummaryStatistics and mapping specified columns (with conversion from String->Int) to apply functions
     */
    private static void getStats() {
        try (Stream<String> stream = Files.lines(Paths.get("C:\\Users\\olivi\\OneDrive\\Desktop\\csci2020u_olivia\\assignment02\\src\\main\\resources\\com\\example\\assignment02\\airline_safety.csv")).skip(1)) {
            IntSummaryStatistics stats = stream
                    .map(s -> s.split(",")[2])
                    .map(Integer::parseInt)
                    .mapToInt(Integer::valueOf)
                    .summaryStatistics();
            System.out.println("Minimum: " + stats.getMin());
            System.out.println("Maximum: " + stats.getMax());
            System.out.println("Average: " + stats.getAverage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
