package com.example.ecommerse;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

public class UserInterface {
    GridPane loginPage;

    Label welcomeLabel;
    HBox headerBar;

    HBox footerBar;

    Button signin;

    VBox body;

    Customer loggedInCustomer;

    ProductList productList = new ProductList();
    VBox productPage;

    Button placeOrderButton = new Button("Place Order");

    ObservableList<product> itemInCart = FXCollections.observableArrayList();

    BorderPane createContent(){
        BorderPane root = new BorderPane();
        root.setPrefSize(800,600);
//        root.getChildren().add(loginPage);
        root.setTop(headerBar);
//        root.setCenter(loginPage);
        body = new VBox();
        body.setPadding(new Insets(10));
        body.setAlignment(Pos.CENTER);
        root.setCenter(body);
        productPage = productList.getAllProducts();
//        root.setCenter(productPage);
        body.getChildren().add(productPage);
        // footer bar
        root.setBottom(footerBar);
        return root;

    }

    public UserInterface(){
        createLoginPage();
        createHeader();
        setFooterBar();
    }


    private void createLoginPage(){
        Text userNameText = new Text("User Name");
        Text passwordText = new Text("Password");

        TextField userName = new TextField("Vishal@gmail.com");
        userName.setPromptText("Tye user name");
        PasswordField password = new PasswordField();
        password.setText("919156");
        password.setPromptText("Type password");
        Label massegelabel = new Label("HI");

        Button loginButton = new Button("Login");


        loginPage= new GridPane();

//        loginPage.setStyle("-fx-background-color:grey;");
        loginPage.setAlignment(Pos.CENTER);
        loginPage.setHgap(10);
        loginPage.setVgap(10);
        loginPage.add(userNameText,0,0);
        loginPage.add(userName,1,0);
        loginPage.add(passwordText,0,1);
        loginPage.add(password,1,1);
        loginPage.add(massegelabel,0,2);
        loginPage.add(loginButton,1,2);

        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String name = userName.getText();
                String pass = password.getText();
                Login login = new Login();
                loggedInCustomer = login.customerLogin(name,pass);

                if(loggedInCustomer !=null){
                 massegelabel.setText("Welocome "+ loggedInCustomer.getName());
                 welcomeLabel.setText("Welcome- "+loggedInCustomer.getName());
                 headerBar.getChildren().add(welcomeLabel);
                 body.getChildren().clear();
                 body.getChildren().add(productPage);

                }else{
                    massegelabel.setText("Login Failed");
                }

            }
        });

    }

    private void createHeader(){

        Button imageButton = new Button();
        Image img = new Image("C:\\Users\\NACHIKET\\IdeaProjects\\Ecommerse\\src\\ecom.jpg");
        ImageView imageView = new ImageView();
        imageView.setImage(img);
        imageView.setFitHeight(20);
        imageView.setFitWidth(100);
        imageButton.setGraphic(imageView);
        TextField searchBar = new TextField();
        searchBar.setPromptText("Search here");
        searchBar.setPrefWidth(300);

        Button searchButton = new Button("Search");
         signin = new Button("Sign In");
         welcomeLabel = new Label();

         Button cartButton = new Button("Cart");

         Button orderButton = new Button("Orders");

        headerBar = new HBox();
        headerBar.setStyle("-fx-background-color:grey;");
        headerBar.setPadding(new Insets(10));
        headerBar.setSpacing(10);
        headerBar.setAlignment(Pos.CENTER);
        headerBar.getChildren().addAll(imageButton,searchBar,searchButton,signin,cartButton,orderButton);

        signin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                body.getChildren().clear();
                body.getChildren().add(loginPage);
                headerBar.getChildren().remove(signin);
            }
        }
        );
        placeOrderButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                product pr = productList.getSelectedProduct();
                if(itemInCart==null){
                    showDialog("Please select at least one items");
                }
                if(pr == null){
                    showDialog("Please select a product first to place order");
                    return;
                }
                if(loggedInCustomer == null){
                    showDialog("Please login first to place order");
                    return;
                }
                int count = Order.placeMultipleOrder(loggedInCustomer,itemInCart);
                if(count !=0){
                    showDialog(""+count+"Order has been placed Successfully");
                }else{
                    showDialog("Order failed");
                }

            }
        });
        cartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                body.getChildren().clear();
                VBox prodPage = productList.getProductsInCart(itemInCart);
                prodPage.setAlignment(Pos.CENTER);
                prodPage.setSpacing(10);
                prodPage.getChildren().add(placeOrderButton);
                body.getChildren().add(prodPage);
                footerBar.setVisible(false);
            }
        });
        imageButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                body.getChildren().clear();
                body.getChildren().add(productPage);
                footerBar.setVisible(true);
                if(loggedInCustomer == null && headerBar.getChildren().indexOf(signin) == -1){

                        headerBar.getChildren().add(signin);
                }
            }
        });
    }




    private void setFooterBar(){
        Button buyNowButton = new Button("Buy Now");
        Button addtoCartButton = new Button("Add to Cart");
        footerBar = new HBox();
//        headerBar.setStyle("-fx-background-color:grey;");
        footerBar.setPadding(new Insets(10));
        footerBar.setSpacing(10);
        footerBar.setAlignment(Pos.CENTER);
        footerBar.getChildren().addAll(buyNowButton,addtoCartButton);


        buyNowButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                product pr = productList.getSelectedProduct();
                if(pr == null){
                    // please select a product first to place order
                    showDialog("Please select a product first to place order");
                    return;

                }
                if(loggedInCustomer == null){
                    showDialog("Please login First to Place order");
                    return;
                }
                boolean status = Order.placeOrder(loggedInCustomer, pr);
                if(status == true){
                    showDialog("Order placed Successfully!!");
                }else{
                    showDialog("Order failed !!");
                }
            }
        });

        addtoCartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                product pr = productList.getSelectedProduct();
                if(pr == null){
                    showDialog("Please select a product first to add it to cart");
                    return;
                }
                itemInCart.add(pr);
                showDialog("Selected Item");
            }
        });




    }

    private  void showDialog(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.setTitle("Message");
        alert.showAndWait();
    }
}
