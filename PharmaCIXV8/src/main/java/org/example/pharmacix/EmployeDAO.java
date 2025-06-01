package org.example.pharmacix;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;

public class EmployeDAO {

    public boolean ajouterEmploye(Employe employe, String identifiant, String password) {
        String sql = "INSERT INTO Employe (profession, salaire, typeContrat, identifiant, password, status) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = connector.connectDb();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, employe.getProfession());
            pstmt.setFloat(2, employe.getSalaire());
            pstmt.setString(3, employe.getTypeContrat());
            pstmt.setString(4, identifiant);

            // Hacher le mot de passe avec BCrypt
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
            pstmt.setString(5, hashedPassword);

            pstmt.setInt(6, employe.getStatus());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error during INSERT operation: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}