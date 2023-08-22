package com.mycompany.app;

import Model.Configuracion;
import Model.Estudiante;
import Model.Juego;
import Model.Materia;
import Model.Opcion;
import Model.Paralelo;
import Model.Pregunta;
import Model.Respuesta;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;

public class GameController implements Initializable {

    @FXML
    private Button cmd50;
    @FXML
    private Button cmdCompaniero;
    @FXML
    private Button cmdSalon;
    @FXML
    private Label pregunta;
    @FXML
    private Button opcionA;
    @FXML
    private Button opcionB;
    @FXML
    private Button opcionC;
    @FXML
    private Button opcionD;
    @FXML
    private VBox listPreguntas;
    
    private ArrayList<Pregunta> preguntas;
    private int PreguntaActual = 0;
    private int nivelActual = 0;
    private ArrayList<Character> opciones;
    private ArrayList<Button> botones;
    private Juego juego;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        opciones = new ArrayList<Character>();
        botones = new ArrayList<Button>();
        opciones.add('A');
        opciones.add('B');
        opciones.add('C');
        opciones.add('D');
        botones.add(opcionA);
        botones.add(opcionB);
        botones.add(opcionC);
        botones.add(opcionD);

    }

    public void cargarDatos(Juego juego, ArrayList<Pregunta> preguntas) {
        this.preguntas = preguntas;
        this.juego = juego;

        for (int i = 0; i < preguntas.size(); i++) {
            Label pre = new Label("Pregunta " + (i+1));
            listPreguntas.getChildren().add(pre);   
        }

        MostrarPregunta();
    }

    private void MostrarPregunta() {
        Label lb = (Label)listPreguntas.getChildren().get(PreguntaActual);
        lb.setStyle("-fx-background-color: yellow");
        if (PreguntaActual != 0){
        Label lbant = (Label)listPreguntas.getChildren().get(PreguntaActual-1);
        lbant.setBackground(Background.EMPTY);
        }
        for (Button b : botones) {
            b.setDisable(false);
        }
        Pregunta pre = preguntas.get(PreguntaActual);
        pregunta.setText(pre.getEnunciado());

        opcionA.setText("A: " + pre.getOpciones()[0].getTexto());
        opcionB.setText("B: " + pre.getOpciones()[1].getTexto());
        opcionC.setText("C: " + pre.getOpciones()[2].getTexto());
        opcionD.setText("D: " + pre.getOpciones()[3].getTexto());
        
        

    }

    @FXML
    private void siguientePregunta(ActionEvent event) throws IOException {
        Button origen = (Button) event.getSource();
        boolean respuesta = verificarRespuesta(origen);

        if (respuesta) {
            PreguntaActual++;
            if (PreguntaActual == preguntas.size()) {
                finalizarAlerta("GANASTE C:", AlertType.CONFIRMATION);
                App.setRoot("Start");
            } else {
                MostrarPregunta();
            }
        } else {
            finalizarAlerta("PERDISTE :C", AlertType.ERROR);
            App.setRoot("Start");
        }

    }

    public boolean verificarRespuesta(Button origen) {
        Opcion op = obtenerOpcion(origen);
        //verifica las respuesta
        if (op.getRespuesta() == Respuesta.CORRECTO) {
            return true;
        }
        return false;
    }

    private void finalizarAlerta(String titulo, AlertType type) {
        /*Alert alert = new Alert(type);
        alert.setTitle("MENSAJE");
        alert.setHeaderText(titulo);
        alert.setContentText("EL JUEGO TERMINO!!\n"
                + "Completaste: " + (PreguntaActual + 1) + " preguntas\n"
        );

        alert.showAndWait();*/
        
        String premio = "";
        Alert alert = new Alert(type);
        alert.setTitle("MENSAJE");
        alert.setHeaderText(titulo);
        alert.setContentText("EL JUEGO TERMINO!!\n"
                + "Completaste: " + (PreguntaActual + 1) + " preguntas\n"
        );

        alert.showAndWait();
        
        juego.setPreguntasContestadas(PreguntaActual+1);
        juego.setPremio(premio);
        
        Configuracion.juegos.add(juego);
    }

    @FXML
    private void usar50(ActionEvent event) {
        //genera numeros aleatorios 
        Random rd = new Random();
        int eliminados = 0;
        //bucle que se repite hasta que 2 opcines hallan sido modificadas.
        while (eliminados < 2) {
            int i = rd.nextInt(4);
            Button bt = botones.get(i);
            Opcion op = obtenerOpcion(bt);
            //si la opcion es incorrecta modifica su texto
            if (op.getRespuesta() == Respuesta.INCORRECTO && !bt.isDisable()) {
                bt.setDisable(true);
                eliminados++;
            }
        }
        cmd50.setDisable(true);
    }

    @FXML
    private void usarConsulta(ActionEvent event) {

        //probabilidad de que el apoyo brinde la respuesta correcta 80%
        int[] probabilidad = {1, 1, 0, 1, 1, 1, 1, 0, 1, 1};
        Random rd = new Random();
        int i = rd.nextInt(10);
        boolean resultado = (probabilidad[i] == 1);
        String respuesta = "";
        if (resultado) {
            for (Opcion op : preguntas.get(PreguntaActual).getOpciones()) {
                if (op.getRespuesta() == Respuesta.CORRECTO) {
                    respuesta = op.getTexto();
                }
            }
        } else {
            respuesta = preguntas.get(PreguntaActual).getOpciones()[rd.nextInt(4)].getTexto();
        }
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Comodin ");
        alert.setHeaderText("Consulta a: " + juego.getApoyo());
        alert.setContentText(juego.getApoyo().getNombre() + " opina que la respuesta correcta es: " + respuesta);

        alert.showAndWait();

        cmdCompaniero.setDisable(true);
    }

    @FXML
    private void usarSalon(ActionEvent event) {
        int[] probabilidad = {0, 1, 0, 0, 0, 0, 0, 0, 1, 0};
        Random rd = new Random();
        int[] porcentajes = new int[4];
        int total = juego.getParalelo().getEstudiantes().size();

        int correcta = 0;
        for (int i = 0; i < 4; i++) {
            if (preguntas.get(PreguntaActual).getOpciones()[i].getRespuesta() == Respuesta.CORRECTO) {
                correcta = i;
            }
        }

        for (int i = 0; i < total - 1; i++) {
            int j = rd.nextInt(10);
            if (probabilidad[j] == 1) {
                porcentajes[correcta]++;
            } else {
                porcentajes[rd.nextInt(4)]++;
            }
        }
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Comodin ");
        alert.setHeaderText("Consulta al salon: ");
        alert.setContentText("RESULTADOS DE LA ENCUESTA AL SALON\n"
                + "A: " + ((porcentajes[0] * 100) / total) + "%\n"
                + "B: " + ((porcentajes[1] * 100) / total) + "%\n"
                + "C: " + ((porcentajes[2] * 100) / total) + "%\n"
                + "D: " + ((porcentajes[3] * 100) / total) + "%");

        alert.showAndWait();
        cmdSalon.setDisable(true);
    }

    private Opcion obtenerOpcion(Button opcion) {
        char respuesta = opcion.getText().charAt(0);
        int index = opciones.indexOf(respuesta);
        return preguntas.get(PreguntaActual).getOpciones()[index];

    }

}
