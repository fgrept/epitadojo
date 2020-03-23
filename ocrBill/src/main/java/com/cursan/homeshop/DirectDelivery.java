package com.cursan.homeshop;

public class DirectDelivery implements Delivery {
    private static final String intituleLivraison = "livraison à domicile ";

    @Override
    public double getPrice() {
        return 4.99;
    }

    @Override
    public String getIntituleLivraison() {
        return null;
    }
}
