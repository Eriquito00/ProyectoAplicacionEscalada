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

        String query = "INSERT INTO vies (sector_id, tipus_id, ancoratge_id, tipus_roca_id, escalador_id, dificultat_id, nom, llargada, numero_via, orientacio, estat) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(query);
        MySQLSectorDAO mySQLSectorDAO = new MySQLSectorDAO(conn);
        MySQLTipusDAO mySQLTipusDAO = new MySQLTipusDAO(conn);
        MySQLAncoratgeDAO mySQLAncoratgeDAO = new MySQLAncoratgeDAO(conn);
        MySQLEscaladorDAO mySQLEscaladorDAO = new MySQLEscaladorDAO(conn);
        MySQLDificultatDAO mySQLDificultatDAO = new MySQLDificultatDAO(conn);
        MySQLTipusRocaDAO mySQLTipusRocaDAO = new MySQLTipusRocaDAO(conn);
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

        int tipusRocaId = mySQLTipusRocaDAO.getTipusRocaIdByNom(o.getTipus_roca());
        if (tipusRocaId == -1) {
            throw new SQLException("El tipus de roca no existeix a la base de dades");
        }
        pstmt.setInt(4, tipusRocaId);

        int escaladorId = mySQLEscaladorDAO.getEscaladorIdByNom(o.getEscalador());
        if (escaladorId == -1) {
            throw new SQLException("L'escalador no existeix a la base de dades");
        }
        pstmt.setInt(5, escaladorId);
        int dificultatId = mySQLDificultatDAO.getDificultatIdByNom(o.getDificultat());
        if (dificultatId == -1) {
            throw new SQLException("La dificultat no existeix a la base de dades");
        }
        pstmt.setInt(6, dificultatId);
        pstmt.setString(7, o.getNom());
        pstmt.setInt(8, o.getLlargada());
        pstmt.setInt(9, o.getNumero_via());
        pstmt.setString(10, o.getOrientacio());
        pstmt.setString(11, o.getEstat());
        pstmt.executeUpdate();

        // Actualitzar el numero de vies del sector
        String updateQuery = "UPDATE sectors SET num_vies = num_vies + 1 WHERE sector_id = ?";
        PreparedStatement updatePstmt = conn.prepareStatement(updateQuery);
        updatePstmt.setInt(1, sectorId);
        updatePstmt.executeUpdate();

        // Actualitzar el numero de vies de l'escola
        String updateEscolaQuery = "UPDATE escoles SET num_vies = num_vies + 1 WHERE escola_id = (SELECT escola_id FROM sectors WHERE sector_id = ?)";
        PreparedStatement updateEscolaPstmt = conn.prepareStatement(updateEscolaQuery);
        updateEscolaPstmt.setInt(1, sectorId);
        updateEscolaPstmt.executeUpdate();
    }
    @Override
    public Via read(Integer id) throws SQLException {
        String query = "SELECT v.nom, s.nom AS sector, t.nom AS tipus, a.nom AS ancoratge, tr.nom AS tipus_roca, e.nom AS escalador, d.grau AS dificultat, v.llargada, v.numero_via, v.orientacio, v.estat " +
                        "FROM vies v " +
                        "INNER JOIN sectors s ON v.sector_id = s.sector_id " +
                        "INNER JOIN tipus t ON v.tipus_id = t.tipus_id " +
                        "INNER JOIN ancoratges a ON v.ancoratge_id = a.ancoratge_id " +
                        "INNER JOIN tipus_roques tr ON v.tipus_roca_id = tr.tipus_roca_id " +
                        "INNER JOIN escaladors e ON v.escalador_id = e.escalador_id " +
                        "INNER JOIN dificultats d ON v.dificultat_id = d.dificultat_id " +
                        "WHERE v.via_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Via(rs.getString("sector"), rs.getString("tipus"), rs.getString("ancoratge"),
                        rs.getString("tipus_roca"), rs.getString("escalador"), rs.getString("dificultat"),
                        rs.getString("nom"), rs.getInt("llargada"), rs.getInt("numero_via"),
                        rs.getString("orientacio"), rs.getString("estat"));
            } else {
                throw new SQLException("La via no existeix a la base de dades");
            }
    }

    public ResultSet readAll() throws SQLException {
        String query = "SELECT v.via_id, s.nom AS sector, t.nom AS tipo, a.nom AS ancorage, tp.nom AS tipo_roca, e.nom AS escalador, d.grau AS dificultad,v.nom,v.llargada,v.numero_via,v.orientacio,v.estat " +
                "FROM vies v " +
                "LEFT JOIN sectors s ON v.sector_id = s.sector_id " +
                "LEFT JOIN tipus t ON t.tipus_id = v.tipus_id " +
                "LEFT JOIN ancoratges a ON a.ancoratge_id = v.ancoratge_id " +
                "LEFT JOIN tipus_roques tp ON tp.tipus_roca_id = v.tipus_roca_id " +
                "LEFT JOIN escaladors e ON e.escalador_id = v.escalador_id " +
                "LEFT JOIN dificultats d ON d.dificultat_id = v.dificultat_id";
        PreparedStatement pstmt = conn.prepareStatement(query);
        return pstmt.executeQuery();
    }

    @Override
    public void update(Via o) {

    }
    @Override
    public void delete(Integer id) throws SQLException {
        String query = "DELETE FROM vies WHERE via_id = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setInt(1,id);
        pstmt.executeUpdate();
    }

    public ResultSet viesEscola (String esc) throws SQLException{
        MySQLEscolaDAO escolaDAO = new MySQLEscolaDAO(conn);
        if (!escolaDAO.existeEscola(esc)) throw new SQLException("El nombre de la escuela introducida no existe.");
        String query = "SELECT v.nom, v.numero_via, v.orientacio, t.nom AS tipus, d.grau AS dificultat, s.nom AS sector, e.nom AS escola" +
                " FROM vies v" +
                " INNER JOIN tipus t ON t.tipus_id = v.tipus_id" +
                " INNER JOIN dificultats d ON d.dificultat_id = v.dificultat_id" +
                " INNER JOIN sectors s ON s.sector_id = v.sector_id" +
                " INNER JOIN escoles e ON e.escola_id = s.escola_id" +
                " WHERE e.nom = ? AND v.estat = \"apte\"";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1,esc);
        ResultSet rs = pstmt.executeQuery();
        if (rs.isBeforeFirst()) return rs;
        else throw new SQLException("La escuela introducida no tiene ninguna via.");
    }

    public ResultSet viesDificultat (String dif) throws SQLException{
        MySQLDificultatDAO dificultatDAO = new MySQLDificultatDAO(conn);
        if (dificultatDAO.getDificultatIdByNom(dif) == -1) throw new SQLException("La dificultad introducida no existe.");
        String query = "SELECT v.nom, d.grau AS dificultad, s.nom AS sector, e.nom AS escola" +
                " FROM vies v" +
                " INNER JOIN dificultats d ON d.dificultat_id = v.dificultat_id" +
                " INNER JOIN sectors s ON s.sector_id = v.sector_id" +
                " INNER JOIN escoles e ON e.escola_id = s.escola_id" +
                " WHERE d.grau = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1,dif);
        ResultSet rs = pstmt.executeQuery();
        if (rs.isBeforeFirst()) return rs;
        else throw new SQLException("La dificultad introducida no tiene ninguna via.");
    }
}
