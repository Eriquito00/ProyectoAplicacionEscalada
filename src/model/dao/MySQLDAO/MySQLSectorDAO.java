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
        // TODO: Comprovar si el nombre del sector existe en la escuela indicada
        pstmt.setInt(1, escolaId);
        pstmt.setString(2, o.getNom());

        if (getSectorDispo(escolaId, o.getNom())) throw new SQLException("El sector introducido ya existe en esta escuela.");

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

    public boolean getSectorDispo(int id, String nom) throws SQLException{
        String query = "SELECT sector_id FROM sectors WHERE escola_id = " + id + " AND nom = " + nom;
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        return rs.getString(1).equals("null");
        //NO SE SI DEVUELVE NULL O SI NO ENCUENTRA
    }

    @Override
    public Sector read(Integer id) {
        return null;
    }

    @Override
    public void update(Sector o) {

    }

    @Override
    public void delete(Integer id) {

    }

}
