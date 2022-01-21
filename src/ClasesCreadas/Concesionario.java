package ClasesCreadas;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Clase Concesionario que gestiona objetos que cada uno de ellos puede contener
 * un máximo de TAM_MAX_CONCESIONARIO objetos de clase Vehiculo.
 *
 * Implementa la interfaz <b>Serializable</b> para que se pueda serializar, es
 * decir, se puedan pasar los objetos de esta clase a un fichero y luego
 * recuperar desde ahí. Los métodos que se usan para serializizar los objetos
 * están en la clase <b>Serializadora</b> en el paquete <b>Principal</b>
 *
 * @author Radomir Dimitrov Atanasov
 */
public class Concesionario implements Serializable {

    /*--------------------------------------------------*
     *              ATRIBUTOS DE CLASE                  *
     *--------------------------------------------------*/
    /**
     * Tamaño máximo del concesioanrio. Es una constante. Actualmente fijada en
     * 50.
     */
    private static final int TAM_MAX_CONCESIONARIO = 50;

    /*--------------------------------------------------*
     *             ATRIBUTOS DE OBJETOS                 *
     *--------------------------------------------------*/
    /**
     * Una lista que contendrá los vehículos guardados en el objeto instanciado
     * de la clase Concesionario.
     */
    LinkedList<Vehiculo> concesionario;

    /*-----------------------------------------------------------------------*
     *           EXPLICACIÓN SOBRE POR QUÉ UTILIZO LINKEDLIST                *
     *-----------------------------------------------------------------------*
     * He decidido utilizar LinkedList porque es una estructura adecuada     *
     * cuando la intención es hacer mucha manipulación de los datos.         *
     * Considero que el concesionario cumple con estos criterios. No se      *
     * plantea un constante borrado de datos, por lo tanto me parece muy     *
     * adecuado usar esta estructura.                                        *
     *                                                                       *
     * Además, a la hora de mantener por orden de matrícula es tan fácil     *
     * como utilizar la interfaz Comparable en Vehiculo y sobreescribir el   *
     * método compareTo indicando como se van a comparar dos objetos; junto  *
     * con esto utilizar Collections con el método sort() para ordenar la    *
     * lista tipo LinkedList después de cada insercion.                      *
     *                                                                       *
     * También hay que tener en cuenta que con las listas (y en particular   *
     * con LinkedList) se pueden utilizar los iteradores que facilitan mucho *
     * el manejo de la estructura de datos.                                  *
     *                                                                       *
     * Por último, dada la poca complejidad de la estructura a manejar, es   *
     * irrelevante si se van a utilizar listas o conjuntos. Quizás para esta *
     * tarea habría bastado tanto con LinkedList como con ArrayList o        *
     * TreeSet. Sin embargo, tras una investigación en la red me he          *
     * decantado por utilizar las listas LinkedList. Además de cumplir con   *
     * lo requerido en esta tarea, me parece una estructura bastante         *
     * elegante.                                                             *
     *                                                                       *
     * Quizás lo que más me hizo decantarme por las LinkedList es la         *
     * complejidad algorítmica de ambas listas. A continuación muestro un    *
     * breve resumen de las complejidades de cada una de las listas:         *
     *                                                                       *
     *-----------------------------------------------------------------------*
     *                     | Complejidad | Complejidad | Preferible en       *
     *       Operación     | LinkedList: |  ArrayList: |   este caso:        *
     * --------------------------------------------------------------------- *
     *     Insertar en el  |    O(1)     |    O(1)     | LinkedList          *
     *     última posición |             |             |                     *
     * --------------------------------------------------------------------- *
     *     Insertar en un  |    O(N)     |    O(N)     | LinkedList          *
     *       índice dado   |             |             |                     *
     * --------------------------------------------------------------------- *
     *      Buscar por     |    O(N)     |    O(N)     | ArrayList           *
     *         valor       |             |             |                     *
     * --------------------------------------------------------------------- *
     *      Obtener un     |    O(N)     |    O(1)     | ArrayList           *
     *        índice       |             |             |                     *
     * --------------------------------------------------------------------- *
     *      Borrar por     |    O(N)     |    O(N)     | LinkedList          *
     *      valor dado     |             |             |                     *
     * --------------------------------------------------------------------- *
     *      Borrar por     |    O(N)     |    O(N)     | LinkedList          *
     *      índice dado    |             |             |                     *
     * --------------------------------------------------------------------- *
     * fuente:                                                               *
     * https://dzone.com/articles/performance-analysis-of-arraylist-and-linkedlist-i
     *-----------------------------------------------------------------------*


     *--------------------------------------------------*
     *                   CONSTRUCTORES                  *
     *--------------------------------------------------*/
    /**
     * Constructor para la clase Concesioanrio.
     */
    public Concesionario() {

        this.concesionario = new LinkedList<>();

    }//fin constructor Concesionario()

    /**
     * Constructor que recibe como parametro un cocnesionario y crea uno nuevo
     * con los mismos datos que el pasado como parámetro. Es decir, hace una
     * copia identica.
     *
     * @param cons El cocnesionario dek que copiamos
     */
    public Concesionario(Concesionario cons) {

        Iterator<Vehiculo> it = cons.concesionario.iterator();
        this.concesionario = new LinkedList<>();

        //Copiamos uno a uno los objetos de la lista
        while (it.hasNext()) {
            this.concesionario.add(it.next());
        }
        //Ordenamos la lista
        Collections.sort(this.concesionario);

    }//fin constructor Cocnesionario(Concesionario cons)

    /*--------------------------------------------------*
     *                     MÉTODOS                      *
     *--------------------------------------------------*/
    /**
     * Este método busca en los datos almacenados en el concesionario y si
     * encuentra la matrícula devuelve una cadena con la información del
     * vehículo.
     *
     * Utiliza un iterador para ir vehiculo por vehiculo buscando el que tenga
     * la matricula indicada.
     *
     * Se ha agregado que imprima todos los datos del vehículo.
     *
     * @param matricula La matrícula que vamos a buscar en el cocnesioanrio
     *
     * @return Si encuentra el vehículo devuelve un <b>String</b> con la
     * información del mismo. Sino devuelve <b>null</b>.
     */
    public String buscaVehiculo(String matricula) {

        String datosVehiculo = null;
        Iterator<Vehiculo> it = this.concesionario.iterator();
        Vehiculo vehAux;

        //Si la matrícula pasada como parámetro no es null
        //Y el concesionario contiene datos
        if ((matricula != null) && (!this.concesionario.isEmpty())) {
            //Recorremos la lista con el iterador
            while (it.hasNext()) {
                vehAux = it.next();
                //Si la matricula coincide copiamos la información del vehículo
                if (vehAux.comparaMatricula(matricula) == true) {
                    datosVehiculo
                            = "-- Matrícula: " + vehAux.getMatricula()
                            + "\n-- Marca: " + vehAux.getMarca()
                            + "\n-- Precio: " + String.format("%.2f", vehAux.getPrecio()) + "€"
                            + "\n-- Fecha de matrícula: "
                            + vehAux.getFechaMatricula().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                            + "\n-- Años de antigüedad: " + vehAux.getAnios()
                            + "\n-- Kilometraje: " + vehAux.getKilometros()
                            + "\n-- Nombre del propietario: " + vehAux.getNombrePropietario()
                            + "\n-- DNI del propietario: " + vehAux.getDniPropietario()
                            + "\n-- Descripción del vehículo: " + vehAux.getDescripcionVehiculo();
                    break;//Una vez encontrado el vehículo no hace falta seguir.
                }
            }
        }

        return datosVehiculo;

    }//fin buscaVehiculo

    /**
     * Insertar un nuevo objeto de la clase Vehiculo en el obejto que representa
     * el concesionario.
     *
     * Se comprueba que el concesionario no esté lleno y se crea un objeto de
     * tipo Vehiculo con los datos del vehículo que se quiere insertar. Luego,
     * utilizando un iterador se mira vehiculo por vehiculo, comparando con el
     * método <b>compareTo</b>, si hay otro vehiculo presente con la misma
     * matricula. Por último, si no se encuentra y el concesionario no esta
     * lleno, se procede a insertar el nuevo vehiculo.
     *
     * @param marca Marca del vehículo que se va a insertar
     * @param matricula Matrícula del vehículo que se va a insertar
     * @param km Kilometraje del vehículo que se va a insertar
     * @param fecha Fecha de matriculación del vehículo que se va a isnertar
     * @param precio Precio del vehículo que se va a insertar
     * @param nombre Nombre del propietario del vehículo que se va a insertar
     * @param dni DNI del propietario del vehículo que se va a insertar
     * @param descripcion Descripción del vehículo que se va a insertar
     *
     * @return <b>0</b> si se ha incertado correctamente; <b>-1</b> si el
     * concesioanrio está lleno; <b>-2</b> si la matrícula del vehículo que se
     * quiere insertar ya está presente.
     */
    public int insertarVehiculo(String marca, String matricula, int km, String fecha,
            double precio, String nombre, String dni, String descripcion) {

        int devolver = 0;

        //Si el concesionario está lleno
        if (this.concesionario.size() == TAM_MAX_CONCESIONARIO) {
            devolver = -1;
        } else {
            //Solo hace falta usar estas variables si el concesionario no esta lleno.
            Vehiculo veh = new Vehiculo(marca, matricula, km, fecha, precio, nombre,
                    dni, descripcion);
            Vehiculo vehAux;

            //Recorremos el concesioanrio con un iterador viendo si la matrícula ya está
            Iterator<Vehiculo> it = this.concesionario.iterator();
            while (it.hasNext()) {
                vehAux = it.next();
                if (vehAux.compareTo(veh) == 0) {
                    //El vehículo ya está 
                    devolver = -2;
                    break; //No hace falta seguir con el while. Salimos.
                }
            }
            //Si no se ha encontrado la matricula
            if (devolver != -2) {
                //Introducimos el nuevo vehículo
                this.concesionario.add(veh);
                //Después de cada inserción ordenamos el concesionario. 
                //En la implementacion del método compareTo se especifica que el
                //orden es por matrícula (de menor a mayor)
                Collections.sort(this.concesionario);
            }
        }

        return devolver;

    }//fin insertarVehiculo

    /**
     * Imprime por pantalla los datos de todos los vehículos pertenecientes al
     * concecionario.
     *
     * Primero comprobamos que el concesioanrio no esté vacío. Si no lo está
     * recorremos la lista e imprimimos los dátos de los vehículo.
     *
     * @return <b>true</b> si hay vehículos y se imprimen. <b>false</b> si no
     * hay vehículos para imprimir
     */
    public boolean listarVehiculos() {

        boolean valido = false;

        //Si el array existe y no está vacío
        if (!this.concesionario.isEmpty()) {
            int index = 1; //Usamos como indice para imprimir los datos
            Iterator<Vehiculo> it = this.concesionario.iterator();
            Vehiculo vehAux;

            //Recorremos el array con un iterador
            while (it.hasNext()) {
                //Copiamos elemento a mostrar
                vehAux = it.next();
                //Mostrar información de cada objeto
                System.out.println(String.format("%2d", index++)
                        + ". Matrícula: " + vehAux.getMatricula()
                        + " | Marca: " + vehAux.getMarca()
                        + " | Precio: " + String.format("%.2f", vehAux.getPrecio()) + "€"
                        + " | Kms: " + vehAux.getKilometros()
                        + " | Descripción: " + vehAux.getDescripcionVehiculo()
                );
            }//fin while
            //Indicamos que había datos para imprimir y los hemos imprimido
            valido = true;
        }

        return valido;
        
    }//fin listarVehiculos

    /**
     * Método que actualiza el kilometraje de un vehículo.
     *
     * Se reciben como parámetros la matrícula del vehículo cuyos kms se quieren
     * actualizar y el nuevo valor del kilometraje (debe ser superior al ya
     * existente).
     *
     * @param matricula Matrícula del vehículo
     * @param kms Nuevo valor del kilometraje del vehículo
     *
     * @return <b>true</b> si ha sido posible actualizar; <b>false</b> si no ha
     * sido posible actualizar (concecionario vacío o vehículo con la matrícula
     * indicada no está presente).
     */
    public boolean actualizaKms(String matricula, int kms) {

        boolean valido = false;
        Iterator<Vehiculo> it = this.concesionario.iterator();
        Vehiculo vehAux;

        //Si el concesionario no está vacío
        if (!this.concesionario.isEmpty()) {
            //Recorremos con un iterador
            while (it.hasNext()) {
                vehAux = it.next();
                //Si encontramos la matrícula
                if (vehAux.comparaMatricula(matricula) == true) {
                    //Si intentamos ingresar un valor inferior al actual
                    if (kms <= vehAux.getKilometros()) {
                        valido = false;
                    } else {
                        //Actualizamos los KMs
                        vehAux.setKilometros(kms);
                        valido = true;
                        break;//Una vez actualizado salimos del while
                    }
                }
            }//fin while
        }

        return valido;
        
    }//fin actualizaKms

    /**
     * Comprobar si el concesionario está vacío.
     *
     * Método reimplementado. Para mantener compatibilidad, simplemente se han
     * cambiado las instrucciones. Llamado desde fuera, el método parece igual.
     *
     * @return <b>true</b> si está vacío; <b>false</b> si no está vacío.
     */
    public boolean esVacio() {

        return this.concesionario.isEmpty();

    }//fin esVacio

    /**
     * Método que borra un vehículo con una matrícula dada.
     *
     * @param matricula La matrícula del vehículo que queremos borrar
     *
     * @return <b>0</b> si se ha borrado; <b>-1</b> si no existe y no se ha
     * borrado
     */
    public int borrarVehiculo(String matricula) {

        int dev = -1;
        Vehiculo vehAux;
        Iterator<Vehiculo> it = this.concesionario.iterator();

        //Recorremos el concesionario con un iterador
        while (it.hasNext()) {
            vehAux = it.next();
            //Si encontramos el elemento lo borramos.
            if (vehAux.comparaMatricula(matricula) == true) {
                it.remove();
                dev = 0;
                break; //Una vez borrado salimos del while; no hace falta seguir
            }
        }

        return dev;
        
    }//fin borrarVehiculo

}//fin Concesioanrio
