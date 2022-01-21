package ClasesCreadas;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Clase Vehiculo que contiene los atributos y métodos para manejar objetos de
 * esta clase.
 *
 * Implementamos la interfaz <b>Comparable</b> para poder reescribir el método
 * <b>compareTo</b> que se usará para establecer una manera de comparar dos
 * vehículos.
 *
 * Implementa la interfaz <b>Serializable</b> para que se pueda serializar, es
 * decir, se puedan pasar los objetos de esta clase a un fichero y luego
 * recuperar desde ahí. Los métodos que se usan para serializizar los objetos
 * están en la clase <b>Serializadora</b> en el paquete <b>Principal</b>
 *
 * @author Radomir Dimitrov Atanasov
 */
public class Vehiculo implements Comparable<Vehiculo>, Serializable {

    /*--------------------------------------------------*
     *             ATRIBUTOS DE OBJETOS                 *
     *--------------------------------------------------*/
    /**
     * Marca del vehículo
     */
    private String marca;
    /**
     * Matrícula del vehículo
     */
    private String matricula;
    /**
     * Kilómetros recorridos del vehículo
     */
    private int kilometros;
    /**
     * Fecha de matriculación del vehículo
     */
    private LocalDate fechaMatricula;
    /**
     * Precio de compra del vehículo
     */
    private double precio;
    /**
     * Nombre del propietario del vehículo
     */
    private String nombrePropietario;
    /**
     * DNI del propietario del vehículo
     */
    private String dniPropietario;
    /**
     * Descripción del vehículo
     */
    private String descripcionVehiculo;

    /*--------------------------------------------------*
     *                  CONSTRUCTORES                   *
     *--------------------------------------------------*/
    /**
     * Método constructor que inicializa los valores de los atributos a través
     * de los parámetros que se le pasan.
     *
     * @param marca Marca del vehículo
     * @param matricula Matrícula del vehículo
     * @param km Kilometraje del vehículo
     * @param fecha Representa la fecha en formato DD-MM-AAAA
     * @param precio Precio en coma flotante
     * @param nombre Nombre del propietario
     * @param dni DNI del propietario
     * @param descripcion Descripcioón del vehículo
     */
    public Vehiculo(String marca, String matricula, int km, String fecha,
            double precio, String nombre, String dni, String descripcion) {

        this.marca = marca;
        this.matricula = matricula;
        this.kilometros = km;
        //Crear la fecha (LocalDate) a partir del String que la representa
        this.fechaMatricula = LocalDate.of(Integer.parseInt(fecha.substring(6, 10)),
                Integer.parseInt(fecha.substring(3, 5)),
                Integer.parseInt(fecha.substring(0, 2)));
        this.precio = precio;
        this.nombrePropietario = nombre;
        this.dniPropietario = dni;
        this.descripcionVehiculo = descripcion;
    }

    /**
     * Constructor que no recibe ningún parametro e inicializa los valores por
     * defecto (en este caso null para String y LocalDate y 0 para los valores
     * numéricos).
     */
    public Vehiculo() {

        //Iniciar los valores de los atributos en el constructor vacío.
        this.marca = null;
        this.matricula = null;
        this.kilometros = 0;
        this.fechaMatricula = null;
        this.precio = 0.0;
        this.nombrePropietario = null;
        this.dniPropietario = null;
        this.descripcionVehiculo = null;
    }

    /**
     * Constructor que cosntruye un objeto nuevo a traves de un objeto ya
     * existente.
     *
     * @param vehiculo El objeto de tipo Vehiculo del que se copian los datos
     * para crear el nuevo
     */
    public Vehiculo(Vehiculo vehiculo) {

        this.marca = vehiculo.marca;
        this.matricula = vehiculo.matricula;
        this.kilometros = vehiculo.kilometros;
        this.fechaMatricula = vehiculo.fechaMatricula;
        this.precio = vehiculo.precio;
        this.nombrePropietario = vehiculo.nombrePropietario;
        this.dniPropietario = vehiculo.dniPropietario;
        this.descripcionVehiculo = vehiculo.descripcionVehiculo;
    }

    /*--------------------------------------------------*
     *                     MÉTODOS                      *
     *--------------------------------------------------*/
    /**
     * Método que calcula los años que tiene un vehículo.
     *
     * El método solamente calcula los años entero cumplidos en comparación con
     * la fecha de hoy. Es decir, si un vehículo tiene 364 días se informará que
     * tiene 0 años cumplidos. Se compara la fecha de matrícula
     * (this.fechaMatricula) usando el método until() con la fecha de hoy
     * (LocalDate.now()). El resultado de esta comparación es un período de
     * tiempo (un objeto de java.time.Period) del que extraemos los años usando
     * el método getYears(). Como se puede ver, todo se ha comprimido en una
     * sola sentencia.
     *
     * @return Los años enteros cumplidos que tiene el vehículo
     */
    public int getAnios() {

        int anios;

        //Calcular los años: la fecha de matricula -> hasta fecha de hoy -> extraemos solo años
        //Si no hay un año completo no se tiene en cuenta. Solamente muestra los años completos.
        anios = this.fechaMatricula.until(LocalDate.now()).getYears();

        return anios;
    }

    /*--------------------------------------------------*
     * MÉTODOS GET Y SET PARA CADA UNO DE LOS ATRIBUTOS *
     *--------------------------------------------------*/
    /**
     * Método get que devuelve el valor del atributo marca
     *
     * @return El valor del atributo marca
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Método que cambia el valor del atributo marca
     *
     * @param marca El valor que queremos poner al atributo
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * Método get que devuelve el valor del atributo matricula
     *
     * @return El valor del atributo matricula
     */
    public String getMatricula() {
        return matricula;
    }

    /**
     * Método que cambia el valor del atributo matricula
     *
     * @param matricula El valor que queremos poner al atributo
     */
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    /**
     * Método get que devuelve el valor del atributo kilometros
     *
     * @return El valor (un entero) de los kilómetros del vehiculo
     */
    public int getKilometros() {
        return kilometros;
    }

    /**
     * Método que cambia el valor del atributo kilometros
     *
     * @param kilometros El valor que queremos poner al atributo
     */
    public void setKilometros(int kilometros) {
        this.kilometros = kilometros;
    }

    /**
     * Método get que devuelve el valor del atributo fechaMatricula
     *
     * @return La fecha de compra del vehículo como un objeto de tipo LocalDate
     */
    public LocalDate getFechaMatricula() {
        return fechaMatricula;
    }

    /**
     * Método que cambia el valor del atributo fechaMatricula
     *
     * @param fechaMatricula El valor que queremos poner al atributo
     */
    public void setFechaMatricula(LocalDate fechaMatricula) {
        this.fechaMatricula = fechaMatricula;
    }

    /**
     * Método sobrecargado que cambia el valor del atributo fechaMatricula
     * proporcionando dia, mes y año como parámetros
     *
     * @param dia Dia de la fecha
     * @param mes Mes de la fecha
     * @param anio Año de la fecha
     */
    public void setFechaMatricula(int dia, int mes, int anio) {
        this.fechaMatricula = LocalDate.of(anio, mes, dia);
    }

    /**
     * Método get que devuelve el valor del atributo precio
     *
     * @return El valor del vehículo
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Método que cambia el valor del atributo precio
     *
     * @param precio El valor que queremos poner al atributo
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Método get que devuelve el valor del atributo nombrePropietario
     *
     * @return El nombre del propietario del behículo
     */
    public String getNombrePropietario() {
        return nombrePropietario;
    }

    /**
     * Método que cambia el valor del atributo nombrePropietario
     *
     * @param nombrePropietario El nombre que queremos poner
     */
    public void setNombrePropietario(String nombrePropietario) {
        this.nombrePropietario = nombrePropietario;
    }

    /**
     * Método get que devuelve el valor del atributo dniPropietario
     *
     * @return Cadena con el DNI del propietario
     */
    public String getDniPropietario() {
        return dniPropietario;
    }

    /**
     * Método que cambia el valor del atributo dniPropietario
     *
     * @param dniPropietario Cadena que representa el DNI que queremos cambiar
     */
    public void setDniPropietario(String dniPropietario) {
        this.dniPropietario = dniPropietario;
    }

    /**
     * Método get que devuelve el valor del atributo getDescripcionVehiculo
     *
     * @return Cadena con la descripcion
     */
    public String getDescripcionVehiculo() {
        return descripcionVehiculo;
    }

    /**
     * Método que cambia el valor del atributo setDescripcionVehiculo.
     *
     * @param descripcion Descripción del vehículo
     */
    public void setDescripcionVehiculo(String descripcion) {

        this.descripcionVehiculo = descripcion;
    }

    /**
     * Método que compara dos mátriculas.
     *
     * @param matr la matrícula contra la que se comprueba
     *
     * @return <b>true</b> si son iguales; <b>false</b> si no son iguales
     */
    public boolean comparaMatricula(String matr) {

        return this.matricula.equalsIgnoreCase(matr);

    }//fin comparaMatricula

    /**
     * Método que compara un vehículo con el que se pasa como parámetro.
     *
     * Para la comparación se usa la comparación de matrículas. Si la primera es
     * anterior (menor) se devuelve un número negatívo (-1); si por lo contrario
     * es posterior (mayor) se devuelve un número positivo (1); Y si las dos
     * matrículas (cadenas) son iguales, se devuelve 0.
     *
     * @param veh El objeto Vehiculo contra el que comprobamos
     *
     * @return <b>-1</b> si es menor; <b>1</b> si es mayor; <b>0</b> si son
     * iguales.
     */
    @Override
    public int compareTo(Vehiculo veh) {

        int dev;

        if (this.matricula.compareToIgnoreCase(veh.matricula) < 0) {
            //Primera matrícula menor (anterior)
            dev = -1;
        } else if (this.matricula.compareToIgnoreCase(veh.matricula) > 0) {
            //Primera matrícula mayor (posterior)
            dev = 1;
        } else {
            //Ambas matrículas son iguales
            dev = 0;
        }

        return dev;
    }//fin compareTo

}//fin Vehiculo
