package main;

import java.util.ArrayList;

/**
 * Created by radek_000 on 16.04.2017.
 */
public class Invoice extends Document {

    Vendor purchaser;
    int sumNet;
    int sumGross;
    int sumVat;


    public Invoice(Vendor vendor, String number, ArrayList<Product> products) {
        super(vendor, number, products);
    }
}
