package sknerus.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
import java.util.ResourceBundle;
import java.util.TimerTask;

/**
 * Created by radek_000 on 16.04.2017.
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
    private TableColumn<Document, Float> tableCol7;
    @FXML
    private TableColumn<Document, String> tableCol8;
    @FXML
    private TableColumn<Document, String> tableCol9;

    @FXML
    private Label currentTime;


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

    @FXML
    public void genStorePDF(){

        AppCore.getInstance().generatePDF(1);

    }

    @FXML
    public void genQualityPDF(){
        AppCore.getInstance().generatePDF(2);
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
    }

    DateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    final Timeline timeline = new Timeline(
            new KeyFrame(
                    Duration.millis(500),
                    event -> {
                        currentTime.setText(timeFormat.format(System.currentTimeMillis()));
                    }
            )
    );


}
