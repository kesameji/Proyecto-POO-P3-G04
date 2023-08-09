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
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
/**
 * FXML Controller class
 *
 * @author arauj
 */
public class AgregarPreguntaController implements Initializable {


    @FXML
    private Label lblTitulo;
    @FXML
    private TextField textEnunciado;
    @FXML
    private TextField textNivel;
    @FXML
    private TextField textRespuestaPosible3;
    @FXML
    private ComboBox<Materia> cmbMaterias;
    @FXML
    private TextField textRespuestaCorrecta;
    @FXML
    private TextField textRespuestaPosible1;
    @FXML
    private TextField textRespuestaPosible2;
    @FXML
    private Button BtnCancelar;
    @FXML
    private Button BtnIngresar;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbMaterias.getItems().setAll(Configuracion.materias);
    }    
    
    @FXML
    private void GoToPreguntas() throws IOException {
        App.setRoot("Preguntas");
    }
    
    @FXML
    private void MostrarNivelMateria(ActionEvent event){
        Materia materia = cmbMaterias.getValue();
        textNivel.setPromptText("Nivel máximo: " + materia.getNumeroNiveles());
    }

    @FXML
    private void IngresarPregunta(ActionEvent event) throws IOException {
        Materia materia = cmbMaterias.getValue();
        if (materia.getPreguntas() == null){
            materia.inicializarPreguntas();
        }
        
        String enunciado = textEnunciado.getText();
        int nivel = Integer.parseInt(textNivel.getText());
        String correctaRespuesta = textRespuestaCorrecta.getText();
        String posibleRespuesta1 = textRespuestaPosible1.getText();
        String posibleRespuesta2 = textRespuestaPosible2.getText();
        String posibleRespuesta3 = textRespuestaPosible3.getText();
        Opcion[] opciones = {new Opcion(correctaRespuesta,Respuesta.CORRECTO), new Opcion(posibleRespuesta1,Respuesta.INCORRECTO), new Opcion(posibleRespuesta2,Respuesta.INCORRECTO), new Opcion(posibleRespuesta3,Respuesta.INCORRECTO)};

        materia.AgregarPreguntas(new Pregunta(enunciado,nivel, opciones));

        //materia.getPreguntas().sort();

        App.setRoot("Preguntas");
    }

}
