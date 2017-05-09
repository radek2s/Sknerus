package sknerus.main;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * @author radek2s
 */
public abstract class Document {

    public abstract String getType();

    public abstract String getDocType();

    public abstract String getCreationDate();

    public abstract String getNumber();

    public abstract String getName();

    public abstract float getValue();

    public abstract float getAmount();

    public abstract String getVat();

    public abstract String getClient();


    //TODO: dodać getDocType i getVat i getClient
    @Override
    public String toString(){
        return this.getType() + ";" + this.getCreationDate() + ";" + this.getNumber() + ";" + this.getName() + ";" + this.getValue() + ";" + this.getAmount();
    }


}
