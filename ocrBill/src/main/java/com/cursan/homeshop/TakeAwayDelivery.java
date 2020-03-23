package com.cursan.homeshop;

public class TakeAwayDelivery implements Delivery {
    private static final String intituleLivraison = "retrait gratuit à l'entrepôt de Paris ";

    @Override
    public double getPrice() {
        return 0;
    }

    @Override
    public String getIntituleLivraison() {
        return intituleLivraison;
    }
}
