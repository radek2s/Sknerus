package sknerus.main;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;

/**
 * @author radek2s
 * @version created on &{DATE}.
 *          Description:
 */
public class AppCore {

    private static AppCore instance = null;

    public Stage currentStage = null;
    public final ObservableList<Document> data;


    protected AppCore(){

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

    /**
     * Add Data to data ObservableList
     * @param type - string ('faktura'/'paragon')
     * @param id - string (user input id)
     * @param name - string (name of good)
     * @param value - float ( value of the product )
     *              Funkcja do użycia w kontrolerze ręcznego wprowadzania dancyh
     *              wystarczy jej przekazać odpowiednie paramtery a utworzy nowa
     *              instancje dokumentu w tablicy 'data';
     */
    public void addData(String date, String id, String type, String document, String name, Float value, Float amount, String tax, String client) {

        data.add(DocumentFactory.getDocument(
                date,id,type,document,name,value,amount,tax,client
        ));

        /* Jesli ilosc danych wzrozla o 20 generuj raport */
//        if ( count % 1000 == 0 ){
//            generatePDF(1);
//        }
    }

    public void addData(String date, String type, String document, String name, Float value, Float amount, String tax, String client){
        int id = data.size();
        data.add(DocumentFactory.getDocument(
                date, String.valueOf(id),type,document,name,value,amount,tax,client
        ));
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
