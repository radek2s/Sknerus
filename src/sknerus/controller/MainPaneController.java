package sknerus.controller;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sknerus.main.AppCore;
import sknerus.main.Document;
import sknerus.main.PDFGenerator;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

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
    private TableColumn<Document, Float> tableCol4;


    @FXML
    public void getDataFromUser(){

        Parent root;
        try {

            root = FXMLLoader.load(this.getClass().getResource("../gui/InputData.fxml"));

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
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
     * za pomocą metody addData dodaje pojedyncza zmienna.
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
            BufferedReader bufferedReader = null;
            String line;
            String separator = ";";
            try {
                bufferedReader = new BufferedReader(new FileReader(file.getAbsolutePath()));
                while (( line = bufferedReader.readLine()) != null){

                    String[] csvData = line.split(separator);
                    AppCore.getInstance().addData(csvData[0],csvData[1],csvData[2],Float.valueOf(csvData[3]));

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
        tableView.setEditable(true);

        tableCol1.setCellValueFactory(
                new PropertyValueFactory<>("type")
        );
        tableCol2.setCellValueFactory(
                new PropertyValueFactory<>("number")
        );
        tableCol3.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );
        tableCol4.setCellValueFactory(
                new PropertyValueFactory<>("value")
        );
    }
}
