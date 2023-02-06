package org.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * Esta clase llamada CharacterController es usada para lidiar la informacion de la tabla "characters".
 * Tiene metodos que muestran la tabla, rellenan la tabla, y crean la fila de un character en la tabla.
 *
 * @author Tarik Aabouch - tarikii in GitHub
 * @version 8.0
 */
public class CharacterController {
    private Connection connection;
    Scanner scanner;


    /**
     * Este constructor del controlador nos permite conectarnos a la base de datos del postgres, en base a este controlador, podremos buscar,rellenar,etc... .
     */
    public CharacterController(Connection connection){
        this.connection = connection;
        this.scanner = new Scanner(System.in);
    }

    /**
     * El metodo showCharacterInfo recoge toda la informacion actual de la tabla "characters" y la despliega para
     * que el usuario la lea.
     */
    public void showCharacterInfo(){
        System.out.println("\nCHARACTERS");
        ResultSet rs = null;
        String sql = "SELECT * FROM characters";
        try{
            Statement st = connection.createStatement();

            rs = st.executeQuery(sql);

            while (rs.next()) {
                System.out.println("******************************************************" +
                        "\nCharacter ID: " + rs.getString("id_character") +
                        "\nCharacter Name: " + rs.getString("name") +
                        "\nCharacter Image: " + rs.getString("image") +
                        "\nCharacter Health: " + rs.getString("health") +
                        "\nVariant: " + rs.getString("variant") +
                        "\nPrimary Weapon: " + rs.getString("primary_weapon") +
                        "\nWeapon Damage: " + rs.getString("weapon_damage") +
                        "\nCharacter Abilities: " + rs.getString("abilities") +
                        "\nCharacter FPS Class: " + rs.getString("fps_class")
                        + "\n******************************************************");
            }

            rs.close();
            st.close();

        }catch (SQLException e){
            System.out.println("Error: La tabla characters no existe");
        }
    }


    /**
     * El metodo showCharacterInfoFPS muestra la informacion actual de "characters" en base al atributo "FPS class",
     * donde se lo preguntara al usuario, despues, despliega los "characters" con la "FPS Class" que ha elegido el usuario.
     *
     */
    public void showCharacterInfoFPS(){
        ResultSet rs = null;
        System.out.println("Inserta una de estas clases FPS: ");
        System.out.println("Assault class");
        System.out.println("Medic class");
        System.out.println("Melee class");
        System.out.println("Sniper class");
        System.out.println("Tank class");
        System.out.println("Support class");
        System.out.println("Heavy tank class");
        String fpsClass = scanner.nextLine();

        String sql = "select * from characters WHERE fps_class = '" + fpsClass + "'";
        try{
            Statement st = connection.createStatement();

            rs = st.executeQuery(sql);

            while (rs.next()) {
                System.out.println("******************************************************" +
                        "\nCharacter ID: " + rs.getString("id_character") +
                        "\nCharacter Name: " + rs.getString("name") +
                        "\nCharacter Image: " + rs.getString("image") +
                        "\nCharacter Health: " + rs.getString("health") +
                        "\nVariant: " + rs.getString("variant") +
                        "\nPrimary Weapon: " + rs.getString("primary_weapon") +
                        "\nWeapon Damage: " + rs.getString("weapon_damage") +
                        "\nCharacter Abilities: " + rs.getString("abilities") +
                        "\nCharacter FPS Class: " + rs.getString("fps_class")
                        + "\n******************************************************");
            }

            rs.close();
            st.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    /**
     * El metodo createCharacter crea un character y lo mete en la fila de la tabla "characters", le preguntara
     * al usuario los atributos que tiene, el usuario los insertara y este metodo creara lo que ha insertado el usuario
     * dentro de la tabla "characters" como nuevo character.
     *
     */
    public void createCharacter(){
        try{
            System.out.println("CREAR UN CHARACTER");
            System.out.println("******************");

            System.out.println("Nombre del character: ");
            String name = scanner.nextLine();

            System.out.println("Inserta su imagen: ");
            String image = scanner.nextLine();

            System.out.println("Inserta la vida del character: ");
            String health = scanner.nextLine();

            System.out.println("Inserta que tipo de variante tiene el character: ");
            String variant = scanner.nextLine();

            System.out.println("Inserta el primary weapon: ");
            String primaryWeapon = scanner.nextLine();

            System.out.println("Inserta el damage del weapon: ");
            String damageWeapon = scanner.nextLine();

            System.out.println("Inserta las habilidades del character: ");
            String abilities = scanner.nextLine();

            System.out.println("Inserta una de las clases FPS: ");
            System.out.println("Assault class");
            System.out.println("Medic class");
            System.out.println("Melee class");
            System.out.println("Sniper class");
            System.out.println("Tank class");
            System.out.println("Support class");
            System.out.println("Heavy tank class");
            String fpsClass = scanner.nextLine();

            String sql = "INSERT INTO characters" + "(name,image,health,variant,primary_weapon,weapon_damage," +
                    "abilities,fps_class) VALUES(?,?,?,?,?,?,?,?)";

            PreparedStatement pst = connection.prepareCall(sql);
            pst.setString(1,name);
            pst.setString(2,image);
            pst.setString(3,health);
            pst.setString(4,variant);
            pst.setString(5,primaryWeapon);
            pst.setString(6,damageWeapon);
            pst.setString(7,abilities);
            pst.setString(8,fpsClass);

            pst.executeUpdate();
            pst.close();


        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * El metodo putCSVInfoCharacter creara una lista de la data de un archivo CSV que leeremos con BufferedReader.
     * Una vez hecho esto, separaremos la linea haciendo un split, y añadiremos el resultado de la informacion en
     * la lista que hemos creado. Una vez hecho esto, lo recorrera con un for, y hara los INSERTs del archivo CSV.
     *
     */
    public void putCSVInfoCharacter(){
        List<String[]> csvData = new ArrayList<>();
        try{
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/charactersTable.csv"));
            String line;

            while ((line = br.readLine()) != null){
                String[] data = line.split("\",\"");
                csvData.add(data);
            }

            for (String[] data : csvData) {
                try{
                    String name = data[0];
                    String image = data[1];
                    String health = data[2];
                    String variant = data[3];
                    String primary_weapon = data[4];
                    String weapon_damage = data[5];
                    String abilities = data[6];
                    String fps_class = data[7];

                    String sql = "INSERT INTO characters " + "(name,image,health,variant,primary_weapon,weapon_damage,abilities,fps_class) VALUES(?,?,?,?,?,?,?,?)";

                    PreparedStatement pst = connection.prepareStatement(sql);
                    pst.setString(1,name);
                    pst.setString(2,image);
                    pst.setString(3,health);
                    pst.setString(4,variant);
                    pst.setString(5,primary_weapon);
                    pst.setString(6,weapon_damage);
                    pst.setString(7,abilities);
                    pst.setString(8,fps_class);

                    pst.executeUpdate();
                    pst.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
