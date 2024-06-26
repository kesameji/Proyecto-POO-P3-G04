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
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;

public class GameController implements Initializable {

    //Componentes
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
    @FXML
    private Label tiempoActual;

    //recursos globales
    private ArrayList<Pregunta> preguntas;
    private int PreguntaActual = 0;
    private int nivelActual = 0;
    private ArrayList<Character> opciones;
    private ArrayList<Button> botones;
    private Juego juego;
    private Thread temporizador;
    private int tiempo;
    private boolean progresion;
    private int numeroNiveles;

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

    public void cargarDatos(Juego juego, int numeroNiveles) {
        this.numeroNiveles = numeroNiveles;
        this.preguntas = juego.getListaPreguntas();
        this.juego = juego;
        for (int i = 0; i < preguntas.size(); i++) {
            Label pre = new Label("Pregunta " + (i + 1));
            listPreguntas.getChildren().add(pre);
        }

        MostrarPregunta();
    }

    private void MostrarPregunta() {
        Label lb = (Label) listPreguntas.getChildren().get(PreguntaActual);
        lb.setStyle("-fx-background-color: blue");

        if (PreguntaActual != 0) {
            Label lbant = (Label) listPreguntas.getChildren().get(PreguntaActual - 1);
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

        tiempo = 61;

        temporizador = new Thread(() -> {
            progresion = true;
            while (progresion) {
                if (tiempo <= 0) {
                    Platform.runLater(() -> {
                        try {
                            finalizarAlerta("PERDISTE :C", AlertType.ERROR);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    });
                    progresion = false;
                } else {
                    tiempo--;
                    Platform.runLater(() -> {
                        tiempoActual.setText("Tiempo: " + tiempo);
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        temporizador.start();
    }

    @FXML
    private void siguientePregunta(ActionEvent event) throws IOException {

        temporizador.stop();
        Button origen = (Button) event.getSource();
        boolean respuesta = verificarRespuesta(origen);

        if (respuesta) {
            PreguntaActual++;
            if (PreguntaActual == preguntas.size()) {
                finalizarAlerta("GANASTE C:", AlertType.CONFIRMATION);

            } else {
                MostrarPregunta();
            }
        } else {
            finalizarAlerta("PERDISTE :C", AlertType.ERROR);

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

    private void finalizarAlerta(String titulo, AlertType type) throws IOException {
        /*Alert alert = new Alert(type);
        alert.setTitle("MENSAJE");
        alert.setHeaderText(titulo);
        alert.setContentText("EL JUEGO TERMINO!!\n"
                + "Completaste: " + (PreguntaActual + 1) + " preguntas\n"
        );

        alert.showAndWait();*/
        
        LocalTime tiempofinal = LocalTime.now();
        int horas = Duration.between(juego.getTiempo(), tiempofinal).toHoursPart();
        int minutos = Duration.between(juego.getTiempo(), tiempofinal).toMinutesPart();
        int segundos = Duration.between(juego.getTiempo(), tiempofinal).toSecondsPart();
        juego.setTiempo(LocalTime.of(horas, minutos, segundos));
        
        Alert alert = new Alert(type);
        alert.setTitle("MENSAJE");
        alert.setHeaderText(titulo);
        alert.setContentText("EL JUEGO TERMINO!!\n"
                + "Completaste: " + PreguntaActual + " preguntas\n"
        );
        alert.showAndWait();

        System.out.println(PreguntaActual);
        System.out.println(numeroNiveles);
        if(PreguntaActual > 0){
            juego.setNivelActual(preguntas.get(PreguntaActual - 1).getNivel());
        }
        
        String premio = ConsultarPremio();

        juego.setPreguntasContestadas(PreguntaActual);
        juego.setPremio(premio);

        Configuracion.juegos.add(juego);
        Collections.sort(Configuracion.juegos);
        App.setRoot("Start");
    }

    private String ConsultarPremio() {
        if (PreguntaActual >= numeroNiveles) {
            TextInputDialog td = new TextInputDialog("Premio");
            td.setHeaderText("Ingrese el premio");
            td.showAndWait();
            return td.getEditor().getText();
        }
        return "NO PREMIO";
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
        juego.getComodines().get(0).setUso(true);
        juego.getComodines().get(0).setNivel(PreguntaActual);
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
        juego.getComodines().get(1).setUso(true);
        juego.getComodines().get(1).setNivel(PreguntaActual);
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
        juego.getComodines().get(2).setUso(true);
        juego.getComodines().get(2).setNivel(PreguntaActual);
    }

    private Opcion obtenerOpcion(Button opcion) {
        char respuesta = opcion.getText().charAt(0);
        int index = opciones.indexOf(respuesta);
        return preguntas.get(PreguntaActual).getOpciones()[index];

    }

}
