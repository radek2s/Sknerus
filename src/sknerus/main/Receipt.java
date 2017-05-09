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
    private final SimpleFloatProperty amount;
    private final SimpleStringProperty docType;
    private final SimpleStringProperty tax;
    private final SimpleStringProperty client;

    public Receipt(String number,String creationDate,String docType, String name, float value, float amount, String tax, String client){
        this.type = new SimpleStringProperty("paragon");
        this.creationDate = new SimpleStringProperty(creationDate);
        this.number = new SimpleStringProperty("P_"+number);
        this.name = new SimpleStringProperty(name);
        this.value = new SimpleFloatProperty(value);
        this.amount = new SimpleFloatProperty(amount);
        this.docType = new SimpleStringProperty(docType);
        this.tax = new SimpleStringProperty(tax);
        this.client = new SimpleStringProperty(client);
    }

    @Override
    public String getType() {
        return "Paragon";
    }

    @Override
    public String getDocType() {
        return docType.get();
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
    public float getAmount() {
        return amount.get();
    }

    @Override
    public String getVat() {
        return tax.get();
    }

    @Override
    public String getClient() {
        return client.get();
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
