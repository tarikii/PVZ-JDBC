package org.example;

import java.sql.Connection;

/**
 * Clase principal de nuestro proyecto, se encarga de iniciar la conexion a nuestra base de datos y muestra el
 * menu principal, con el que el usuario interactua con las tablas "variants" y "characters"
 *
 * @author Tarik Aabouch - tarikii in GitHub
 * @version 8.0
 */
public class ACBMain {
    public static void main(String[] args){
        PVZMenu pvzMenu = new PVZMenu();

        ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
        Connection connectDatabase = connectionFactory.connect();

        CharacterController characterController = new CharacterController(connectDatabase);
        VariantController variantController = new VariantController(connectDatabase);
        TablesController tablesController = new TablesController(connectDatabase);


        boolean disconnect = false;
        while (!disconnect){
            int option = pvzMenu.mainMenu();
            switch (option){
                case 1:
                    characterController.showCharacterInfo();
                    break;
                case 2:
                    variantController.showVariantInfo();
                    break;
                case 3:
                    characterController.showCharacterInfoFPS();
                    break;
                case 4:
                    variantController.showVariantInfoRareza();
                    break;
                case 5:
                    characterController.createCharacter();
                    break;
                case 6:
                    variantController.createVariant();
                    break;
                case 7:
                    tablesController.crearEstructuraSQL();
                    break;
                case 8:
                    tablesController.deleteTables();
                    break;
                case 9:
                    characterController.putCSVInfoCharacter();
                    break;
                case 10:
                    variantController.putCSVInfoVariant();
                    break;
                case 11:
                    disconnect = true;
            }
        }
    }
}