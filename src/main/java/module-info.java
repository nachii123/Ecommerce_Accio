module com.example.ecommerse {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.ecommerse to javafx.fxml;
    exports com.example.ecommerse;
}