package sknerus.main;

import java.util.ArrayList;

/**
 * Created by radek_000 on 16.04.2017.
 */
public class Receipt extends Document{

    int idCashDesk;
    int cash;

    public Receipt(Vendor vendor, String number, ArrayList<Product> products) {
        super(vendor, number, products);
    }
}
