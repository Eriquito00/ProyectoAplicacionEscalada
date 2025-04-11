package model.dao.MySQLDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLAncoratgeDAO {
    private final Connection conn;

    /**
     * Constructor de la classe MySQLAncoratgeDAO
     * @param conn Connexió a la base de dades
     */
    public MySQLAncoratgeDAO(Connection conn) {
        this.conn = conn;
    }

    // Metodes especificos per llegir la taula ancoratges

    /**
     * Comprovar si un ancoratge existeix a la base de dades
     * @param nom Nom de l'ancoratge
     * @return id de l'ancoratge amb aquell nom o -1 si no existeix
     */
    public int getAncoratgeIdByNom(String nom) {
        String query = "SELECT ancoratge_id FROM ancoratges WHERE nom = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, nom);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("ancoratge_id");
            } else {
                return -1; // Ancoratge no trobat
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1; // Error en la consulta
        }
    }
}
