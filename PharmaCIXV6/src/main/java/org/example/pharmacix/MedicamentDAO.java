
package org.example.pharmacix;

import javafx.fxml.FXML;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicamentDAO {

    public List<Medicament> getAllMedicaments() {
        List<Medicament> liste = new ArrayList<>();
        String sql = "SELECT * FROM Medicament";
        try (Connection conn = connector.connectDb();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Medicament m = new Medicament(
                        rs.getInt("numMedicament"),
                        rs.getString("nom"),
                        rs.getDouble("prix"),
                        rs.getString("type"),
                        rs.getString("tableau"),
                        rs.getBoolean("enVenteLibre"),
                        rs.getInt("quantite")
                );
                liste.add(m);
            }
        } catch (SQLException e) {
            System.err.println("Error during SELECT: " + e.getMessage());
            e.printStackTrace();
        }
        return liste;
    }

    public boolean supprimerMedicament(int numMedicament) {
        String sql = "DELETE FROM Medicament WHERE numMedicament = ?";
        try (Connection conn = connector.connectDb();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, numMedicament); // Must match type!
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error during DELETE: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }


    public boolean ReqAjoutMed(int numMedicament, int qte) {
        String sql = "UPDATE Medicament SET quantite = quantite + ? WHERE numMedicament = ?";

        try (Connection conn = connector.connectDb();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, qte);
            pstmt.setInt(2, numMedicament);
            int rowsAffected = pstmt.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected); // Log pour vérifier le nombre de lignes affectées
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error during UPDATE operation: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }




    public boolean ajouterMedicament(Medicament medicament) {
        String sql = "INSERT INTO Medicament (nom, prix, type, tableau, enVenteLibre, quantite) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = connector.connectDb();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, medicament.getNom());
            stmt.setDouble(2, medicament.getPrix());
            stmt.setString(3, medicament.getType());
            stmt.setString(4, medicament.getTableau());
            stmt.setBoolean(5, medicament.isEnVenteLibre());
            stmt.setInt(6, medicament.getQuantite());

            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int generatedId = generatedKeys.getInt(1);
                        medicament.setNumMedicament(generatedId);  // 💡 Now you can access it
                        System.out.println("Generated Medicament ID: " + generatedId);
                    }
                }
                return true;
            }

        } catch (SQLException e) {
            System.err.println("Error during INSERT: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

}
