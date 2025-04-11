package model.dao.MySQLDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLDificultatDAO {
    private final Connection conn;

    /**
     * Constructor de la classe MySQLDificultatDAO
     * @param conn Connexió a la base de dades
     */
    public MySQLDificultatDAO(Connection conn) {
        this.conn = conn;
    }

    // Metodes especificos per llegir la taula dificultats
    /**
     * Comprovar si una dificultat existeix a la base de dades
     * @param nom Nom de la dificultat
     * @return id de la dificultat amb aquell nom o -1 si no existeix
     */
    public int getDificultatIdByNom(String nom) {
        String query = "SELECT dificultat_id FROM dificultats WHERE nom = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, nom);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("dificultat_id");
            } else {
                return -1; // Dificultat no trobada
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1; // Error en la consulta
        }
    }
}
