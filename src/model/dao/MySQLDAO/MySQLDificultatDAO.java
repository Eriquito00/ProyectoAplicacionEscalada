package model.dao.MySQLDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLDificultatDAO {
    private final Connection conn;

    /**
     * Constructor de la classe MySQLDificultatDAO
     * @param conn Connexió a la base de dades
     */
    public MySQLDificultatDAO(Connection conn) {
        this.conn = conn;
    }

    // Metodes especificos per llegir la taula dificultats
    /**
     * Comprovar si una dificultat existeix a la base de dades
     * @param nom Nom de la dificultat
     * @return id de la dificultat amb aquell nom o -1 si no existeix
     */
    public int getDificultatIdByNom(String nom) throws SQLException{
        String query = "SELECT dificultat_id FROM dificultats WHERE grau = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, nom);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("dificultat_id");
        } else {
            return -1; // Dificultat no trobada
        }
    }

    /**
     * Obtenir el nom d'una dificultat a partir del seu id
     * @param idDificultat id de la dificultat
     * @return Nom de la dificultat o null si no existeix
     */
    public String getNomDificultatById(int idDificultat) {
        String query = "SELECT grau FROM dificultats WHERE dificultat_id = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, idDificultat);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("grau");
            } else {
                return null; // Dificultat no trobada
            }
        } catch (SQLException e) {
            return null; // Error en la consulta
        }
    }

}
