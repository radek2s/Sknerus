package main;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Created by radek_000 on 16.04.2017.
 */
public abstract class Document {

    String              number;
    int                 value;
    LocalDateTime       time;
    Vendor              vendor;
    ArrayList<Product>  products;

    public Document(Vendor vendor,String number, ArrayList<Product> products){
        this.time = LocalDateTime.now();
        this.number = number;
        this.vendor = vendor;
        this.products = products;

    }


}
