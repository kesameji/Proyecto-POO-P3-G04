
package com.mycompany.app;

import Model.Configuracion;
import static Model.Configuracion.terminos;
import Model.Materia;
import Model.TerminoAcademico;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

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
    @FXML
    private TableColumn<TerminoAcademico, Void> ColEditar;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        termino = FXCollections.observableArrayList();
        
        colAño.setCellValueFactory(new PropertyValueFactory("anio"));
        colNumeroTermino.setCellValueFactory(new PropertyValueFactory("numeroTermino"));
        colAño.prefWidthProperty().bind(tblTerminosAcademicos.widthProperty().multiply(0.4));
        colNumeroTermino.prefWidthProperty().bind(tblTerminosAcademicos.widthProperty().multiply(0.3));
        ColEditar.prefWidthProperty().bind(tblTerminosAcademicos.widthProperty().multiply(0.3));
        
        cmbTerminos.getItems().setAll(Configuracion.terminos);
        agregarEditarTermino();
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
    private void establecerTerminosJuego(ActionEvent event) {
        TerminoAcademico terminoJuego = cmbTerminos.getValue();
        Configuracion.setTerminoJuego(terminoJuego);
    }

    @FXML
    private void AgregarTerminoAcademico(ActionEvent event) throws IOException {
        App.setRoot("AgregarTermino");
                
    }
    
    private void agregarEditarTermino() {
        Callback<TableColumn<TerminoAcademico, Void>, TableCell<TerminoAcademico, Void>> cellFactory = new Callback<TableColumn<TerminoAcademico, Void>, TableCell<TerminoAcademico, Void>>() {
            @Override
            public TableCell<TerminoAcademico, Void> call(final TableColumn<TerminoAcademico, Void> param) {
                TableCell<TerminoAcademico, Void> cell = new TableCell<TerminoAcademico, Void>() {

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            //recuperar la materia de la fila
                            TerminoAcademico ta = getTableView().getItems().get(getIndex());
                            //boton editar
                            Button btnEd = new Button("🔄️️");
                            btnEd.setOnAction(r -> {
                                try {
                                    editarTermino(ta);
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            });
                            //se ubica hbox en la celda
                            setGraphic(btnEd);
                        }
                    }
                };
                return cell;
            }
        };
        ColEditar.setCellFactory(cellFactory);
    }
    
    private void editarTermino(TerminoAcademico ta) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("AgregarTermino.fxml"));
        //DetalleJuegoController ct = new DetalleJuegoController();
        

        //loader.setController(ct);//se asigna el controlador
        AnchorPane root = (AnchorPane) loader.load();//carga los objetos del fxml

        //luego que el fxml ha sido cargado puedo utilizar el controlador para realizar cambios
        AgregarTerminoController ct = loader.getController();
        ct.editarTermino(ta);
        //asignar el nodo raiz a la escena

        App.changeRoot(root);
    }

}