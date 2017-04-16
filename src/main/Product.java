package main;

/**
 * Created by radek_000 on 16.04.2017.
 */
public class Product {

    private String  name;
    private int     value;

    public Product(String name, int value){
        this.name = name;
        this.value= value;
    }

    public String getName(){
        return name;
    }

    public int getValue(){
        return value;
    }
}
