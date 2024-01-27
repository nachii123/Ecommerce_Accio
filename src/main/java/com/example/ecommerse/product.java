package com.example.ecommerse;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;

public class product {

    private SimpleIntegerProperty id;

    private SimpleStringProperty name;

    private SimpleDoubleProperty price;

    public product(int id, String name, Double price) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
    }

    public static  ObservableList<product> getAllProduct(){
        String query ="SELECT id, name, price FROM product";
        return fetchAllProduct(query);
    }

    public static ObservableList<product> fetchAllProduct(String query){
        ObservableList<product> data = FXCollections.observableArrayList();
        DBConnection dbConnection = new DBConnection();
        try{
            ResultSet rs = dbConnection.getQueryTable(query);
            while(rs.next()){
              product pr = new product(rs.getInt("id"),rs.getString("name"), rs.getDouble("price"));
              data.add(pr);
            }
            return data;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public int getId() {
        return id.get();
    }

    public String getName() {
        return name.get();
    }

    public double getPrice() {
        return price.get();
    }
}
