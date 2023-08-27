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
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

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
    @FXML
    private Label lblTitulo;

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
        if (!BtnIngresar.getText().equals("Guardar Cambios")) {
            TerminoAcademico ta = cmbTerminos.getValue();
            String codigo = textCodigo.getText();
            if (comprobarCodigoMateria(codigo)) {
                String nombre = textNombre.getText();
                int niveles = Integer.parseInt(textNiveles.getText());

                Materia ma = new Materia(codigo, nombre, niveles, ta);
                ta.addMaterias(ma);

                Configuracion.materias.add(ma);
            }
            else{
                CrearAlerta("Codigo de materia ya existe", "El codigo ingresado ya existe por favor ingresar otro");
                return;
            }

        }

        App.setRoot("MateriasParalelos");
    }

    public void editarMateria(Materia ma) {
        cmbTerminos.setValue(ma.getTermino());
        cmbTerminos.setEditable(false);
        textNombre.setText(ma.getNombre());
        textNiveles.setText(String.valueOf(ma.getNumeroNiveles()));
        textCodigo.setText(ma.getCodigo());
        textCodigo.setEditable(false);
        lblTitulo.setText("Editar Materia");
        BtnIngresar.setText("Guardar Cambios");

        BtnIngresar.setOnMouseClicked(r -> guardarCambios(ma));

    }

    private void guardarCambios(Materia ma) {
        if (BtnIngresar.getText().equals("Guardar Cambios")) {
            ma.setTermino(cmbTerminos.getValue());
            ma.setCodigo(textCodigo.getText());
            ma.setNombre(textNombre.getText());
            ma.setNumeroNiveles(Integer.parseInt(textNiveles.getText()));

            System.out.println(ma);
        }

    }

    private boolean comprobarCodigoMateria(String codigo) {
        for (TerminoAcademico ta : Configuracion.terminos) {
            for (Materia ma : ta.getMaterias()) {
                if (ma.getCodigo().equals(codigo)) {
                    return false;
                }
            }
        }
        return true;
    }

    private void CrearAlerta(String titulo, String contenido) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Notificacion");
        alert.setHeaderText(titulo);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

}