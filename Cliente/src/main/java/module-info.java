module com.example.cliente {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.Mesero to javafx.fxml;
    exports com.example.Mesero;
    exports controller;
    opens controller to javafx.fxml;
}