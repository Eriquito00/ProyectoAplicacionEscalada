package model.dao.MySQLDAO;

import model.classes.Tram;
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
    public int getViaIdByNom(String nom, String escolaNom) {
        String query = "SELECT v.via_id FROM vies v " +
                        "INNER JOIN sectors s ON v.sector_id = s.sector_id " +
                        "WHERE v.nom = ? AND s.escola_id = (SELECT escola_id FROM escoles WHERE nom = ?)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, nom);
            pstmt.setString(2, escolaNom);
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
        String query = "SELECT v.via_id FROM vies v" +
                        " INNER JOIN sectors s ON v.sector_id = s.sector_id" +
                        " WHERE v.nom = ? AND s.escola_id = (SELECT escola_id FROM escoles WHERE nom = ?)";
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

    public ResultSet viesEstat(String estat) throws SQLException {
        String query = "SELECT v.nom, v.numero_via, v.orientacio, t.nom AS tipus, d.grau AS dificultat, s.nom AS sector, e.nom AS escola" +
                " FROM vies v" +
                " INNER JOIN tipus t ON t.tipus_id = v.tipus_id" +
                " INNER JOIN dificultats d ON d.dificultat_id = v.dificultat_id" +
                " INNER JOIN sectors s ON s.sector_id = v.sector_id" +
                " INNER JOIN escoles e ON e.escola_id = s.escola_id" +
                " WHERE v.estat = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1,estat);
        ResultSet rs = pstmt.executeQuery();
        if (rs.isBeforeFirst()) return rs;
        else throw new SQLException("No hay ninguna via en este estado.");
    }

    public ResultSet viesAptesRecents() throws SQLException {
        String query = "SELECT v.nom, v.llargada, v.numero_via, v.orientacio, v.estat, v.ultim_apte" +
                " FROM vies v" +
                " WHERE v.ultim_apte > CURDATE() - 30";
        PreparedStatement pstmt = conn.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        if (rs.isBeforeFirst()) return rs;
        else throw new SQLException("No hay ninguna via que haya sido apta recientemente.");
    }

    public ResultSet viesescolaLlargues(String escola) throws SQLException{
        MySQLEscolaDAO escolaDAO = new MySQLEscolaDAO(conn);
        if (!escolaDAO.existeEscola(escola)) throw new SQLException("La escuela introducida no existe.");
        String query = "SELECT v.nom, v.llargada, v.numero_via, v.orientacio, t.nom AS tipus, d.grau AS dificultat, s.nom AS sector, e.nom AS escola" +
                " FROM vies v" +
                " INNER JOIN tipus t ON t.tipus_id = v.tipus_id" +
                " INNER JOIN dificultats d ON d.dificultat_id = v.dificultat_id" +
                " INNER JOIN sectors s ON s.sector_id = v.sector_id" +
                " INNER JOIN escoles e ON e.escola_id = s.escola_id" +
                " WHERE e.nom = ? AND v.llargada = (SELECT MAX(v1.llargada) FROM vies v1 INNER JOIN sectors s ON s.sector_id = v1.sector_id INNER JOIN escoles e ON e.escola_id = s.escola_id WHERE e.nom = ?)" +
                " ORDER BY v.llargada DESC";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1,escola);
        pstmt.setString(2,escola);
        ResultSet rs = pstmt.executeQuery();
        if (rs.isBeforeFirst()) return rs;
        else throw new SQLException("Esta escuela no tiene vias asignadas.");
    }

    // CRUD
    @Override
    public void create(Via o) throws SQLException {

        // Comporvar si la connexió és null
        if (conn == null) {
            throw new SQLException("La connexió a la base de dades és null");
        }

        String query = "INSERT INTO vies (sector_id, tipus_id, ancoratge_id, tipus_roca_id, escalador_id, dificultat_id, nom, llargada, numero_via, orientacio, estat, ultim_apte) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, CASE WHEN estat = \"apte\" THEN CURDATE() ELSE null END)";
        PreparedStatement pstmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        MySQLSectorDAO mySQLSectorDAO = new MySQLSectorDAO(conn);
        MySQLTipusDAO mySQLTipusDAO = new MySQLTipusDAO(conn);
        MySQLAncoratgeDAO mySQLAncoratgeDAO = new MySQLAncoratgeDAO(conn);
        MySQLEscaladorDAO mySQLEscaladorDAO = new MySQLEscaladorDAO(conn);
        MySQLDificultatDAO mySQLDificultatDAO = new MySQLDificultatDAO(conn);
        MySQLTipusRocaDAO mySQLTipusRocaDAO = new MySQLTipusRocaDAO(conn);
        MySQLViaDAO mySQLViaDAO = new MySQLViaDAO(conn);
        MySQLTramDAO mySQLTramDAO = new MySQLTramDAO(conn);

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

        int dificultatId = -1;
        if(!o.getTipus().equalsIgnoreCase("esportiva")) {
            for (Tram tram : o.getTrams()) {
                dificultatId = mySQLDificultatDAO.getDificultatIdByNom(tram.getDificultat());
                if (dificultatId == -1) {
                    throw new SQLException("La dificultat del tram " + tram.toString() + " no existeix a la base de dades");
                }
            }
        }
        else {
            dificultatId = mySQLDificultatDAO.getDificultatIdByNom(o.getDificultat());
            if (dificultatId == -1) {
                throw new SQLException("La dificultat no existeix a la base de dades");
            }
        }

        int llargada = 0;
        if(o.getTipus().equalsIgnoreCase("esportiva")) {
            llargada = o.getLlargada();
        }
        else {
            for (Tram tram : o.getTrams()) {
                llargada += tram.getLlargada();
            }
        }

        pstmt.setInt(6, dificultatId);
        pstmt.setString(7, o.getNom());
        pstmt.setInt(8, o.getLlargada());
        pstmt.setInt(9, o.getNumero_via());
        pstmt.setString(10, o.getOrientacio());
        pstmt.setString(11, o.getEstat());

        pstmt.executeUpdate();

        // Obtener el ID generado de la via
        ResultSet rs = pstmt.getGeneratedKeys();
        int viaId = -1;
        if (rs.next()) {
            viaId = rs.getInt(1);
        } else {
            throw new SQLException("No s'ha pogut obtenir l'ID de la via creada.");
        }

        // Insertar tramos segun el tipo de via
        if (!o.getTipus().equalsIgnoreCase("esportiva")) {
            // Para clásica o gel
            for (Tram tramo : o.getTrams()) {
                mySQLTramDAO.insertTram(viaId, tramo);
            }
        } else {
            // Para esportiva: un único tramo igual que la via
            Tram tramo = new Tram(o.getLlargada(), o.getDificultat(), 1);
            mySQLTramDAO.insertTram(viaId, tramo);
        }

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
        String query = "SELECT v.nom, s.nom AS sector, t.nom AS tipus, a.nom AS ancoratge, tr.nom AS tipus_roca, e.nom AS escalador, d.grau AS dificultat, v.llargada, v.numero_via, v.orientacio, v.estat, v.ultim_apte " +
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
                        rs.getString("orientacio"), rs.getString("estat"), rs.getString("ultim_apte"));
            } else {
                throw new SQLException("La via no existeix a la base de dades");
            }
    }

    public ResultSet readAll() throws SQLException {
        String query = "SELECT v.via_id, s.nom AS sector, t.nom AS tipo, a.nom AS ancorage, tp.nom AS tipo_roca, e.nom AS escalador, d.grau AS dificultad,v.nom,v.llargada,v.numero_via,v.orientacio,v.estat, v.ultim_apte " +
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
    public void update(Via o) throws SQLException {
        String query = "UPDATE vies SET " +
                        "sector_id = ?, " +
                        "tipus_id = ?, " +
                        "ancoratge_id = ?, " +
                        "tipus_roca_id = ?, " +
                        "escalador_id = ?, " +
                        "dificultat_id = ?, " +
                        "nom = ?, " +
                        "llargada = ?, " +
                        "numero_via = ?, " +
                        "orientacio = ?, " +
                        "estat = ? " +
                        "WHERE via_id = ?";

        PreparedStatement pstmt = conn.prepareStatement(query);
        MySQLSectorDAO mySQLSectorDAO = new MySQLSectorDAO(conn);
        // TODO: hace falta traerse la ID de la via en el objeto, nuevo constructor en todas las clases con id, solo para actualizar
        // SI EL NOMBRE Y EL SECTOR NO CAMBIAN NO HARIA FALTA, PERO SI LO ACTUALIZAN YA NO SE PODRIA ENCONTRAR

        //Update sector
        int sectorId = mySQLSectorDAO.getSectorIdByNom(o.getSector());
        if (sectorId == -1) {
            throw new SQLException("El sector no existeix a la base de dades");
        }
        pstmt.setInt(1, sectorId);

        //Update tipus
        int tipusId = new MySQLTipusDAO(conn).getTipusIdByNom(o.getTipus());
        if (tipusId == -1) {
            throw new SQLException("El tipus no existeix a la base de dades");
        }
        pstmt.setInt(2, tipusId);

        //Update ancoratge
        int ancoratgeId = new MySQLAncoratgeDAO(conn).getAncoratgeIdByNom(o.getAncoratge());
        if (ancoratgeId == -1) {
            throw new SQLException("L'ancoratge no existeix a la base de dades");
        }
        pstmt.setInt(3, ancoratgeId);

        //Update tipus roca
        int tipusRocaId = new MySQLTipusRocaDAO(conn).getTipusRocaIdByNom(o.getTipus_roca());
        if (tipusRocaId == -1) {
            throw new SQLException("El tipus de roca no existeix a la base de dades");
        }
        pstmt.setInt(4, tipusRocaId);

        //Update escalador
        int escaladorId = new MySQLEscaladorDAO(conn).getEscaladorIdByNom(o.getEscalador());
        if (escaladorId == -1) {
            throw new SQLException("L'escalador no existeix a la base de dades");
        }
        pstmt.setInt(5, escaladorId);

        //Update dificultat
        int dificultatId = new MySQLDificultatDAO(conn).getDificultatIdByNom(o.getDificultat());
        if (dificultatId == -1) {
            throw new SQLException("La dificultat no existeix a la base de dades");
        }
        pstmt.setInt(6, dificultatId);
        pstmt.setString(7, o.getNom());
        pstmt.setInt(8, o.getLlargada());
        pstmt.setInt(9, o.getNumero_via());
        pstmt.setString(10, o.getOrientacio());
        //TODO: hará falta actualizar un campo historial y una ficha si ha pasado a ser apte
        pstmt.setString(11, o.getEstat());
        pstmt.setInt(12, o.getId());
        pstmt.executeUpdate();

        // Actualitzar el numero de vies del sector
        String updateQuery = "UPDATE sectors SET num_vies = num_vies + 1 WHERE sector_id = ?";
        PreparedStatement updatePstmt = conn.prepareStatement(updateQuery);
        updatePstmt.setInt(1, sectorId);
        updatePstmt.executeUpdate();
    }

    @Override
    public void delete(Integer id) throws SQLException {
        String query = "DELETE FROM vies WHERE via_id = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setInt(1,id);
        pstmt.executeUpdate();
    }
}