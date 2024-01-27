package com.example.ecommerse;

import javafx.collections.ObservableList;

import java.sql.ResultSet;

public class Order {

    public  static boolean placeOrder(Customer customer, product pr){
        String groupOrderId = "SELECT max(group_order_id) +1 id FROM orders";
        DBConnection dbConnection = new DBConnection();
                try{
                    ResultSet rs = dbConnection.getQueryTable(groupOrderId);
                    if(rs.next()){
                        String placeOrder = "INSERT INTO orders(group_order_id, customer_id, product_id) VALUES("+rs.getInt("id")+","+customer.getId()+","+pr.getId()+")";
                        return dbConnection.updateDatabase(placeOrder) !=0;
                    }

                }
                catch (Exception e){
                    e.printStackTrace();
                }
                return false;
    }

    public  static int placeMultipleOrder(Customer customer, ObservableList<product> productList){
        String groupOrderId = "SELECT max(group_order_id) +1 id FROM orders";
        DBConnection dbConnection = new DBConnection();
        try{
            ResultSet rs = dbConnection.getQueryTable(groupOrderId);
            if(rs.next()){
                int count =0;
                for( product pr: productList){
                    String placeOrder = "INSERT INTO orders(group_order_id, customer_id, product_id) VALUES("+rs.getInt("id")+","+customer.getId()+","+pr.getId()+")";
                      count += dbConnection.updateDatabase(placeOrder);
                }
                return count;
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
}
