package org.example;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * La clase TablesController nos proporciona la funcionalidad de crear y borrar las tablas "variants" y "characters"
 *
 * @author Tarik Aabouch - tarikii in GitHub
 * @version 8.0
 */
public class TablesController {
    private Connection connection;
    Scanner scanner;


    /**
     * Constructor de la clase, le damos la conexion de nuestra base de datos y la opcion que elige le usuario
     * desde un scanner (System.in)
     *
     * @param connection La conexion que le pasamos para que se conecte al postgreSQL
     */
    public TablesController(Connection connection){
        this.connection = connection;
        this.scanner = new Scanner(System.in);
    }


    /**
     * Crea la estructura SQL que hay en la carpeta resources, y crea las tablas "variants" y characters".
     */
    public void crearEstructuraSQL(){
        try{
            Statement st = connection.createStatement();

            st.executeUpdate("CREATE TABLE characters (" +
                    "id_character serial," +
                    "name varchar(1000)," +
                    "image varchar(1000)," +
                    "health varchar(1000)," +
                    "variant varchar(1000)," +
                    "primary_weapon varchar(1000)," +
                    "weapon_damage varchar(1000)," +
                    "abilities varchar(1000)," +
                    "fps_class varchar(1000)," +
                    "primary key(id_character));");

            st.executeUpdate("CREATE TABLE variants (" +
                    "id_variant serial," +
                    "name varchar(1000)," +
                    "image varchar(1000)," +
                    "health varchar(1000)," +
                    "primary_weapon varchar(1000)," +
                    "weapon_damage varchar(1000)," +
                    "variant_perk varchar(1000)," +
                    "rarity varchar(1000)," +
                    "primary key(id_variant));");

            st.close();

        }catch (SQLException e){
            System.out.println("Error: No se pueden crear las tablas, fijate si ya estan creadas.");
        }
    }

    /**
     * Borra las tablas "variants" y "characters" en la base de datos postgreSQL.
     */
    public void deleteTables(){
        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE characters");
            statement.executeUpdate("DROP TABLE variants");
            statement.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}