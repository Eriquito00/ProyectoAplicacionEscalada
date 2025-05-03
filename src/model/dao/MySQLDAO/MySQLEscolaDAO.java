package model.dao.MySQLDAO;

import model.classes.Escola;
import model.dao.interfaces.EscolaDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLEscolaDAO implements EscolaDAO {

    private final Connection conn;

    public MySQLEscolaDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * Comprovar si una escola existeix a la base de dades
     * @param nom Nom de l'escola
     * @return true si existeix, false si no
     * @throws SQLException Error en la consulta
     */
    public boolean existeEscola(String nom) {
        String query = "SELECT COUNT(escola_id) AS num_escoles " +
                       "FROM escoles " +
                       "WHERE nom = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, nom);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("num_escoles") > 0;
            } else {
                return false; // Escola no trobada
            }
        } catch (SQLException e) {
            return false; // Error en la consulta
        }
    }

    /**
     * Obtenir l'ID d'una escola a partir del seu nom
     * @param nom Nom de l'escola
     * @return ID de l'escola o -1 si no existeix
     * @throws SQLException Error en la consulta
     */
    public int getEscolaIdByNom(String nom) {
        String query = "SELECT escola_id FROM escoles WHERE nom = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, nom);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("escola_id");
            } else {
                return -1; // Escola no trobada
            }
        } catch (SQLException e) {
            return -1; // Error en la consulta
        }
    }

    public ResultSet escolaRestriccions() throws SQLException{
        String query = "SELECT e.nom, p.nom AS poblacio, e.aproximacio, e.num_vies, e.popularitat, e.restriccions" +
                " FROM escoles e" +
                " INNER JOIN poblacions p ON p.poblacio_id = e.poblacio_id" +
                " WHERE e.restriccions RLIKE \".*\"";
        PreparedStatement pstmt = conn.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        if (rs.isBeforeFirst()) return rs;
        else throw new SQLException("No hay escuelas con restricciones.");
    }

    // CRUD

    /**
     * Crear una nova escola a la base de dades
     * @param escola Escola a crear
     * @throws SQLException Error en la consulta
     */
    public void create(Escola escola) throws SQLException {

        // Comprovar que la connexió no és null
        if (conn == null) {
            throw new SQLException("La connexió a la base de dades és null");
        }

        int poblacioId = new MySQLPoblacionsDAO(conn).getIdPoblacioByNom(escola.getPoblacio());
        if (poblacioId == -1) throw new SQLException("La població no existeix a la base de dades");

        String query = "INSERT INTO escoles (poblacio_id, nom, aproximacio, num_vies, popularitat, restriccions) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setInt(1, poblacioId);
        pstmt.setString(2, escola.getNom());

        if (escola.getAproximacio().equals("")) {
            pstmt.setString(3, null); // Aproximacio no es obligatori
        } else {
            pstmt.setString(3, escola.getAproximacio());
        }

        pstmt.setInt(4, escola.getNum_vies());
        pstmt.setString(5, escola.getPopularitat());

        if (escola.getRestriccions().equals("")) {
            pstmt.setString(6, null); // Restriccions no es obligatori
        } else {
            pstmt.setString(6, escola.getRestriccions());
        }

        pstmt.executeUpdate();
    }

    public Escola read(Integer id) throws SQLException {
        String query = "SELECT p.nom AS poblacio, e.nom, e.aproximacio, e.num_vies, e.popularitat, e.restriccions " +
                        "FROM escoles e " +
                        "INNER JOIN poblacions p ON p.poblacio_id = e.poblacio_id " +
                        "WHERE escola_id = ?";

        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return new Escola(
                    rs.getString("poblacio"),
                    rs.getString("nom"),
                    rs.getString("aproximacio"),
                    rs.getInt("num_vies"),
                    rs.getString("popularitat"),
                    rs.getString("restriccions")
            );
        } else {
            throw new SQLException("Escola no trobada"); // Escola no trobada
        }
    }

    public ResultSet readAll() throws SQLException {
        String query = "SELECT e.escola_id, e.nom, p.nom AS poblacio, e.aproximacio, e.num_vies, e.popularitat, e.restriccions " +
                        "FROM escoles e " +
                        "LEFT JOIN poblacions p ON p.poblacio_id = e.poblacio_id";
        PreparedStatement pstmt = conn.prepareStatement(query);
        return pstmt.executeQuery();
    }

    public void update(Escola escola) throws SQLException {
        String queryentorn = "SELECT @cuantas := COUNT(via_id)" +
                " FROM vies v" +
                " INNER JOIN sectors s ON s.sector_id = v.sector_id" +
                " INNER JOIN escoles e ON s.escola_id = e.escola_id" +
                " WHERE e.escola_id = ?";
        PreparedStatement pstmtentorn = conn.prepareStatement(queryentorn);
        pstmtentorn.setInt(1,escola.getId());
        pstmtentorn.executeQuery();

        String query = "UPDATE escoles " +
                        " SET poblacio_id = ?, nom = ?, aproximacio = ?, " +
                        " num_vies = @cuantas, " +
                        " popularitat = ?, restriccions = ? WHERE escola_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            if (new MySQLPoblacionsDAO(conn).getIdPoblacioByNom(escola.getPoblacio()) == -1) {
                throw new SQLException("La població no existeix a la base de dades");
            }
            pstmt.setInt(1, new MySQLPoblacionsDAO(conn).getIdPoblacioByNom(escola.getPoblacio()));
            pstmt.setString(2, escola.getNom());
            pstmt.setString(3, escola.getAproximacio());
            pstmt.setString(4, escola.getPopularitat());
            pstmt.setString(5, escola.getRestriccions());
            pstmt.setInt(6, escola.getId());
            pstmt.executeUpdate();
    }

    public void delete(Integer id) throws SQLException {
        String query = "DELETE FROM escoles WHERE escola_id = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setInt(1,id);
        pstmt.executeUpdate();
    }
}