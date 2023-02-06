package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * La clase PVZMenu permite interactuar con un menú en consola que permite realizar
 * diversas operaciones con una base de datos PostgreSQL.
 *
 * @author Tarik Aabouch - tarikii in GitHub
 * @version 8.0
 */
public class PVZMenu {
    private int option;
    private String opciones;
    Scanner sc = new Scanner(System.in);

    /**
     * Un constructor que llama al super(), que en este caso es el Main, alli invocaremos el menu.
     */
    public PVZMenu() {
        super();
    }

    /**
     * Método mainMenu que muestra las opciones disponibles para interactuar con la base de datos PostgreSQL.
     *
     * @return La opción elegida por el usuario en la consola.
     */
    public int mainMenu() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        do {
            System.out.println("MENU INTERACTIVO CON POSTGRESQL: ");
            System.out.println();
            System.out.println();
            System.out.println("1. Mostrar toda la tabla characters");
            System.out.println("2. Mostrar toda la tabla variants");
            System.out.println("3. Mostrar los characters por su clase FPS");
            System.out.println("4. Mostrar los variants por su rareza");
            System.out.println("5. Crear un character");
            System.out.println("6. Crear un variant");
            System.out.println("7. Crear Tablas");
            System.out.println("8. Borrar Tablas");
            System.out.println("9. Rellenar tabla \"characters\" con archivo CSV");
            System.out.println("10. Rellenar tabla \"variants\" con archivo CSV");
            System.out.println("11. Salir \n");
            System.out.println("Escoge una opción: ");
            try {
                option = Integer.parseInt(br.readLine());
            } catch (NumberFormatException | IOException e) {
                System.out.println("valor no vàlido");

            }

        } while (option != 1 && option != 2 && option != 3 && option != 4 && option != 5 && option != 6 && option != 7 && option != 8 && option != 9 && option != 10 && option != 11);
        return option;
    }



    /**
     * Método authenticate que pide y verifica los credenciales de un usuario postgreSQL.
     *
     * @return La identidad del usuario.
     * @throws IOException Devuelve una excepción si hay problemas al leer los datos del usuario.
     */
    public Identity authenticate() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("************************************************************");
        System.out.println("*                    PVZ-CHARACTERS                        *");
        System.out.println("************************************************************");
        System.out.println("Inserta el nombre del usuario: ");
        String user = br.readLine();
        System.out.println("Inserta la password del usuario: ");
        String password = br.readLine();

        Identity identity = new Identity(user, password);
        return identity;
    }

    /**
     * Método elegirOpcion que permite elegir una de las opciones en el menú PVZMenu.
     *
     * @param opciones Opciones disponibles en el menú.
     * @return La opción elegida por el usuario.
     */
    public int elegirOpcion(String[] opciones) {
        boolean seguirPidiendo = true;
        int opcion = 0;

        for (int i = 0; i < opciones.length; i++) {
            System.out.println((i + 1) + ". " + opciones[i]);
        }
        while(seguirPidiendo) {
            System.out.println("\nOpcion:");
            opcion = sc.nextInt();

            try {
                if (opcion > opciones.length) {
                    System.out.println("Esa opción no existe usa un numero");
                } else {
                    seguirPidiendo = false;
                }
            } catch (Exception e) {
                System.out.println("¡Introduzca una opcion usa un numero!");
                seguirPidiendo = true;
            }
        }
        return opcion;
    }
}
