package com.mycompany.app;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;


public class NuevoJuegoController implements Initializable {

    @FXML
    private Button BtnVolver;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    

    @FXML
    private void GoToStart(ActionEvent event) throws IOException {
        App.setRoot("Start");
    }
    
}
