package com.mycompany.app;

import Model.Materia;
import Model.Paralelo;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AgregarParaleloController implements Initializable {

    @FXML
    private Label lblMateria;
    @FXML
    private TextField textNumero;
    @FXML
    private Button BtnCancelar;
    @FXML
    private Button BtnIngresar;
    @FXML
    private TextField txtEstudiantes;

    private Materia ma;

    @FXML
    private void GoToMaterias(ActionEvent event) throws IOException {
        App.setRoot("MateriasParalelos");
    }

    @FXML
    private void IngresarParalelo(ActionEvent event) throws IOException {
        int numero = Integer.valueOf(textNumero.getText());
        String path = txtEstudiantes.getText();

        if (paraleloNoExiste(numero)) {
            Paralelo pa = new Paralelo(numero, ma.getTermino(), ma);
            pa.setPathEstudiantes(path);
            ma.AgregarParalelo(pa);
        } else {
            CrearAlerta("Paralelo repetido", "El paralelo ya existe dentro de la materia " + ma);
            App.setRoot("MateriasParalelos");
        }

        App.setRoot("MateriasParalelos");
    }

    public void seleccionarMateria(Materia ma) {
        this.ma = ma;
    }

    private void CrearAlerta(String titulo, String contenido) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Notificacion");
        alert.setHeaderText(titulo);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    private boolean paraleloNoExiste(int numero) {
        for (Paralelo pa : ma.getParalelos()) {
            if (pa.getNumeroParalelo() == numero) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}