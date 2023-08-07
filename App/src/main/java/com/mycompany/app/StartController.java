package com.mycompany.app;

import Model.Configuracion;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class StartController implements Initializable {

    @FXML
    private Button BtnConfiguracion;
    @FXML
    private Button BtnStartGame;
    @FXML
    private Button BtnSalir;
    @FXML
    private Button BtnReportes;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void GoToConfiguracion(ActionEvent event) throws IOException {
        App.setRoot("Configuracion");
    }

    @FXML
    private void GoToIniciarJuego(ActionEvent event) throws IOException {
        App.setRoot("NuevoJuego");
    }

    @FXML
    private void GoToReportes(ActionEvent event) throws IOException {
        App.setRoot("Reportes");
    }

    @FXML
    private void EndApp(ActionEvent event) {
        Configuracion.SerializarDatos();
        System.exit(0);
    }

}
