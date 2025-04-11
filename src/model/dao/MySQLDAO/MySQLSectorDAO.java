package model.dao.MySQLDAO;

import model.classes.Sector;
import model.dao.interfaces.SectorDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    public void create(Sector o) {

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
