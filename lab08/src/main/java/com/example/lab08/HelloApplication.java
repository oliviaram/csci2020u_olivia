package com.example.lab08;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import java.awt.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HelloApplication extends Application {
    Stage window;
    TableView<StudentRecord> table;
    TextField sidField, assiField, midField, examField;
    String csvFile = "C:\\Users\\olivi\\OneDrive\\Desktop\\csci2020u_olivia\\lab08\\src\\main\\java\\com\\example\\lab08\\Person.csv";
    private Desktop desktop = Desktop.getDesktop();
    private final ObservableList<StudentRecord> data = FXCollections.observableArrayList();

    public void start(Stage primaryStage) throws IOException {
        // creating a menu with the following menu items
        Menu fileMenu = new Menu("File");
        MenuItem newItem = new MenuItem("New");
        MenuItem openItem = new MenuItem("Open");
        MenuItem saveItem = new MenuItem("Save");
        MenuItem saveAsItem = new MenuItem("Save As");
        MenuItem exitItem = new MenuItem("Get out. Leave. Right now");
        fileMenu.getItems().addAll(newItem, openItem, saveItem, saveAsItem, exitItem);

        // creating file chooser
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("All Files", "*.*"));
        System.out.println(fileChooser.getInitialFileName());


        // [event handlers] -------------------------------------------------------------------

        // clear table data
        newItem.setOnAction(event -> table.getItems().clear());

        // open selected file
        openItem.setOnAction(event -> {
            File file = fileChooser.showOpenDialog(primaryStage);
            if (file != null) {
                openFile(file);
            }
        });

        // writes data to CSV file
        saveItem.setOnAction(event -> {
            Writer writer = null;
            try {
                File file = new File(csvFile);
                writer = new BufferedWriter(new FileWriter(file));
                for(StudentRecord person : getMarks()) {
                    String text = person.getStudentID() + "," + person.getAssignments() + ","
                            + person.getMidterm() + "," + person.getFinalExam() + "," + "\n";
                    writer.write(text);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            finally { // intelliji prompted the swarm of try-catch blocks
                try {
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        // save csv file data to table
        saveAsItem.setOnAction(event -> {
            String delimiter = ",";
            BufferedReader br;
            try {
                br = new BufferedReader(new FileReader(csvFile));
                String line;
                while((line = br.readLine()) != null) {
                    String[] cols = line.split(delimiter);
                    StudentRecord record = new StudentRecord(cols[0], Float.parseFloat(cols[1]),
                            Float.parseFloat(cols[2]), Float.parseFloat(cols[3]));
                    data.add(record);
                    table.setItems(data);
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(HelloApplication.class.getName())
                        .log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(HelloApplication.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        });

        // system exit
        exitItem.setOnAction(event -> System.exit(0));

        // ----------------------------------------------------------------------------------------------

        // adding fileMenu to class instance
        MenuBar menuBar = new MenuBar(fileMenu);
        menuBar.setTranslateX(3);
        menuBar.setTranslateY(3);
        Group root = new Group(menuBar);

        // table stuff---------------------------------------------------------------------------------------
        window = primaryStage;
        window.setTitle("Lab 08 Solution");

        // set up columns --------------------------------------------------------
        TableColumn<StudentRecord, String> idCol = new TableColumn<>("SID");
        idCol.setMinWidth(200);
        // use the studentID property of our objects to get all the values of studentID
        idCol.setCellValueFactory(new PropertyValueFactory<>("studentID"));

        TableColumn<StudentRecord, Float> assiCol = new TableColumn<>("Assignments");
        assiCol.setMinWidth(200);
        assiCol.setCellValueFactory(new PropertyValueFactory<>("assignments"));

        TableColumn<StudentRecord, Float> midCol = new TableColumn<>("Midterm");
        midCol.setMinWidth(200);
        midCol.setCellValueFactory(new PropertyValueFactory<>("midterm"));

        TableColumn<StudentRecord, Float> examCol = new TableColumn<>("Final Exam");
        examCol.setMinWidth(200);
        examCol.setCellValueFactory(new PropertyValueFactory<>("finalExam"));

        // buttons ---------------------------------------------------------------
        sidField = new TextField();
        sidField.setPromptText("SID:");
        sidField.setMinWidth(50);

        assiField = new TextField();
        assiField.setPromptText("Assignments:");

        midField = new TextField();
        midField.setPromptText("Midterm:");

        examField = new TextField();
        examField.setPromptText("Final Exam:");

        Button button = new Button("Add");
        button.setOnAction(event -> addButtonClick());

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(10, 10, 10, 10));
        hbox.setSpacing(10);
        hbox.getChildren().addAll(sidField, assiField, midField, examField, button);

        table = new TableView<>();
        table.setItems(getMarks());
        table.getColumns().addAll(idCol, assiCol, midCol, examCol);

        VBox v = new VBox();
        v.getChildren().addAll(table, hbox, root); // table, field boxes, menu bar

        Scene scene = new Scene(v);
        window.setScene(scene);
        window.show();
    }

    // method called to open and display the selected file
    private void openFile(File file) {
        try {
            desktop.open(file);
        } catch (IOException e) {
            Logger.getLogger(HelloApplication.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    // Clicking add button
    public void addButtonClick() {
        StudentRecord student = new StudentRecord();
        student.setStudentID(sidField.getText());
        student.setAssignments(Float.parseFloat(assiField.getText()));
        student.setMidterm(Float.parseFloat(midField.getText()));
        student.setFinalExam(Float.parseFloat(examField.getText()));
        table.getItems().add(student);
        sidField.clear();
        assiField.clear();
        midField.clear();
        examField.clear();
    }

    //  DataSource -----------------------------------------------------------------------------
    public ObservableList<StudentRecord> getMarks() {
        ObservableList<StudentRecord> marks = FXCollections.observableArrayList();
        marks.add(new StudentRecord("100100100", 75.0f, 68.0f, 54.25f));
        marks.add(new StudentRecord("100100101", 70.0f, 69.25f, 51.5f));
        marks.add(new StudentRecord("100100102", 100.0f, 97.0f, 92.5f));
        marks.add(new StudentRecord("100100103", 90.0f, 88.5f, 68.75f));
        marks.add(new StudentRecord("100100104", 72.25f, 74.75f, 58.25f));
        marks.add(new StudentRecord("100100105", 85.0f, 56.0f, 62.5f));
        marks.add(new StudentRecord("100100106", 70.0f, 66.5f, 61.75f));
        marks.add(new StudentRecord("100100107", 55.0f, 47.0f, 50.5f));
        marks.add(new StudentRecord("100100108", 40.0f, 32.5f, 27.75f));
        marks.add(new StudentRecord("100100109", 82.5f, 77.0f, 74.25f));
        return marks;
    }

    public static void main(String args[]) { launch(args); }
}