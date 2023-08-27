
package com.mycompany.app;

import Model.Configuracion;
import static Model.Configuracion.terminos;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class TerminosAcademicosController implements Initializable {


    @FXML
    private Button BtnVolver1;
    @FXML
    private TableView<TerminoAcademico> tblTerminosAcademicos;
    @FXML
    private TableColumn colAño;
    @FXML
    private TableColumn colNumeroTermino;
    
    private ObservableList<TerminoAcademico> termino;
    @FXML
    private ComboBox<TerminoAcademico> cmbTerminos;
    @FXML
    private Button BtnAgregarTerminoAcademico;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        termino = FXCollections.observableArrayList();
        
        this.colAño.setCellValueFactory(new PropertyValueFactory("anio"));
        this.colNumeroTermino.setCellValueFactory(new PropertyValueFactory("numeroTermino"));
        cmbTerminos.getItems().setAll(Configuracion.terminos);
        tblTerminosAcademicos.getItems().setAll(Configuracion.terminos);
        
    }    
    
    @FXML
    private void GoToConfiguracion(ActionEvent event) throws IOException {
        App.setRoot("Configuracion");
    }

    //@FXML
    //private void AgregarTerminoAcademico(ActionEvent event) {
        //try{
            //String anio = this.txtAño.getText();
            //int numeroTermino = Integer.parseInt(this.txtNumeroTermino.getText());
            
            //TerminoAcademico ta = new TerminoAcademico(anio,numeroTermino);
            
            //if(!this.termino.contains(ta)){
                //this.termino.add(ta);
                //this.tblTerminosAcademicos.setItems(termino);
            //} else{
                //Alert alert = new Alert(Alert.AlertType.ERROR);
                //alert.setHeaderText(null);
                //alert.setTitle("Error");
                //alert.setContentText("El término académico ya existe");
                //alert.showAndWait();
            //}
        //} catch(NumberFormatException e){
            //Alert alert = new Alert(Alert.AlertType.ERROR);
            //alert.setHeaderText(null);
            //alert.setTitle("Error");
            //alert.setContentText("El término académico ya existe");
            //alert.showAndWait(); 
        //}
        
        //TerminoAcademico ta = new TerminoAcademico(txtAño.getText(),Integer.parseInt(txtNumeroTermino.getText()));
        //terminos.add(ta);
        //cmbTerminos.getItems().add(ta);

        //Configuracion.terminos.add(ta);
    //}
    
    //public void editarTermino(TerminoAcademico ta){
    //  
    //}

    
    @FXML
    private void filtrarTerminosAcademicos(ActionEvent event) {
        TerminoAcademico ta = cmbTerminos.getValue();
    }

    @FXML
    private void AgregarTerminoAcademico(ActionEvent event) throws IOException {
        App.setRoot("AgregarTermino");
                
    }

}