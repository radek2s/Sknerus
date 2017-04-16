package sknerus.main;

/**
 * Created by radek_000 on 16.04.2017.
 */
public class Vendor {

    private String  name;
    private String  address;
    private String  postCode;
    private int     nip;

    public Vendor(int nip, String name, String address, String postCode){
        this.name = name;
        this.address = address;
        this.postCode = postCode;
        this.nip = nip;
    }

    public int getNip(){
        return this.nip;
    }

    @Override
    public String toString(){
        return name + " " + address +" : "+ postCode + nip;
    }

}
