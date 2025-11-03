package org.generation.main;

import org.generation.exceptions.AgendaLlenaException;
import org.generation.exceptions.ContactoException;
import org.generation.model.Contacto;
import org.generation.service.Agenda;
import org.generation.service.InterfaceAgenda;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    // Se declara la variable usando la Interfaz
    private static  InterfaceAgenda agenda;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        inicializarAgenda();
        mostrarMenu();
    }

    private static void inicializarAgenda() {
        System.out.println("--- 📞 Creación de Agenda Telefonica ---");
        System.out.println("¿Desea especificar un tamaño máximo para la agenda?");
        System.out.println("1. Sí ");
        System.out.println("2. No (Tamaño por defecto: 10)");
        System.out.print("Seleccione una opción: ");

        int opcion;
        try {
            opcion = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Usando tamaño por defecto (10).");
            scanner.nextLine();
            opcion = 2;
        }

        if (opcion == 1) {
            System.out.print("Ingrese el tamaño máximo de la agenda: ");
            int tamano;
            try {
                tamano = scanner.nextInt();
                scanner.nextLine();
                // Inicialización de la clase concreta
                agenda = new Agenda(tamano);
                System.out.println("Agenda creada con capacidad máxima de " + tamano + " contactos.");
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Usando tamaño por defecto (10).");
                scanner.nextLine();
                agenda = new Agenda();
            }
        } else {
            // Inicialización de la clase concreta
            agenda = new Agenda();
            System.out.println("Agenda creada con capacidad por defecto de 10 contactos.");
        }
        System.out.println("-------------------------------------\n");
    }

    private static void mostrarMenu() {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n===== MENÚ AGENDA TELEFÓNICA =====");
            System.out.println("1. Añadir Contacto");
            System.out.println("2. Verificar Existencia de Contacto");
            System.out.println("3. Listar Contactos");
            System.out.println("4. Buscar Contacto");
            System.out.println("5. Eliminar Contacto");
            System.out.println("6. Modificar Teléfono de Contacto");
            System.out.println("7. Verificar Espacio Libre");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        opcionAnadirContacto();
                        break;
                    case 2:
                        opcionExisteContacto();
                        break;
                    case 3:
                        opcionListarContactos();
                        break;
                    case 4:
                        opcionBuscarContacto();
                        break;
                    case 5:
                        opcionEliminarContacto();
                        break;
                    case 6:
                        opcionModificarTelefono();
                        break;
                    case 7:
                        opcionEspacioLibre();
                        break;
                    case 0:
                        System.out.println("¡Gracias por usar la Agenda! Saliendo...");
                        break;
                    default:
                        System.out.println("Opción inválida. Intente de nuevo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número del menú.");
                scanner.nextLine();
                opcion = -1;
            }
        }
    }

    // --- Lógica del Menú con Manejo de Excepciones ---

    private static void opcionAnadirContacto() {
        System.out.println("\n--- AÑADIR CONTACTO ---");

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine().trim();
        System.out.print("Apellido: ");
        String apellido = scanner.nextLine().trim();
        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();

        Contacto nuevo = new Contacto(nombre, apellido, telefono);

        try {
            agenda.añadirContacto(nuevo); // Llama al servicio
            System.out.println("Éxito: Contacto añadido correctamente.");
        } catch (AgendaLlenaException | ContactoException e) {
            // Capturamos las excepciones lanzadas por el servicio
            System.out.println(e.getMessage());
        }
    }

    private static void opcionExisteContacto() {
        System.out.println("\n--- VERIFICAR EXISTENCIA ---");
        System.out.print("Nombre del contacto: ");
        String nombre = scanner.nextLine().trim();
        System.out.print("Apellido del contacto: ");
        String apellido = scanner.nextLine().trim();

        Contacto busqueda = new Contacto(nombre, apellido, "");

        if (agenda.existeContacto(busqueda)) {
            System.out.println("✅ El contacto (" + nombre + " " + apellido + ") SÍ existe en la agenda.");
        } else {
            System.out.println("❌ El contacto (" + nombre + " " + apellido + ") NO existe en la agenda.");
        }
    }

    private static void opcionListarContactos() {
        System.out.println("\n--- LISTAR CONTACTOS ---");
        System.out.println(agenda.listarContactos());
    }

    private static void opcionBuscarContacto() {
        System.out.println("\n--- BUSCAR CONTACTO ---");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine().trim();
        System.out.print("Apellido: ");
        String apellido = scanner.nextLine().trim();

        System.out.println(agenda.buscaContacto(nombre, apellido));
    }

    private static void opcionEliminarContacto() {
        System.out.println("\n--- ELIMINAR CONTACTO ---");
        System.out.print("Nombre del contacto a eliminar: ");
        String nombre = scanner.nextLine().trim();
        System.out.print("Apellido del contacto a eliminar: ");
        String apellido = scanner.nextLine().trim();

        Contacto contactoAEliminar = new Contacto(nombre, apellido, "");

        try {
            agenda.eliminarContacto(contactoAEliminar);
            System.out.println("Éxito: Contacto (" + nombre + " " + apellido + ") eliminado exitosamente.");
        } catch (ContactoException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void opcionModificarTelefono() {
        System.out.println("\n--- MODIFICAR TELÉFONO ---");
        System.out.print("Nombre del contacto a modificar: ");
        String nombre = scanner.nextLine().trim();
        System.out.print("Apellido del contacto a modificar: ");
        String apellido = scanner.nextLine().trim();

        System.out.print("Nuevo Teléfono: ");
        String nuevoTelefono = scanner.nextLine();

        try {
            agenda.modificarTelefono(nombre, apellido, nuevoTelefono);
            System.out.println("Éxito: Teléfono de " + nombre + " " + apellido + " modificado a " + nuevoTelefono);
        } catch (ContactoException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void opcionEspacioLibre() {
        System.out.println("\n--- ESPACIO DISPONIBLE ---");
        int libres = agenda.espaciosLibres();
        if (libres == 0) {
            System.out.println("¡Agenda Llena! No hay espacio disponible.");
        } else {
            System.out.println("Espacio Libre: Se pueden agregar " + libres + " contactos más.");
        }
    }


}
