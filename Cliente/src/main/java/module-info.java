module com.example.cliente {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.Cliente to javafx.fxml;
    exports com.example.Cliente;
    exports controller;
    opens controller to javafx.fxml;
}