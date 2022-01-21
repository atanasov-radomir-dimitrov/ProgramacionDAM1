package Principal;

import ClasesCreadas.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Clase Principal que contiene el método main en el que se encuentran las
 * sentencias que hacen el algoritmo de funcionamiento de la tarea de la unidad
 * de trabajo 9 de Programación.
 *
 * Se implementa un menú con 6 opciones a traves del cual se maneja un
 * concesionario y los vehículos que almacena.
 *
 * Se trata de la misma clase principal que en la tarea de la unidad 8, pero
 * ahora los datos se guardan en un fichero externo y también se cargan desde un
 * fichero externo.
 *
 * Para conseguir esto, he tenido que implementar la interfaz
 * <b>Serializable</b> en las clases Concesioanrio y Vehiculo. De esta manera,
 * los objetos de dichas clases se pueden guardar en ficheros binarios y
 * posteriormente leer desde ficheros y recuperar los datos. Se implementa una
 * clase <b>Serializadora</b> que contiene los métodos que escriben y leen los
 * objetos en/desde ficheros.
 *
 * @author Radomir Dimitrov Atanasov
 */
public class Principal {

    /**
     * Método main que implementa el algoritmo del programa que representa la
     * tarea de la unidad 9 de programación.
     *
     * @param args Lista de argumentos.
     */
    public static void main(String[] args) {

        //Variables auxiliares a usar
        BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
        String linea;
        int entrada;
        boolean valido;
        int resultadoInsertar;
        Concesionario concesionario;
        byte controlarError = 0;

        //Variables para almacenar los datos de vehículo que se va a insertar
        String marca;
        String matricula;
        int km;
        String fecha;
        double precio;
        String nombrePropietario;
        String dni;
        String descripcion;

        /*----------------------------------------------------------------*
         * En esta parte del código se solicita el archivo desde el que   *
         * se va a obtener la información de los vehiculos almacenados.   *
         * Si el fichero existe y no es un directorio, se leeran los      *
         * datos si los hubiera. Si el fichero no existe se informa de    *
         * ello y tambien se informa que al terminar el archivo se creara *
         * y los datos se guardaran en él.                                *
         *----------------------------------------------------------------*/
        //Mostramos contenido del directorio actual
        Auxiliar.mostrarContenido();

        //Pedimos ruta y creamos el objeto de tipo File para trabajar con el fichero
        String ruta = Auxiliar.pedirRuta();
        File fichero = new File(ruta);

        //Si no es un directorio procedemos
        if (!fichero.isDirectory()) {
            //Si el fichero no existe iniciamos un concesionario vacío y continuamos
            if (!fichero.exists()) {
                System.out.println("El fichero introducido no existe.");
                System.out.println("Al terminar el trabajo se creará el fichero y"
                        + " los datos se guardarán en él.");
                concesionario = new Concesionario();
            } else {
                //Si el fichero existe leemos datos del fichero
                try {
                    //Leemos los datos
                    concesionario = (Concesionario) Serializadora.leerObjeto(ruta);
                    System.out.println("\nFichero cargado correctamente.");
                    if (concesionario.esVacio()) {
                        //Si el fichero del concesionario no contiene ningun vehiculo
                        System.out.println("El concesionario no contiene datos de vehículos.");
                    } else {
                        //Imprimir los datos que se han sacado del fichero
                        System.out.println("Contiene los siguientes datos:\n");
                        concesionario.listarVehiculos();
                        //El método listarVehiculos() devuelve null si el concesionario está vacío
                        //Realmente no hace falta comprobar esto porque ya nos hemos asegurado
                        //que el concesionario NO está vacío.
                    }
                } catch (Exception ex) {
                    //Si se ha producido algun error
                    System.out.println("\nError leyendo el fichero.");
                    System.out.println("Se procede sin fichero.");
                    System.out.println("Los datos de guardaran en un fichero llamado \"temporal.dat\"");
                    //Si ha habido error creamos un concesionario vacío
                    concesionario = new Concesionario();
                    //asignamos nombre del archivo nuevo
                    ruta = "temporal.dat";
                    controlarError = -1;
                }
            }
        } else {
            //Si es un directorio, entonces creamos un concecionario vacío y procedemos
            System.out.println("Has introducido el nombre de un directorio.");
            System.out.println("Los datos de guardaran en un fichero llamado "
                    + "\"temporal.dat\"");
            concesionario = new Concesionario();
            ruta = "temporal.dat";
            controlarError = -1;
        }

        //Bucle do-while que lee desde teclado hasta que se elija la opción 6 (salir)
        do {
            /*--------------------------------------------------*
             *             LEER OPCION DEL MENU                 *
             *--------------------------------------------------*
            Pintar el menú y leer la opción elegida. Control de lo que
            se introduce utilizando excepciones */
            try {
                Auxiliar.pintarMenu();
                linea = teclado.readLine();
                entrada = Integer.parseInt(linea);
            } catch (IOException e) {
                System.out.println("Error leyendo del teclado.");
                entrada = 0;
            } catch (NumberFormatException e) {
                //Indicamos que la opción no es adecuada para
                //luego tratarla en el switch (default)
                entrada = 0;
            }

            /*------------------------------------------------------------*
             * SWITCH CON LAS OPCIONES EN FUNCION DE LO LEIDO POR TECLADO *
             *------------------------------------------------------------*/
            switch (entrada) {
                //CREAR NUEVO VEHICULO
                //Se piden todos los datos hasta que se introduzcan correctamente
                case 1:
                    //Leer matrícula
                    System.out.println("");
                    matricula = null;
                    valido = false;
                    System.out.println("Introduce matrícula del vehículo.");
                    System.out.print("El formato de la matrícula es 4 dígitos + "
                            + "3 letras (ej. 1111AAA): ");
                    do {
                        try {
                            matricula = teclado.readLine();
                            valido = Validar.validarMatricula(matricula);
                            //Si el formato de la matrícula es válido
                            if (valido == true) {
                                //Si la matrícula es válida y no está presente en
                                //el cocnesionario la pasamos a mayúsculas
                                //Así se ve mejor
                                matricula = matricula.toUpperCase();
                            } else {
                                System.out.println("Matrícula introducida no válida.");
                                System.out.print("Introduce de nuevo: ");
                            }
                        } catch (IOException e) {
                            System.out.print("Error leyendo, vuelve a intentarlo: ");
                        }
                    } while (valido == false);

                    //Leer marca
                    System.out.println("");
                    marca = null;
                    valido = false;
                    do {
                        System.out.print("Introduce marca del vehículo: ");
                        try {
                            marca = teclado.readLine();
                            valido = Validar.validarInput(marca);
                            //Si es un input válido
                            if (valido == false) {
                                System.out.println("Marca introducida no válida.");
                            }
                        } catch (IOException e) {
                            System.out.print("Error leyendo, vuelve a intentarlo: ");
                        }
                    } while (valido == false);

                    //Leer kilómetros
                    System.out.println("");
                    km = -1;
                    while (km < 0) {
                        try {
                            System.out.print("Introduce kilómetros del vehículo: ");
                            linea = teclado.readLine();
                            km = Integer.parseInt(linea);
                            if (km < 0) {
                                System.out.println("Valor no válido.");
                            }
                        } catch (IOException | NumberFormatException e) {
                            System.out.println("Error leyendo | Dato introducido no válido.");
                        }
                    }

                    //Leer fecha
                    System.out.println("");
                    fecha = null;
                    valido = false;
                    System.out.println("Formato de la fecha: DD-MM-AAAA | Debe ser "
                            + "superior a 01-01-1900 e inferior a hoy.");
                    do {
                        try {
                            System.out.print("Introduce fecha: ");
                            fecha = teclado.readLine();
                            valido = Validar.validarFecha(fecha);
                            //Si la fecha no es válida
                            if (valido == false) {
                                System.out.println("Fecha introducida no válida.");
                            }
                        } catch (IOException e) {
                            System.out.println("Error leyendo, vuelve a intentarlo: ");
                        }
                    } while (valido == false);

                    //Leer precio
                    System.out.println("");
                    valido = false;
                    precio = -1.0;
                    do {
                        try {
                            System.out.print("Introduce precio del vehículo: ");
                            linea = teclado.readLine();
                            precio = Double.parseDouble(linea);
                            valido = Validar.validarPrecio(precio);
                            //Si el precio introducido no es válido
                            if (valido == false) {
                                System.out.println("Valor no válido.");
                            }
                        } catch (IOException | NumberFormatException e) {
                            System.out.println("Error leyendo | Dato introducido no válido.");
                        }
                    } while (valido == false);

                    //Leer nombre propietario
                    System.out.println("");
                    System.out.println("Para el nombre no se permiten números.");
                    nombrePropietario = null;
                    valido = false;
                    do {
                        try {
                            System.out.print("Introduce nombre del propietario: ");
                            nombrePropietario = teclado.readLine();
                            valido = Validar.validarNombre(nombrePropietario);
                            //Si el nombre no es válido
                            if (valido == false) {
                                System.out.println("Nombre introducido no válida.");
                            }
                        } catch (IOException e) {
                            System.out.print("Error leyendo, vuelve a intentarlo.");
                        }
                    } while (valido == false);

                    //Leer DNI del propietario
                    System.out.println("");
                    valido = false;
                    dni = null;
                    System.out.println("El DNI debe tener 7 u 8 dígitos seguidos de una letra.");
                    do {
                        try {
                            //Leer el DNI por teclado y comprobar validez
                            System.out.print("Introduce DNI del propietario: ");
                            dni = teclado.readLine();
                            valido = Validar.validarDNI(dni);
                            //Si el formato del DNI no es válido
                            if (valido == false) {
                                System.out.println("DNI no válido.");
                            } else {
                                //Si es válido pasamos a mayúsculas
                                dni = dni.toUpperCase();
                            }
                        } catch (IOException e) {
                            System.out.print("Error leyendo, vuelve a intentarlo.");
                        }
                    } while (valido == false);

                    //Introducir descripción
                    System.out.println("");
                    descripcion = null;
                    do {
                        System.out.println("Por último, introduce una descripción del vehículo.");
                        System.out.print("Se permite cualquier caracter: ");
                        try {
                            descripcion = teclado.readLine();
                            //No hacemos validación, es válido cualquier texto
                        } catch (IOException e) {
                            System.out.print("Error leyendo, vuelve a intentarlo: ");
                        }
                    } while (descripcion == null);

                    //Insertamos vehículo en el concesionario
                    resultadoInsertar = concesionario.insertarVehiculo(marca, matricula,
                            km, fecha, precio, nombrePropietario, dni, descripcion);
                    //Comprobamos si ha sido posible insertar el vehículo e informamos
                    switch (resultadoInsertar) {
                        case 0:
                            System.out.println("Vehículo insertado correctamente");
                            break;
                        case -1:
                            System.out.println("El concesionario está lleno. "
                                    + "El vehículo no puede ser insertado.");
                            break;
                        case -2:
                            System.out.println("La matrícula ya existe. "
                                    + "El vehículo no puede ser isnertado");
                            break;
                    }
                    break; //fin Case 1

                //LISTAR VEHICULOS
                case 2:
                    System.out.println("");
                    if (concesionario.listarVehiculos() == false) {
                        System.out.println("El concesionario está vacío.");
                    }
                    break; //fin Case 2

                //BUSCAR VEHICULO
                case 3:
                    //Leer matrícula
                    System.out.println("");
                    if (concesionario.esVacio() == true) {
                        System.out.println("El concesionario está vacío.");
                    } else {
                        matricula = null;
                        valido = false;
                        System.out.println("Introduce matrícula del vehículo.");
                        System.out.print("El formato de la matrícula es 4 dígitos + "
                                + "3 letras (ej. 1111AAA): ");
                        do {
                            try {
                                matricula = teclado.readLine();
                                valido = Validar.validarMatricula(matricula);
                                if (valido == true) {
                                    //Si la matrícula es válida la pasamos a mayúsculas
                                    //Así se ve mejor
                                    matricula = matricula.toUpperCase();
                                } else {
                                    System.out.println("Matrícula introducida no válida.");
                                    System.out.print("Introduce de nuevo: ");
                                }
                            } catch (IOException e) {
                                System.out.print("Error leyendo, vuelve a intentarlo: ");
                            }
                        } while (valido == false);

                        //Una vez validada la matrícula la buscamos en el concesionario
                        String buscandoVehiculo = concesionario.buscaVehiculo(matricula);
                        if (buscandoVehiculo == null) {
                            System.out.println("\nNo existe vehículo con la matrícula introducida.");
                        } else {
                            //Si la encontramos imprimimos los datos por pantalla.
                            System.out.println("\nVehículo encontrado.\n"
                                    + "-- Datos del vehículo --\n"
                                    + "------------------------\n"
                                    + buscandoVehiculo);
                        }
                    }
                    break; //fin Case 3

                //MODIFICAR KMS VEHICULO
                case 4:
                    System.out.println("");
                    //Vemos si el concesionario está vacío 
                    if (concesionario.esVacio() == true) {
                        System.out.println("El concesionario está vacío.");
                    } else {
                        matricula = null;
                        valido = false;
                        System.out.println("Introduce matrícula del vehículo.");
                        System.out.print("El formato de la matrícula es 4 dígitos + "
                                + "3 letras (ej. 1111AAA): ");
                        do {
                            try {
                                matricula = teclado.readLine();
                                valido = Validar.validarMatricula(matricula);
                                if (valido == true) {
                                    //Si la matrícula es válida la pasamos a mayúsculas
                                    //Así se ve mejor
                                    matricula = matricula.toUpperCase();
                                } else {
                                    System.out.println("Matrícula introducida no válida.");
                                    System.out.print("Introduce de nuevo: ");
                                }
                            } catch (IOException e) {
                                System.out.print("Error leyendo, vuelve a intentarlo: ");
                            }
                        } while (valido == false);

                        //Leer kilómetros
                        System.out.println("");
                        km = -1;
                        while (km < 0) {
                            try {
                                System.out.print("Introduce kilómetros: ");
                                linea = teclado.readLine();
                                km = Integer.parseInt(linea);
                                if (km < 0) {
                                    System.out.println("Valor no válido.");
                                }
                            } catch (IOException | NumberFormatException e) {
                                System.out.println("Error leyendo | Dato introducido no válido.");
                            }
                        }

                        //Una vez leída la matrícula y los kilómetros intentaremos actualizar
                        if (concesionario.actualizaKms(matricula, km) == false) {
                            System.out.println("No se ha podido actualizar.");
                        } else {
                            System.out.println("Se ha actualizado con éxito.");
                        }
                    }
                    break; //fin case 4

                //ELIMINAR VEHICULO
                case 5:
                    //Leer matrícula
                    System.out.println("");
                    if (concesionario.esVacio() == true) {
                        System.out.println("El concesionario está vacío.");
                    } else {
                        matricula = null;
                        valido = false;
                        System.out.println("Introduce matrícula del vehículo.");
                        System.out.print("El formato de la matrícula es 4 dígitos + "
                                + "3 letras (ej. 1111AAA): ");
                        do {
                            try {
                                matricula = teclado.readLine();
                                valido = Validar.validarMatricula(matricula);
                                if (valido == true) {
                                    //Si la matrícula es válida la pasamos a mayúsculas
                                    //Así se ve mejor
                                    matricula = matricula.toUpperCase();
                                } else {
                                    System.out.println("Matrícula introducida no válida.");
                                    System.out.print("Introduce de nuevo: ");
                                }
                            } catch (IOException e) {
                                System.out.print("Error leyendo, vuelve a intentarlo: ");
                            }
                        } while (valido == false);

                        //Una vez validada la matrícula la buscamos en el concesionario
                        //e intentamos borrarla
                        if (concesionario.borrarVehiculo(matricula) == 0) {
                            System.out.println("Vehículo borrado correctamente");
                        } else {
                            //Informamos que no ha sido posible borrar el vehículo
                            System.out.println("Imposible borrar el vehículo (no existe)");
                        }
                    }
                    break; //fin Case 5

                //SALIR
                case 6: {
                    try {
                        //En caso de que se vaya a guardar en el fichero "temporal.dat" porque haya
                        //surgido algun error antes, se ofrece la posibilidad de guardar en
                        //un fichero a eleccion del usuario
                        if (ruta.equals("temporal.dat") && controlarError == -1) {
                            System.out.println("Los datos se guardarán en el fichero: " + ruta);
                            System.out.println("Si prefieres que se guarden en otro fichero, "
                                    + "escribe su nombre (si este no existe, se creará).");
                            System.out.print("Sino escribe la palabra \"no\" (minúsculas): ");
                            linea = teclado.readLine();
                            if (!linea.equals("no")) {
                                ruta = linea;
                                fichero = new File(ruta);
                                if (fichero.isDirectory()) {
                                    System.out.println("Has introducido el nombre de un directorio.");
                                    System.out.println("Finalmente los datos se guardarán en: \"temporal.dat\"");
                                    ruta = "temporal.dat";
                                }
                            }
                        }
                        //Serializar los datos, es decir, guardarlos en el fichero
                        Serializadora.escribirObjeto(concesionario, ruta);
                        System.out.println("Saliendo ...\nDatos guardados correctamente en el "
                                + "fichero \"" + ruta + "\"");
                    } catch (IOException e) {
                        System.out.println("ERROR al guardar los datos. Saliendo...");
                    } catch (Exception ex) {
                        System.out.println("ERROR al guardar los datos. Saliendo...");
                    }
                    break; //fin case 6
                }
                default:
                    //Cualquier otra opción (incluida la 0)
                    System.out.println("\nOpción incorrecta. Introduce de nuevo.");
            }//fin swtich

        } while (entrada != 6); //se repite hasta que se elija la opción de salir (6)

    }//fin main

}//fin Principal
