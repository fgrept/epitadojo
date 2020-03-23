package com.cursan.homeshop;

import java.util.*;

public class Bill {
    private Customer customer;
    private Map<Product, Integer> products = new HashMap<>();
    private Delivery delivery;

    public Bill(Customer customer,Delivery delivery) {
        this.customer = customer;
        this.delivery = delivery;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "customer=" + customer.getFullname() +
                ", products=" + products.toString() +
                '}';
    }

    /**
     * Add a product with a quantity in the bill
     * @param product product to add
     * @param quantity quantity of the product
     */
    public void addProduct(Product product, Integer quantity) {
        this.products.put(product, quantity);
    }

    public Customer getCustomer() {
        return customer;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public void generate (Writer writer) throws NoProductInBillException {

        if (products.isEmpty() == true) throw new NoProductInBillException("Vous cherchez à générer une facture" +
                " sans aucun produit pour le client " + this.getCustomer().getFullname());

        writer.start();

        writer.writeLine("Homeshop companie");
        writer.writeLine("1 place charles de gaulle, 75008 Paris");
        writer.writeLine("");
        writer.writeLine("Facture à l'attention de ");
        writer.writeLine(customer.getFullname());
        writer.writeLine(customer.getAddress());
        writer.writeLine("");
        writer.writeLine("Mode de livraison : " + delivery.getIntituleLivraison());
        writer.writeLine("");
        writer.writeLine("Produits");
        writer.writeLine("-------------------------");

        // Several products
        for (Map.Entry<Product, Integer> product : products.entrySet()) {
            writer.writeLine(product.getKey().getName() + product.getKey().getPrice() +
                    product.getValue() + " unité(s) ");
            writer.writeLine(product.getKey().getDescription());
            writer.writeLine("");
        }

        writer.writeLine("Livraison : " + delivery.getPrice());
        writer.writeLine("-------------------------");
        writer.writeLine("Total = " + getTotal());

        writer.stop();

    }

    public double getTotal () {
        Double total = delivery.getPrice();

        for (Map.Entry<Product, Integer> product : products.entrySet()) {
            total += product.getValue() * product.getKey().getPrice();
        }

        return total;
    };

}
