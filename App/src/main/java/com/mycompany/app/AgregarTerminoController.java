/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.app;

import Model.Configuracion;
import static Model.Configuracion.terminos;
import Model.TerminoAcademico;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author EQUIPO
 */
public class AgregarTerminoController implements Initializable {

    @FXML
    private Label lblTitulo;
    @FXML
    private TextField textAño;
    @FXML
    private TextField textNumTermino;
    @FXML
    private Button BtnCancelar;
    @FXML
    private Button BtnIngresar;
    
    private String anioActual;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        anioActual = "2023";
    }    

    @FXML
    private void GoToTerminosAcademicos(ActionEvent event) throws IOException {
        App.setRoot("TerminosAcademicos");
    }

    @FXML
    private void IngresarTermino(ActionEvent event) throws IOException {
        if (!BtnIngresar.getText().equals("Guardar Cambios")) {
            try{
                String anio = textAño.getText();
                int numTermino = Integer.parseInt(textNumTermino.getText());
                if (Integer.parseInt(anio) < Integer.parseInt(anioActual)) {
                    textAño.setText("");
                    showAlert("Error", "El año tiene que ser mayor o igual al actual.");
                    textAño.setPromptText("Ingrese año de término");
                } else if (encontrarTermino(anio,numTermino) != -1){
                    showAlert("Error", "No se pueden repetir los términos.\nIngrese nuevamente.");
                    textAño.setPromptText("Ingrese año de término");
                    textNumTermino.setText("");
                    textNumTermino.setPromptText("Ingrese número de término");
                } else {
                    TerminoAcademico ta = new TerminoAcademico(anio,numTermino);
                    terminos.add(ta);
                    App.setRoot("TerminosAcademicos");
                }
            } catch (NumberFormatException e) {
                textAño.setText("");
                textAño.setPromptText("Ingrese año de término");
                textNumTermino.setText("");
                textNumTermino.setPromptText("Ingrese número de término");
            }
            
        }
    }
    
    /**
     * Método que muestra una alerta 
     * @param title El parámetro title es un String del título de la alerta
     * @param message El parámetro message es un String del mensaje de la alerta
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    /*
    * Método estático que recibe el año y número de un término académico y 
    * devuelve la posición de un término académico cargada en el programa que
    * coincida con el año y número recibido.
    * Devuelve -1 si no se encuentra un término académico.
    * 
    * @param anio El parámetro anio es un String del año del término académico
    * @param numero El parámetro numero es el número del término académico
    * @return La posición de una materia cargada en el programa
     */
    public static int encontrarTermino(String anio, int numero) {
        int i = 0;
        for (TerminoAcademico ta : terminos) {
            if (anio.equals(ta.getAnio()) && numero == ta.getNumeroTermino()) {
                return i;
            }
            i++;
        }
        return -1;
    }
    
    public void editarTermino(TerminoAcademico ta) {
        lblTitulo.setText("Editar Término Academico");
        textAño.setText(ta.getAnio());
        System.out.println(ta.getAnio());
        textNumTermino.setText(""+ta.getNumeroTermino());
        BtnIngresar.setText("Guardar Cambios");

        BtnIngresar.setOnMouseClicked(r -> {
            try {
                guardarCambios(ta);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

    }
    
    private void guardarCambios(TerminoAcademico ta) throws IOException {
        if (BtnIngresar.getText().equals("Guardar Cambios")) {
            try{
                String anio = textAño.getText();
                if(!anio.isEmpty()){
                    ta.setAnio(textAño.getText());
                } else {
                    textAño.setText(ta.getAnio());
                }
                ta.setNumeroTermino(Integer.parseInt(textNumTermino.getText()));
                App.setRoot("TerminosAcademicos");
            } catch (NumberFormatException e) {
                textNumTermino.setText(""+ta.getNumeroTermino());
            }
        }

    }
}
