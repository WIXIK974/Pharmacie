package org.example.pharmacix;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataValidator {

    public static boolean validatePatient(String numeroSS) {
        String sql = "SELECT 1 FROM Patient WHERE numeroSS = ?";
        try (Connection conn = connector.connectDb();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, numeroSS);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean validateEmploye(int idPersonne) {
        String sql = "SELECT 1 FROM Employe WHERE idPersonne = ?";
        try (Connection conn = connector.connectDb();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idPersonne);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean validateMedicament(String numMedicament) {
        String sql = "SELECT 1 FROM Medicament WHERE numMedicament = ?";
        try (Connection conn = connector.connectDb();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, numMedicament);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean validateOrdonnance(int numOrdonnance) {
        String sql = "SELECT 1 FROM Ordonnance WHERE numOrdonnance = ?";
        try (Connection conn = connector.connectDb();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, numOrdonnance);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
