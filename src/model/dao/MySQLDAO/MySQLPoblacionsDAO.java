package model.dao.MySQLDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLPoblacionsDAO {

    private final Connection conn;

    public MySQLPoblacionsDAO(Connection conn) {
        this.conn = conn;
    }

    public int getIdPoblacioByNom(String nom) {
        String query = "SELECT poblacio_id FROM poblacions WHERE nom = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, nom);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("poblacio_id");
            } else {
                return -1; // Població no trobada
            }
        } catch (SQLException e) {
            return -1; // Error en la consulta
        }
    }
}
