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
    public void create(Via o) {
    }
    @Override
    public Via read(Integer key) {
        return null;
    }
    @Override
    public void update(Via o) {

    }
    @Override
    public void delete(Integer key) {

    }
}
