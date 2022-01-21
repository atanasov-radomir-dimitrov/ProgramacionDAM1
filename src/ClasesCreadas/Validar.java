package ClasesCreadas;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clase que contiene métodos públicos estáticos para validar datos.
 *
 * Los métodos que contiene son: validarDNI, validarMatricula, validarInput,
 * validarNombre, validarPrecio, validarFecha, esBisiesto.
 *
 * @author Radomir Dimitrov Atanasov
 */
public class Validar {

    /*--------------------------------------------------*
     *              ATRIBUTOS DE CLASE                  *
     *--------------------------------------------------*/
    /**
     * Expresión regular que representa un NIE o DNI.
     *
     * Solamente comprueba el formato, no la validez de la letra final.
     *
     * Ejemplo de NIE: X1111111D. Ejemplo de DNI: 88888888V
     */
    private static final String REGEX_DNI = "([XxYy]?)([0-9]{7,8})([A-Za-z])";
    /**
     * Expresión regular que represanta una matrícula de un coche.
     *
     * Ejemplo: 1111AAA
     */
    private static final String REGEX_MATRICULA = "([0-9]{4})([A-Za-z]{3})";
    /**
     * Expresión regular que reporesenta un input de texto de lóngitud mínima 1
     * caracter y máxima 50, obligando a empezar por letra; el resto de
     * caracteres pueden ser letras o espacios en blanco.
     */
    private static final String REGEX_INPUT = "(([A-Za-z]+)([A-Za-z ]*)){1,50}";

    /*--------------------------------------------------*
     *                     MÉTODOS                      *
     *--------------------------------------------------*/
    /**
     * Validar que un DNI (acepta tambien NIE) es válido en formato.
     *
     * El formato correcto es: Letra X o Y en caso de NIE (nada en caso de DNI),
     * seguido de 7 u 8 dígitos (0-9), seguido de una letra (A-Za-z). Únicamente
     * se mira, a través de expresiones regulares, que el DNI o NIE tenga el
     * formato correcto. Se utilizan patrones de la clase pattern y se comprueba
     * si el dni/nie coincide utilizando objetos de la clase matcher. Se utiliza
     * la siguiente expresión regular para comprobar el formato del dni/nie:
     * ([XxYy]{0,1})([0-9]{7,8})([A-Za-z])
     *
     * @param dni <b>DNI/NIE</b> que queremos verificar
     * @return <b>true</b> si el formato del DNI/NIE es válido; <b>false</b> si
     * no es válido
     */
    public static boolean validarDNI(String dni) {

        boolean valido;

        Pattern p = Pattern.compile(REGEX_DNI);
        Matcher m = p.matcher(dni);
        valido = m.matches();

        return valido;

    }//fin validarDNI

    /**
     * Validar que una matrícula de vehícula es válida en formato.
     *
     * El formato correcto es: NNNNLLL, es decir, 4 números (dígitos) seguidos
     * de 3 letras (ej. 1111AAA). Se utiliza la siguiente expresión regular para
     * validar el formato de la matrícula: ([0-9]{4,4})([A-Za-z]{3,3})
     *
     * @param matricula La <b>matrícula</b> que queremos verificar
     * @return <b>true</b> si el formato es válido; <b>false</b> si no es válido
     */
    public static boolean validarMatricula(String matricula) {

        boolean valido;

        Pattern p = Pattern.compile(REGEX_MATRICULA);
        Matcher m = p.matcher(matricula);
        valido = m.matches();

        return valido;

    }//fin de validarMatricula

    /**
     * Validar que un input (lo que introducimos por teclado) tenga el siguiente
     * formato: empieza con una letra, luego sigue opcionalmente con combinación
     * de letras y/o espacios en blanco.
     *
     * La longitud máxima aceptada es de 50 caracteres; la mínima: 1. Se usa la
     * siguiente expresión regular: (([A-Za-z]+)([A-Za-z ]*)){1,50}
     *
     * @param input La cadena que queremos validar
     * @return <b>true</b> si el formato es válido; <b>false</b> si no es válido
     */
    public static boolean validarInput(String input) {

        boolean valido;

        Pattern p = Pattern.compile(REGEX_INPUT);
        Matcher m = p.matcher(input);
        valido = m.matches();

        return valido;

    }//fin validarInput

    /**
     * Método público estático que comprueba que la cadena pasada como parámetro
     * que representa un nombre de persona contiene al menos 3 partes separadas
     * por espacios en blanco; es decir: 1 nombre y dos apellidos mínimo; además
     * comprueba que la longitud no exceda los 40 caracteres.
     *
     * (No se usan expreisones regulares, sino métodos de la clase String).
     *
     * Lo primero es mirar si contiene caracteres en blanco y sustituirlos todos
     * por expacio. Despues se mira si todos los caracteres son exclusivamente
     * espacios y letras (no se aceptan otros caracteres).Despues se mira si la
     * longitud es correcta (menos o igual a 40 caracteres). Después se repite
     * un proceso de quitar espacios blancos del principio y final de la cadena,
     * buscar el primer espacio en blanco, separar y contar palabras.
     *
     * @param nombre El nombre que queremos comproabr.
     *
     * @return <b>true</b> si es un nombre válido; <b>false</b> si no es válido
     */
    public static boolean validarNombre(String nombre) {

        boolean valido = true;
        int contador = 0;
        int index = 0;

        //Sustituir todos los caracteres invisibles (cuyo valor es menor de 20)
        //por un espacio blanco.
        for (int i = 0; i < nombre.length(); i++) {
            if (nombre.charAt(i) <= 20) {
                nombre = nombre.replace(nombre.charAt(i), ' ');
            }
        }

        //Comprobar que todos los caracteres sean letras o espacios en blanco
        for (int i = 0; i < nombre.length(); i++) {
            if ((Character.isLetter(nombre.charAt(i)) || Character.isWhitespace(nombre.charAt(i)))) {
            } else {
                valido = false;
                break;
            }
        }

        //Si es un input válido, es decir solo contiene letras y espacios, continuamos
        if (valido == true) {
            valido = false;
            //Vemos si la longitud es correcta (menor o igual a 40 caracteres)
            if (nombre.length() <= 40) {
                while (index != -1) {
                    //Recortamos la cadena desde donde indica index hasta el final
                    nombre = nombre.substring(index);
                    //Quitamos los espacios blancos del principio y del final
                    nombre = nombre.trim();
                    //Ponemos el index al principio de la cadena
                    index = 0;
                    //Buscamos donde está el primer espacio blanco en la nueva cadena recortada
                    index = nombre.indexOf(' ', index);
                    //Si hay espacio blanco (index != -1) sumamos al contador -> nueva palabra
                    if (index != -1) {
                        contador++;
                    }
                }
                //Si contador es al menos 2, significa que hay al menos 3 palabras (2 espacios)
                if (contador >= 2) {
                    valido = true;
                }
            }
        }

        return valido;

    }//fin validarNombre

    /**
     * Método público estático que comprueba que el precio que le pasamos es
     * mayor o igual a 0 y menor de 10.000.000 (precio máximo "razonable" de un
     * vehículo)
     *
     * @param precio El precio que queremos comprobar
     *
     * @return <b>true</b> si es válido; <b>false</b> si no es válido
     */
    public static boolean validarPrecio(double precio) {

        boolean valido = false;

        if (precio >= 0 && precio < 10000000) {
            valido = true;
        }

        return valido;

    }// fin validarPrecio

    /**
     * Método público estático que valida una fecha.
     *
     * Comprueba si la fecha pasado como un String está en el formato DD-MM-AAAA
     * y si los meses tienen los dias que deban tener en función del mes y si se
     * trata de febrero y el año es o no bisiesto. La fecha debe ser superior a
     * 01-01-1900 e inferior o igual a la de hoy (día en el que se ejecuta el
     * programa)
     *
     * @param fecha Un String que comprobamos si tiene el formato requerido
     *
     * @return <b>true</b> si el formato es válido; <b>false</b> en caso
     * contrario
     */
    public static boolean validarFecha(String fecha) {

        boolean valida = false; //Asumimos que la fecha no es correcta
        int dia, mes, anio;

        //Si se pasa un valor no nulo; Si tiene la longitud correcta; 
        //Si los separadores entre dia, mes y año son los correctos: '-'
        if ((fecha != null) && (fecha.length() == 10)
                && (fecha.charAt(2) == '-') && (fecha.charAt(5) == '-')) {
            try {
                //Separamos el string en 3 partes: dia, mes, año
                //Si alguno de los 3 datos no es un entero se lanza una
                //excepción y esto significa que el string pasado no es una fecha válida
                dia = Integer.parseInt(fecha.substring(0, 2));
                mes = Integer.parseInt(fecha.substring(3, 5));
                anio = Integer.parseInt(fecha.substring(6, 10));
                //Ponemos restricción para que el año sea superior o igual a
                //1900 e inferior o igual al año en el que nos encontramos ahora
                if ((anio >= 1900) && (anio <= LocalDate.now().getYear())) {
                    //Comprobamos si el día es correcto en función del mes
                    switch (mes) {
                        case 2: //Febrero
                            if ((dia >= 1) && (dia <= 29)) {
                                valida = true;
                                //Si el año no es bisiesto, la fecha no puede ser 29-02-AAAA
                                if ((dia == 29) && (Validar.esBisiesto(anio) == false)) {
                                    valida = false;
                                }
                            }
                            break;

                        case 1: //Enero
                        case 3: //Marzo
                        case 5: //Mayo
                        case 7: //Julio
                        case 8: //Agosto
                        case 10: //Octubre
                        case 12: //Diciembre
                            if ((dia >= 1) && (dia <= 31)) {
                                valida = true;
                            }
                            break;

                        case 4: //Abril
                        case 6: //Junio
                        case 9: //Septiembre
                        case 11:
                            if ((dia >= 1) && (dia <= 30)) {
                                valida = true;
                            }
                            break;

                        //Cualquier otra opción leída sobre meses no es válida
                        default:
                            valida = false;
                    }
                }
                //Si, aun siendo una fecha correcta en formato, observamos que es superior al dia de hoy
                if ((valida == true) && (LocalDate.of(anio, mes, dia).until(LocalDate.now()).getDays() < 0)) {
                    valida = false;
                }
            } catch (NumberFormatException e) {
                /* Capturamos la excepción en caso de haber introducido no números
                   en vez de números para el día, mes y/o año; pero no hacemos 
                   nada porque el hecho de haber sido lanzada indica que la 
                   fecha no es válida. 
                 */
            }
        }//fin (fecha.length() == 10) && ((fecha.charAt(2) == '-') && (fecha.charAt(5) == '-'))

        return valida;

    }//fin validarFecha

    /**
     * Método público estático que comprueba si un año es bisiesto
     *
     * @param anio Año que queremos ver si es bisiesto o no
     *
     * @return <b>true</b> si el año es bisiesto; <b>false</b> si no lo es
     */
    public static boolean esBisiesto(int anio) {

        boolean esBisiesto = (anio % 4 == 0) && ((anio % 100 != 0) || (anio % 400 == 0));

        return esBisiesto;

    }//fin esBisiesto

}//fin Validar
