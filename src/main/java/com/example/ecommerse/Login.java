package com.example.ecommerse;

import javax.xml.transform.Result;
import java.sql.ResultSet;

public class Login {


    public Customer customerLogin(String username, String password){

        String query = "SELECT * FROM customers WHERE email= '"+username+"' AND password= '"+password+"'";
        DBConnection connection = new DBConnection();
        try{
            ResultSet rs =connection.getQueryTable(query);
            if(rs.next()){
                return new Customer(rs.getInt("id"),rs.getString("name"),rs.getString("email"),rs.getString("mobile"));
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {
        Login lb = new Login();
        Customer customer = lb.customerLogin("Vishal@gmail.com","919156");
        System.out.print("Welcome "+ customer.getName());
//        System.out.print(lb.createLogin("Vishal@gmail.com","919156"));
    }
}
