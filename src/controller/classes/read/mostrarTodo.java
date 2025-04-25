package controller.classes.read;

import model.dao.MySQLDAO.MySQLEscaladorDAO;
import model.dao.MySQLDAO.MySQLEscolaDAO;
import model.dao.MySQLDAO.MySQLSectorDAO;
import model.dao.MySQLDAO.MySQLViaDAO;

import java.sql.*;

public class mostrarTodo {
    public static String mostrarEscoles(Connection c) throws SQLException {
        MySQLEscolaDAO escolaDAO = new MySQLEscolaDAO(c);
        ResultSet rs = escolaDAO.readAll();
        ResultSetMetaData md = rs.getMetaData();

        String tabla = String.format("%-10s %-50s %-50s %-100s %-10s %-15s %-100s",
                md.getColumnName(1),
                md.getColumnName(2),
                md.getColumnName(3),
                md.getColumnName(4),
                md.getColumnName(5),
                md.getColumnName(6),
                md.getColumnName(7));

        while (rs.next()){
            tabla += "\n" +  String.format("%-10s %-50s %-50s %-100s %-10s %-15s %-100s",
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

    public static String mostrarVies(Connection c) throws SQLException {
        MySQLViaDAO viaDAO = new MySQLViaDAO(c);
        ResultSet rs = viaDAO.readAll();
        ResultSetMetaData md = rs.getMetaData();

        String tabla = String.format("%-10s %-50s %-50s %-50s %-50s %-10s %-50s %-10s %-10s %-10s %-10s",
                md.getColumnName(1),
                md.getColumnName(2),
                md.getColumnName(3),
                md.getColumnName(4),
                md.getColumnName(5),
                md.getColumnName(6),
                md.getColumnName(7),
                md.getColumnName(8),
                md.getColumnName(9),
                md.getColumnName(10),
                md.getColumnName(11));

        while (rs.next()){
            tabla += "\n" +  String.format("%-10s %-50s %-50s %-50s %-50s %-10s %-50s %-10s %-10s %-10s %-10s",
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7),
                    rs.getString(8),
                    rs.getString(9),
                    rs.getString(10),
                    rs.getString(11));
        }

        return tabla;
    }

    public static String mostrarSectores(Connection c) throws SQLException {
        MySQLSectorDAO sectorDAO = new MySQLSectorDAO(c);
        ResultSet rs = sectorDAO.readAll();
        ResultSetMetaData md = rs.getMetaData();

        String tabla = String.format("%-10s %-50s %-50s %-15s %-15s %-100s %-10s %-10s %-100s",
                md.getColumnName(1),
                md.getColumnName(2),
                md.getColumnName(3),
                md.getColumnName(4),
                md.getColumnName(5),
                md.getColumnName(6),
                md.getColumnName(7),
                md.getColumnName(8),
                md.getColumnName(9));

        while (rs.next()){
            tabla += "\n" +  String.format("%-10s %-50s %-50s %-15s %-15s %-100s %-10s %-10s %-100s",
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7),
                    rs.getString(8),
                    rs.getString(9));
        }

        return tabla;
    }

    public static String mostrarEscaladores(Connection c) throws SQLException {
        MySQLEscaladorDAO escaladorDAO = new MySQLEscaladorDAO(c);
        ResultSet rs = escaladorDAO.readAll();
        ResultSetMetaData md = rs.getMetaData();

        String tabla = String.format("%-15s %-50s %-50s %-10s %-10s %-50s %-15s %-100s",
                md.getColumnName(1),
                md.getColumnName(2),
                md.getColumnName(3),
                md.getColumnName(4),
                md.getColumnName(5),
                md.getColumnName(6),
                md.getColumnName(7),
                md.getColumnName(8));

        while (rs.next()){
            tabla += "\n" +  String.format("%-15s %-50s %-50s %-10s %-10s %-50s %-15s %-100s",
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