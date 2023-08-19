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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

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
        cmbMaterias.setPromptText("Seleccione una materia");
        //Llenamos el comboBox con las materias
        cmbMaterias.getItems().setAll(Configuracion.materias);
    }    
    
    @FXML
    private void GoToPreguntas() throws IOException {
        App.setRoot("Preguntas");
    }
    
    /**
     * Método que muestra el nivel máximo de la materia escogida.
     * 
     * @param event 
     */
    @FXML
    private void MostrarNivelMateria(ActionEvent event){
        Materia materia = cmbMaterias.getValue();
        textNivel.setPromptText("Nivel máximo: " + materia.getNumeroNiveles());
    }

    /**
     * Método que crea una pregunta con los datos ingresados del formulario y lo
     * guarda en la materia seleccionada.
     * Este método esta conectado con el botón BtnIngresar.
     * 
     * @param event
     * @throws IOException 
     */
    @FXML
    private void IngresarPregunta(ActionEvent event) throws IOException {
        Materia materia = null;
        try{
            //Obtenemos la materia seleccionada
            materia = cmbMaterias.getValue();
            if (materia.getPreguntas() == null){ //Inicializamos el ArrayList de preguntas si la materia no tiene ninguna pregunta
                materia.inicializarPreguntas();
            }
            
            //Validamos que se ingresen los datos de texto
            if (textEnunciado.getText().isEmpty() || textRespuestaCorrecta.getText().isEmpty() ||
                textRespuestaPosible1.getText().isEmpty() || textRespuestaPosible2.getText().isEmpty() ||
                textRespuestaPosible3.getText().isEmpty()) {
                showAlert("Error", "Todos los campos deben estar completos.");
                textEnunciado.setPromptText("Ingrese el enunciado");
                textRespuestaCorrecta.setPromptText("Ingrese una respuesta");
                textRespuestaPosible1.setPromptText("Ingrese una respuesta");
                textRespuestaPosible2.setPromptText("Ingrese una respuesta");
                textRespuestaPosible3.setPromptText("Ingrese una respuesta");
            } else {
                String enunciado = textEnunciado.getText();
                int nivel;
                do{ //Validamos que el nivel se ingrese correctamente
                    nivel = Integer.parseInt(textNivel.getText());
                    if(nivel < 1 || nivel > materia.getNumeroNiveles()){
                        textNivel.setText("");
                        textNivel.setPromptText("Ingrese un número entre 0 y " + materia.getNumeroNiveles());
                    }
                }while(nivel < 1 || nivel > materia.getNumeroNiveles());
                String correctaRespuesta = textRespuestaCorrecta.getText();
                String posibleRespuesta1 = textEnunciado.getText();
                String posibleRespuesta2 = textRespuestaPosible2.getText();
                String posibleRespuesta3 = textRespuestaPosible3.getText();
                Opcion[] opciones = {new Opcion(correctaRespuesta,Respuesta.CORRECTO), new Opcion(posibleRespuesta1,Respuesta.INCORRECTO), new Opcion(posibleRespuesta2,Respuesta.INCORRECTO), new Opcion(posibleRespuesta3,Respuesta.INCORRECTO)};
                //Agregamos la pregunta a la materia
                materia.AgregarPreguntas(new Pregunta(enunciado,nivel, opciones));
                //Regresamos a la interfaz Preguntas
                App.setRoot("Preguntas");
            }
        } catch (NullPointerException e) { //Error al ingresar texto en campo numérico
            cmbMaterias.setPromptText("Seleccione una materia");
            textNivel.setText("");
        } catch(NumberFormatException e){ //Error al no escoger una materia
            textNivel.setText("");
            textNivel.setPromptText("Ingrese un número entre 0 y " + materia.getNumeroNiveles());
        } catch(Exception e){}
    }
    
    /**
     * Método que muestra una alerta 
     * @param title El parámetro title es un String del título de la alerta
     * @param message El parámetro message es un String del mensaje de la alerta
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
