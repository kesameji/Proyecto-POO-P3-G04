package com.mycompany.app;

import Model.Materia;
import Model.Paralelo;
import java.io.BufferedWriter;
import java.io.FileWriter;
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
        int numero;
        try {
            numero = Integer.valueOf(textNumero.getText());
        } catch (NumberFormatException e) {
            CrearAlerta("Numero de paralelo invalido", "El numero de paralelo "
                    + "ingresado no es valido");
            return;
        }

        String path = txtEstudiantes.getText();
        if (path.equals("")) {
            CrearAlerta("Ruta de estudiantes", "No se ingreso ninguna ruta de los estudiantes");
        }

        boolean pathValido = validarPath(path, ma, numero);

        if (paraleloDisponible(numero)) {
            if (pathValido) {
                Paralelo pa = new Paralelo(numero, ma.getTermino(), ma);
                pa.setPathEstudiantes(path);
                ma.AgregarParalelo(pa);
            } else {
                CrearAlerta("Ruta invalida", "La ruta ingresada no es valida o "
                        + "no sigue el formato indicado");
            }
        } else {
            CrearAlerta("Paralelo repetido", "El paralelo ya existe dentro de "
                    + "la materia " + ma);
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

    private boolean paraleloDisponible(int numero) {
        for (Paralelo pa : ma.getParalelos()) {
            if (pa.getNumeroParalelo() == numero) {
                return false;
            }
        }
        return true;
    }

    private boolean validarPath(String path, Materia ma, int numero) {
        if (path.endsWith(".csv")) {
            int start = path.lastIndexOf("\\");
            int last = path.lastIndexOf(".");
            String nombreArchivo = path.substring(start + 1, last);
            String[] datos = nombreArchivo.split("-");
            if (!datos[0].equals(ma.getCodigo())) {
                return false;
            }
            if (!datos[1].equals(String.valueOf(numero))) {
                return false;
            }
            if (!datos[2].equals(ma.getTermino().getAnio())) {
                return false;
            }
            if (!datos[3].equals(String.valueOf(ma.getTermino().getNumeroTermino()))) {
                return false;
            }
        } else if (path.contains(".")) {
            return false;
        }
        return true;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
