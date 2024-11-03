package controller.menu;

import controller.ReservaControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import services.MesaService;

import java.util.Map;
import java.util.Objects;

public class MesaControl {

    @FXML
    public ImageView fondoiz;
    @FXML
    public ImageView fondodr;
    @FXML
    public Button btnMenu;
    public Button btnReservar;
    public Button btnhome;

    public int idUuario;

    @FXML
    public Button bntm1, bntm2, bntm3, bntm4, bntm5, bntm6, bntm7, bntm8, bntm9, bntm10;
    @FXML
    public Button bntm11, bntm12, bntm13, bntm14, bntm15, bntm16, bntm17, bntm18, bntm19;
    public Button btnHorarios;

    private RedireccionGeneral Ira = new RedireccionGeneral();
    private final MesaService mesaRepository = new MesaService();

    private int mesaSeleccionada=0;
    @FXML
    public void initialize(){

        Image img1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/mesa/fondomesa.png")));
        Image img2= new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/mesa/fondomesa.png")));

        fondodr.setImage(img1);
        fondoiz.setImage(img2);

        cargarPosicion();
    }

    private void cargarPosicion() {
        Map<Integer, Double[]> posicionesMesas = mesaRepository.obtenerPosicionesMesas();

        if (posicionesMesas.isEmpty()) {
            mostrarErrorConexion();
            return;
        }

        posicionesMesas.forEach((idMesa, ubicacion) -> {
            Button mesaButton = getMesaButton(idMesa);
            if (mesaButton != null) {
                mesaButton.setLayoutX(ubicacion[0]);
                mesaButton.setLayoutY(ubicacion[1]);
            }
        });
    }
    private void mostrarErrorConexion() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error de Conexión");
        alert.setHeaderText("No se pudo conectar con la base de datos");
        alert.setContentText("Por favor, revisa la conexión.");
        alert.showAndWait();
    }

    // Método auxiliar para obtener el botón de la mesa correspondiente por ID
    private Button getMesaButton(int idMesa) {
        switch (idMesa) {
            case 1: return bntm1;
            case 2: return bntm2;
            case 3: return bntm3;
            case 4: return bntm4;
            case 5: return bntm5;
            case 6: return bntm6;
            case 7: return bntm7;
            case 8: return bntm8;
            case 9: return bntm9;
            case 10: return bntm10;
            case 11: return bntm11;
            case 12: return bntm12;
            case 13: return bntm13;
            case 14: return bntm14;
            case 15: return bntm15;
            case 16: return bntm16;
            case 17: return bntm17;
            case 18: return bntm18;
            case 19: return bntm19;
            default: return null;
        }
    }

    public void IrHome(ActionEvent actionEvent) { Ira.IrHome(btnhome);
    }

    public void IrMenu(ActionEvent actionEvent) { Ira.IrMenu(btnMenu);
    }
    public void IrReserva(ActionEvent actionEvent) {Ira.IrReserva(btnReservar);
    }

    @FXML
    public void seleccionarMesa(ActionEvent actionEvent) {
        Button clickedButton = (Button) actionEvent.getSource(); // Obtén el botón que fue clicado
        String buttonId = clickedButton.getId(); // Obtén el ID del botón

        // Elimina el prefijo "bntm" y convierte el valor restante a un entero
        mesaSeleccionada = Integer.parseInt(buttonId.replace("bntm", ""));

    }

    // Método para obtener la mesa seleccionada (número entero)
    public int getMesa() {

        return mesaSeleccionada;
    }

    public void irHorarios(ActionEvent actionEvent) {
        try {
            // Carga la nueva ventana
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/ReservaFecha.fxml"));
            Parent root = loader.load();
            // Obtener el controlador de la nueva ventana
            ReservaControl reservaControl = loader.getController();

            // Pasar la instancia de MesaControl a ReservaControl
            reservaControl.setMesaControl(this);  // Pasar la instancia actual de MesaControl
            // Crea una nueva escena
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Reservar"); // Título de la nueva ventana
            stage.show();

            // Opcionalmente, cierra la ventana actual
            ((Stage) btnHorarios.getScene().getWindow()).close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setIdUsuario(int id){
        idUuario=id;
    }
}
