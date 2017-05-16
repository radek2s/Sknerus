package sknerus.main;

import com.itextpdf.text.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import javafx.collections.ObservableList;

import java.time.LocalDate;

/**
 * @author radek2s
 * @version created on &{DATE}.
 *          Description:
 */
public class PDFGenerator {

    /**
     * Set up metadata for PDF file
     * @param document
     */
    public static void addMetaData(Document document){
        document.addTitle("Rozliczenie " + LocalDate.now());
        document.addAuthor("Sknerus Company");
    }

    /**
     * addData - generate PDF with content
     * @param document
     * @param type - quality or store
     * @throws DocumentException
     */
    public static void addData(Document document, int type) throws DocumentException {

        Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD);


        String title;
        if (type == 1){
            title = "Rozliczenie ilosciowe";
        } else if ( type == 2){
            title = "Rozliczenie jakosciowe";
        } else {
            title = "Inne";
        }

        Paragraph p1 = new Paragraph(title,boldFont);
        Paragraph p2 = new Paragraph(String.valueOf(LocalDate.now()),boldFont);
        document.add(p1);
        document.add(p2);
        document.add(new Paragraph(" "));
        document.add(createTable(AppCore.getInstance().data));

        if (type == 1){
            int incomeCount = 0;
            int outcomeCount = 0;
            for (sknerus.main.Document row : AppCore.getInstance().data){
                if(row.getDocType().equalsIgnoreCase("income")){
                    incomeCount++;
                } else if (row.getDocType().equalsIgnoreCase("outcome")){
                    outcomeCount++;
                }
            }
            document.add(new Paragraph("Liczba przychodow: " + incomeCount));
            document.add(new Paragraph("Liczba rozchodow: " + outcomeCount));
        } else if (type == 2){
            float moneySum = 0;
            float moneyDiv = 0;
            for (sknerus.main.Document row : AppCore.getInstance().data){
                if(row.getDocType().equalsIgnoreCase("income")){
                    moneySum += row.getAmount() * row.getValue();
                } else if (row.getDocType().equalsIgnoreCase("outcome")){
                    moneyDiv += row.getAmount() * row.getValue();
                } else {
                    moneyDiv = 0;
                }



            }

            float sum = moneySum - moneyDiv;
            document.add(new Paragraph("Suma wydatkow: " + sum));

        }

    }

    /**
     * Create table based on the data stored in AppCore
     * @param list
     * @return
     */
    
    public static Element createTable(ObservableList list){

        String header1 = "Typ dokumentu";
        String header2 = "Data";
        String header3 = "Numer dokumentu";
        String header4 = "Nazwa";
        String header5 = "Wartosc";
        String header6 = "Ilosc";
        String header7 = "Podatek";
        String header8 = "Stan";
        String header9 = "Klient";
        
        PdfPTable table = new PdfPTable(9);

        PdfPCell cell1 = new PdfPCell(new Phrase(header1));
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell1);

        PdfPCell cell2 = new PdfPCell(new Phrase(header2));
        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell2);

        PdfPCell cell3 = new PdfPCell(new Phrase(header3));
        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell3);

        PdfPCell cell4 = new PdfPCell(new Phrase(header4));
        cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell4);

        PdfPCell cell5 = new PdfPCell(new Phrase(header5));
        cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell5);

        PdfPCell cell6 = new PdfPCell(new Phrase(header6));
        cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell6);

        PdfPCell cell7 = new PdfPCell(new Phrase(header7));
        cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell7);

        PdfPCell cell8 = new PdfPCell(new Phrase(header8));
        cell8.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell8);

        PdfPCell cell9 = new PdfPCell(new Phrase(header9));
        cell9.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell9);

        for (Object row : list) {
            if (row == null){
                continue;
            }
            String[] temp = row.toString().split(";");
            table.addCell(temp[3]);
            table.addCell(temp[0]);
            table.addCell(temp[1]);
            table.addCell(temp[4]);
            table.addCell(temp[5]);
            table.addCell(temp[6]);
            table.addCell(temp[7]);
            table.addCell(temp[2]);
            table.addCell(temp[8]);
        }

        
        return table;

        
    }

}
