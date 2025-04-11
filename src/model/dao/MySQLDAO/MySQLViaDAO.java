package model.dao.MySQLDAO;

import model.classes.Via;
import model.dao.interfaces.ViaDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLViaDAO implements ViaDAO {
    private final Connection conn;

    public MySQLViaDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public int getNumVies(int idEscola) {
        String query = "SELECT COUNT(v.via_id) AS num_vies " +
                        "FROM vies v " +
                        "INNER JOIN sectors s ON s.sector_id = v.sector_id" +
                        "WHERE s.escola_id = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, idEscola);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("num_vies");
            } else {
                return -1; // Escola no trobada
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
            return -1; // Error en la consulta
        }
    }

    // CRUD
    @Override
    public void create(Via o) throws SQLException {

        // Comporvar si la connexió és null
        if (conn == null) {
            throw new SQLException("La connexió a la base de dades és null");
        }

        String query = "INSERT INTO vies (sector_id, tipus_id, ancoratge_id, escalador_id, dificultat_id, nom, llargada, numero_via, orientacio, estat) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(query);
        MySQLSectorDAO mySQLSectorDAO = new MySQLSectorDAO(conn);
        MySQLTipusDAO mySQLTipusDAO = new MySQLTipusDAO(conn);
        MySQLAncoratgeDAO mySQLAncoratgeDAO = new MySQLAncoratgeDAO(conn);
        MySQLEscaladorDAO mySQLEscaladorDAO = new MySQLEscaladorDAO(conn);
        MySQLDificultatDAO mySQLDificultatDAO = new MySQLDificultatDAO(conn);
        int sectorId = mySQLSectorDAO.getSectorIdByNom(o.getSector());
        if (sectorId == -1) {
            throw new SQLException("El sector no existeix a la base de dades");
        }
        pstmt.setInt(1, sectorId);
        int  tipusId = mySQLTipusDAO.getTipusIdByNom(o.getTipus());
        if (tipusId == -1) {
            throw new SQLException("El tipus no existeix a la base de dades");
        }
        pstmt.setInt(2, tipusId);
        int ancoratgeId = mySQLAncoratgeDAO.getAncoratgeIdByNom(o.getAncoratge());
        if (ancoratgeId == -1) {
            throw new SQLException("L'ancoratge no existeix a la base de dades");
        }
        pstmt.setInt(3, ancoratgeId);
        int escaladorId = mySQLEscaladorDAO.getEscaladorIdByNom(o.getEscalador());
        if (escaladorId == -1) {
            throw new SQLException("L'escalador no existeix a la base de dades");
        }
        pstmt.setInt(4, escaladorId);
        int dificultatId = mySQLDificultatDAO.getDificultatIdByNom(o.getDificultat());
        if (dificultatId == -1) {
            throw new SQLException("La dificultat no existeix a la base de dades");
        }
        pstmt.setInt(5, dificultatId);
        pstmt.setString(6, o.getNom());
        pstmt.setInt(7, o.getLlargada());
        pstmt.setInt(8, o.getNumero_via());
        pstmt.setString(9, o.getOrientacio());
        pstmt.setString(10, o.getEstat());
        pstmt.executeUpdate();
    }
    @Override
    public Via read(Integer id) {
        return null;
    }
    @Override
    public void update(Via o) {

    }
    @Override
    public void delete(Integer key) {

    }
}
