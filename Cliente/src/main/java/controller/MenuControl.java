package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import services.RedireccionGeneral;

import java.util.Objects;

public class MenuControl {
    @FXML
    public Button antipastiMenu;
    public ImageView antipastiImage;
    public ImageView platoFuerteImage;
    public ImageView postresImage;
    public ImageView bebidasImage;
    public Button botonbebidasImage;
    public Button botonMenu;

    private RedireccionGeneral Ira=new RedireccionGeneral();
    @FXML
    public void initialize() {
        // Cargar la imagen al inicializar la vista
        Image imgAntipasti = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/menuPrincipal/port_antipasti.png")));
        Image imgPlatoFuerte = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/menuPrincipal/port_platos_fuertes.png")));
        Image imgPostres = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/menuPrincipal/port_postres.png")));
        Image imgBebidas = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/menuPrincipal/port_bebidas.png")));


        antipastiImage.setImage(imgAntipasti); // Establecer la imagen en el ImageView
        platoFuerteImage.setImage(imgPlatoFuerte);
        postresImage.setImage(imgPostres);
        bebidasImage.setImage(imgBebidas);
    }

    @FXML
    private void abrirAntipasti() {
        Ira.IrAntipasti(antipastiMenu);
    }

    @FXML
    private void abrirBebidas() {
        Ira.IrBebidas(botonbebidasImage);
    }

    public void irMenu(ActionEvent actionEvent) {
       Ira.IrMenu(botonMenu);

    }
}