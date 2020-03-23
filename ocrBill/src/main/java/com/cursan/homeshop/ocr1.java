package com.cursan.homeshop;

import java.io.File;

public class ocr1 {

    public static void main(String[] args) {
        Product cafe = new Product("Philips HD7866/61", "Philips SENSEO Quadrante, Noir - 1 ou 2 tasses", 79.99);
        Television tv = new Television("TV Samsung UE49MU6292", "Smart TV LED incurvée 49\"", 599, 49, "LED");
        Fridge fridge = new Fridge("BEKO TSE 1042 F", "Réfrigérateur BEKO 130L - Classe A+ - blanc", 189, 130, false);

        Customer customer = new Customer("Juste Leblanc", "19 rue Germain Pilon, Paris");

        Bill bill = new Bill(customer, new ExpressDelivery("Paris"));
//        bill.addProduct(cafe, 1);
//        bill.addProduct(tv, 1);
//        bill.addProduct(fridge, 1);

        System.out.println(bill.toString());

        FileWriter fileWriter = new FileWriter();
        try {
            bill.generate(fileWriter);
        } catch (NoProductInBillException e) {
            //e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
