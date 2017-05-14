package sknerus.main;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;

/**
 * Created by radek_000 on 16.04.2017.
 */
public class Receipt extends Document{

    public Receipt(String date, String id, String type, String document, String name, Float value, int amount, String tax, int client) {
        super(date, id, type, document, name, value, amount, tax, client);
    }

    @Override
    public String getCreationDate() {
        return super.getCreationDate();
    }

    @Override
    public String getNumber() {
        return super.getNumber();
    }

    @Override
    public String getDocType() {
        return super.getDocType();
    }

    @Override
    public String getType() {
        return "Paragon";
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public float getValue() {
        return super.getValue();
    }

    @Override
    public int getAmount() {
        return super.getAmount();
    }

    @Override
    public String getTax() {
        return super.getTax();
    }

    @Override
    public int getClient() {
        return super.getClient();
    }

    @Override
    public void setCreationDate(String creationDate) {
        super.setCreationDate(creationDate);
    }

    @Override
    public void setNumber(String number) {
        super.setNumber(number);
    }

    @Override
    public void setDocType(String docType) {
        super.setDocType(docType);
    }

    @Override
    public void setType(String type) {
        super.setType(type);
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public void setValue(float value) {
        super.setValue(value);
    }

    @Override
    public void setAmount(int amount) {
        super.setAmount(amount);
    }

    @Override
    public void setTax(String tax) {
        super.setTax(tax);
    }

    @Override
    public void setClient(int client) {
        super.setClient(client);
    }
}
