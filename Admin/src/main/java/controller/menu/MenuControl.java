package controller.menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    public Button botonPlatosFuertes;
    public Button botonPostre;
    public Button botonReservar;
    public Button botonHome;

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

    @FXML
    private void IrPlatosFuertes() { Ira.IrPlatosFuertes(botonPlatosFuertes);}

    @FXML
    private void IrPostre(){ Ira.IrPostre(botonPostre);}

   @FXML
    public void irMenu(ActionEvent actionEvent) {
       Ira.IrMenu(botonMenu);

    }

    public void IrHome(ActionEvent actionEvent) { Ira.IrHome(botonHome);

    }


    public void IrReserva(ActionEvent actionEvent) {Ira.IrReserva(botonReservar);
    }

    public void IrMenu(ActionEvent actionEvent) {
        Ira.IrMenu(botonMenu);
    }
}