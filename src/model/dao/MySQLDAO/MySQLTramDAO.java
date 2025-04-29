package model.dao.MySQLDAO;

import model.classes.Tram;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySQLTramDAO {
    private Connection conn;

    public MySQLTramDAO (Connection conn) {
        this.conn = conn;
    }

    public void insertTram (int viaId, Tram tram) throws SQLException {
        MySQLDificultatDAO mySQLDificultatDAO = new MySQLDificultatDAO(conn);
        String query = "INSERT INTO trams (via_id, llargada, dificultat_id) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, viaId);
            stmt.setInt(2, tram.getLlargada());
            int dificultatId = mySQLDificultatDAO.getDificultatIdByNom(tram.getDificultat());
            if (dificultatId == -1) {
                throw new SQLException("La dificultat del tram no existeix");
            }
            stmt.setInt(3, dificultatId);
            stmt.executeUpdate();
        }
    }
}
