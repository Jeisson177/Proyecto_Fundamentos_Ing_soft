module com.example.cliente2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens com.example.cliente2 to javafx.fxml;
    exports com.example.cliente2;
}