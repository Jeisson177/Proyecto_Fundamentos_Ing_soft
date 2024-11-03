package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import services.loginService;

import java.util.List;

public class loginControl {
    @FXML
    public Button btnHome;
    @FXML
    public Button btnMenu;
    @FXML
    public Button btnReservar;
    @FXML
    public Button btnRegistrar;
    @FXML
    public TextField labUser;
    @FXML
    public TextField LabClave;
    public Button btnLogin;
    public Button btnVerReservas;

    private loginService log=new loginService();
    private RedireccionGeneral Ira = new RedireccionGeneral();


    @FXML
    public void IrHome(ActionEvent actionEvent) {
        Ira.IrHome(btnHome);

    }
    @FXML
    public void IrMenu(ActionEvent actionEvent) {
        Ira.IrMenu(btnMenu);
    }
    @FXML
    public void IrReserva(ActionEvent actionEvent) {
        Ira.IrReserva(btnReservar);
    }
    @FXML
    public void IrRegistro(ActionEvent actionEvent) { Ira.IrRegistro(btnLogin);}
    @FXML
    public void IrMesa(ActionEvent actionEvent) {
        String email = labUser.getText();
        String password = LabClave.getText();
        int idUsuario = log.AutentificarUsuario(email, password);  // Autenticación de usuario

        if (idUsuario != -1) {  // Si la autenticación es exitosa
            try {
                // Carga la nueva ventana
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/Mesa.fxml"));
                Parent root = loader.load();

                // Obtener el controlador de la nueva ventana
                MesaControl mesaControl = loader.getController();

                // Pasar el ID del usuario a MesaControl
                mesaControl.setIdUsuario(idUsuario);

                // Crea una nueva escena
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Mesa");
                stage.show();

                // Opcionalmente, cerrar la ventana actual
                ((Stage) btnRegistrar.getScene().getWindow()).close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Error: Usuario o contraseña incorrecta.");
        }
    }
    @FXML
    public void IrVerReservas(ActionEvent actionEvent) {
        String email = labUser.getText();
        String password = LabClave.getText();
        int idUsuario = log.AutentificarUsuario(email, password);  // Autenticación de usuario

        if (idUsuario != -1) {  // Si la autenticación es exitosa
            // Obtener reservas del usuario
            List<String> reservas = log.ConsultarReservas(idUsuario);

            if (!reservas.isEmpty()) {
                // Crear una cadena con todas las reservas
                StringBuilder reservasTexto = new StringBuilder("Reservas del usuario:\n");
                for (String reserva : reservas) {
                    reservasTexto.append("- ").append(reserva).append("\n");
                }

                // Mostrar ventana emergente con las reservas
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Reservas del Usuario");
                alert.setHeaderText("Reservas actuales del usuario:");
                alert.setContentText(reservasTexto.toString());
                alert.showAndWait();
            } else {
                // Mostrar mensaje si no hay reservas
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Reservas del Usuario");
                alert.setHeaderText(null);
                alert.setContentText("No hay reservas para este usuario.");
                alert.showAndWait();
            }
        } else {
            System.out.println("Error: Usuario o contraseña incorrecta.");
        }
    }
}
