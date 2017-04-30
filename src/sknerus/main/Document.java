package sknerus.main;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * @author radek2s
 */
public abstract class Document {

    public abstract String getType();

    public abstract String getCreationDate();

    public abstract String getNumber();

    public abstract String getName();

    public abstract float getValue();

    public abstract int getAmount();

    @Override
    public String toString(){
        return this.getType() + ";" + this.getCreationDate() + ";" + this.getNumber() + ";" + this.getName() + ";" + this.getValue() + ";" + this.getAmount();
    }


}
