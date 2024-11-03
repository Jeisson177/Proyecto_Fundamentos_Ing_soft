package controller.menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import repository.UsuarioRepository;
import services.RedireccionGeneral;

public class RegistroControl {
    @FXML
    public TextField labNombre;
    @FXML
    public TextField LabTel;
    @FXML
    public TextField labUser;
    @FXML
    public TextField LabClave;

    @FXML
    public Button btnHome;
    @FXML
    public Button btnMenu;
    @FXML
    public Button btnReservar;
    @FXML
    public Button btnRegistrarse;

    private UsuarioRepository log=new UsuarioRepository();
    private RedireccionGeneral Ira = new RedireccionGeneral();

    @FXML
    public void IrHome(ActionEvent actionEvent) {Ira.IrHome(btnHome);}
    @FXML
    public void IrMenu(ActionEvent actionEvent) {
        Ira.IrMenu(btnMenu);
    }
    @FXML
    public void IrReserva(ActionEvent actionEvent) {
        Ira.IrReserva(btnReservar);
    }

    @FXML
    public void IrMesa(ActionEvent actionEvent) {
        String email = labUser.getText();
        String password = LabClave.getText();
        String nombre = labNombre.getText();
        String telefono = LabTel.getText();

        int idUsuario = log.CrearUsuario(email, password, nombre, telefono);  // Registrar usuario


        if (idUsuario != -1) {
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
                ((Stage) btnRegistrarse.getScene().getWindow()).close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // Muestra un mensaje de error
            System.out.println("Error al crear el usuario");
        }
    }
}
