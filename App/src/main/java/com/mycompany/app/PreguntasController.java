/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.app;

import Model.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Santiago
 */
public class PreguntasController implements Initializable {

    @FXML
    private Button BtnVolver;
    @FXML
    private ComboBox<Materia> cmbMateria;
    @FXML
    private TableColumn<Pregunta, Integer> ColNivel;
    @FXML
    private TableColumn<Pregunta, String> ColEnunciado;
    @FXML
    private TableView<Pregunta> TvPreguntas;
    @FXML
    private TableView<Opcion[]> TvOpciones;
    @FXML
    private TableColumn<Opcion, String> ColOpcionPosible1;
    @FXML
    private TableColumn<Opcion, String> ColOpcionCorrecta1;
    @FXML
    private TableColumn<Opcion, String> ColOpcionPosible2;
    @FXML
    private TableColumn<Opcion, String> ColOpcionPosible3;
    @FXML
    private Button BtnAgregar;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbMateria.getItems().setAll(Configuracion.materias);
        
        ColNivel.setCellValueFactory(new PropertyValueFactory<>("nivel"));
        ColEnunciado.setCellValueFactory(new PropertyValueFactory<>("enunciado"));
        /*ColOpcionCorrecta1.setCellFactory(cellData -> {
            Opcion[] opciones = cellData
            if (opciones != null && opciones.length > 0) {
                return new SimpleStringProperty(opciones[0].getTexto());
            } else {
                return new SimpleStringProperty("");
        }});
        */
        //ColOpcionPosible1.setCellFactory(new PropertyValueFactory<>("texto"));
        //ColOpcionPosible2.setCellFactory(new PropertyValueFactory<>("texto"));
        //ColOpcionPosible3.setCellFactory(new PropertyValueFactory<>("texto"));
        
        
        //TvPreguntas.getItems().setAll(Configuracion.materias.get(0).getPreguntas());
    }
    
    @FXML
    private void GoToConfiguracion(ActionEvent event) throws IOException {
        App.setRoot("Configuracion");
    }
    
    @FXML
    private void AgregarPregunta(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("AgregarPregunta.fxml"));
        //AgregarPreguntaController ct = new AgregarPreguntaController();

        //loader.setController(ct);//se asigna el controlador
        AnchorPane root = (AnchorPane) loader.load();//carga los objetos del fxml
        
        App.changeRoot(root);
    }
    
    @FXML
    private void mostrarPreguntaMaterias(ActionEvent event) {
        Materia materia = cmbMateria.getValue();
        //ObservableList<Pregunta> datosPregunta = FXCollections.observableArrayList();
        //ObservableList<Opcion[]> datosOpciones = FXCollections.observableArrayList();
        if (materia.getPreguntas() == null){
            materia.inicializarPreguntas();
        }
        ArrayList<Pregunta> preguntas = materia.getPreguntas();
        /*for (Pregunta p: preguntas){
            datosPregunta.add(new Pregunta(p.getEnunciado(),p.getNivel()));
            datosOpciones.add(p.getOpciones());
        }*/
        TvPreguntas.getItems().setAll(preguntas);
        //TvOpciones.getItems().setAll(preguntas.get(0).getOpciones()[0]);
        //TvPreguntas.setItems(datosPregunta);
        //TvOpciones.setItems(datosOpciones);
    }
}
