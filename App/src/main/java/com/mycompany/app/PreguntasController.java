/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.app;

import Model.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

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
    private TableColumn<Opcion, String> ColOpcionCorrecta;
    @FXML
    private TableColumn<Opcion, String> ColOpcionPosible;
    @FXML
    private TableView<Pregunta> TvPreguntas;
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
        ColOpcionCorrecta.setCellValueFactory(new PropertyValueFactory("texto"));
        ColOpcionCorrecta.setCellValueFactory(new PropertyValueFactory("texto"));
    }
    
    @FXML
    private void GoToConfiguracion(ActionEvent event) throws IOException {
        App.setRoot("Configuracion");
    }
    
    @FXML
    private void AgregarPregunta(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("AgregarPregunta.fxml"));
        AgregarPreguntaController ct = new AgregarPreguntaController();

        loader.setController(ct);//se asigna el controlador
        AnchorPane root = (AnchorPane) loader.load();//carga los objetos del fxml
        
        App.changeRoot(root);
    }

}
