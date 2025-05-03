package model.dao.MySQLDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLTipusRocaDAO {
    private final Connection conn;

    public MySQLTipusRocaDAO(Connection conn) {
        this.conn = conn;
    }

    public int getTipusRocaIdByNom(String nom) {
        String query = "SELECT tipus_roca_id FROM tipus_roques WHERE nom = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, nom);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("tipus_roca_id");
            } else {
                return -1; // Tipus de roca no trobat
            }
        } catch (SQLException e) {
            return -1; // Error en la consulta
        }
    }
}
