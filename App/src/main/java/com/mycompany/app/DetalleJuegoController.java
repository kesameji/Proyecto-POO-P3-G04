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
import java.util.ArrayList;
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
    private TableView<Pregunta> TvPreguntas;
    @FXML
    private TableColumn<Pregunta, String> ColEnunciado;
    @FXML
    private TableColumn<Pregunta, Integer> ColNivel;
    @FXML
    private TableColumn<Pregunta, String> ColComodin;
    @FXML
    private Button BtnVolver;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ColEnunciado.prefWidthProperty().bind(TvPreguntas.widthProperty().multiply(0.6));
        ColNivel.prefWidthProperty().bind(TvPreguntas.widthProperty().multiply(0.1));
        ColComodin.prefWidthProperty().bind(TvPreguntas.widthProperty().multiply(0.3));
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
        
        ColEnunciado.setCellValueFactory(new PropertyValueFactory<>("enunciado"));
        ColNivel.setCellValueFactory(new PropertyValueFactory<>("nivel"));
        //ColComodin.cellValueFactoryProperty(new PropertyValueFactory<>("comodin"));
        //ColComodin.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getComodines().get(0).getNombre()));
        TvPreguntas.getItems().addAll(preguntasContestadas(j.getListaPreguntas(),j.getPreguntasContestadas()));
    }
    
    public ArrayList<Pregunta> preguntasContestadas(ArrayList<Pregunta> preguntasJuego, int preguntasContestadas){
        ArrayList<Pregunta> listaPreguntasContestadas = new ArrayList<>();
        for(int i = 0; i < preguntasContestadas; i++) listaPreguntasContestadas.add(preguntasJuego.get(i));
        return listaPreguntasContestadas;
    }
}
