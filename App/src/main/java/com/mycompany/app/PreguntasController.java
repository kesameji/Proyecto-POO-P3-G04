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
    private TableView<Pregunta> TvPreguntas;
    @FXML
    private TableColumn<Pregunta, Integer> ColNivel;
    @FXML
    private TableColumn<Pregunta, String> ColEnunciado;
    @FXML
    private TableColumn<Pregunta, String> ColOpcion;
    @FXML
    private TableColumn<Pregunta, String> ColOpcionPosible1;
    @FXML
    private TableColumn<Pregunta, String> ColOpcionPosible2;
    @FXML
    private TableColumn<Pregunta, String> ColOpcionPosible3;
    @FXML
    private Button BtnAgregar;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbMateria.getItems().setAll(Configuracion.materias);
        
        ColNivel.setCellValueFactory(new PropertyValueFactory<>("nivel"));
        ColNivel.setMaxWidth(1000);
        ColEnunciado.setCellValueFactory(new PropertyValueFactory<>("enunciado"));
        
        
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
        //TvPreguntas.getItems().clear(); Opcional
        //TvOpciones.getItems().clear(); Opcional
        
        
        if (materia.getPreguntas() == null){
            materia.inicializarPreguntas();
        }
        ArrayList<Pregunta> preguntas = materia.getPreguntas();
        ColOpcion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getOpciones()[0].getTexto()));
        ColOpcionPosible1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getOpciones()[1].getTexto()));
        ColOpcionPosible2.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getOpciones()[2].getTexto()));
        ColOpcionPosible3.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getOpciones()[3].getTexto()));
        TvPreguntas.getItems().setAll(preguntas);
    }
}
