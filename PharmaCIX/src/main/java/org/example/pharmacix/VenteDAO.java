package org.example.pharmacix;

import javafx.fxml.FXML;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VenteDAO {


    public List<Vente> getAllVentes() {
        List<Vente> liste = new ArrayList<>();
        String sql = "SELECT * FROM Vente";
        try (Connection conn = connector.connectDb();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Vente v = new Vente(
                        rs.getInt("numVente"),
                        rs.getDate("dateVente"),
                        rs.getString("fk_numeroSS"),
                        rs.getString("fk_numEmploye"),
                        rs.getString("fk_numMedicament"),
                        rs.getInt("fk_numOrdonnance")
                );
                liste.add(v);
            }
        } catch (SQLException e) {
            System.err.println("Error during SELECT: " + e.getMessage());
            e.printStackTrace();
        }
        return liste;
    }

    public boolean supprimerVente(int numVente) {
        String sql = "DELETE FROM Vente WHERE numVente = ?";
        try (Connection conn = connector.connectDb();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, numVente);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error during DELETE: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean ajouterVente(Vente vente) {
        String sql = "INSERT INTO Vente (dateVente, fk_numeroSS, fk_numEmploye, fk_numMedicament, fk_numOrdonnance) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = connector.connectDb();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, new java.sql.Date(vente.getDateVente().getTime()));
            stmt.setString(2, vente.getFk_numeroSS());
            stmt.setString(3, vente.getFk_numEmploye());
            stmt.setString(4, vente.getFk_numMedicament());
            stmt.setInt(5, vente.getFk_numOrdonnance());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error during INSERT: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
