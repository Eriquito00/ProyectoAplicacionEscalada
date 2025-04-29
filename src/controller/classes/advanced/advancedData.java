package controller.classes.advanced;

import controller.Main;
import model.dao.MySQLDAO.MySQLEscaladorDAO;
import model.dao.MySQLDAO.MySQLEscolaDAO;
import model.dao.MySQLDAO.MySQLSectorDAO;
import model.dao.MySQLDAO.MySQLViaDAO;
import static controller.functions.DemanaDades.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class advancedData {
    public static String escolesDisponibles (Connection c) throws SQLException {
        MySQLViaDAO viaDAO = new MySQLViaDAO(c);
        String escola = demanaString(Main.scan,50, "Introduce el nombre de la escuela que quieres saber que vias estan activas.");
        ResultSet rs = viaDAO.viesEscola(escola.trim().toLowerCase());
        ResultSetMetaData rsmd = rs.getMetaData();

        String tabla = String.format("%-50s %-10s %-10s %-50s %-10s %-50s %-50s",
                rsmd.getColumnLabel(1),
                rsmd.getColumnLabel(2),
                rsmd.getColumnLabel(3),
                rsmd.getColumnLabel(4),
                rsmd.getColumnLabel(5),
                rsmd.getColumnLabel(6),
                rsmd.getColumnLabel(7));

        while (rs.next()){
            tabla += "\n" + String.format("%-50s %-10s %-10s %-50s %-10s %-50s %-50s",
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7));
        }

        return tabla;
    }

    public static String viesDificultat (Connection c) throws SQLException {
        MySQLViaDAO viaDAO = new MySQLViaDAO(c);
        String dificultad = demanaString(Main.scan,3, "Introduce la dificultad de la via que quieres obtener las vias.");
        ResultSet rs = viaDAO.viesDificultat(dificultad.trim().toLowerCase());
        ResultSetMetaData rsmd = rs.getMetaData();

        String tabla = String.format("%-50s %-10s %-50s %-50s",
                rsmd.getColumnLabel(1),
                rsmd.getColumnLabel(2),
                rsmd.getColumnLabel(3),
                rsmd.getColumnLabel(4));

        while (rs.next()){
            tabla += "\n" + String.format("%-50s %-10s %-50s %-50s",
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4));
        }

        return tabla;
    }

    public static String viesEstat (Connection c) throws SQLException {
        MySQLViaDAO viaDAO = new MySQLViaDAO(c);
        String estat = demanaString(Main.scan,15, "Introduce el estado de la via que quieres obtener las vias.");
        ResultSet rs = viaDAO.viesEstat(estat.trim().toLowerCase());
        ResultSetMetaData rsmd = rs.getMetaData();

        String tabla = String.format("%-50s %-15s %-15s %-50s %-15s %-50s %-50s",
                rsmd.getColumnLabel(1),
                rsmd.getColumnLabel(2),
                rsmd.getColumnLabel(3),
                rsmd.getColumnLabel(4),
                rsmd.getColumnLabel(5),
                rsmd.getColumnLabel(6),
                rsmd.getColumnLabel(7));

        while (rs.next()){
            tabla += "\n" + String.format("%-50s %-15s %-15s %-50s %-15s %-50s %-50s",
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7));
        }

        return tabla;
    }

    public static String escolesRestriccions (Connection c) throws SQLException {
        MySQLEscolaDAO escolaDAO = new MySQLEscolaDAO(c);
        ResultSet rs = escolaDAO.escolaRestriccions();
        ResultSetMetaData rsmd = rs.getMetaData();

        String tabla = String.format("%-50s %-50s %-100s %-10s %-15s %-100s",
                rsmd.getColumnLabel(1),
                rsmd.getColumnLabel(2),
                rsmd.getColumnLabel(3),
                rsmd.getColumnLabel(4),
                rsmd.getColumnLabel(5),
                rsmd.getColumnLabel(6));

        while (rs.next()){
            tabla += "\n" + String.format("%-50s %-50s %-100s %-10s %-15s %-100s",
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6));
        }

        return tabla;
    }

    public static String sectorsNumVies (Connection c) throws SQLException {
        MySQLSectorDAO sectorDAO = new MySQLSectorDAO(c);
        int num_vies = demanaInt("Introduce el numero de vias minimo que tiene que tener el sector.",Main.scan,1,50);
        ResultSet rs = sectorDAO.sectorNumVies(num_vies);
        ResultSetMetaData rsmd = rs.getMetaData();

        String tabla = String.format("%-50s %-50s %-25s %-25s %-100s %-10s %-15s %-100s",
                rsmd.getColumnLabel(1),
                rsmd.getColumnLabel(2),
                rsmd.getColumnLabel(3),
                rsmd.getColumnLabel(4),
                rsmd.getColumnLabel(5),
                rsmd.getColumnLabel(6),
                rsmd.getColumnLabel(7),
                rsmd.getColumnLabel(8));

        while (rs.next()){
            tabla += "\n" + String.format("%-50s %-50s %-25s %-25s %-100s %-10s %-15s %-100s",
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7),
                    rs.getString(8));
        }

        return tabla;
    }

    public static String escaladorsDificultat (Connection c) throws SQLException {
        MySQLEscaladorDAO escaladorDAO = new MySQLEscaladorDAO(c);
        String dificultad = demanaString(Main.scan,3, "Introduce la dificultad de la via que quieres obtener los escaladores.");
        ResultSet rs = escaladorDAO.escaladorDificultat(dificultad.trim().toLowerCase());
        ResultSetMetaData rsmd = rs.getMetaData();

        String tabla = String.format("%-50s %-50s %-10s %-15s %-50s %-15s %-100s",
                rsmd.getColumnLabel(1),
                rsmd.getColumnLabel(2),
                rsmd.getColumnLabel(3),
                rsmd.getColumnLabel(4),
                rsmd.getColumnLabel(5),
                rsmd.getColumnLabel(6),
                rsmd.getColumnLabel(7));

        while (rs.next()){
            tabla += "\n" + String.format("%-50s %-50s %-10s %-15s %-50s %-15s %-100s",
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7));
        }

        return tabla;
    }

    public static String viesAptesRecents (Connection c) throws SQLException {
        MySQLViaDAO viaDAO = new MySQLViaDAO(c);
        ResultSet rs = viaDAO.viesAptesRecents();
        ResultSetMetaData rsmd = rs.getMetaData();

        String tabla = String.format("%-50s %-10s %-10s %-10s %-15s %-20s",
                rsmd.getColumnLabel(1),
                rsmd.getColumnLabel(2),
                rsmd.getColumnLabel(3),
                rsmd.getColumnLabel(4),
                rsmd.getColumnLabel(5),
                rsmd.getColumnLabel(6));

        while (rs.next()){
            tabla += "\n" + String.format("%-50s %-10s %-10s %-10s %-15s %-20s",
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6));
        }

        return tabla;
    }

    public static String viesescolaLlargues (Connection c) throws SQLException {
        MySQLViaDAO viaDAO = new MySQLViaDAO(c);
        String escola = demanaString(Main.scan,50,"Introduce el nombre de la escuela que quieres saber las vias mas largas.");
        ResultSet rs = viaDAO.viesescolaLlargues(escola);
        ResultSetMetaData rsmd = rs.getMetaData();

        String tabla = String.format("%-50s %-10s %-15s %-10s %-50s %-10s %-50s %-50s",
                rsmd.getColumnLabel(1),
                rsmd.getColumnLabel(2),
                rsmd.getColumnLabel(3),
                rsmd.getColumnLabel(4),
                rsmd.getColumnLabel(5),
                rsmd.getColumnLabel(6),
                rsmd.getColumnLabel(7),
                rsmd.getColumnLabel(8));

        while (rs.next()){
            tabla += "\n" + String.format("%-50s %-10s %-15s %-10s %-50s %-10s %-50s %-50s",
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7),
                    rs.getString(8));
        }

        return tabla;
    }
}