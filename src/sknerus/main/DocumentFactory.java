package sknerus.main;

/**
 * @author radek2s
 * @version created on &{DATE}.
 *          Description:
 */
public class DocumentFactory {

    public static Document getDocument(String date, String id, String type, String document, String name, Integer amount, Float value, String tax, Integer client){

        if ( document.equalsIgnoreCase("receipt") || document.equalsIgnoreCase("paragon")){
            return new Receipt(date,id,type,document,name,value,amount,tax,client);
        } else if ( document.equalsIgnoreCase("invoice") || document.equalsIgnoreCase("faktura")){
            return new Invoice(date,id,type,document,name,value,amount,tax,client);
        } else if ( document.equalsIgnoreCase("payment") || document.equalsIgnoreCase("platnosc")){
            return new Payment(date,id,type,document,name,value,amount,tax,client);
        } else if ( document.equalsIgnoreCase("other") || document.equalsIgnoreCase("inne")){
            return new OtherDocument(date,id,type,document,name,value,amount,tax,client);
        }
        return null;
    }
}
