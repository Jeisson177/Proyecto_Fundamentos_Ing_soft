module com.example.cliente {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.h2database;


    opens com.example.Mesero to javafx.fxml;
    exports com.example.Mesero;
    exports controller;
    opens controller to javafx.fxml;
    exports controller.menuMesero;
    opens controller.menuMesero to javafx.fxml;
}