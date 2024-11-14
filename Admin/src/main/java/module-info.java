module com.example.cliente {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    opens repository to javafx.base;

    opens com.example.admin to javafx.fxml;
    exports com.example.admin;
    exports controller;
    opens controller to javafx.fxml;
    exports controller.menu;
    opens controller.menu to javafx.fxml;
    opens Entities to javafx.base;
}