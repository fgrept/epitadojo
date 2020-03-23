package com.cursan.homeshop;

public class ExpressDelivery implements Delivery {
    private String city;
    private static final String intituleLivraison = "livraison à domicile express ";

    public ExpressDelivery(String city) {
        this.city = city;
    }

    @Override
    public String getIntituleLivraison() {
        return intituleLivraison;
    }
    @Override
    public double getPrice() {
        if (city.equals("Paris"))
            return 6.99;
        else
            return 9.99;
    }
}
