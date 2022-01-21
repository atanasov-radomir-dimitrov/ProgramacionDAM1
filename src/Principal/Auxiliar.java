package Principal;

import ClasesCreadas.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Clase auxiliar con métodos auxiliares para la tarea.
 *
 * Contiene los métodos pintarMenu() para pintar el menú de opciones y el método
 * rellenarConcesionario() para rellenar el concesionario con objetos de tipo
 * Vehiculo para poder hacer pruebas con él.
 *
 * Se agregan los siguientes métodos: adaptarRuta(), pedirRuta(),
 * mostrarContenido()
 *
 * @author Radomir Dimitrov Atanasov
 */
public class Auxiliar {

    /**
     * Método público estático que imprime por pantalla el menú con las 6
     * opciones descritas en el enunciado de la tarea.
     *
     * Sin parámetros de entrada. No devuelve nada.
     */
    public static void pintarMenu() {

        System.out.println("");
        System.out.println("|----------------------------|");
        System.out.println("|       MENU PRINCIPAL       |");
        System.out.println("|----------------------------|");
        System.out.println("|1. Nuevo vehículo           |");
        System.out.println("|2. Listar vehículos         |");
        System.out.println("|3. Buscar vehículo          |");
        System.out.println("|4. Modificar kms vehículo   |");
        System.out.println("|5. Eliminar vehículo        |");
        System.out.println("|6. Salir                    |");
        System.out.println("|----------------------------|");
        System.out.print("\n¿Qué quieres hacer? Elige 1 | 2 | 3 | 4 | 5 | 6 :  ");

    }//fin pintarMenu

    /**
     * Método auxiliar para rellenar el concesionario con 50 vehículos.
     *
     * Utilizo este método solamente para hacer pruebas con el concesionario, en
     * plan rellenarlo e intentar introducir mas de 50 objetos, borrar,
     * visualizar, etc. Realmente no es parte de la tarea, sino de la prueba del
     * correcto funcionamiento de la tarea. Sin embargo dejo este método
     * implementado por si se quisiera utilizar a la hora de hacer las pruebas.
     *
     * Apunte... aunque se intentara insertar mas de 50 vehículos, no sería
     * posible porque el mñetodo insertarVehiculo de la clase Cocnesionario no
     * lo permitiria; su funcionamiento interno de dejaría que se introdujera
     * ningún vehículo más una vez alcanzados los 50 vehículos.
     *
     * :)
     *
     * @param concesionario El concesionario a rellenar.
     */
    public static void rellenarConcesionario(Concesionario concesionario) {

        for (int i = 60; i > 10; i--) {
            concesionario.insertarVehiculo(
                    "Marca" + (char) (65 + i % 25),
                    i + "11AAA",
                    i,
                    "01-01-19" + i,
                    i,
                    "nombre" + " apellido" + " algomas" + (char) (65 + i % 25),
                    "000000" + i + "t",
                    "Descripcion" + i);
        }
    }//fin rellenarConcesionario

    /**
     * Método que adapta una ruta para que pueda manejar correctamente
     * independientemente del sistema operativo (Windows, Linux, Mac).
     *
     * @param rutaFich La ruta que se quiere adaptar
     *
     * @return La ruta modificada y adaptada
     */
    public static String adaptarRuta(String rutaFich) {

        String separador = "\\";
        try {
            //Estamos en Windows
            if (File.separator.equals(separador)) {
                separador = "/";
            }
            // Reemplaza con File.separator
            return rutaFich.replaceAll(separador, File.separator);
        } catch (Exception e) {
            //Por si ocurre una java.util.regex.PatternSyntaxException
            return rutaFich.replaceAll(separador + separador, File.separator);
        }
    }//fin convertirRutaFichero

    /**
     * Método que pide un fichero con o sin ruta y la adapta independientemente
     * del sistema operativo que se use.
     *
     * Si ha havido algún error se devolverá null.
     *
     * @return La ruta si todo ha ido bien. null si ha habido error.
     */
    public static String pedirRuta() {

        BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
        String ruta;

        System.out.println("Introduce nombre del fichero en el "
                + "que están almacenados los datos.");
        System.out.print("Si el fichero no existe se creará:  ");
        try {
            ruta = buf.readLine();
            ruta = Auxiliar.adaptarRuta(ruta);
        } catch (IOException ex) {
            ruta = null;
        }

        return ruta;
        
    }//fin pedirRuta

    /**
     * Método que muestra el contenido del directorio desde el que se le está
     * llamando.
     */
    public static void mostrarContenido() {

        File f = new File(".");
        String[] s = f.list();
        Arrays.sort(s); //Lo ordeno para imprimir los fichero en orden alfabético

        System.out.println("Este es el contenido del directorio actual.");
        System.out.println("(D) indica directorio, (F) indica fichero: \n");
        for (int i = 0; i < s.length; i++) {
            f = new File(s[i]);
            if (f.isDirectory()) {
                System.out.printf("%3d%s", (i + 1), ".(D)  ");
            } else {
                System.out.printf("%3d%s", (i + 1), ".(F)  ");
            }
            System.out.printf("%s\n", s[i]);

        }
        System.out.println("");

    }//fin mnostrarContenido

}//fin de Auxiliar
