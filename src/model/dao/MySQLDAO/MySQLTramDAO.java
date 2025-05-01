package model.dao.MySQLDAO;

import model.classes.Tram;
import model.classes.Via;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLTramDAO {
    private Connection conn;

    public MySQLTramDAO (Connection conn) {
        this.conn = conn;
    }

    public void insertTram (int viaId, Tram tram) throws SQLException {
        MySQLDificultatDAO mySQLDificultatDAO = new MySQLDificultatDAO(conn);
        String query = "INSERT INTO trams (via_id, dificultat_id, llargada, numero_tram) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, viaId);
        stmt.setInt(3, tram.getLlargada());
        int dificultatId = mySQLDificultatDAO.getDificultatIdByNom(tram.getDificultat());
        if (dificultatId == -1) {
            throw new SQLException("La dificultat del tram no existeix");
        }
        stmt.setInt(2, dificultatId);
        stmt.setInt(4, tram.getNumero_tram());
        stmt.executeUpdate();
    }

    public ResultSet readTram () throws SQLException{
        String query = "SELECT t.tram_id, v.nom AS via, d.grau AS dificultat, t.llargada, t.numero_tram" +
                " FROM trams t" +
                " LEFT JOIN vies v ON v.via_id = t.via_id" +
                " LEFT JOIN dificultats d ON d.dificultat_id = t.dificultat_id";
        PreparedStatement pstmt = conn.prepareStatement(query);
        return pstmt.executeQuery();
    }
}