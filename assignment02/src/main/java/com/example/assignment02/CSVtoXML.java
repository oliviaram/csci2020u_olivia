/**
 * Part 1: Helper class to generate xml file for ProcessData.java.
 * Program takes the csv file and xml file as its parameters, then
 * uses DocumentBuilder to build an xml file using the csv file data.
 */
package com.example.assignment02;

import com.opencsv.CSVReader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CSVtoXML {

    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();

    public CSVtoXML() throws ParserConfigurationException {}

    public int convertCSV(String csvFile, String xmlFile) throws TransformerException, IOException {
        int rowCount = -1;

        Document document = builder.newDocument();
        // Set root element
        Element rootElement = document.createElement("airline_safety_data");
        document.appendChild(rootElement);

        // Using the temp.csv file to be converted
        CSVReader reader = new CSVReader(new FileReader("C:\\Users\\olivi\\OneDrive\\Desktop\\csci2020u_olivia\\assignment02\\src\\main\\resources\\com\\example\\assignment02\\temp.csv"));
        String[] nextLine;
        int line = 0;
        List<String> headerName = new ArrayList<>();

        while ((nextLine = reader.readNext()) != null) {
            // Add header rows
            if (line == 0) {
                for (String column : nextLine) {
                    headerName.add(column);
                }
            }
            // Add data rows
            else {
                Element row = document.createElement("row");
                rootElement.appendChild(row);
                int column = 0;
                for (String val : nextLine) {
                    String header = headerName.get(column);

                    Element curr = document.createElement(header);
                    curr.appendChild(document.createTextNode(val.trim()));
                    row.appendChild(curr);

                    column++;
                }
            }
            line++;
        }

        FileWriter writer = new FileWriter(xmlFile);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        // Print in pretty (indent)
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

        Source source = new DOMSource(document);
        Result result = new StreamResult(writer);
        transformer.transform(source, result);

        writer.flush();
        return rowCount;
    }
}
