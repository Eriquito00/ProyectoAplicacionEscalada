package model.dao.MySQLDAO;

import model.classes.Escalador;
import model.dao.interfaces.EscaladorDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLEscaladorDAO implements EscaladorDAO {
    private final Connection conn;

    /**
     * Constructor de la classe MySQLEscaladorDAO
     * @param conn Connexió a la base de dades
     */
    public MySQLEscaladorDAO(Connection conn) {
        this.conn = conn;
    }

    // Metodes especificos per llegir la taula escaladors
    /**
     * Comprovar si un escalador existeix a la base de dades
     * @param nom Nom de l'escalador
     * @return id de l'escalador amb aquell nom o -1 si no existeix
     */
    public int getEscaladorIdByNom(String nom) {
        String query = "SELECT escalador_id FROM escaladors WHERE nom = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, nom);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("escalador_id");
            } else {
                return -1; // Escalador no trobat
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1; // Error en la consulta
        }
    }

    @Override
    public void create(Escalador o) throws SQLException {

    }

    @Override
    public Escalador read(Integer key) throws SQLException {
        return null;
    }

    @Override
    public void update(Escalador o) throws SQLException {

    }

    @Override
    public void delete(Integer key) throws SQLException {

    }
}
