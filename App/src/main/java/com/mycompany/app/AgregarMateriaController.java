package com.mycompany.app;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import Model.*;
import java.io.IOException;

public class AgregarMateriaController implements Initializable {

    @FXML
    private TextField textCodigo;
    @FXML
    private TextField textNombre;
    @FXML
    private TextField textNiveles;
    @FXML
    private ComboBox<TerminoAcademico> cmbTerminos;
    @FXML
    private Button BtnCancelar;
    @FXML
    private Button BtnIngresar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbTerminos.getItems().setAll(Configuracion.terminos);
    }    

    @FXML
    private void GoToMaterias(ActionEvent event) throws IOException {
        App.setRoot("MateriasParalelos");
    }

    @FXML
    private void IngresarMateria(ActionEvent event) throws IOException {
        try{
            TerminoAcademico ta = cmbTerminos.getValue();
            String codigo = textCodigo.getText();
            String nombre = textNombre.getText();
            int niveles = Integer.parseInt(textNiveles.getText());
            
            Materia ma = new Materia(codigo, nombre, niveles, ta);
            ta.addMaterias(ma);
            
            Configuracion.materias.add(ma);
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        App.setRoot("MateriasParalelos");
    }
    
}
