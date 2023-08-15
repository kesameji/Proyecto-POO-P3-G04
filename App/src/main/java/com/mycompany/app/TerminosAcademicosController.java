
package com.mycompany.app;

import Model.TerminoAcademico;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class TerminosAcademicosController implements Initializable {


    @FXML
    private Button BtnVolver1;
    @FXML
    private Button BtnAgregarTerminoAcademico1;
    @FXML
    private TableView<TerminoAcademico> tblTerminosAcademicos;
    @FXML
    private TableColumn colAño;
    @FXML
    private TableColumn colNumeroTermino;
    @FXML
    private TextField txtAño;
    @FXML
    private TextField txtNumeroTermino;
    
    private ObservableList<TerminoAcademico> termino;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        termino = FXCollections.observableArrayList();
        
        this.colAño.setCellValueFactory(new PropertyValueFactory("anio"));
        this.colNumeroTermino.setCellValueFactory(new PropertyValueFactory("numeroTermino"));
        
    }    
    
    @FXML
    private void GoToConfiguracion(ActionEvent event) throws IOException {
        App.setRoot("Configuracion");
    }

    @FXML
    private void AgregarTerminoAcademico(ActionEvent event) {
        try{
            String anio = this.txtAño.getText();
            int numeroTermino = Integer.parseInt(this.txtNumeroTermino.getText());
            
            TerminoAcademico ta = new TerminoAcademico(anio,numeroTermino);
            
            if(!this.termino.contains(ta)){
                this.termino.add(ta);
                this.tblTerminosAcademicos.setItems(termino);
            } else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("El término académico ya existe");
                alert.showAndWait();
            }
        } catch(NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("El término académico ya existe");
            alert.showAndWait(); 
        }
    }
    
    public void editarTermino(TerminoAcademico ta){
        
    }

}
