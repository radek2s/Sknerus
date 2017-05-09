package sknerus.main;

/**
 * @author radek2s
 * Invoice document class.
 */
public class Invoice extends Document {

    public Invoice(String creationDate,String id,String type, String document, String name, float value, float amount, String tax, String client){

        super(creationDate,id,type,document,name,value,amount,tax,client);
    }

    @Override
    public String getType() {
        return "Faktura";
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
    public String getTax() {
        return tax.get();
    }

    @Override
    public String getClient() {
        return client.get();
    }

}
