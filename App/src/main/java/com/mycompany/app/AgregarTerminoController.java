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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void GoToTerminosAcademicos(ActionEvent event) throws IOException {
        App.setRoot("TerminosAcademicos");
    }

    @FXML
    private void IngresarTermino(ActionEvent event) throws IOException {
        if (!BtnIngresar.getText().equals("Guardar Cambios")) {
            String anio = textAño.getText();
            int numTermino = Integer.parseInt(textNumTermino.getText());

            TerminoAcademico ta = new TerminoAcademico(anio,numTermino);
            terminos.add(ta);

            Configuracion.terminos.add(ta);
        }

        App.setRoot("TerminosAcademicos");
    }
    
}
