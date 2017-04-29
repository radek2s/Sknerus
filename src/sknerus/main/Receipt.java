package sknerus.main;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;

/**
 * Created by radek_000 on 16.04.2017.
 */
public class Receipt extends Document{

    private final SimpleStringProperty type;
    private final SimpleStringProperty number;
    private final SimpleStringProperty name;
    private final SimpleFloatProperty value;

    public Receipt(String number, String name, float value){
        this.type = new SimpleStringProperty("paragon");
        this.number = new SimpleStringProperty("P_" + number);
        this.name = new SimpleStringProperty(name);
        this.value = new SimpleFloatProperty(value);
    }

    @Override
    public String getType() {
        return "Paragon";
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
}
