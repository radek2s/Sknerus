package sknerus.main;

/**
 * @author radek2s
 * @version created on &{DATE}.
 *          Description:
 */
public class DocumentFactory {

    public static Document getDocument(String type, String id, String name, float value){

        if ( type.equalsIgnoreCase("paragon")){
            return new Receipt(id, name, value);
        } else if ( type.equalsIgnoreCase("faktura")){
            return new Invoice(id, name, value);
        }
        return null;
    }
}
