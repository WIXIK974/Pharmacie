package org.example.pharmacix;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VenteDAO {

    public boolean ajouterVente(Vente vente) {
        String sqlVente = "INSERT INTO Vente (fk_numeroSS, fk_numEmploye, fk_numMedicament, fk_numOrdonnance, dateVente, quantiteVendue) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = connector.connectDb()) {
            conn.setAutoCommit(false); // Désactivez l'auto-commit pour gérer les transactions manuellement

            // Insérer la vente
            try (PreparedStatement pstmtVente = conn.prepareStatement(sqlVente)) {
                pstmtVente.setString(1, vente.getFk_numeroSS());
                pstmtVente.setInt(2, Integer.parseInt(vente.getFk_numEmploye())); // Utilisez idPersonne au lieu de numEmploye
                pstmtVente.setInt(3, Integer.parseInt(vente.getFk_numMedicament())); // Assurez-vous que fk_numMedicament est un entier
                pstmtVente.setInt(4, vente.getFk_numOrdonnance());
                pstmtVente.setDate(5, new java.sql.Date(vente.getDateVente().getTime()));
                pstmtVente.setInt(6, Integer.parseInt(vente.getQuantiteVendue())); // Assurez-vous que la quantité est un entier

                int rowsAffectedVente = pstmtVente.executeUpdate();
                if (rowsAffectedVente <= 0) {
                    conn.rollback(); // Annulez la transaction si l'insertion échoue
                    return false;
                }
            }

            // Mettre à jour la quantité de médicament


            conn.commit(); // Validez la transaction si tout est réussi
            return true;

        } catch (SQLException e) {
            System.err.println("Error during INSERT operation: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public List<Vente> getAllVentes() {
        List<Vente> ventes = new ArrayList<>();
        String sql = "SELECT * FROM Vente";

        try (Connection conn = connector.connectDb();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int numVente = rs.getInt("numVente");
                Date dateVente = rs.getDate("dateVente");
                String fk_numeroSS = rs.getString("fk_numeroSS");
                String fk_numEmploye = rs.getString("fk_numEmploye");
                String fk_numMedicament = rs.getString("fk_numMedicament");
                int fk_numOrdonnance = rs.getInt("fk_numOrdonnance");
                String quantiteVendue = rs.getString("quantiteVendue");

                Vente vente = new Vente(numVente, dateVente, fk_numeroSS, fk_numEmploye, fk_numMedicament, fk_numOrdonnance, quantiteVendue);
                ventes.add(vente);
            }

        } catch (SQLException e) {
            System.err.println("Error during SELECT operation: " + e.getMessage());
            e.printStackTrace();
        }

        return ventes;
    }
}
