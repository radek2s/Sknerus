package sknerus.main;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author radek2s
 * @version created on &{DATE}.
 *          Description:
 */
public class AppCore {

    private static AppCore instance = null;

    public Stage currentStage = null;
    public final ObservableList<Document> data;

    // Logger do zapisywania logów w programie //
    private final static Logger LOGGER = Logger.getLogger(AppCore.class.getName());

    protected AppCore(){

        /* --- Przygotowanie środowiksa --- */
        Date now = new Date();

        // Ustawienie czułości Loggera //
        LOGGER.setLevel(Level.ALL);
        // Zapisywanie Logów do plku //
        try {
            LOGGER.addHandler(new FileHandler("Logs - "+ DateFormat.getDateInstance().format(now) +".xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        /* --- Przygotowanie danych --- */
        data = FXCollections.observableArrayList();
    }

    /**
     * AppCore singleton
     * @return appCore instance
     *
     * Wzorzec projektowy singleton do przechowywania zmiennych globalych aplikacji
     * Aby wszystkie okna mogly ze soba wspoldzialac nalezy wszystko zapisac do jakiejs
     * globalnej zmiennej a ta zmienna jest 'AppCore.getInstance' to w niej sa wszystkie
     * potrzebne dla nas zmienne. Do app core możemy sie dostać w dowolnym momencie
     */
    public static AppCore getInstance(){
        if (instance == null)
        {
            instance = new AppCore();
        }
        return instance;
    }

    public static Logger getLogger(){
        return LOGGER;
    }

    /**
     * Add Data to data ObservableList
     * @param params - String array with data from file after separation.
     */
    public void addData(String[] params) {

        if (isNotNumbersMatchs(params[0],params[1],"timestamp")){
            params[0] = "1451606432";
        }
        if (isNotNumbersMatchs(params[1],params[1],"id")){
            params[1] = "404";
        }
        if (isNotNumbersMatchs(params[5],params[1],"value")){
            params[5] = "0";
        }
        if (isNotNumbersMatchs(params[6],params[1],"amount")){
            params[6] = "0";
        }


        String str_val = "0";
        String str_amo = "0";

        if ( params.length == 9) {
            str_val = params[5];
            str_amo = params[6];

        } else if (params.length == 11) {
            if (isNotNumbersMatchs(params[7],params[1],"value")){
                params[7] = "0";
            }
            if (isNotNumbersMatchs(params[8],params[1],"amount")){
                params[8] = "0";
            }

            str_val = params[5] + "." + params[6];
            str_amo = params[7] + "." + params[8];
        } else {
            LOGGER.warning("Error during loading data in file! Id=" + params[1]);
        }
        /* Walidacja poprawoności danych */
        Float f_value;
        Float f_amount;
        try {
            f_value = Float.parseFloat(str_val);
        } catch (NumberFormatException e){
            f_value = 0f;
            LOGGER.warning("In data with id=" + params[1] + " : 'value' has not supported format (float)");
        }
        try {
            f_amount = Float.parseFloat(str_amo);
        } catch (NumberFormatException e){
            f_amount = 0f;
            LOGGER.warning("In data with id=" + params[1] + " : 'amount' has not supported format (float)");
        }

        /* Wszystko jest poprawne - tworzymy dokument */
        if ( params.length == 9){
            data.add(DocumentFactory.getDocument(
                    params[0],params[1],params[2],params[3],params[4],f_value,f_amount,params[7],params[8]));
        } else if (params.length == 11){
            data.add(DocumentFactory.getDocument(
                    params[0],params[1],params[2],params[3],params[4],f_value,f_amount,params[9],params[10]));
        }


    }

    private boolean isNotNumbersMatchs(String text,String id, String description){

        /* Regex expression */
        String digitPattern = "^\\d+(\\.\\d+)?[fd]?";

        /* Checking if input is an number */
        Pattern pattern = Pattern.compile(digitPattern);
        Matcher matcher = pattern.matcher(text);
        if ( !matcher.matches() ) {
            LOGGER.warning("In data with id=" + id + " : '"+ description +"' is not a number! ["+text+"]");
            return true;
        }
        return false;

        // End of regexp section //


    }


    public void generatePDF(int index){

        try {
            com.itextpdf.text.Document pdfDocument = new com.itextpdf.text.Document();

            PdfWriter.getInstance(pdfDocument, new FileOutputStream(index+"-" + LocalDate.now() + "-" + data.size() + "data.pdf",false));

            pdfDocument.open();

            PDFGenerator.addMetaData(pdfDocument);
            PDFGenerator.addData(pdfDocument,index);

            pdfDocument.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }

}
