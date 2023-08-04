package com.mycompany.app;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;


public class ConfiguracionController implements Initializable {

    @FXML
    private Button BtnSalir;
    @FXML
    private Button BtnAdminTerminos;
    @FXML
    private Button BtnAdimMatPar;
    @FXML
    private Button BtnAdminPre;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void GoToMain(ActionEvent event) throws IOException {
        App.setRoot("Start");
    }

    @FXML
    private void GoToAdminTerm(ActionEvent event) throws IOException {
        App.setRoot("TerminosAcademicos");
    }

    @FXML
    private void GoToAdminMatPar(ActionEvent event) throws IOException {
        App.setRoot("MateriasParalelos");
    }

    @FXML
    private void GoToAdminPre(ActionEvent event) throws IOException {
        App.setRoot("Preguntas");
    }
    
}
