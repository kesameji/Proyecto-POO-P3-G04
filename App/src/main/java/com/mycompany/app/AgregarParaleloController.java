package com.mycompany.app;

import Model.Materia;
import Model.Paralelo;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class AgregarParaleloController implements Initializable{

    @FXML
    private Label lblMateria;
    @FXML
    private TextField textNumero;
    @FXML
    private Button BtnCancelar;
    @FXML
    private Button BtnIngresar;
    
    private Materia ma;   

    @FXML
    private void GoToMaterias(ActionEvent event) throws IOException {
        App.setRoot("MateriasParalelos");
    }

    @FXML
    private void IngresarParalelo(ActionEvent event) throws IOException {
        int numero = Integer.valueOf(textNumero.getText());
        Paralelo pa = new Paralelo(numero, ma.getTermino(), ma);
        ma.AgregarParalelo(pa);
        
        
        App.setRoot("MateriasParalelos");
    }
    
    public void seleccionarMateria(Materia ma){
        this.ma = ma;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }
    
}
