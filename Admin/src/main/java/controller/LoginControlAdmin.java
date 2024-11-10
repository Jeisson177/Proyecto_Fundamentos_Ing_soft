package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import services.loginService;

public class LoginControlAdmin {


    public TextField UsuarioField;
    public PasswordField pswdField;
    public Button btnLogin;
    private final loginService log=new loginService();
    private final RedirijirAdmin ir=new RedirijirAdmin();
    private enum errores{
        UsuarioVacio,
        ContrasenaVacia,
        Aprovado,
        rechazado
    }

    public void Login(ActionEvent actionEvent) {
        String usuario = UsuarioField.getText();
        String contrasena = pswdField.getText();

        if (usuario == null || usuario.isEmpty()) {
            errorMesasge(errores.UsuarioVacio);
            return;
        }
        if (contrasena == null || contrasena.isEmpty()) {
            errorMesasge(errores.ContrasenaVacia);
            return;

        }
        if (log.AutentificarAdmin(usuario,contrasena)) {
              errorMesasge(errores.Aprovado);
                ir.IrHome(btnLogin);
        } else {
            errorMesasge(errores.rechazado);
        }

    }

    void errorMesasge(errores e) {
        Alert alert;
        switch (e) {
            case UsuarioVacio:
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error de Inicio de Sesión");
                alert.setHeaderText("Campo de Usuario Vacío");
                alert.setContentText("El campo de usuario no debe estar vacío.");
                alert.showAndWait();
                break;
            case ContrasenaVacia:
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error de Inicio de Sesión");
                alert.setHeaderText("Campo de Contraseña Vacío");
                alert.setContentText("El campo de contraseña no debe estar vacío.");
                alert.showAndWait();
                break;
            case Aprovado:
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Inicio de Sesión Exitoso");
                alert.setHeaderText("Bienvenido");
                alert.setContentText("Inicio de sesión aprobado.");
                alert.showAndWait();
                break;
            case rechazado:
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error de Inicio de Sesión");
                alert.setHeaderText("Credenciales Incorrectas");
                alert.setContentText("Usuario o contraseña incorrectos.");
                alert.showAndWait();
                break;
        }
    }
}
