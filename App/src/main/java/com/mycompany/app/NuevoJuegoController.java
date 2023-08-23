package com.mycompany.app;

import Model.Configuracion;
import Model.Estudiante;
import Model.Juego;
import Model.Materia;
import Model.Paralelo;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class NuevoJuegoController implements Initializable {

    @FXML
    private Button BtnVolver;
    @FXML
    private ComboBox<Materia> cmbMateria;
    @FXML
    private ComboBox<Paralelo> cmbParalelo;
    @FXML
    private TextField textfieldNumero;
    @FXML
    private TextField textfieldParticipante;
    @FXML
    private TextField textfieldApoyo;

    private ArrayList<Estudiante> estudiantes;
    @FXML
    private Button Start;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        llenarMaterias();

    }

    @FXML
    private void GoToStart(ActionEvent event) throws IOException {
        App.setRoot("Start");
    }

    @FXML
    private void filtrarParalelos(ActionEvent event) {
        Materia ma = cmbMateria.getValue();
        cmbParalelo.getItems().setAll(ma.getParalelos());
    }

    @FXML
    private void filtrarEstudiantes(ActionEvent event) {
        estudiantes = cmbParalelo.getValue().getEstudiantes();

    }

    private void llenarMaterias() {
        cmbMateria.getItems().setAll(Configuracion.terminoJuego.getMaterias());
    }

    @FXML
    private void StartGame(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("Game.fxml"));
        GameController game = new GameController();

        loader.setController(game);//se asigna el controlador
        AnchorPane root = (AnchorPane) loader.load();//carga los objetos del fxml

        Materia ma = cmbMateria.getValue();
        if (cmbMateria.getValue() == null) {
            CrearAlerta("Materia No Selecionada", "No se a seleccionada ninguna materia\npara continuar por favor escoja una");
            return;
        }

        Paralelo pa = cmbParalelo.getValue();
        if (cmbParalelo.getValue() == null) {
            CrearAlerta("Paralelo No Selecionado", "No se a seleccionado ningun paralelo\npara continuar por favor escoja uno");
            return;
        }
        int numeroNiveles;
        try {
            numeroNiveles = Integer.parseInt(textfieldNumero.getText());
        } catch (NumberFormatException e) {
            CrearAlerta("Numero de preguntas por nivel no valido", "No se ingreso ningun numero "
                    + "o se ingreso un valor no valido \nSe ingreso: "+textfieldNumero.getText());
            return;
        }

        Estudiante es = pa.obtenerEstudiante(textfieldParticipante.getText());
        if (es == null) {
            CrearAlerta("Estudiante No Encontrado", "El estudiante con matricula "
                    + textfieldParticipante.getText() + " no esta en el paralelo o no existe.");
            return;
        }

        Estudiante ap = pa.obtenerEstudiante(textfieldApoyo.getText());
        if (ap == null) {
            CrearAlerta("Estudiante No Encontrado", "El estudiante con matricula "
                    + textfieldParticipante.getText() + " no esta en el paralelo o no existe.");
            return;
        }
        if (ap.equals(es)) {
            CrearAlerta("Estudiante Repetido", "El estudiante con matricula "
                    + textfieldParticipante.getText() + " no puede ser el mismo que participa.");
            return;
        }

        Juego juego = new Juego(Configuracion.terminoJuego, ma, pa, es, ap, ma.getPreguntas());
        game.cargarDatos(juego, numeroNiveles);
        App.changeRoot(root);

    }

    private void CrearAlerta(String titulo, String contenido) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Notificacion");
        alert.setHeaderText(titulo);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

}
