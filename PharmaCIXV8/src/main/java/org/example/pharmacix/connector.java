package org.example.pharmacix;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class connector {

    private Employe employeActif;

    public void setEmployeActif(Employe employe) {
        this.employeActif = employe;
    }

    public static Connection connectDb() {
        Connection conn = null;
        try {
            // Lecture et déchiffrement du fichier
            byte[] encryptedData = Files.readAllBytes(Paths.get("config.enc"));
            byte[] decryptedData = dechiffrer(encryptedData, "VivelebtsSio14@!");

            // Chargement des propriétés depuis les données déchiffrées
            Properties properties = new Properties();
            properties.load(new ByteArrayInputStream(decryptedData));

            String dbUrl = properties.getProperty("DB_URL");
            String dbUser = properties.getProperty("DB_USER");
            String dbPassword = properties.getProperty("DB_PASSWORD");

            if (dbUrl == null || dbUser == null || dbPassword == null) {
                throw new SQLException("Infos de connexion absentes !");
            }

            conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            System.out.println("Connexion réussie !");
        } catch (Exception e) {
            System.out.println("Échec de la connexion à la base de données.");
            e.printStackTrace();
        }
        return conn;
    }

    private static byte[] dechiffrer(byte[] data, String cle) throws Exception {
        SecretKeySpec key = new SecretKeySpec(cle.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(data);
    }


    public static void main(String[] args) {
        // Test de la connexion à la base de données
        try {
            Connection conn = connectDb();
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
