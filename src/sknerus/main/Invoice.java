package sknerus.main;

import java.util.ArrayList;

/**
 * @author radek2s
 * Invoice document class.
 */
public class Invoice extends Document {

    private Vendor purchaser;
    private float sumNet;
    private float sumGross;
    private float sumVat;


    public Invoice(Vendor vendor, String number, ArrayList<Product> products, Vendor purchaser) {
        super(vendor, number, products);
        this.purchaser = purchaser;

        for (Product product: products){
            sumNet += product.getValue();
        }
        sumGross = (float) (sumNet*0.22);
        sumVat = sumNet - sumGross;
    }

    public float getSumNet(){
        return this.sumNet;
    }
}
