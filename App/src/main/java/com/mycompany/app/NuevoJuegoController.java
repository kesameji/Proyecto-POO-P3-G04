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
    
    private void llenarMaterias(){
        cmbMateria.getItems().setAll(Configuracion.materias);
    }

    @FXML
    private void StartGame(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("Game.fxml"));
        GameController game = new GameController();

        loader.setController(game);//se asigna el controlador
        AnchorPane root = (AnchorPane) loader.load();//carga los objetos del fxml
        Materia ma = cmbMateria.getValue();
        Paralelo pa = cmbParalelo.getValue();
        Juego juego = new Juego(ma.getTermino(), ma, pa, pa.getEstudiantes().get(0),pa.getEstudiantes().get(1) );
        game.cargarDatos(juego, cmbMateria.getValue().getPreguntas());
        App.changeRoot(root);
        
    }
      
}
