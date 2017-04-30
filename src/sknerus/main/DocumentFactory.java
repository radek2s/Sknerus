package sknerus.main;

/**
 * @author radek2s
 * @version created on &{DATE}.
 *          Description:
 */
public class DocumentFactory {

    public static Document getDocument(String type, String date, String id, String name, float value, int amount){

        if ( type.equalsIgnoreCase("paragon")){
            return new Receipt(id, date, name, value, amount);
        } else if ( type.equalsIgnoreCase("faktura")){
            return new Invoice(id, date, name, value, amount);
        }
        return null;
    }
}
