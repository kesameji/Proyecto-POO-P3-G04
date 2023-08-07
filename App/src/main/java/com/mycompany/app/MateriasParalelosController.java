package com.mycompany.app;

import Model.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class MateriasParalelosController implements Initializable {

    @FXML
    private Button BtnVolver;
    @FXML
    private ComboBox<TerminoAcademico> cmbTermino;
    @FXML
    private Button BtnAgregar;
    @FXML
    TableView<Materia> TvMaterias;
    @FXML
    private TableColumn<Materia, String> ColCodigo;
    @FXML
    private TableColumn<Materia, String> ColNombre;
    @FXML
    private TableColumn<Materia, String> ColTermino;
    @FXML
    private TableColumn<Materia, Void> ColOpcionesMa;
    @FXML
    TableView<Paralelo> TvParalelos;
    @FXML
    private TableColumn<Paralelo, String> ColNumero;
    @FXML
    private TableColumn<Paralelo, Void> ColOpcionesPa;
    @FXML
    private TextField TfCodigo;
    @FXML
    private TextField TfNombre;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbTermino.getItems().setAll(Configuracion.terminos);

        // TODO
        ColCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        ColNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        ColTermino.setCellValueFactory(new PropertyValueFactory("termino"));
        agregarOpcionesMa();
        TvMaterias.getItems().setAll(Configuracion.materias);
        
        ColNumero.setCellValueFactory(new PropertyValueFactory<>("numeroParalelo"));
        

    }

    private void agregarOpcionesMa() {
        Callback<TableColumn<Materia, Void>, TableCell<Materia, Void>> cellFactory = new Callback<TableColumn<Materia, Void>, TableCell<Materia, Void>>() {
            @Override
            public TableCell<Materia, Void> call(final TableColumn<Materia, Void> param) {
                TableCell<Materia, Void> cell = new TableCell<Materia, Void>() {

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            //hbox para ubicar los botones
                            HBox hbOpciones = new HBox(5);
                            //recuperar la materia de la fila
                            Materia ma = getTableView().getItems().get(getIndex());
                            //boton editar
                            Button btnEd = new Button("🔄️️");
                            btnEd.setOnAction(r -> {
                                try {
                                    editarMateria(ma);
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            });

                            //boton agregar
                            Button btnAg = new Button("➕");
                            btnAg.setOnAction(r -> {
                                try {
                                    agregarParalelo(ma);
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            });

                            //se agregan botones al hbox
                            hbOpciones.getChildren().addAll(btnEd, btnAg);
                            //se ubica hbox en la celda
                            setGraphic(hbOpciones);
                        }
                    }
                };
                return cell;
            }
        };
        ColOpcionesMa.setCellFactory(cellFactory);
    }
    
    private void editarMateria(Materia ma) throws IOException{
        FXMLLoader loader = new FXMLLoader(App.class.getResource("AgregarMateria.fxml"));
        AgregarMateriaController ct = new AgregarMateriaController();

        loader.setController(ct);//se asigna el controlador
        AnchorPane root = (AnchorPane) loader.load();//carga los objetos del fxml
       
        //luego que el fxml ha sido cargado puedo utilizar el controlador para realizar cambios
        ct.editarMateria(ma);
        //asignar el nodo raiz a la escena
        
        App.changeRoot(root);
    }
    
    @FXML
    private void agregarParalelo(Materia ma) throws IOException {
       FXMLLoader loader = new FXMLLoader(App.class.getResource("AgregarParalelo.fxml"));

       AgregarParaleloController ct = new AgregarParaleloController();

        loader.setController(ct);//se asigna el controlador

        AnchorPane root = (AnchorPane) loader.load();//carga los objetos del fxml
        //luego que el fxml ha sido cargado puedo utilizar el controlador para realizar cambios
        ct.seleccionarMateria(ma);
        //asignar el nodo raiz a la escena
        
        App.changeRoot(root);
       
        
    }
    
    private void agregarOpcionesPa() {
        Callback<TableColumn<Paralelo, Void>, TableCell<Paralelo, Void>> cellFactory = new Callback<TableColumn<Paralelo, Void>, TableCell<Paralelo, Void>>() {
            @Override
            public TableCell<Paralelo, Void> call(final TableColumn<Paralelo, Void> param) {
                TableCell<Paralelo, Void> cell = new TableCell<Paralelo, Void>() {

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            //hbox para ubicar los botones
                            HBox hbOpciones = new HBox(5);
                            //recuperar el empleado de la fila
                            Paralelo pa = getTableView().getItems().get(getIndex());
                            //boton editar

                            //boton eliminar
                            Button btnEl = new Button("❌");
                            btnEl.setOnAction(r -> eliminarParalelo(pa));

                            //se agregan botones al hbox
                            hbOpciones.getChildren().addAll(btnEl);
                            //se ubica hbox en la celda
                            setGraphic(hbOpciones);
                        }
                    }
                };
                return cell;
            }
        };

        ColOpcionesPa.setCellFactory(cellFactory);

    }
    
    private void eliminarParalelo(Paralelo pa){
        Materia ma = TvMaterias.getSelectionModel().getSelectedItem();
        ma.getParalelos().remove(pa);
        TvParalelos.getItems().setAll(ma.getParalelos());
    }
    
    @FXML
    private void GoToConfiguracion(ActionEvent event) throws IOException {
        App.setRoot("Configuracion");
    }

    @FXML
    private void AgregarMateria(ActionEvent event) throws IOException {
       FXMLLoader loader = new FXMLLoader(App.class.getResource("AgregarMateria.fxml"));
        AgregarMateriaController ct = new AgregarMateriaController();

        loader.setController(ct);//se asigna el controlador
        AnchorPane root = (AnchorPane) loader.load();//carga los objetos del fxml
        
        App.changeRoot(root);
    }

    @FXML
    private void filtrarMateriasTermino(ActionEvent event) {
        TerminoAcademico ta = cmbTermino.getValue();
        ArrayList<Materia> materias = new ArrayList<Materia>();
        for (Materia ma : Configuracion.materias){
            if(ma.getTermino().equals(ta)){
                materias.add(ma);
            }
        }
        
        TvMaterias.getItems().setAll(materias);
        
    }
    
    @FXML
    private void MostrarParalelos(MouseEvent event) {
        Materia ma = (Materia) TvMaterias.getSelectionModel().getSelectedItem();
        agregarOpcionesPa();
        TvParalelos.getItems().setAll(ma.getParalelos());
    }

    @FXML
    private void filtrarMateriasCodigo(KeyEvent event) {
        ArrayList<Materia> materias = new ArrayList<Materia>();
        for(Materia ma : Configuracion.materias){
            if(ma.getCodigo().startsWith(TfCodigo.getText())){
                materias.add(ma);
                System.out.println(TfCodigo.getText());
            }
        }
        TvMaterias.getItems().setAll(materias);
    }

    @FXML
    private void filtrarMateriasNombre(KeyEvent event) {
        ArrayList<Materia> materias = new ArrayList<Materia>();
        for(Materia ma : Configuracion.materias){
            if(ma.getNombre().startsWith(TfNombre.getText())){
                materias.add(ma);
            }
        }
        TvMaterias.getItems().setAll(materias);
    }
  
}
