package model.dao.MySQLDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLTipusDAO {
    private final Connection conn;

    public MySQLTipusDAO(Connection conn) {
        this.conn = conn;
    }

    // Metodos especificos para leer la tabla tipus

    /**
     * Comprovar si un tipus existeix a la base de dades
     * @param nom Nom del tipus
     * @return id del tipus amb aquell nom o -1 si no existeix
     */
    public int getTipusIdByNom(String nom) {
        String query = "SELECT tipus_id FROM tipus WHERE nom = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, nom);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("tipus_id");
            } else {
                return -1; // Tipus no trobat
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1; // Error en la consulta
        }
    }
}
