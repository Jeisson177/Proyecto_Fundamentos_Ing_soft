package com.example.Mesero;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import persistencia.DBConnectionManager;

public class Mesero extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        DBConnectionManager connMgr = new DBConnectionManager();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/menu/PostreMenu.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 700, 400);
        stage.setTitle("Bella Venture mesero");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}