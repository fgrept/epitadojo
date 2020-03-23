package com.cursan.homeshop.dao;

import com.cursan.homeshop.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    private String url = "jdbc:mysql://localhost/homeshop?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private String user = "fabien";
    private String pwd = "c14390";

    public List<Product> getAll () {

        List<Product> products = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(url,user,pwd);
            Statement statement = connection.createStatement();
            ResultSet rs1 = statement.executeQuery("use homeshop");
            ResultSet rs = statement.executeQuery("select * from product");
            while (rs.next()) {
                //String name = rs.getString("name'");
                String name = rs.getString(2);
                //String description = rs.getString("description'");
                String description = rs.getString(3);
                //Double price = rs.getDouble("price");
                Double price = rs.getDouble(4);
                products.add(new Product(name,description,price));
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}
