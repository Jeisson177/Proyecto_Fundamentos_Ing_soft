package controller.menuMesero;

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
import services.GestionarReserva;
import services.MesaService;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import controller.Reserva;

public class MesaControl {

    @FXML
    public ImageView fondoiz;
    @FXML
    public ImageView fondodr;
    @FXML
    public Button btnMenu;
    public Button btnReservar;
    public Button btnhome;

    public int idUsuario;

    @FXML
    public Button bntm1, bntm2, bntm3, bntm4, bntm5, bntm6, bntm7, bntm8, bntm9, bntm10;
    @FXML
    public Button bntm11, bntm12, bntm13, bntm14, bntm15, bntm16, bntm17, bntm18, bntm19;
    public Button btnHorarios;
    public Button btnRegresar;

    private final RedireccionGeneral Ira = new RedireccionGeneral();
    private final MesaService mesaRepository = new MesaService();
    private final GestionarReserva gestionarReservaService;

    private int mesaSeleccionada = 0;

    public MesaControl() {
        this.gestionarReservaService = new GestionarReserva(this);
    }

    @FXML
    public void initialize() {
        Image img1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/mesa/fondomesa.png")));
        Image img2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/mesa/fondomesa.png")));
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

    public void IrHome(ActionEvent actionEvent) { Ira.IrHome(btnhome); }

    public void IrMenu(ActionEvent actionEvent) { Ira.IrMenu(btnMenu); }

    public void IrReserva(ActionEvent actionEvent) { Ira.IrReserva(btnReservar); }

    @FXML
    public void seleccionarMesa(ActionEvent actionEvent) {
        Button clickedButton = (Button) actionEvent.getSource();
        String buttonId = clickedButton.getId();
        mesaSeleccionada = Integer.parseInt(buttonId.replace("bntm", ""));

        LocalDate fechaActual = LocalDate.now();
        LocalTime horaActual = LocalTime.now();

        if (!gestionarReservaService.estaMesaDisponible(mesaSeleccionada, fechaActual, horaActual)) {
            Optional<Reserva> reservaFuturaOpt = gestionarReservaService.obtenerReservasPorFecha(fechaActual).stream()
                    .filter(r -> r.getIdMesa() == mesaSeleccionada && r.getFechaHora().toLocalTime().isAfter(horaActual))
                    .findFirst();

            if (reservaFuturaOpt.isPresent()) {
                Reserva reservaFutura = reservaFuturaOpt.get();
                mostrarAlerta("Mesa Ocupada", "La mesa seleccionada está ocupada.\n" +
                        "Reserva existente para el cliente con ID: " + reservaFutura.getIdCliente() +
                        "\nHora de reserva: " + reservaFutura.getFechaHora().toLocalTime());
                return;
            }
        }

        if (crearReservaSinCita(mesaSeleccionada)) {
            IrMenu(actionEvent);
        } else {
            mostrarAlerta("Error", "No se pudo crear la reserva sin cita para la mesa seleccionada.");
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public int getMesa() {
        return mesaSeleccionada;
    }

    public void irHorarios(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/menu/ReservaFecha.fxml"));
            Parent root = loader.load();
            ReservaControl reservaControl = loader.getController();
            reservaControl.setMesaControl(this);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Reservar");
            stage.show();
            ((Stage) btnHorarios.getScene().getWindow()).close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setIdUsuario(int id) {
        idUsuario = id;
    }

    public boolean crearReservaSinCita(int mesaId) {
        boolean reservaCreada = gestionarReservaService.crearReservaSinCita(mesaId);
        if (reservaCreada) {
            System.out.println("Reserva sin cita creada exitosamente para la mesa " + mesaId);
            return true;
        } else {
            System.out.println("No se pudo crear la reserva sin cita. La mesa " + mesaId + " no está disponible en este momento.");
            return false;
        }
    }

    public void irInicio(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/Inicio.fxml"));
            Stage stage = (Stage) btnRegresar.getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
