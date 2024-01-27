package com.example.ecommerse;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class ProductList {

    private TableView<product> productTable;

    public VBox createTable( ObservableList<product> data){
        TableColumn id = new TableColumn("ID");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn  name = new TableColumn("NAME");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn price = new TableColumn("PRICE");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));


        productTable = new TableView<>();
        productTable.getColumns().addAll(id,name,price);
        productTable.setItems(data);
        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(20));
        vBox.getChildren().addAll(productTable);
        return vBox;

    }
    public VBox getDummyData(){
        ObservableList<product> data = FXCollections.observableArrayList();

        data.add(new product(2,"Iphone 13",13000.00));
        data.add(new product(5,"Sumsung",15000.0));
        return createTable(data);
    }
    public VBox getAllProducts(){
        ObservableList<product> data = product.getAllProduct();
        return createTable(data);
    }
    public  product getSelectedProduct(){
        return productTable.getSelectionModel().getSelectedItem();
    }

    public VBox getProductsInCart(ObservableList<product> data){
        return createTable(data);
    }

}
