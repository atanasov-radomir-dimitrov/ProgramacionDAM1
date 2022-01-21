package Principal;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Esta clase contiene 2 métodos para poder serializar objetos.
 *
 * Es decir, pasar objetos de cualquier clase a un fichero o leer un objeto
 * desde un fichero.
 *
 * Si sucediese cualquier probela y se lanzara alguna excepcion, esta es lanzada
 * con la clauslua throws y es tratada desde el punto en el que se llama al
 * método que lanza la excepcion.
 *
 * Para poder escribir/leer los objetos, las clases de dichos objetos deben
 * implementar la interfaz Serializable; se trata de las clases Vehiculo y
 * Concesionario
 *
 * @author Radomir Dimitrov Atanasov
 */
public class Serializadora {

    /**
     * Escribe el objeto en un fichero.
     *
     * @param objeto El objeto que quiere ser escrito en el fichero
     * @param fich El fichero en el que se quiere escribir el objeto
     *
     * @throws Exception Excepcion que lanza si se ha producido una excepcion y
     * esta pueda ser capturada fuera de este metodo.
     */
    public static void escribirObjeto(Object objeto, String fich) throws Exception {

        ObjectOutputStream escritorObjetos = new ObjectOutputStream(
                new FileOutputStream(fich));
        escritorObjetos.writeObject(objeto);

    }//fin escribirObjeto

    /**
     * Lee un objeto desde un archivo.
     *
     * @param nombreDelArchivo Nombre del archivo desde el que vamos a leer el
     * objeto
     *
     * @return El objeto que se ha leido desde el archivo
     *
     * @throws Exception Excepcion que lanza si se ha producido una excepcion y
     * esta pueda ser capturada fuera de este metodo.
     */
    public static Object leerObjeto(String nombreDelArchivo) throws Exception {

        ObjectInputStream lectorObjetos = new ObjectInputStream(
                new FileInputStream(nombreDelArchivo));
        return lectorObjetos.readObject();

    }//fin leerObjeto

}//fin Serializadora
