/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.app;

import Model.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.util.Callback;

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
    private TableColumn<Pregunta, Void> ColEliminar;
    @FXML
    private Button BtnAgregar;
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbMateria.setPromptText("Seleccione una materia");
        cmbMateria.getItems().setAll(Configuracion.materias);
        
        ColNivel.setCellValueFactory(new PropertyValueFactory<>("nivel"));
        ColNivel.setMaxWidth(1000);
        ColEnunciado.setCellValueFactory(new PropertyValueFactory<>("enunciado"));
        agregarEliminacionPreguntas();
        ColOpcion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getOpciones()[0].getTexto()));
        ColOpcionPosible1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getOpciones()[1].getTexto()));
        ColOpcionPosible2.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getOpciones()[2].getTexto()));
        ColOpcionPosible3.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getOpciones()[3].getTexto()));
        
        ColEliminar.setMaxWidth(2000);
    }
    
    @FXML
    private void GoToConfiguracion(ActionEvent event) throws IOException {
        App.setRoot("Configuracion");
    }
    
    /**
     * Método que cambia a la interfaz AgregarPregunta.
     * Este método esta asociado al botón BtnAgregar.
     * 
     * @param event
     * @throws IOException 
     */
    @FXML
    private void AgregarPregunta(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("AgregarPregunta.fxml"));
        //AgregarPreguntaController ct = new AgregarPreguntaController();

        //loader.setController(ct);//se asigna el controlador
        AnchorPane root = (AnchorPane) loader.load();//carga los objetos del fxml
        
        App.changeRoot(root);
    }
    
    
    /**
     * Método que muestra las preguntas de la materia seleccionada en el TableView
     * 
     * @param event 
     */
    @FXML
    private void mostrarPreguntaMaterias(ActionEvent event) {
        //Guardamos la materia seleccionada
        Materia materia = cmbMateria.getValue();
        //Inicializamos el ArrayList de preguntas de la materia si no tiene preguntas
        if (materia.getPreguntas() == null){
            materia.inicializarPreguntas();
        }
        ArrayList<Pregunta> preguntas = materia.getPreguntas();
        //Ordenamos las preguntas por su nivel
        Collections.sort(preguntas);
        //Agregamos las preguntas al TableView
        TvPreguntas.getItems().setAll(preguntas);
    }
    
    /**
     * Método que elimina una pregunta y actualiza el TableView.
     * 
     * @param p El parámetro p de tipo Pregunta es la pregunta que se desea eliminar
     */
    private void eliminarPregunta(Pregunta p) {
        //Guardamos la materia seleccionada
        Materia materia = cmbMateria.getValue();
        //Eliminamos la pregunta de la materia seleccionada
        materia.getPreguntas().remove(p);
        //Actualizamos las preguntas en el TableView
        TvPreguntas.getItems().setAll(materia.getPreguntas());
    }
    
    /**
     * Método que agrega los botones de eliminación en la columna ColEliminar
     */
    private void agregarEliminacionPreguntas() {
        Callback<TableColumn<Pregunta, Void>, TableCell<Pregunta, Void>> cellFactory = new Callback<TableColumn<Pregunta, Void>, TableCell<Pregunta, Void>>() {
            @Override
            public TableCell<Pregunta, Void> call(final TableColumn<Pregunta, Void> param) {
                TableCell<Pregunta, Void> cell = new TableCell<Pregunta, Void>() {

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            Pregunta p = getTableView().getItems().get(getIndex());
                            //boton eliminar
                            Button btnEl = new Button("❌");
                            btnEl.setOnAction(r -> eliminarPregunta(p));

                            //se agrega el botón en la celda
                            setGraphic(btnEl);
                        }
                    }
                };
                return cell;
            }
        };
        ColEliminar.setCellFactory(cellFactory);
    }
}
