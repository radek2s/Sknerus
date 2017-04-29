package sknerus.main;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.LinkedList;

/**
 * @author radek2s
 * @version created on &{DATE}.
 *          Description:
 */
public class AppCore {

    private static AppCore instance = null;

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
    public void addData(String type, String id, String name, Float value){
        int count = data.size();
        data.add(DocumentFactory.getDocument(type,id + "0" + String.valueOf(count+1),name,value));

        /* Jesli ilosc danych wzrozla o 20 generuj raport */
        if ( count % 20 == 0 ){
            generatePDF(1);
        }
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
