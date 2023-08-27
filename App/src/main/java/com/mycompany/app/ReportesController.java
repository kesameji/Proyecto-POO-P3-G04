package com.mycompany.app;

import Model.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;


public class ReportesController implements Initializable {

    @FXML
    private Button BtnVolver;
    @FXML
    private ComboBox<TerminoAcademico> cmbTermino;
    @FXML
    private ComboBox<Materia> cmbMateria;
    @FXML
    private ComboBox<Paralelo> cmbParalelo;
    @FXML
    private TableView<Juego> TvReportes;
    @FXML
    private TableColumn<Juego, LocalDate> ColFecha;
    @FXML
    private TableColumn<Juego, String> ColParticipante;
    @FXML
    private TableColumn<Juego, Integer> ColNivelMax;
    @FXML
    private TableColumn<Juego, Double> ColTiempo;
    @FXML
    private TableColumn<Juego, Integer> ColPreguntasContestadas;
    @FXML
    private TableColumn<Juego, String> ColComodinesUsados;
    @FXML
    private TableColumn<Juego, String> ColPremio;
    @FXML
    private TableColumn<Juego, Void> ColOpciones;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbTermino.setPromptText("-Término-");
        cmbTermino.getItems().addAll(Configuracion.terminos);
        ColFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        ColParticipante.setCellValueFactory(new PropertyValueFactory<>("participante"));
        ColNivelMax.setCellValueFactory(new PropertyValueFactory<>("nivelActual"));
        ColTiempo.setCellValueFactory(new PropertyValueFactory<>("tiempo"));
        ColPreguntasContestadas.setCellValueFactory(new PropertyValueFactory<>("preguntasContestadas"));
        ColComodinesUsados.setCellValueFactory(param -> {
            Juego j = param.getValue();
            ArrayList<Comodin> comodines = j.getComodines();
            int comodinesUsados = 0;
            for(Comodin c: comodines){
                if (c.getUso()) comodinesUsados++;
            }
            return new SimpleStringProperty(""+comodinesUsados);
        });
        ColPremio.setCellValueFactory(new PropertyValueFactory<>("premio"));
        agregarDetalleJuego();
        
        ColFecha.prefWidthProperty().bind(TvReportes.widthProperty().multiply(0.1));
        ColParticipante.prefWidthProperty().bind(TvReportes.widthProperty().multiply(0.3));
        ColNivelMax.prefWidthProperty().bind(TvReportes.widthProperty().multiply(0.1));
        ColTiempo.prefWidthProperty().bind(TvReportes.widthProperty().multiply(0.1));
        ColPreguntasContestadas.prefWidthProperty().bind(TvReportes.widthProperty().multiply(0.1));
        ColComodinesUsados.prefWidthProperty().bind(TvReportes.widthProperty().multiply(0.1));
        ColPremio.prefWidthProperty().bind(TvReportes.widthProperty().multiply(0.1));
        ColOpciones.prefWidthProperty().bind(TvReportes.widthProperty().multiply(0.1));
        
        
    }    

    @FXML
    private void GoToStart(ActionEvent event) throws IOException {
        App.setRoot("Start");
    }
    
    @FXML
    public void llenarCmbMaterias(ActionEvent event){
        TerminoAcademico ta = cmbTermino.getValue();
        cmbMateria.setPromptText("-Materia-");
        cmbMateria.getItems().addAll(ta.getMaterias());
    }
    
    @FXML
    public void llenarCmbParalelos(ActionEvent event){
        Materia ma = cmbMateria.getValue();
        cmbParalelo.setPromptText("-Paralelo-");
        cmbParalelo.getItems().addAll(ma.getParalelos());
    }
    
    @FXML
    public void llenarTvReportes(ActionEvent event){
        Paralelo pa = cmbParalelo.getValue();
        TvReportes.getItems().setAll(listaJuegosParalelo(pa));
    }
    
    /*
    * Método que muestra todos los juegos realizados en el programa de
    * los estudiantes de un paralelo recibido.
    *
    * @param posicionParalelo El parámetro posicionParalelo es la posición del paralelo en una lista de todas los paralelos cargados en el programa
     */
    public ArrayList<Juego> listaJuegosParalelo(Paralelo p) {
        ArrayList<Juego> juegosFiltrados = new ArrayList<>();
        for (Juego j : Configuracion.juegos) {
            if (j.getParalelo().equals(p)) {
                juegosFiltrados.add(j);
            }
        }
        return juegosFiltrados;
    }
    
    /**
     * Método que agrega los botones para ver en detalle los juegos en la columna ColOpciones
     */
    private void agregarDetalleJuego(){
        Callback<TableColumn<Juego, Void>, TableCell<Juego, Void>> cellFactory = new Callback<TableColumn<Juego, Void>, TableCell<Juego, Void>>() {
            @Override
            public TableCell<Juego, Void> call(final TableColumn<Juego, Void> param) {
                TableCell<Juego, Void> cell = new TableCell<Juego, Void>() {

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            Juego j = getTableView().getItems().get(getIndex());
                            //boton Detalle
                            Button btnDetalle = new Button("➕");
                            btnDetalle.setOnAction(r -> {
                                try {
                                    detalleJuego(j);
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            });

                            //se agrega el botón en la celda
                            setGraphic(btnDetalle);
                        }
                    }
                };
                return cell;
            }
        };
        ColOpciones.setCellFactory(cellFactory);
        
    }
    
    //Función verdadera
    private void detalleJuego(Juego j) throws IOException{
        FXMLLoader loader = new FXMLLoader(App.class.getResource("DetalleJuego.fxml"));
        //DetalleJuegoController ct = new DetalleJuegoController();
        

        //loader.setController(ct);//se asigna el controlador
        AnchorPane root = (AnchorPane) loader.load();//carga los objetos del fxml

        //luego que el fxml ha sido cargado puedo utilizar el controlador para realizar cambios
        DetalleJuegoController ct = loader.getController();
        ct.verDetalleJuego(j);
        //asignar el nodo raiz a la escena

        App.changeRoot(root);
    }
}
