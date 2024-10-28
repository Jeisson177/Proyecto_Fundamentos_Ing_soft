module com.example.cliente {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.admin to javafx.fxml;
    exports com.example.admin;
    exports controller;
    opens controller to javafx.fxml;
    exports controller.menu;
    opens controller.menu to javafx.fxml;
}