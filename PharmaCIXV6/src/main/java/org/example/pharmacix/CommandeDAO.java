package org.example.pharmacix;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommandeDAO {

    public List<Commande> getAllCommandes() {
        List<Commande> liste = new ArrayList<>();
        String sql = "SELECT * FROM Commande";

        try (Connection conn = connector.connectDb();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Commande commande = new Commande(
                        rs.getString("numCommande"),
                        rs.getDate("dateCommande"),
                        rs.getDate("dateLivraison"),
                        rs.getInt("quantiteCommande"),
                        rs.getString("fk_numEmploye"),
                        rs.getString("fk_numFournisseur"),
                        rs.getInt("status")
                );
                liste.add(commande);
            }

        } catch (SQLException e) {
            System.err.println("Erreur SELECT Commande : " + e.getMessage());
            e.printStackTrace();
        }

        return liste;
    }

    public boolean ajouterCommande(Commande commande) {
        String sql = "INSERT INTO Commande (dateCommande, dateLivraison, quantiteCommande, fk_numEmploye, fk_numFournisseur, status) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = connector.connectDb();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDate(1, commande.getDateCommande());
            pstmt.setDate(2, commande.getDateLivraison());
            pstmt.setInt(3, commande.getQuantiteCommande());
            pstmt.setString(4, commande.getFk_numEmploye());
            pstmt.setString(5, commande.getFk_numFournisseur());
            pstmt.setInt(6, commande.getStatus());

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erreur INSERT Commande : " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean supprimerCommande(int numCommande) {
        String sql = "DELETE FROM Commande WHERE numCommande = ?";

        try (Connection conn = connector.connectDb();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, numCommande);
            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erreur DELETE Commande : " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}