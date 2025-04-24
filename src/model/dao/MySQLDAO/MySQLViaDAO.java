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

    @Override
    public int getViaIdByNom(String nom) {
        String query = "SELECT via_id FROM vies WHERE nom = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, nom);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("via_id");
            } else {
                return -1; // Via no trobada
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1; // Error en la consulta
        }
    }

    @Override
    public String getDificultatByNom(String nom, String escolaNom) {
        String query = "SELECT d.grau FROM vies v " +
                        "INNER JOIN dificultats d ON d.dificultat_id = v.dificultat_id " +
                        "INNER JOIN sectors s ON v.sector_id = s.sector_id " +
                        "WHERE v.nom = ? AND s.escola_id = (SELECT escola_id FROM escoles WHERE nom = ?)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, nom);
            pstmt.setString(2, escolaNom);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("grau");
            } else {
                return null; // Via no trobada
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null; // Error en la consulta
        }
    }

    @Override
    public Boolean existeVia(String nom, String escolaNom) {
        String query = "SELECT v.via_id FROM vies v " +
                        "INNER JOIN sectors s ON v.sector_id = s.sector_id" +
                        "WHERE v.nom = ? AND s.escola_id = (SELECT escola_id FROM escoles WHERE nom = ?)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, nom);
            pstmt.setString(2, escolaNom);
            ResultSet rs = pstmt.executeQuery();
            return rs.next(); // Si hi ha resultats, la via existeix
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Error en la consulta
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
        MySQLViaDAO mySQLViaDAO = new MySQLViaDAO(conn);

        int sectorId = mySQLSectorDAO.getSectorIdByNom(o.getSector());
        if (sectorId == -1) {
            throw new SQLException("El sector no existeix a la base de dades");
        }
        if (mySQLViaDAO.existeVia(o.getNom(), mySQLSectorDAO.getEscola(o.getSector()))) {
            throw new SQLException("La via ja existeix a la base de dades");
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
