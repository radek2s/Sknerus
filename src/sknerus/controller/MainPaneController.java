package sknerus.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import sknerus.main.AppCore;
import sknerus.main.Document;
import sknerus.main.Main;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import static java.util.concurrent.TimeUnit.*;

/**
 * @author radek2s
 * @version created on 16.04.2017.
 *          Description:
 */

public class MainPaneController implements Initializable {

    @FXML
    private TableView<Document> tableView;
    @FXML
    private TableColumn<Document, String> tableCol1;
    @FXML
    private TableColumn<Document, String> tableCol2;
    @FXML
    private TableColumn<Document, String> tableCol3;
    @FXML
    private TableColumn<Document, String> tableCol4;
    @FXML
    private TableColumn<Document, String> tableCol5;
    @FXML
    private TableColumn<Document, Float> tableCol6;
    @FXML
    private TableColumn<Document, Integer> tableCol7;
    @FXML
    private TableColumn<Document, String> tableCol8;
    @FXML
    private TableColumn<Document, Integer> tableCol9;
    @FXML
    private ToggleButton toggleButtonCount;
    @FXML
    private ChoiceBox chBox;
    @FXML
    private Label currentTime;


    DateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    final Timeline timeline = new Timeline(
            new KeyFrame(
                    Duration.millis(500),
                    event -> {
                        currentTime.setText(timeFormat.format(System.currentTimeMillis()));

                    }
            )
    );

    @FXML
    public void getDataFromUser(){

        Parent root;
        try {

            root = FXMLLoader.load(this.getClass().getResource("../gui/InputData.fxml"));

            AppCore.getInstance().currentStage = new Stage();
            Stage stage = AppCore.getInstance().currentStage;
            stage.setScene(new Scene(root));
            stage.setTitle("Input data - manual mode");
            stage.show();


        } catch (IOException e){
            e.printStackTrace();
        }


    }

    @FXML
    public void refreshTable(){

        tableView.setItems(AppCore.getInstance().data);

    }

    /**
     * readFromCSVfile
     * Funkcja do otwierania pliku csv i wczytania do aplikacji danych
     * Kazda nowa daną dodaje do globalnej zmiennej AppCore.getIsnstance
     * za pomocą    metody addData dodaje pojedyncza zmienna.
     */
    @FXML
    public void readFromFile(){

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV data","*.csv"),
                new FileChooser.ExtensionFilter("All files","*.*")
        );
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null){
            AppCore.getLogger().info("Application starting loading data form file: " + file.getName());
            BufferedReader bufferedReader = null;
            String line;
            String separator = ",";
            try {
                bufferedReader = new BufferedReader(new FileReader(file.getAbsolutePath()));
                while (( line = bufferedReader.readLine()) != null){

                    String[] csvData = line.split(separator);
                    try{

                        AppCore.getInstance().addData(csvData);


                    } catch ( ArrayIndexOutOfBoundsException e){

                        System.out.println("Wyjatek!");
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (bufferedReader != null){
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }

        AppCore.getLogger().info("Loading from file done!");
        refreshTable();
    }

    /**
     * genStorePDF
     * generuje plik PDF z podsumowaniem ilosciowym
     */
    @FXML
    public void genStorePDF(){

        AppCore.getLogger().info("Generating Store PDF started...");
        AppCore.getInstance().generatePDF(1);
        AppCore.getLogger().info("Generating Store PDF finished!");

    }

    /**
     * genQualityPDF
     * generuje plik PDF z podsumowaniem jakosciowym
     */
    @FXML
    public void genQualityPDF(){


        AppCore.getLogger().info("Generating Quality PDF started...");
        AppCore.getInstance().generatePDF(2);
        AppCore.getLogger().info("Generating Quality PDF finished!");
    }

    /**
     * updateCount
     * przelaczanie pomiedzy automatycznym generowaniem raportow
     */
    @FXML
    public void updateCount(){
        if ( toggleButtonCount.isSelected() ){
            toggleButtonCount.setText("Włączone");
            AppCore.getInstance().setAutoCountPDFGenEnable(true);
        } else {
            toggleButtonCount.setText("Wyłączone");
            AppCore.getInstance().setAutoCountPDFGenEnable(false);
        }
    }


    @FXML
    public void exit(){
        Platform.exit();
    }

    /**
     * Initialize method to load data and prepare application
     * @param location
     * @param resources
     *
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableView.setEditable(false);

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        tableCol1.setCellValueFactory(
                new PropertyValueFactory<>("creationDate")
        );
        tableCol2.setCellValueFactory(
                new PropertyValueFactory<>("number")
        );
        tableCol3.setCellValueFactory(
                new PropertyValueFactory<>("docType")
        );
        tableCol4.setCellValueFactory(
                new PropertyValueFactory<>("type")
        );
        tableCol5.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );
        tableCol6.setCellValueFactory(
                new PropertyValueFactory<>("value")
        );
        tableCol7.setCellValueFactory(
                new PropertyValueFactory<>("amount")
        );
        tableCol8.setCellValueFactory(
                new PropertyValueFactory<>("tax")
        );
        tableCol9.setCellValueFactory(
                new PropertyValueFactory<>("client")
        );

        chBox.setItems(FXCollections.observableArrayList(
                "All","Warnings","Severe"
        ));

        chBox.getSelectionModel().selectedIndexProperty().addListener(
                new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                        if (newValue.intValue() == 0){
                            AppCore.getLogger().setLevel(Level.ALL);
                        } else if (newValue.intValue() == 1){
                            AppCore.getLogger().setLevel(Level.WARNING);
                        } else if (newValue.intValue() == 2){
                            AppCore.getLogger().setLevel(Level.SEVERE);
                        }
                    }
                }
        );


    }



}
