package org.example.pharmacix;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connector {
    private static final String URL = "jdbc:mysql://172.20.10.7:3306/pharmacieDB";
    private static final String USER = "gaetan";
    private static final String PASSWORD = "changemePLS";

    public static Connection connectDb() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connexion réussie !");
        } catch (SQLException e) {
            System.err.println("Échec de la connexion à la base de données.");
            e.printStackTrace();
        }
        return connection;
    }
}