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
            return -1; // Error en la consulta
        }
    }

    /**
     * Comprovar si un escalador existeix a la base de dades
     * @param alies Alies de l'escalador
     * @return true si existeix, false si no existeix
     */
    public boolean existeEscalador(String alies) {
        String query = "SELECT escalador_id FROM escaladors WHERE alies = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, alies);
            ResultSet rs = pstmt.executeQuery();
            return rs.next(); // Si hi ha resultats, l'escalador existeix
        } catch (SQLException e) {
            return false; // Error en la consulta
        }
    }

    public ResultSet escaladorDificultat(String dif) throws SQLException{
        MySQLDificultatDAO dificultatDAO = new MySQLDificultatDAO(conn);
        if (dificultatDAO.getDificultatIdByNom(dif) == -1) throw new SQLException("La dificultad introducida no existe.");
        String query = "SELECT e.nom, e.alies, e.edat, e.nivell_max, e.nom_via_max, e.tipus_fav, e.fita" +
                " FROM escaladors e" +
                " WHERE e.nivell_max = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1,dif);
        ResultSet rs = pstmt.executeQuery();
        if (rs.isBeforeFirst()) return rs;
        else throw new SQLException("La dificultad introducida no tiene ninguna via.");
    }

    // CRUD

    @Override
    public void create(Escalador o) throws SQLException {
        // Comprovar que la connexió no és null
        if (conn == null) {
            throw new SQLException("La connexió a la base de dades és null");
        }

        String query = "INSERT INTO escaladors (nom, alies, edat, nivell_max, nom_via_max, tipus_fav, fita) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, o.getNom());
        if(new MySQLEscaladorDAO(conn).existeEscalador(o.getAlies())){
            throw new SQLException("L'escalador amb aquest alies ja existeix a la base de dades");
        }
        pstmt.setString(2, o.getAlies());
        pstmt.setInt(3, o.getEdad());
        String dificultat = new MySQLViaDAO(conn).getDificultatByNom(o.getNombre_via_max(), o.getEscola_via_max());
        if (dificultat == null) {
            throw new SQLException("La via no existeix a la base de dades");
        }
        pstmt.setString(4, dificultat);
        pstmt.setString(5, o.getNombre_via_max());
        pstmt.setString(6, o.getTipo_favorito());
        pstmt.setString(7, o.getFita());
        pstmt.executeUpdate();
    }

    @Override
    public Escalador read(Integer key) throws SQLException {
        String query = "SELECT e.nom, e.alies, e.edat, e.nivell_max, e.nom_via_max, e.tipus_fav, e.fita " +
                       "FROM escaladors e " +
                       "WHERE e.escalador_id = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setInt(1, key);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return new Escalador(
                    rs.getString("nom"),
                    rs.getString("alies"),
                    rs.getInt("edat"),
                    rs.getString("nivell_max"),
                    rs.getString("nom_via_max"),
                    rs.getString("tipus_fav"),
                    rs.getString("fita"),
                    null // Escola no disponible a la taula escaladors
            );
        }
        else {
            throw new SQLException("Escalador no trobat"); // Escalador no trobat
        }
    }

    public ResultSet readAll() throws SQLException {
        String query = "SELECT * FROM escaladors";
        PreparedStatement pstmt = conn.prepareStatement(query);
        return pstmt.executeQuery();
    }

    @Override
    public void update(Escalador o) throws SQLException {
        String query = "UPDATE escaladors SET nom = ?, alies = ?, edat = ?, nivell_max = ?, nom_via_max = ?, tipus_fav = ?, fita = ? WHERE escalador_id = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, o.getNom());
        pstmt.setString(2, o.getAlies());
        pstmt.setInt(3, o.getEdad());
        String dificultat = new MySQLViaDAO(conn).getDificultatByNom(o.getNombre_via_max(), o.getEscola_via_max());
        if (dificultat == null) throw new SQLException("La via del escalador y no pertenece a la escuela introducida.");
        pstmt.setString(4, dificultat);
        pstmt.setString(5, o.getNombre_via_max());
        pstmt.setString(6, o.getTipo_favorito());
        pstmt.setString(7, o.getFita());
        pstmt.setInt(8, o.getId());
        pstmt.executeUpdate();
    }

    @Override
    public void delete(Integer id) throws SQLException {
        String query = "DELETE FROM escaladors WHERE escalador_id = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setInt(1,id);
        pstmt.executeUpdate();
    }
}
