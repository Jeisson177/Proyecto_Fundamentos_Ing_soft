module com.example.cliente {
    requires javafx.controls;
    requires javafx.fxml;
    requires jfxrt;


    opens com.example.cliente to javafx.fxml;
    exports com.example.cliente;
    exports controller;
    opens controller to javafx.fxml;
}