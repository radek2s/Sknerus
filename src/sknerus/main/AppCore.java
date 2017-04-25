package sknerus.main;

import java.util.LinkedList;

/**
 * @author radek2s
 * @version created on &{DATE}.
 *          Description:
 */
public class AppCore {

    private static AppCore instance = null;

    protected AppCore(){

        dokumenty = new LinkedList<>();

    }

    public static AppCore getInstance(){
        if (instance == null)
        {
            instance = new AppCore();
        }
        return instance;
    }

    private String tekst;
    private LinkedList<Document> dokumenty;


    public void setTekst(String tekst){
        this.tekst = tekst;
    }
}
