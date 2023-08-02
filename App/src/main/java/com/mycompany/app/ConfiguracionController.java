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

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void GoToMain(ActionEvent event) throws IOException {
        App.setRoot("Start");
    }
    
}
