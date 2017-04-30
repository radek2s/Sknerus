package sknerus.main;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;

/**
 * Created by radek_000 on 16.04.2017.
 */
public class Receipt extends Document{

    private final SimpleStringProperty type;
    private final SimpleStringProperty creationDate;
    private final SimpleStringProperty number;
    private final SimpleStringProperty name;
    private final SimpleFloatProperty value;
    private final SimpleIntegerProperty amount;

    public Receipt(String number,String creationDate, String name, float value, int amount){
        this.type = new SimpleStringProperty("paragon");
        this.creationDate = new SimpleStringProperty(creationDate);
        this.number = new SimpleStringProperty("P_"+number);
        this.name = new SimpleStringProperty(name);
        this.value = new SimpleFloatProperty(value);
        this.amount = new SimpleIntegerProperty(amount);
    }

    @Override
    public String getType() {
        return "Paragon";
    }

    @Override
    public String getCreationDate() {
        return creationDate.get();
    }

    @Override
    public String getNumber() {
        return number.get();
    }

    @Override
    public String getName() {
        return name.get();
    }

    @Override
    public float getValue() {
        return value.get();
    }

    @Override
    public int getAmount() {
        return amount.get();
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public void setNumber(String number) {
        this.number.set(number);
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setValue(float value) {
        this.value.set(value);
    }

    public void setCreationDate(String creationDate) {
        this.creationDate.set(creationDate);
    }

    public void setAmount(int amount) {
        this.amount.set(amount);
    }
}
