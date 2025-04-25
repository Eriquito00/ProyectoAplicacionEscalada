package model.dao.MySQLDAO;

import model.classes.Sector;
import model.dao.interfaces.SectorDAO;

import java.sql.*;

public class MySQLSectorDAO implements SectorDAO {
    private final Connection conn;

    /**
     * Constructor de la classe MySQLSectorDAO
     * @param conn Connexió a la base de dades
     */
    public MySQLSectorDAO(Connection conn) {
        this.conn = conn;
    }

    public int getNumVies(int idEscola) {
        return 0; // TODO: Hace falta implementar una query para obtener el numero de vias de el sector que se le pase por parametro
    }

    /**
     * Comprovar si un sector existeix a la base de dades
     * @param nom Nom del sector
     * @return id del sector amb aquell nom o -1 si no existeix
     */
    public int getSectorIdByNom(String nom) {
        String query = "SELECT sector_id FROM sectors WHERE nom = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, nom);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("sector_id");
            } else {
                return -1; // Sector no trobat
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1; // Error en la consulta
        }
    }

    @Override
    public String getEscola(String sector) throws SQLException {
        String query = "SELECT e.nom FROM sectors s INNER JOIN escoles e ON s.escola_id = e.escola_id WHERE s.nom = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, sector);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return rs.getString("nom");
        } else {
            return null; // Sector no trobat
        }
    }

    @Override
    public Boolean sectorExists(String nom, String escola) throws SQLException {
        String query = "SELECT s.sector_id " +
                        "FROM sectors s " +
                        "INNER JOIN escoles e ON e.escola_id = s.escola_id " +
                        "WHERE nom = ? AND e.nom = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, nom);
        pstmt.setInt(2, new MySQLEscolaDAO(conn).getEscolaIdByNom(escola));
        ResultSet rs = pstmt.executeQuery();
        return rs.next(); // Si hi ha resultats, el sector existeix
    }

    // CRUD

    @Override
    public void create(Sector o) throws SQLException {

        if (conn == null) {
            throw new SQLException("Connection is null");
        }
        String query = "INSERT INTO sectors (escola_id, nom, latitud, longitud, aproximacio, num_vies, popularitat, restriccions) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(query);
        int escolaId = new MySQLEscolaDAO(conn).getEscolaIdByNom(o.getEscola());
        if (escolaId == -1) throw new SQLException("La escola indicada no existeix a la base de dades");

        if (new MySQLSectorDAO(conn).sectorExists(o.getNom(), o.getEscola())) {
            throw new SQLException("El sector introduit ja existeix a la escola indicada");
        }

        pstmt.setInt(1, escolaId);
        pstmt.setString(2, o.getNom());
        pstmt.setString(3, o.getLatitud());
        pstmt.setString(4, o.getLongitud());

        if (o.getAproximacio().equals("")) {
            pstmt.setString(5, null); // Si aproximacio es buida, posar null
        } else {
            pstmt.setString(5, o.getAproximacio());
        }

        pstmt.setInt(6, o.getNum_vies()); // Num vies
        pstmt.setString(7, o.getPopularitat());

        if (o.getRestriccions().equals("")) {
            pstmt.setString(8, null); // Si restriccions es buida, posar null
        } else {
            pstmt.setString(8, o.getRestriccions());
        }
        pstmt.executeUpdate();
    }

    @Override
    public Sector read(Integer id) {
        String query = "SELECT e.nom AS escola, s.nom, s.latitud, s.longitud, s.aproximacio, s.num_vies, s.popularitat, s.restriccions " +
                            "FROM sectors s " +
                            "INNER JOIN escoles e ON e.escola_id = s.escola_id " +
                        "WHERE sector_id = ?";

        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Sector(rs.getString("escola"),
                        rs.getString("nom"),
                        rs.getString("latitud"),
                        rs.getString("longitud"),
                        rs.getString("aproximacio"),
                        rs.getInt("num_vies"),
                        rs.getString("popularitat"),
                        rs.getString("restriccions"));
            } else {
                return null; // Sector no trobat
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null; // Error en la consulta
        }
    }

    @Override
    public void update(Sector o) {

    }

    @Override
    public void delete(Integer id) {

    }

}
