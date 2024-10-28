package services;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class  RedireccionGeneral {

    public void IrHome(Button Home) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/menu/Home.fxml"));
            Parent root = loader.load();

            // Crea una nueva escena
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Home"); // Título de la nueva ventana
            stage.show();

            // Opcionalmente, cierra la ventana actual
            ((Stage) Home.getScene().getWindow()).close();
        } catch (IOException e) {
            System.err.println("Error al cargar la vista Home: " + e.getMessage());
        }
    }

    public void IrMenu(Button Menu) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/menu/Menu.fxml"));
            Parent root = loader.load();

            // Crea una nueva escena
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Menu"); // Título de la nueva ventana
            stage.show();

            // Opcionalmente, cierra la ventana actual
            ((Stage) Menu.getScene().getWindow()).close();
        } catch (IOException e) {
            System.err.println("Error al cargar la vista Menu: " + e.getMessage());
        }
    }

    public void IrReserva(Button Reserva) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/menu/Login.fxml"));
            Parent root = loader.load();

            // Crea una nueva escena
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Login"); // Título de la nueva ventana
            stage.show();

            // Opcionalmente, cierra la ventana actual
            ((Stage) Reserva.getScene().getWindow()).close();
        } catch (IOException e) {
            System.err.println("Error al cargar la vista Reserva: " + e.getMessage());
        }
    }

    public void IrLogin(Button login) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/menu/Antipasti.fxml"));
            Parent root = loader.load();

            // Crea una nueva escena
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Login"); // Título de la nueva ventana
            stage.show();

            // Opcionalmente, cierra la ventana actual
            ((Stage) login.getScene().getWindow()).close();
        } catch (IOException e) {
            System.err.println("Error al cargar la vista Login: " + e.getMessage());
        }
    }

    public void IrPlatosFuertes(Button platofuerte) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/menu/PlatosFuertes.fxml"));
            Parent root = loader.load();

            // Crea una nueva escena
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Platos fuertes"); // Título de la nueva ventana
            stage.show();

            // Opcionalmente, cierra la ventana actual
            ((Stage) platofuerte.getScene().getWindow()).close();
        } catch (IOException e) {
            System.err.println("Error al cargar la vista Platos Fuertes: " + e.getMessage());
        }
    }

    public void IrAntipasti(Button Antipasti) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/menu/Antipasti.fxml"));
            Parent root = loader.load();

            // Crea una nueva escena
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Antipasti"); // Título de la nueva ventana
            stage.show();

            // Opcionalmente, cierra la ventana actual
            ((Stage) Antipasti.getScene().getWindow()).close();
        } catch (IOException e) {
            System.err.println("Error al cargar la vista Antipasti: " + e.getMessage());
        }
    }

    public void IrPostre(Button Postre) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/menu/PostreMenu.fxml"));
            Parent root = loader.load();

            // Crea una nueva escena
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Postres"); // Título de la nueva ventana
            stage.show();

            // Opcionalmente, cierra la ventana actual
            ((Stage) Postre.getScene().getWindow()).close();
        } catch (IOException e) {
            System.err.println("Error al cargar la vista Postre: " + e.getMessage());
        }
    }

    public void IrBebidas(Button Bebidas) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/menu/Bebidas.fxml"));
            Parent root = loader.load();

            // Crea una nueva escena
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Bebidas"); // Título de la nueva ventana
            stage.show();

            // Opcionalmente, cierra la ventana actual
            ((Stage) Bebidas.getScene().getWindow()).close();
        } catch (IOException e) {
            System.err.println("Error al cargar la vista Bebidas: " + e.getMessage());
        }
    }

    public void IrPizzaMenu(Button pizza){
        try {
            // Carga la nueva ventana
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/menu/PizzaMenu.fxml"));
            Parent root = loader.load();

            // Crea una nueva escena
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Platos Fuertes"); // Título de la nueva ventana
            stage.show();

            //Opcionalmente, cierra la ventana actual
            ((Stage) pizza.getScene().getWindow()).close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void IrPasta(Button pasta){
        try {
            // Carga la nueva ventana
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/menu/PastaMenu.fxml"));
            Parent root = loader.load();

            // Crea una nueva escena
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Menu Pasta"); // Título de la nueva ventana
            stage.show();

            //Opcionalmente, cierra la ventana actual
            ((Stage) pasta.getScene().getWindow()).close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void IrLasanaMenu(Button M){
        try {
            // Carga la nueva ventana
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/menu/LasanaMenu.fxml"));
            Parent root = loader.load();

            // Crea una nueva escena
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Menu Lasaña"); // Título de la nueva ventana
            stage.show();

            //Opcionalmente, cierra la ventana actual
            ((Stage) M.getScene().getWindow()).close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void IrRisotoMenu(Button M){
        try {
            // Carga la nueva ventana
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/menu/RisotoMenu.fxml"));
            Parent root = loader.load();

            // Crea una nueva escena
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Menu Risoto"); // Título de la nueva ventana
            stage.show();

            //Opcionalmente, cierra la ventana actual
            ((Stage) M.getScene().getWindow()).close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
