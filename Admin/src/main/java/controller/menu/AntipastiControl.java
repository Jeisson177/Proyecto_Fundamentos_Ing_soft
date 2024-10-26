package controller.menu;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.RedireccionGeneral;

import java.util.Objects;

import java.sql.*;

public class AntipastiControl {

    @FXML
    public ImageView carpaccioResImage;
    public ImageView carpaccioSalmonImage;
    public ImageView focacciaGenoveseImage;
    public ImageView focacciaDolceImage;
    public ImageView focacciaVenetaImage;
    public ImageView focacciaPuglieseImage;
    public ImageView ensaladaCesarRomanaImage;
    public ImageView ensaldaPortobelloImage;
    public ImageView ensaladaVentrescaImage;
    public ImageView tablaQuesosImage;

    public Button menuBoton;
    public Button reservarBoton;
    public Button homeBoton;

    public Label carpaccioResPrecio;
    public Label carpaccioSalmonPrecio;
    public Label focacciaGenovesePrecio;
    public Label focacciaDolcePrecio;
    public Label focacciaVenetaPrecio;
    public Label focacciaPugliesePrecio;
    public Label ensaladaCesarRomanaPrecio;
    public Label ensaldaPortobelloPrecio;
    public Label ensaladaVentrescaPrecio;
    public Label tablaQuesosPrecio;

    private RedireccionGeneral Ira=new RedireccionGeneral();
    @FXML
    public void initialize() {

        // Cargar la imagen al inicializar la vista
        Image img1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/antipasti/carpaccio_res.png")));
        Image img2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/antipasti/carpaccio_salmon.png")));
        Image img3 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/antipasti/focaccia_genovese.png")));
        Image img4 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/antipasti/focaccia_dolce.png")));
        Image img5 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/antipasti/focaccia_veneta.png")));
        Image img6 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/antipasti/focaccia_pugliese.png")));
        Image img7 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/antipasti/ensalada_cesar_romana.png")));
        Image img8 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/antipasti/ensalada_portobello.png")));
        Image img9 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/antipasti/ensalada_ventresca.png")));
        Image img10 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/antipasti/tabal_quesos.png")));

        carpaccioResImage.setImage(img1); // Establecer la imagen en el ImageView
        carpaccioSalmonImage.setImage(img2);
        focacciaGenoveseImage.setImage(img3);
        focacciaDolceImage.setImage(img4);
        focacciaVenetaImage.setImage(img5);
        focacciaPuglieseImage.setImage(img6);
        ensaladaCesarRomanaImage.setImage(img7);
        ensaldaPortobelloImage.setImage(img8);
        ensaladaVentrescaImage.setImage(img9);
        tablaQuesosImage.setImage(img10);

        cargarPreciosEntrada();

    }

    private void cargarPreciosEntrada() {
        //String url = "jdbc:mysql://localhost:3307/proyecto ingesoft";
        String url = "jdbc:mysql://localhost:3306/proyecto ingesoft";
        String user = "root";
        String password = "cl";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT NOMBRE, PRECIO FROM PLATO WHERE CATEGORIA = 'Entrada'";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String nombre = resultSet.getString("NOMBRE");
                int precio = resultSet.getInt("PRECIO");

                // Asignar los precios a los Labels correspondientes
                switch (nombre) {
                    case "Carpaccio de Res":
                        carpaccioResPrecio.setText(precio + " COP");
                        break;
                    case "Carpaccio de Salmon":
                        carpaccioSalmonPrecio.setText(precio + " COP");
                        break;
                    case "Focaccia Genovese":
                        focacciaGenovesePrecio.setText(precio + " COP");
                        break;
                    case "Focaccia Dolce":
                        focacciaDolcePrecio.setText(precio + " COP");
                        break;
                    case "Focaccia Veneta":
                        focacciaVenetaPrecio.setText(precio + " COP");
                        break;
                    case "Focaccia Pugliese":
                        focacciaPugliesePrecio.setText(precio + " COP");
                        break;
                    case "Ensalada Cesar Romana":
                        ensaladaCesarRomanaPrecio.setText(precio + " COP");
                        break;
                    case "Ensalada de Portobello":
                        ensaldaPortobelloPrecio.setText(precio + " COP");
                        break;
                    case "Ensalada de Ventresca":
                        ensaladaVentrescaPrecio.setText(precio + " COP");
                        break;
                    case "Tabla de Quesos":
                        tablaQuesosPrecio.setText(precio + " COP");
                        break;
                }
            }

        } catch (SQLException e) {
            {  // Especificar el tipo de excepción
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error de Conexión");
                alert.setHeaderText("No se pudo conectar con la base de datos");
                alert.setContentText(e.getMessage());  // Mostrar el error específico
                alert.showAndWait();
            }
        }
    }

    @FXML

    public void menuButtonClick(ActionEvent actionEvent) { Ira.IrMenu(menuBoton); }

    public void reservarButtonClick(ActionEvent actionEvent) {
       Ira.IrReserva(reservarBoton);
    }

    public void homeButtonClick(ActionEvent actionEvent) {
        Ira.IrHome(homeBoton);
    }

}

