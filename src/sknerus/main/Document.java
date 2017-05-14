package sknerus.main;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author radek2s
 */
public abstract class Document {

    protected final SimpleStringProperty creationDate;
    protected final SimpleStringProperty number;
    protected final SimpleStringProperty docType;
    protected final SimpleStringProperty type;
    protected final SimpleStringProperty name;
    protected final SimpleFloatProperty value;
    protected final SimpleIntegerProperty amount;
    protected final SimpleStringProperty tax;
    protected final SimpleIntegerProperty client;

    public Document(String date, String id, String type, String document, String name, Float value, Integer amount, String tax, Integer client){
        String dateAsText = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(Integer.valueOf(date) * 1000L));

        this.creationDate   = new SimpleStringProperty(dateAsText);
        this.number         = new SimpleStringProperty(id);
        this.docType        = new SimpleStringProperty(type);
        this.type           = new SimpleStringProperty(document);
        this.name           = new SimpleStringProperty(name);
        this.value          = new SimpleFloatProperty(value);
        this.amount         = new SimpleIntegerProperty(amount);
        this.tax            = new SimpleStringProperty(tax);
        this.client         = new SimpleIntegerProperty(client);

    }

    public String getCreationDate() {
        return creationDate.get();
    }

    public String getNumber() {
        return number.get();
    }

    public String getDocType() {
        return docType.get();
    }

    public String getType() {
        return type.get();
    }

    public String getName() {
        return name.get();
    }

    public float getValue() {
        return value.get();
    }

    public int getAmount() {
        return amount.get();
    }

    public String getTax() {
        return tax.get();
    }

    public int getClient() {
        return client.get();
    }

    public void setCreationDate(String creationDate) {
        this.creationDate.set(creationDate);
    }

    public void setNumber(String number) {
        this.number.set(number);
    }

    public void setDocType(String docType) {
        this.docType.set(docType);
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setValue(float value) {
        this.value.set(value);
    }

    public void setAmount(int amount) {
        this.amount.set(amount);
    }

    public void setTax(String tax) {
        this.tax.set(tax);
    }

    public void setClient(int client) {
        this.client.set(client);
    }

    @Override
    public String toString(){
        return this.getCreationDate() + ";" + this.getNumber() + ";" +
                this.getDocType() + ";" + this.getType() + ";" +
                this.getName() + ";" + this.getValue() + ";" +
                this.getAmount() + ";" + this.getTax() + ";" + getClient();
    }


}
