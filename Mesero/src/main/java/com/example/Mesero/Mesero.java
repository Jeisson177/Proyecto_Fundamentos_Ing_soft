package com.example.Mesero;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Mesero extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        System.out.println(getClass().getResource("/vista/Inicio.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/Inicio.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 700, 400);
        stage.setTitle("Bella Venture Mesero");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}
