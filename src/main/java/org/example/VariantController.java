package org.example;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * Esta clase llamada VariantController es usada para lidiar la informacion de la tabla "variants".
 * Tiene metodos que muestran la tabla, rellenan la tabla, y crean la fila de un variant en la tabla.
 *
 * @author Tarik Aabouch - tarikii in GitHub
 * @version 8.0
 */
public class VariantController {
    private Connection connection;
    Scanner scanner;

    /**
     * Este constructor del controlador nos permite conectarnos a la base de datos del postgres, en base a este controlador, podremos buscar,rellenar,etc... .
     */
    public VariantController(Connection connection){
        this.connection = connection;
        this.scanner = new Scanner(System.in);
    }

    /**
     * El metodo showVariantInfo recoge toda la informacion actual de la tabla "variants" y la despliega para
     * que el usuario la lea.
     */
    public void showVariantInfo(){
        System.out.println("\nVARIANTS");
        ResultSet rs = null;
        String sql = "SELECT * FROM variants";
        try{
            Statement st = connection.createStatement();

            rs = st.executeQuery(sql);

            while (rs.next()) {
                System.out.println("******************************************************" +
                        "\nVariant ID: " + rs.getString("id_variant") +
                        "\nVariant Name: " + rs.getString("name") +
                        "\nVariant Image: " + rs.getString("image") +
                        "\nVariant Health: " + rs.getString("health") +
                        "\nPrimary Weapon: " + rs.getString("primary_weapon") +
                        "\nWeapon Damage: " + rs.getString("weapon_damage") +
                        "\nVariant Perk: " + rs.getString("variant_perk") +
                        "\nVariant Rarity: " + rs.getString("rarity")
                + "\n******************************************************");
            }

            rs.close();
            st.close();

        }catch (SQLException e){
            System.out.println("Error: La tabla characters no existe");
        }
    }


    /**
     * El metodo showVariantInfoRareza muestra la informacion actual de "variants" en base al atributo "rarity",
     * donde se lo preguntara al usuario, despues, despliega los "variants" con la "rarity" que ha elegido el usuario.
     *
     */
    public void showVariantInfoRareza(){
        ResultSet rs = null;
        System.out.println("Inserta una de estas rarezas: ");
        System.out.println("RARE");
        System.out.println("SUPER RARE");
        System.out.println("SPECIAL");
        System.out.println("LEGENDARY");
        String rarity = scanner.nextLine();

        String sql = "select * from variants WHERE rarity = '" + rarity + "'";
        try{
            Statement st = connection.createStatement();

            rs = st.executeQuery(sql);

            while (rs.next()) {
                System.out.println("******************************************************" +
                        "\nVariant ID: " + rs.getString("id_variant") +
                        "\nVariant Name: " + rs.getString("name") +
                        "\nVariant Image: " + rs.getString("image") +
                        "\nVariant Health: " + rs.getString("health") +
                        "\nPrimary Weapon: " + rs.getString("primary_weapon") +
                        "\nWeapon Damage: " + rs.getString("weapon_damage") +
                        "\nVariant Perk: " + rs.getString("variant_perk") +
                        "\nVariant Rarity: " + rs.getString("rarity")
                        + "\n******************************************************");
            }

            rs.close();
            st.close();

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    /**
     * El metodo createVariant crea un variant y lo mete en la fila de la tabla "variants", le preguntara
     * al usuario los atributos que tiene, el usuario los insertara y este metodo creara lo que ha insertado el usuario
     * dentro de la tabla "variants" como nuevo variant.
     *
     */
    public void createVariant(){
        try{
            System.out.println("CREAR UN VARIANT");
            System.out.println("******************");

            System.out.println("Nombre de la variante: ");
            String name = scanner.nextLine();

            System.out.println("Inserta su imagen: ");
            String image = scanner.nextLine();

            System.out.println("Inserta la vida de la variante: ");
            String health = scanner.nextLine();

            System.out.println("Inserta el primary weapon: ");
            String primaryWeapon = scanner.nextLine();

            System.out.println("Inserta el damage del weapon: ");
            String damageWeapon = scanner.nextLine();

            System.out.println("Inserta los beneficios (perks) de la variante: ");
            String variant_perk = scanner.nextLine();

            System.out.println("Inserta que tipo de rareza es la variante: ");
            System.out.println("RARE");
            System.out.println("SUPER RARE");
            System.out.println("SPECIAL");
            System.out.println("LEGENDARY");
            String rarity = scanner.nextLine();

            String sql = "INSERT INTO variants" + "(name,image,health,primary_weapon,weapon_damage," +
                    "variant_perk,rarity) VALUES(?,?,?,?,?,?,?)";

            PreparedStatement pst = connection.prepareCall(sql);
            pst.setString(1,name);
            pst.setString(2,image);
            pst.setString(3,health);
            pst.setString(4,primaryWeapon);
            pst.setString(5,damageWeapon);
            pst.setString(6,variant_perk);
            pst.setString(7,rarity);

            pst.executeUpdate();
            pst.close();


        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * El metodo putCSVInfoVariant creara una lista de la data de un archivo CSV que leeremos con BufferedReader.
     * Una vez hecho esto, separaremos la linea haciendo un split, y añadiremos el resultado de la informacion en
     * la lista que hemos creado. Una vez hecho esto, lo recorrera con un for, y hara los INSERTs del archivo CSV.
     *
     */
    public void putCSVInfoVariant(){
        List<String[]> csvData = new ArrayList<>();
        try{
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/variantsTable.csv"));
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
                    String primary_weapon = data[3];
                    String weapon_damage = data[4];
                    String variant_perk = data[5];
                    String rarity = data[6];

                    String sql = "INSERT INTO variants " + "(name,image,health,primary_weapon,weapon_damage,variant_perk,rarity) VALUES(?,?,?,?,?,?,?)";

                    PreparedStatement pst = connection.prepareStatement(sql);
                    pst.setString(1,name);
                    pst.setString(2,image);
                    pst.setString(3,health);
                    pst.setString(4,primary_weapon);
                    pst.setString(5,weapon_damage);
                    pst.setString(6,variant_perk);
                    pst.setString(7,rarity);

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
