/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.app;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import Model.*;
import java.io.IOException;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
/**
 * FXML Controller class
 *
 * @author arauj
 */
public class DetalleJuegoController implements Initializable {


    @FXML
    private Label lblFecha;
    @FXML
    private Label lblParticipante;
    @FXML
    private Label lblApoyo;
    @FXML
    private Label lblNivelMax;
    @FXML
    private Label lblTiempo;
    @FXML
    private Label lblPremio;
    @FXML
    private TableView<Juego> TvPreguntas;
    @FXML
    private TableColumn<Juego, String> ColEnunciado;
    @FXML
    private TableColumn<Juego, Integer> ColNivel;
    @FXML
    private TableColumn<Juego, Comodin> ColComodin;
    @FXML
    private Button BtnVolver;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    @FXML
    public void GoToReportes(ActionEvent event) throws IOException{
        App.setRoot("Reportes");
    }
    
    public void verDetalleJuego(Juego j){
        lblFecha.setText(""+j.getFecha());
        lblParticipante.setText(j.getParticipante().getNombre());
        lblApoyo.setText(j.getApoyo().getNombre());
        lblNivelMax.setText(""+j.getNivelActual());
        lblTiempo.setText(""+j.getTiempo());
        lblPremio.setText(j.getPremio());
        
        //ColEnunciado.cellValueFactoryProperty(new PropertyValueFactory<>("enunciado"));
        ColEnunciado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getListaPreguntas().get(0).getEnunciado()));
        //ColNivel.cellValueFactoryProperty(new PropertyValueFactory<>("nivel"));
        //ColNivel.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getListaPreguntas().get(0).getNivel()));
        //ColComodin.cellValueFactoryProperty(new PropertyValueFactory<>("comodin"));
        //ColComodin.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getComodines().get(0).getNombre()));
        TvPreguntas.getItems().addAll(j);
    }
}
