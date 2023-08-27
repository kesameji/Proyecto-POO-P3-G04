package com.mycompany.app;

import java.net.URL;
import java.lang.NumberFormatException;
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
            if (ta == null) {
                CrearAlerta("Termino no seleccionado", "No escogio un termino "
                        + "del combobox");
                return;
            }

            String codigo = textCodigo.getText();
            if (codigo.equals("")) {
                CrearAlerta("Codigo no ingresado", "El codigo de la materia no "
                        + "fue ingresado");
                return;
            }

            String nombre = textNombre.getText();
            if (nombre.equals("")) {
                CrearAlerta("Nombre no ingresado", "El nombre de la materia no "
                        + "fue ingresado");
                return;
            }

            int niveles;
            try {
                niveles = Integer.parseInt(textNiveles.getText());
                if (niveles<1) throw new NumberFormatException();
            } catch (NumberFormatException e) {
                CrearAlerta("Numero niveles invalido", "El numero de niveles "
                        + "ingresado no es valido");
                return;
            }

            if (comprobarCodigoMateria(codigo, ta)) {
                Materia ma = new Materia(codigo, nombre, niveles, ta);
                ta.addMaterias(ma);

                Configuracion.materias.add(ma);
            } else {
                CrearAlerta("Codigo de materia ya existe", "El codigo ingresado "
                        + "ya existe en el termino academico por favor ingresar otro");
                return;
            }
        }
        App.setRoot("MateriasParalelos");
    }

    public void editarMateria(Materia ma) {
        cmbTerminos.setValue(ma.getTermino());
        cmbTerminos.setDisable(true);

        textNombre.setText(ma.getNombre());

        textNiveles.setText(String.valueOf(ma.getNumeroNiveles()));

        textCodigo.setText(ma.getCodigo());
        textCodigo.setDisable(true);

        lblTitulo.setText("Editar Materia");

        BtnIngresar.setText("Guardar Cambios");
        BtnIngresar.setOnMouseClicked(r -> guardarCambios(ma));
    }

    private void guardarCambios(Materia ma) {
        if (BtnIngresar.getText().equals("Guardar Cambios")) {

            ma.setTermino(cmbTerminos.getValue());
            ma.setCodigo(textCodigo.getText());
            ma.setNombre(textNombre.getText());
            int niveles;
            try {
                niveles = Integer.parseInt(textNiveles.getText());
                if (niveles<1) throw new NumberFormatException();
                ma.setNumeroNiveles(niveles);
            } catch (NumberFormatException e) {
                CrearAlerta("Numero niveles invalido", "El numero de niveles "
                        + "ingresado no es valido");
                return;
            }
        }
    }

    private boolean comprobarCodigoMateria(String codigo, TerminoAcademico ta) {
        for (Materia ma : ta.getMaterias()) {
            if (ma.getCodigo().equals(codigo)) {
                return false;
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
