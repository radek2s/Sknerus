package sknerus.main;

/**
 * @author radek2s
 * @version created on &{DATE}.
 *          Description:
 */
public class DocumentFactory {

    public static Document getDocument(String type, String date, String id, String docType, String name, float value, float amount, String tax, String client){

        if ( type.equalsIgnoreCase("receipt")){
            return new Receipt(id, date,docType, name, value, amount, tax, client);
        } else if ( type.equalsIgnoreCase("invoice")){
            return new Invoice(id, date,docType, name, value, amount, tax, client);
        } else if ( type.equalsIgnoreCase("paymanet")){
            return new Receipt(id, date,docType, name, value, amount, tax, client);
        } else if ( type.equalsIgnoreCase("other")){
            return new Receipt(id, date,docType, name, value, amount, tax, client);
        }
        return null;
    }
}
