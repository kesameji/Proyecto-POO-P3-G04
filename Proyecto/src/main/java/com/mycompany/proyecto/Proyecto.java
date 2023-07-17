package com.mycompany.proyecto;

import com.mycompany.proyecto.administrables.Estudiante;
import com.mycompany.proyecto.administrables.Paralelo;
import com.mycompany.proyecto.administrables.Materia;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Proyecto {

    public static void main(String[] args) {

        Configuracion.CargarInformacion();
        Scanner sc = new Scanner(System.in);
        String opcion = "";

        while (!"4".equals(opcion)) {
            System.out.println("""
                               1. Configuraciones
                               2. Nuevo juego
                               3. Reporte
                               4. Salir""");
            opcion = sc.nextLine().strip();
            System.out.println("");

            switch (opcion) {
                case "1":
                    Configuraciones();
                    break;
                case "2":
                    nuevoJuego();
                    break;
                case "3":
                    Reporte();
                    break;
            }
        }
    }

    /**
     * Muestra en pantalla las distintas opciones que se pueden configurar
     * este método no recibe parametros
     */
    public static void Configuraciones() {
        String opcion = "";
        Scanner sc = new Scanner(System.in);
        while (!"4".equals(opcion)) {
            System.out.println("""
                           1. Administrar terminos academicos
                           2. Administrar materias y paralelos
                           3. Administrar preguntas
                           4. Salir""");
            opcion = sc.nextLine().strip();
            System.out.println("");
            switch (opcion) {
                case "1":
                    AdministrarTermino();
                    break;
                case "2":
                    AdministrarMateriaParalelo();



                    break;
                case "3":
                    AdministrarPreguntas();
                    break;
            }
        }

package com.mycompany.proyecto;

import com.mycompany.proyecto.administrables.Estudiante;
import com.mycompany.proyecto.administrables.Paralelo;
import com.mycompany.proyecto.administrables.Materia;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Proyecto {

    public static void main(String[] args) {

        Configuracion.CargarInformacion();
        Scanner sc = new Scanner(System.in);
        String opcion = "";

        while (!"4".equals(opcion)) {
            System.out.println("""
                               1. Configuraciones
                               2. Nuevo juego
                               3. Reporte
                               4. Salir""");
            opcion = sc.nextLine().strip();
            System.out.println("");

            switch (opcion) {
                case "1":
                    Configuraciones();
                    break;
                case "2":
                    nuevoJuego();
                    break;
                case "3":
                    Reporte();
                    break;
            }
        }
    }

    /**
     * Muestra en pantalla las distintas opciones que se pueden configurar
     * este método no recibe parametros
     */
    public static void Configuraciones() {
        String opcion = "";
        Scanner sc = new Scanner(System.in);
        while (!"4".equals(opcion)) {
            System.out.println("""
                           1. Administrar terminos academicos
                           2. Administrar materias y paralelos
                           3. Administrar preguntas
                           4. Salir""");
            opcion = sc.nextLine().strip();
            System.out.println("");
            switch (opcion) {
                case "1":
                    AdministrarTermino();
                    break;
                case "2":
                    AdministrarMateriaParalelo();



                    break;
                case "3":
                    AdministrarPreguntas();
                    break;
            }
        }

    }

    /**
     * Metodo estatico para acceder a las distintas opciones para hacer configuraciones en los terminos academicos
     * este método no recibe parametros
     */
    public static void AdministrarTermino() {
        String opcion = "";
        Scanner sc = new Scanner(System.in);
        Configuracion.mostrarTerminos();
        while (!"4".equals(opcion)) {
            System.out.println("""
                           1. Ingresar termino 
                           2. Editar termino 
                           3. Configurar termino para el juego
                           4. Salir""");
            opcion = sc.nextLine().strip();
            System.out.println("");
            switch (opcion) {
                case "1":
                    Configuracion.ingresarTermino();
                    break;
                case "2":
                    Configuracion.editarTermino();
                    break;
                case "3":
                    Configuracion.escogerTermino();
                    break;
            }
        }
    }

    /**
     * Metodo estatico indica los cambios significativos que se pueden realizar en cuanto a la materia y paralelo
     * este método no recibe parametros
     */
    public static void AdministrarMateriaParalelo() {
        String opcion = "";
        Scanner sc = new Scanner(System.in);
        while (!"5".equals(opcion)) {
            Configuracion.mostrarMateriasyParalelos();
            System.out.println("""
                           1. Ingresar materia
                           2. Editar materia
                           3. Agregar paralelo

                           4. Eliminar paralelo
                           5. Salir""");
            opcion = sc.nextLine().strip();
            System.out.println("");
            switch (opcion) {
                case "1":
                    Configuracion.ingresarMateria();
                    break;
                case "2":
                    Configuracion.editarMateria();
                    break;
                case "3":
                    Configuracion.ingresarParalelo();
                    break;
                case "4":
                    if (Configuracion.paralelos.isEmpty()) {
                        System.out.println("No existen paralelos para eliminar\n");
                    } else {
                        Configuracion.eliminarParalelo();
                    }
                    break;
            }
        }
    }

    /**
     * Metodo estatico para acceder a las distintas opciones para hacer configuraciones en los terminos academicos
     * este método no recibe parametros
     */
    public static void AdministrarTermino() {
        String opcion = "";
        Scanner sc = new Scanner(System.in);
        Configuracion.mostrarTerminos();
        while (!"4".equals(opcion)) {
            System.out.println("""
                           1. Ingresar termino 
                           2. Editar termino 
                           3. Configurar termino para el juego
                           4. Salir""");
            opcion = sc.nextLine().strip();
            System.out.println("");
            switch (opcion) {
                case "1":
                    Configuracion.ingresarTermino();
                    break;
                case "2":
                    Configuracion.editarTermino();
                    break;
                case "3":
                    Configuracion.escogerTermino();
                    break;
            }
        }
    }

    /**
     * Metodo estatico indica los cambios significativos que se pueden realizar en cuanto a la materia y paralelo
     * este método no recibe parametros
     */
    public static void AdministrarMateriaParalelo() {
        String opcion = "";
        Scanner sc = new Scanner(System.in);
        while (!"5".equals(opcion)) {
            Configuracion.mostrarMateriasyParalelos();
            System.out.println("""
                           1. Ingresar materia
                           2. Editar materia
                           3. Agregar paralelo

                           4. Eliminar paralelo
                           5. Salir""");
            opcion = sc.nextLine().strip();
            System.out.println("");
            switch (opcion) {
                case "1":
                    Configuracion.ingresarMateria();
                    break;
                case "2":
                    Configuracion.editarMateria();
                    break;
                case "3":
                    Configuracion.ingresarParalelo();
                    break;
                case "4":
                    if (Configuracion.paralelos.isEmpty()) {
                        System.out.println("No existen paralelos para eliminar\n");
                    } else {
                        Configuracion.eliminarParalelo();
                    }
                    break;
            }
        }
    }


    /**
     * Metodo estatico que crea un nuevo juego acorde al jugador
     * metodo sin parametros
     */
    public static void nuevoJuego() {
        Scanner sc = new Scanner(System.in);
        ArrayList<Materia> materias = seleccionarMaterias();
        if (materias.isEmpty()) {
            System.out.println("No existe materias registradas en el termino actual");
            System.out.println("");
            return;
        }
        Configuracion.mostrarMaterias(materias);
        int opcion;
        do {
            System.out.println("Por favor seleccione la materia: ");
            opcion = Integer.parseInt(sc.nextLine().strip()) - 1;
            if (opcion < 0 || opcion > materias.size() - 1) {
                System.out.println("Materia no existente\n");
            }
        } while (opcion < 0 || opcion > materias.size() - 1);
        Materia ma = materias.get(opcion);

        System.out.println();

        if (Configuracion.existenParalelos(opcion)) {
            int valorMax = Configuracion.mostrarParalelos(opcion) - 1;
            do {
                System.out.println("Por favor seleccione el Paralelo: ");
                opcion = Integer.parseInt(sc.nextLine().strip()) - 1;
                if (opcion < 0 || opcion > valorMax) {
                    System.out.println("Paralelo no existente\n");
                }
            } while (opcion < 0 || opcion > valorMax);
            Paralelo pa = Configuracion.paralelos.get(opcion);
            System.out.println();

            Estudiante participante = seleccionarEstudiante(pa, "participante");

            Estudiante apoyo = new Estudiante();
            do {
                apoyo = seleccionarEstudiante(pa, "apoyo", participante);
            } while (apoyo == null || apoyo.getMatricula().equals(participante.getMatricula()));

            Juego juego = new Juego(Configuracion.terminoJuego, ma, pa, participante, apoyo, "hoy", 1, Configuracion.cuestionarios.get(0));
            juego.iniciarJuego();
        } else {
            System.out.println("No existen paralelos para esta materia\n");
        }
    }

    /**
     * Muestra los juegos creados en base a las materias, paralelos y terminos academicos
     * este método no recibe parametros
     */
    public static void Reporte() {

        Scanner sc = new Scanner(System.in);

        Configuracion.mostrarMaterias();
        int materiaEscogida;
        do {
            System.out.print("Seleccione una materia: ");
            materiaEscogida = sc.nextInt();
            if (materiaEscogida < 1 || materiaEscogida > Configuracion.materias.size()) {
                System.out.println("Materia no existente\n");
            }
        } while (materiaEscogida < 1 || materiaEscogida > Configuracion.materias.size());
        System.out.println();

        Configuracion.mostrarTerminos();
                    AdministrarMateriaParalelo();
        String opcion = "";
        Scanner sc = new Scanner(System.in);
        Configuracion.mostrarTerminos();
        while (!"4".equals(opcion)) {
            System.out.println("""
                           1. Ingresar termino 
                           2. Editar termino 
                           3. Configurar termino para el juego
                           4. Salir""");
            opcion = sc.nextLine().strip();
            System.out.println("");
            switch (opcion) {
                case "1":
                    Configuracion.ingresarTermino();
            if (opcion < 0 || opcion > materias.size() - 1) {
                System.out.println("Materia no existente\n");
            }
        } while (opcion < 0 || opcion > materias.size() - 1);
        Materia ma = materias.get(opcion);

        System.out.println();

        if (Configuracion.existenParalelos(opcion)) {
            int valorMax = Configuracion.mostrarParalelos(opcion) - 1;
            do {
                System.out.println("Por favor seleccione el Paralelo: ");
                opcion = Integer.parseInt(sc.nextLine().strip()) - 1;
                if (opcion < 0 || opcion > valorMax) {
                    System.out.println("Paralelo no existente\n");
                }
            } while (opcion < 0 || opcion > valorMax);
            Paralelo pa = Configuracion.paralelos.get(opcion);
            System.out.println();

            Estudiante participante = seleccionarEstudiante(pa, "participante");

            Estudiante apoyo = new Estudiante();
            do {
                apoyo = seleccionarEstudiante(pa, "apoyo", participante);
            } while (apoyo == null || apoyo.getMatricula().equals(participante.getMatricula()));

            Juego juego = new Juego(Configuracion.terminoJuego, ma, pa, participante, apoyo, "hoy", 1, Configuracion.cuestionarios.get(0));
            juego.iniciarJuego();
        } else {
            System.out.println("No existen paralelos para esta materia\n");
        }
    }

    /**
     * Muestra los juegos creados en base a las materias, paralelos y terminos academicos
     * este método no recibe parametros
     */
    public static void Reporte() {

        Scanner sc = new Scanner(System.in);

        Configuracion.mostrarMaterias();
        int materiaEscogida;
        do {
            System.out.print("Seleccione una materia: ");
            materiaEscogida = sc.nextInt();
            if (materiaEscogida < 1 || materiaEscogida > Configuracion.materias.size()) {
                System.out.println("Materia no existente\n");
            }
        } while (materiaEscogida < 1 || materiaEscogida > Configuracion.materias.size());
        System.out.println();

        Configuracion.mostrarTerminos();
        int terminoEscogido;
        do {
            System.out.print("Seleccione un término académico: ");
            terminoEscogido = sc.nextInt();
            if (terminoEscogido < 1 || terminoEscogido > Configuracion.terminos.size()) {
                System.out.println("Término académico no existente\n");
            }
        } while (terminoEscogido < 1 || terminoEscogido > Configuracion.terminos.size());
        System.out.println();

        if (Configuracion.existenParalelos(materiaEscogida - 1, terminoEscogido - 1)) {
            int indiceMax = Configuracion.mostrarParalelos(terminoEscogido - 1, materiaEscogida - 1);
            int paraleloEscogido;
            do {
                System.out.print("Seleccione un paralelo: ");
                paraleloEscogido = sc.nextInt();
                if (paraleloEscogido < 1 || paraleloEscogido > indiceMax) {
                    System.out.println("Paralelo no existente\n");
                }
            } while (paraleloEscogido < 1 || paraleloEscogido > indiceMax);
            System.out.println();
            if (Configuracion.juegos.isEmpty()) {
                System.out.println("No existen reportes en el paralelo escogido");
            } else {
                Configuracion.mostrarJuegos(paraleloEscogido - 1);
            }
        } else {
            System.out.println("\nNo existen paralelos para la materia y el termino escogido");
        }
        System.out.println();

    }
    
    /**
     * Escoge al jugador por su matricula, sino aleatoriamente
     * @param pa 
     * @param mensaje
     */
    public static Estudiante seleccionarEstudiante(Paralelo pa, String mensaje) {
        Scanner sc = new Scanner(System.in);

        int est;
        do {
            System.out.println("Por favor seleccione al " + mensaje + ": ");
            System.out.println("""
                                   1. Ingresar matricula
                                   2. Escoger Aleatorio""");
            est = sc.nextInt();
            if (est < 1 || est > 2) {
                System.out.println("Opción no encontrada\n");
            }
        } while (est < 1 || est > 2);
        Estudiante estudiante = new Estudiante();
        if (est == 1) {
            sc.nextLine();
            do {
                System.out.println("Matricula: ");
                String matricula = sc.nextLine().strip();
                estudiante = encontrarEstudiante(matricula, pa);
                if (estudiante == null) {
                    System.out.println("Participante no encontrado, Por favor vuelva a intentar.\n");
                }
            } while (estudiante == null);
        } else if (est == 2) {
            Random rd = new Random();
            int escogido = rd.nextInt(0, pa.getEstudiantes().length);
            estudiante = pa.getEstudiantes()[escogido];
        }

        System.out.println("Participante " + estudiante.getNombre() + " escogido\n");

        return estudiante;
    }
    
    /**
     * Metodo que retorna a cada estudiante segun su matricula y paralelo
     * @param matricula
     * @param paralelo
     */
    public static Estudiante encontrarEstudiante(String matricula, Paralelo paralelo) {
        for (Estudiante e : paralelo.getEstudiantes()) {
            if (matricula.equals(e.getMatricula())) {
                return e;
            }
        }
        return null;
    }
    
    
    /**
     * Selecciona las materias en base a los terminos academicos validos
     * no recibe parametros
     */
    public static ArrayList<Materia> seleccionarMaterias() {
        ArrayList<Materia> materias = new ArrayList<Materia>();
        for (Materia ma : Configuracion.materias) {
            if (ma.getTermino().equals(Configuracion.terminoJuego)) {
                materias.add(ma);
            }
        }
        return materias;
    }
}