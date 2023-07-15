package com.mycompany.proyecto;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

public class Configuracion {

    static ArrayList<TerminoAcademico> terminos = new ArrayList<TerminoAcademico>();

    static ArrayList<Materia> materias = new ArrayList<Materia>();

    static ArrayList<Paralelo> paralelos = new ArrayList<Paralelo>();

    static ArrayList<Cuestionario> cuestionarios = new ArrayList<Cuestionario>();

    static ArrayList<Juego> juegos = new ArrayList<>();

    static TerminoAcademico terminoJuego;

    public static void ingresarTerminos(TerminoAcademico termino) {
        terminos.add(termino);
    }

    public static void mostrarTerminos() {
        System.out.println("TÉRMINOS ACADÉMICOS");
        int i = 1;
        for (TerminoAcademico t : terminos) {
            System.out.println(i + ". " + t);
            i++;

        }
    }

    public static void ingresarMateria() {
        Scanner sc = new Scanner(System.in);

        String codigoIngresado;
        do {
            System.out.println("INGRESO DE MATERIA");
            System.out.print("Ingrese el código de la materia: ");
            codigoIngresado = sc.nextLine();
            if (encontrarMateria(codigoIngresado) != -1) {
                System.out.println("Código de materia ya existente\n");
            }
        } while (encontrarMateria(codigoIngresado) != -1);

        System.out.print("Ingrese el nombre de la materia: ");
        String nombreIngresado = sc.nextLine();

        System.out.print("Ingrese la cantidad de niveles para las preguntas: ");
        int numNivelesIngresado = sc.nextInt();

        System.out.println("Ingrese el termino de la materia");
        int posicionTermino;
        mostrarTerminos();
        if (terminos.isEmpty()) {
            System.out.println("Por favor Ingrese terminos antes de continuar");
            return;
        }
        do {
            System.out.print("Seleccione el termino: ");
            posicionTermino = Integer.parseInt(sc.nextLine()) - 1;
            if (posicionTermino < 0 || posicionTermino > (Configuracion.terminos.size() - 1)) {
                System.out.println("Opcion no encontrada\n");
            }
        } while (posicionTermino < 0 || posicionTermino > (Configuracion.terminos.size() - 1));
        TerminoAcademico ta = terminos.get(posicionTermino);

        materias.add(new Materia(codigoIngresado, nombreIngresado, numNivelesIngresado, ta));
        System.out.println("Materia ingresada correctamente\n");

    }

    public static void ingresarTermino() {
        Scanner sc = new Scanner(System.in);

        String anio;
        int numero;
        do {
            System.out.println("INGRESO DE TERMINO ACADEMICO");
            System.out.print("Ingrese el anio del termino: ");
            anio = sc.nextLine();
            System.out.print("Ingrese el numero de termino: ");
            numero = Integer.parseInt(sc.nextLine());
            if (encontrarTermino(anio, numero) != -1) {
                System.out.println("Termino Academico ya existente\n");
            }
        } while (encontrarTermino(anio, numero) != -1);

        terminos.add(new TerminoAcademico(anio, numero));
        System.out.println("Termino ingresado correctamente\n");
    }

    public static void mostrarMaterias() {
        System.out.println("MATERIAS");
        int i = 1;
        for (Materia m : materias) {
            System.out.println(i + ". " + m);
            i++;
        }
    }

    public static void mostrarMaterias(ArrayList<Materia> ma) {
        System.out.println("MATERIAS");
        int i = 1;
        for (Materia m : ma) {
            System.out.println(i + ". " + m);
            i++;
        }
    }

    public static void editarTermino() {
        Scanner sc = new Scanner(System.in);
        String opcion;
        System.out.println("""
                           MODIFICACIÓN DEL TERMINO ACADEMICO
                           Escoga una termino para modificar
                           1. Escoger el termino por el anio
                           2. Mostrar una lista de los terminos y escoger uno
                           3. Regresar
                           Ingrese una opcion:""");
        opcion = sc.nextLine().strip();
        System.out.println();
        int posicionTermino = -1;
        switch (opcion) {
            case "1":
                String anio;
                int numero;
                System.out.print("Ingrese el anio del termino: ");
                anio = sc.nextLine();
                System.out.print("Ingrese el numero de termino: ");
                numero = Integer.parseInt(sc.nextLine());
                posicionTermino = encontrarTermino(anio, numero);
                terminos.set(posicionTermino, new TerminoAcademico(anio, numero));
                mostrarTerminos();
                System.out.println("");
                break;

            case "2":
                mostrarTerminos();
                if (terminos.isEmpty()) {
                    System.out.println("No existen terminos para editar");
                    return;
                }
                do {
                    System.out.print("Seleccione el termino a editar: ");
                    posicionTermino = Integer.parseInt(sc.nextLine()) - 1;
                    if (posicionTermino < 0 || posicionTermino > (Configuracion.terminos.size() - 1)) {
                        System.out.println("Opcion no encontrada\n");
                    }
                } while (posicionTermino < 0 || posicionTermino > (Configuracion.terminos.size() - 1));
                TerminoAcademico ta = terminos.get(posicionTermino);
                System.out.print("Ingrese el anio del termino: ");
                anio = sc.nextLine();
                System.out.print("Ingrese el numero de termino: ");
                numero = Integer.parseInt(sc.nextLine());
                ta.setAnio(anio);
                ta.setNumeroTermino(numero);
                mostrarTerminos();
                System.out.println("");
                break;
            case "3":
        }
    }

    public static void escogerTermino() {
        mostrarTerminos();
        int posicionTermino;
        Scanner sc = new Scanner(System.in);
        if (terminos.isEmpty()) {
            System.out.println("No existen terminos para escoger");
            return;
        }
        do {
            System.out.print("Seleccione el termino a escoger: ");
            posicionTermino = Integer.parseInt(sc.nextLine()) - 1;
            if (posicionTermino < 0 || posicionTermino > (Configuracion.terminos.size() - 1)) {
                System.out.println("Opcion no encontrada\n");
            }
        } while (posicionTermino < 0 || posicionTermino > (Configuracion.terminos.size() - 1));
        terminoJuego = terminos.get(posicionTermino);
        System.out.println("");
    }

    public static int encontrarMateria(String materia) {
        int i = 0;
        for (Materia m : materias) {
            if (m.getCodigo().equals(materia)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static int encontrarTermino(String anio, int numero) {
        int i = 0;
        for (TerminoAcademico ta : terminos) {
            if (anio.equals(ta.getAnio()) && numero == ta.getNumeroTermino()) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static void editarMateria() {
        Scanner sc = new Scanner(System.in);

        int opcion = 0;
        while (opcion != 3) {
            System.out.println("""
                               MODIFICACIÓN DE MATERIA
                               Escoga una materia para modificar
                               1. Escoger por código de la materia
                               2. Mostrar una lista de las materas y escoger una
                               3. Regresar
                               Ingrese una opcion:""");
            opcion = sc.nextInt();
            System.out.println();
            int posicionMateria;
            if (opcion == 1 || opcion == 2) {
                if (opcion == 1) {
                    sc.nextLine();
                    System.out.print("Ingrese el código de la materia a editar: ");
                    String codigoIngresado = sc.nextLine();
                    posicionMateria = encontrarMateria(codigoIngresado);
                } else {
                    Configuracion.mostrarMaterias();
                    do {
                        System.out.print("Seleccione la materia a editar: ");
                        posicionMateria = (sc.nextInt()) - 1;
                        if (posicionMateria < 0 || posicionMateria > (Configuracion.materias.size() - 1)) {
                            System.out.println("Opcion no encontrada\n");
                        }
                    } while (posicionMateria < 0 || posicionMateria > (Configuracion.materias.size() - 1));
                }
                System.out.println();
                if (posicionMateria != -1) {
                    int opcion2 = 0;
                    while (opcion2 != 3) {
                        System.out.println("Qué desea modificar de " + materias.get(posicionMateria) + "?");
                        System.out.println("""
                                           1. Modificar nombre de la materia
                                           2. Modificar cantidad de niveles de la materia
                                           3. Regresar
                                           Ingrese una opcion:""");
                        opcion2 = sc.nextInt();
                        System.out.println();
                        if (opcion2 == 1 || opcion2 == 2) {
                            if (opcion2 == 1) {
                                sc.nextLine();
                                System.out.print("Ingrese un nuevo nombre de la materia: ");
                                String nombreNuevo = sc.nextLine();
                                materias.get(posicionMateria).setNombre(nombreNuevo);
                            } else {
                                System.out.print("Ingrese una nueva cantidad de niveles de la materia: ");
                                int numNivelesNuevo = sc.nextInt();
                                materias.get(posicionMateria).setNumeroNiveles(numNivelesNuevo);
                            }
                            System.out.println("Materia modificada correctamente\n");
                        } else if (opcion != 3) {
                            System.out.println("Opcion no encontrada\n");
                        }
                    }
                } else {
                    System.out.println("Materia no encontrada\n");
                }
            } else if (opcion != 3) {
                System.out.println("Opcion no encontrada\n");
            }
        }

    }

    public static void ingresarParalelo() {
        Scanner sc = new Scanner(System.in);

        System.out.println("INGRESO DE PARALELO");
        //muestra una lista de todas las materias
        mostrarMaterias();
        //ingreso y validación de materia
        int posicionMateria;
        do {
            System.out.print("Seleccione la materia: ");
            posicionMateria = (sc.nextInt()) - 1;
            if (posicionMateria < 0 || posicionMateria > (materias.size() - 1)) {
                System.out.println("Materia no existente\n");
            }
        } while (posicionMateria < 0 || posicionMateria > (materias.size() - 1));
        System.out.println();
        mostrarTerminos();
        //ingreso y validación de término académico
        int posicionTerminoAcademico;
        do {
            System.out.print("Seleccione el término académico: ");
            posicionTerminoAcademico = (sc.nextInt()) - 1;
            if (posicionTerminoAcademico < 0 || posicionTerminoAcademico > terminos.size() - 1) {
                System.out.println("Término académico no existente\n");
            }
        } while (posicionTerminoAcademico < 0 || posicionTerminoAcademico > terminos.size() - 1);

        //ingreso y agregación de número de paralelo
        int numParaleloIngresado;
        do {
            System.out.print("\nIngrese el número del nuevo paralelo: ");
            numParaleloIngresado = sc.nextInt();
            if (numParaleloIngresado < 1 || repiteParalelo(numParaleloIngresado, posicionMateria, posicionTerminoAcademico)) {
                System.out.println("Paralelo repetido");
            }
        } while (numParaleloIngresado < 1 || repiteParalelo(numParaleloIngresado, posicionMateria, posicionTerminoAcademico));

        Estudiante[] estudiantes = new Estudiante[3];
        estudiantes[0] = new Estudiante("202208880", "MEJIA PARRA KEVIN SANTIAGO", "kesameji@espol.edu.ec");
        estudiantes[1] = new Estudiante("202211355", "ARAUJO ORTEGA DIEGO ENZO JAVIER", "dienarau@espol.edu.ec");
        estudiantes[2] = new Estudiante("202006086", "CABRERA VIVANCO ALVARO DAVID", "alvdcabr@espol.edu.ec");

        System.out.println("Cargando archivo de estudiantes........");

        paralelos.add(new Paralelo(numParaleloIngresado, terminos.get(posicionTerminoAcademico), materias.get(posicionMateria), estudiantes));

        System.out.println("Se ha añadido correctamento el paralelo\n");

    }

    public static boolean repiteParalelo(int numeroParalelo, int posicionMateria, int posicionTermino) {
        for (Paralelo p : paralelos) {
            if (p.getNumeroParalelo() == numeroParalelo && p.getMateria().equals(materias.get(posicionMateria)) && p.getTermino().equals(terminos.get(posicionTermino))) {
                return true;
            }
        }
        return false;
    }

    public static boolean existenParalelos(int posicionMateria) {
        for (Paralelo p : paralelos) {
            if (p.getMateria().equals(materias.get(posicionMateria))) {
                return true;
            }
        }
        return false;
    }

    public static boolean existenParalelos(int posicionMateria, int posicionTermino) {
        for (Paralelo p : paralelos) {
            if (p.getMateria().equals(materias.get(posicionMateria)) && p.getTermino().equals(terminos.get(posicionTermino))) {
                return true;
            }
        }
        return false;
    }

    public static void mostrarMateriasyParalelos() {
        System.out.println("MATERIAS");
        int i = 1;
        for (Materia m : materias) {
            System.out.println(i + ". " + m);
            i++;
            for (Paralelo p : paralelos) {
                if (p.getMateria().equals(m)) {
                    System.out.println("\t Paralelo " + p.getNumeroParalelo());
                }
            }
        }
    }

    public static void mostrarParalelos() {
        System.out.println("PARALELOS");
        int i = 1;
        for (Paralelo p : paralelos) {
            System.out.println(i + ". " + p);
            i++;
        }
    }

    public static int mostrarParalelos(int posicionMateria) {
        System.out.println("PARALELOS");
        int i = 1;
        for (Paralelo p : paralelos) {
            if (p.getMateria().equals(materias.get(posicionMateria))) {
                System.out.println(i + ". Paralelo " + p.getNumeroParalelo());
                i++;
            }
        }
        return i - 1;
    }

    public static int mostrarParalelos(int posicionMateria, int posicionTermino) {
        System.out.println("PARALELOS");
        int i = 1;
        for (Paralelo p : paralelos) {
            if (p.getTermino().equals(terminos.get(posicionTermino)) && p.getMateria().equals(materias.get(posicionMateria))) {
                System.out.println(i + ". " + p);
                i++;
            }
        }
        return i - 1;
    }

    //método que permite eliminar un paralelo
    public static void eliminarParalelo() {
        Scanner sc = new Scanner(System.in);

        System.out.println("ELIMINACIÓN DE PARALELO");

        //muestra una lista de todos los paralelos
        mostrarParalelos();
        //ingreso y validación de paralelo
        int posicionParalelo;
        do {
            System.out.print("Seleccione un paralelo a eliminar (0 para regresar): ");
            posicionParalelo = sc.nextInt() - 1;
            if (posicionParalelo < -1 || posicionParalelo > paralelos.size() - 1) {
                System.out.println("Paralelo no existente\n");
            }
        } while (posicionParalelo < -1 || posicionParalelo > paralelos.size() - 1);

        //elimina el paralelo seleccionado
        if (posicionParalelo != -1) {
            paralelos.remove(posicionParalelo);
            System.out.println("Paralelo eliminado correctamente");
        }
        System.out.println();

    }

    public static void ingresarCuestionario(Cuestionario cuestionario) {
        cuestionarios.add(cuestionario);
    }

    public static void mostrarJuegos() {
        for (Juego j : juegos) {
            System.out.println(j);
        }
    }

    public static void mostrarJuegos(int posicionParalelo) {
        System.out.println("REPORTES DEL PARALELO " + paralelos.get(posicionParalelo));
        for (Juego j : juegos) {
            if (j.getParalelo().equals(paralelos.get(posicionParalelo))) {
                System.out.println(j);
            }
        }
    }
    
    public static void mostrarPreguntasMateria(int posicionMateria){
        int i = 0;
        for(Pregunta p: materias.get(posicionMateria).getPreguntas()){
            System.out.println(i + ". " + p);
            i++;
            for(Opcion op: p.getOpciones()){
                System.out.println(op + "\n");
            }    
        }
    }
    
    public static int visualizarPreguntas(){
        mostrarMaterias();
        Scanner sc = new Scanner(System.in);
        int posicionMateria;
        do{
            System.out.println("Seleccione una materia (0 para regresar): ");
            posicionMateria = sc.nextInt() - 1;
            if (posicionMateria < -1 || posicionMateria > materias.size() - 1) System.out.println("Materia no encontrada\n");
        }while (posicionMateria < -1 || posicionMateria > materias.size() - 1);
        if (posicionMateria != -1){
            if (materias.get(posicionMateria).getPreguntas().isEmpty()) System.out.println("No existen preguntas para " + materias.get(posicionMateria) + "\n");
            else mostrarPreguntasMateria(posicionMateria);
        }
        System.out.println();
        return posicionMateria;
    }
    
    public static void eliminarPregunta(){
        int posicionMateria = visualizarPreguntas();
        if (posicionMateria != -1){
            Scanner sc = new Scanner(System.in);
            int posicionPregunta;
            do {
                System.out.print("Seleccione un pregunta a eliminar (0 para regresar): ");
                posicionPregunta = sc.nextInt() - 1;
                if (posicionPregunta < -1 || posicionPregunta > materias.get(posicionMateria).getPreguntas().size() - 1) {
                    System.out.println("Pregunta no existente\n");
                }
            } while (posicionPregunta < -1 || posicionPregunta > materias.get(posicionMateria).getPreguntas().size() - 1);

            //elimina la pregunta seleccionado
            if (posicionPregunta != -1) {
                materias.get(posicionMateria).getPreguntas().remove(posicionPregunta);
                System.out.println("Pregunta eliminado correctamente");
            }

            System.out.println();
        }
    }

    public static void CargarInformacion() {
        Estudiante[] estudiantes = new Estudiante[33];
        estudiantes[0] = new Estudiante("202210712", "BORBOR GUTIERREZ VICTOR DANIEL", "vicbguti@espol.edu.ec");
        estudiantes[1] = new Estudiante("202208880", "MEJIA PARRA KEVIN SANTIAGO", "kesameji@espol.edu.ec");
        estudiantes[2] = new Estudiante("202211355", "ARAUJO ORTEGA DIEGO ENZO JAVIER", "dienarau@espol.edu.ec");
        estudiantes[3] = new Estudiante("202006086", "CABRERA VIVANCO ALVARO DAVID", "alvdcabr@espol.edu.ec");
        estudiantes[4] = new Estudiante("202110136", "ACELDO TORRES MARIA GRAZIA", "maactorr@espol.edu.ec");
        estudiantes[5] = new Estudiante("202108643", "AGUILAR TINOCO JEAN CARLOS", "jcaguila@espol.edu.ec");
        estudiantes[6] = new Estudiante("202111928", "AMORETTI SANCHEZ JUAN CARLOS", "@espol.edu.ec");
        estudiantes[7] = new Estudiante("202105946", "ANDRADE VELASCO ANGELLO BERNIE", "jamorett@espol.edu.ec");
        estudiantes[8] = new Estudiante("202104816", "AZU PERLAZA NICOLE FERNANDA", "nfazu@espol.edu.ec");
        estudiantes[9] = new Estudiante("202110219", "BALDEON BAQUE IVAN GONZALO", "ivagbald@espol.edu.ec");
        estudiantes[10] = new Estudiante("202113056", "BARBERAN GALLARDO MELISSA ESTEFANIA", "melesbar@espol.edu.ec");
        estudiantes[11] = new Estudiante("202109328", "BASILIO ACEBO DANIELA MILENA", "dmbasili@espol.edu.ec");
        estudiantes[12] = new Estudiante("202113049", "CORDERO CALLES RONALD ELIAS", "rcordero@espol.edu.ec");
        estudiantes[13] = new Estudiante("202010278", "ESPINOZA PINELA ANGELO ALEXANDER", "angepine@espol.edu.ec");
        estudiantes[14] = new Estudiante("202108288", "GONZABAY ESPIN DOUGLAS VICENTE", "dvgonzab@espol.edu.ec");
        estudiantes[15] = new Estudiante("202100772", "GUAMAN QUIJIJE RONALD STEVEN", "rsguaman@espol.edu.ec");
        estudiantes[16] = new Estudiante("202208302", "HERRERA LEON ANTHONY ARTURO", "anthleon@espol.edu.ec");
        estudiantes[17] = new Estudiante("202202552", "LINO INDACOCHEA STEVEN MOISES", "stemlino@espol.edu.ec");
        estudiantes[18] = new Estudiante("202212965", "LORENZO LOPEZ ERICK GABRIEL", "erillope@espol.edu.ec");
        estudiantes[19] = new Estudiante("201405946", "MACIAS ARTURO LEONARDO DAVID", "leodamac@espol.edu.ec");
        estudiantes[20] = new Estudiante("202001244", "MAZA PUNINE ISSAC ALEXANDER", "issamaza@espol.edu.ec");
        estudiantes[21] = new Estudiante("202211306", "NAVARRETE CASTILLO ANTHONY JOSUE", "annacast@espol.edu.ec");
        estudiantes[22] = new Estudiante("202207726", "POVEDA QUIMIZ MICHAEL CRESCENCIO", "mcpoveda@espol.edu.ec");
        estudiantes[23] = new Estudiante("202207924", "RIVAS ABAD BRAULIO DE JESUS", "brarabad@espol.edu.ec");
        estudiantes[24] = new Estudiante("202111589", "RIVAS PINCAY EMMANUEL GERARDO", "egrivas@espol.edu.ec");
        estudiantes[25] = new Estudiante("202203428", "ROMERO ALMEIDA EMILIO JOSE", "emjorome@espol.edu.ec");
        estudiantes[26] = new Estudiante("202111910", "SANTANDER LOPEZ EDU ISRAEL", "eduisant@espol.edu.ec");
        estudiantes[27] = new Estudiante("201417520", "SUAREZ MENDIETA GARY STEVEN", "gssuarez@espol.edu.ec");
        estudiantes[28] = new Estudiante("202205324", "SUAREZ VALDIVIESO JOSE JULIO", "jojusuar@espol.edu.ec");
        estudiantes[29] = new Estudiante("202107645", "VARGAS ISA GENESIS DAYANNA", "gdvargas@espol.edu.ec");
        estudiantes[30] = new Estudiante("202109229", "VILLAMAGUA ESCUDERO JUAN MATEO", "juamvill@espol.edu.ec");
        estudiantes[31] = new Estudiante("202106050", "ZAMORA CEDEÑO JORDY STEVEN", "jszamora@espol.edu.ec");
        estudiantes[32] = new Estudiante("202208260", "ZARUMA GAME JOSHUA ANDRES", "jazaruma@espol.edu.ec");

        TerminoAcademico ta = new TerminoAcademico("2023", 1);
        terminos.add(ta);
        terminoJuego = ta;

        ArrayList<Pregunta> pre = new ArrayList<Pregunta>();

        pre.add(new Pregunta("Con que palabra se crea una clase", 1, new Opcion[]{
            new Opcion("Class", Respuesta.INCORRECTO),
            new Opcion("Clase", Respuesta.INCORRECTO),
            new Opcion("class", Respuesta.CORRECTO),
            new Opcion("clas", Respuesta.INCORRECTO)}));

        pre.add(new Pregunta("Que es POO", 1, new Opcion[]{
            new Opcion("Programacion orientada a objetos", Respuesta.CORRECTO),
            new Opcion("Problemas orientados a osos", Respuesta.INCORRECTO),
            new Opcion("Paradigma oriental de objetos", Respuesta.INCORRECTO),
            new Opcion("Presentacion occidental de ornitorincos", Respuesta.INCORRECTO)}));

        pre.add(new Pregunta("Qué línea de código se utiliza para importar ArrayList?", 1, new Opcion[]{
            new Opcion("import java.util.Stack", Respuesta.INCORRECTO),
            new Opcion("package java.util.Scanner", Respuesta.INCORRECTO),
            new Opcion("import java.util.ArrayList", Respuesta.CORRECTO),
            new Opcion("package java.util.ArrayList", Respuesta.INCORRECTO)}));

        pre.add(new Pregunta("Como se llama el compilador de java", 2, new Opcion[]{
            new Opcion("Java", Respuesta.INCORRECTO),
            new Opcion("JavaC", Respuesta.CORRECTO),
            new Opcion("JVM", Respuesta.INCORRECTO),
            new Opcion(".Jar", Respuesta.INCORRECTO)}));

        pre.add(new Pregunta("Que elementos corresponden a la firma de un metodo", 2, new Opcion[]{
            new Opcion("Tipo de retorno, nombre, parametros", Respuesta.INCORRECTO),
            new Opcion("modificador de acceso, tipo de retorno, nombre", Respuesta.INCORRECTO),
            new Opcion("modificador de acceso, nombre", Respuesta.INCORRECTO),
            new Opcion("nombre, parametros", Respuesta.CORRECTO)}));

        pre.add(new Pregunta("Cuáles son los modificadores de acceso?", 2, new Opcion[]{
            new Opcion("public, protected, private y default", Respuesta.CORRECTO),
            new Opcion("public, protected, private y friend", Respuesta.INCORRECTO),
            new Opcion("public, protected, private e internal", Respuesta.INCORRECTO),
            new Opcion("public, protected, private y  package-private plus", Respuesta.INCORRECTO)}));

        pre.add(new Pregunta("Cual es el valor por defecto de un arreglo de caracteres", 3, new Opcion[]{
            new Opcion("\\u000", Respuesta.CORRECTO),
            new Opcion("null", Respuesta.INCORRECTO),
            new Opcion("\\null", Respuesta.INCORRECTO),
            new Opcion("''", Respuesta.INCORRECTO)}));

        pre.add(new Pregunta("Cual es el tipo de retorno del metodo hashCode", 3, new Opcion[]{
            new Opcion("Object", Respuesta.INCORRECTO),
            new Opcion("boolean", Respuesta.INCORRECTO),
            new Opcion("int", Respuesta.CORRECTO),
            new Opcion("HashMap", Respuesta.INCORRECTO)}));

        pre.add(new Pregunta("Qué palabra reservada se utiliza para llamar en una clase hija a un atributo o método de una clase padre?", 3, new Opcion[]{
            new Opcion("father", Respuesta.INCORRECTO),
            new Opcion("super", Respuesta.CORRECTO),
            new Opcion("parent", Respuesta.INCORRECTO),
            new Opcion("this", Respuesta.INCORRECTO)}));

        pre.add(new Pregunta("¿Cuales son los 4 pilares fundamentales de la programacion orientada a objetos?", 2, new Opcion[]{
            new Opcion("Abstraccion, Encapsulamiento, Polimorfismo, Transferencia", Respuesta.INCORRECTO),
            new Opcion("Abstraccion, Capsulamiento, Polimorfismo, Herencia", Respuesta.INCORRECTO),
            new Opcion("Abstraccion, Encapsulamiento, Polimorfismo, Herencia", Respuesta.CORRECTO),
            new Opcion("Atraccion, Encapsulamiento, Polimorfismo, Herencia", Respuesta.INCORRECTO)}));

        pre.add(new Pregunta("¿Cual es la estructura para que una subclase publica herede de una superclase?", 2, new Opcion[]{
            new Opcion("public class Subclase extern Superclase", Respuesta.INCORRECTO),
            new Opcion("publica class Subclase extends Superclase", Respuesta.INCORRECTO),
            new Opcion("public class Superclase extends Subclase", Respuesta.INCORRECTO),
            new Opcion("public class Subclase extends Superclase", Respuesta.CORRECTO)}));

        pre.add(new Pregunta("¿Cómo escribiría el atributo privado nombre de tipo String en un diagrama UML?", 2, new Opcion[]{
            new Opcion(" + nombre: String", Respuesta.INCORRECTO),
            new Opcion(" - String: nombre", Respuesta.INCORRECTO),
            new Opcion(" - nombre: String", Respuesta.CORRECTO),
            new Opcion(" - nombre; String", Respuesta.INCORRECTO)}));
        
        Materia ma = new Materia("CCPG1052", "POO", 3, ta, pre);
        materias.add(ma);
        ta.addMaterias(ma);

        Paralelo pa = new Paralelo(3, ta, ma, estudiantes);
        paralelos.add(pa);

        cuestionarios.add(new Cuestionario(
                new Comodin[]{new Comodin("50/50"), new Comodin("Llamada al apoyo"), new Comodin("Pregunta al publico")},
                pre));

    }
}
