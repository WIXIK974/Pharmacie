package org.example.pharmacix;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class connector {

    public static Connection connectDb() {
        Connection conn = null;
        try {
            // Chargement du fichier de configuration
            Properties properties = new Properties();
            FileInputStream fis = new FileInputStream("config.properties");
            properties.load(fis);

             //Récupération des informations de la base de données
            String dbUrl = properties.getProperty("DB_URL");
            String dbUser = properties.getProperty("DB_USER");
            String dbPassword = properties.getProperty("DB_PASSWORD");
            //String dbUrl ="jdbc:mysql://172.20.0.122:3306/PharmaTestWE";
            //String dbUser ="pepe";
            //String dbPassword="pepe";

            // Vérification si les informations sont présentes
            if (dbUrl == null || dbUser == null || dbPassword == null) {
                throw new SQLException("Les informations de connexion ne sont pas définies dans le fichier config.properties !");
            }
            System.out.println(dbUrl +" "+ dbUser +" "+ dbPassword  );
            // Connexion à la base de données
            conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            System.out.println("Connexion réussie à la base de données !");
        } catch (IOException | SQLException e) {
            System.out.println("Échec de la connexion à la base de données.");
            e.printStackTrace();
        }
        return conn;
    }

    public static void main(String[] args) {
        // Test de la connexion à la base de données
        connectDb();
    }
}
