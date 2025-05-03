package controller.classes.read;

import model.dao.MySQLDAO.*;

import java.sql.*;

public class mostrarTodo {
    public static String mostrarEscoles(Connection c) throws SQLException {
        MySQLEscolaDAO escolaDAO = new MySQLEscolaDAO(c);
        ResultSet rs = escolaDAO.readAll();
        ResultSetMetaData md = rs.getMetaData();

        String tabla = String.format("%-10s %-50s %-50s %-100s %-10s %-15s %-100s",
                md.getColumnLabel(1), md.getColumnLabel(2), md.getColumnLabel(3),
                md.getColumnLabel(4), md.getColumnLabel(5), md.getColumnLabel(6),
                md.getColumnLabel(7));

        while (rs.next()){
            tabla += "\n" +  String.format("%-10s %-50s %-50s %-100s %-10s %-15s %-100s",
                    rs.getString(1), rs.getString(2), rs.getString(3),
                    rs.getString(4), rs.getString(5), rs.getString(6),
                    rs.getString(7));
        }

        return tabla;
    }

    public static String mostrarVies(Connection c) throws SQLException {
        MySQLViaDAO viaDAO = new MySQLViaDAO(c);
        ResultSet rs = viaDAO.readAll();
        ResultSetMetaData md = rs.getMetaData();

        String tabla = String.format("%-10s %-50s %-50s %-50s %-50s %-50s %-10s %-50s %-10s %-10s %-10s %-15s %-15s",
                md.getColumnLabel(1), md.getColumnLabel(2), md.getColumnLabel(3),
                md.getColumnLabel(4), md.getColumnLabel(5), md.getColumnLabel(6),
                md.getColumnLabel(7), md.getColumnLabel(8), md.getColumnLabel(9),
                md.getColumnLabel(10), md.getColumnLabel(11), md.getColumnLabel(12),
                md.getColumnLabel(13));

        while (rs.next()){
            tabla += "\n" +  String.format("%-10s %-50s %-50s %-50s %-50s %-50s %-10s %-50s %-10s %-10s %-10s %-15s %-15s",
                    rs.getString(1), rs.getString(2), rs.getString(3),
                    rs.getString(4), rs.getString(5), rs.getString(6),
                    rs.getString(7), rs.getString(8), rs.getString(9),
                    rs.getString(10), rs.getString(11), rs.getString(12),
                    rs.getString(13));
        }

        return tabla;
    }

    public static String mostrarTramos(Connection c) throws SQLException{
        MySQLTramDAO tramDAO = new MySQLTramDAO(c);
        ResultSet rs = tramDAO.readTram();
        ResultSetMetaData md = rs.getMetaData();

        String tabla = String.format("%-15s %-50s %-15s %-20s %-20s",
                md.getColumnLabel(1), md.getColumnLabel(2), md.getColumnLabel(3),
                md.getColumnLabel(4), md.getColumnLabel(5));

        while (rs.next()){
            tabla += "\n" +  String.format("%-15s %-50s %-15s %-20s %-20s",
                    rs.getString(1), rs.getString(2), rs.getString(3),
                    rs.getString(4), rs.getString(5));
        }

        return tabla;
    }

    public static String mostrarSectores(Connection c) throws SQLException {
        MySQLSectorDAO sectorDAO = new MySQLSectorDAO(c);
        ResultSet rs = sectorDAO.readAll();
        ResultSetMetaData md = rs.getMetaData();

        String tabla = String.format("%-10s %-50s %-50s %-15s %-15s %-100s %-10s %-15s %-100s",
                md.getColumnLabel(1), md.getColumnLabel(2), md.getColumnLabel(3),
                md.getColumnLabel(4), md.getColumnLabel(5), md.getColumnLabel(6),
                md.getColumnLabel(7), md.getColumnLabel(8), md.getColumnLabel(9));

        while (rs.next()){
            tabla += "\n" +  String.format("%-10s %-50s %-50s %-15s %-15s %-100s %-10s %-15s %-100s",
                    rs.getString(1), rs.getString(2), rs.getString(3),
                    rs.getString(4), rs.getString(5), rs.getString(6),
                    rs.getString(7), rs.getString(8), rs.getString(9));
        }

        return tabla;
    }

    public static String mostrarEscaladores(Connection c) throws SQLException {
        MySQLEscaladorDAO escaladorDAO = new MySQLEscaladorDAO(c);
        ResultSet rs = escaladorDAO.readAll();
        ResultSetMetaData md = rs.getMetaData();

        String tabla = String.format("%-15s %-50s %-50s %-10s %-10s %-50s %-15s %-100s",
                md.getColumnLabel(1), md.getColumnLabel(2), md.getColumnLabel(3),
                md.getColumnLabel(4), md.getColumnLabel(5), md.getColumnLabel(6),
                md.getColumnLabel(7), md.getColumnLabel(8));

        while (rs.next()){
            tabla += "\n" +  String.format("%-15s %-50s %-50s %-10s %-10s %-50s %-15s %-100s",
                    rs.getString(1), rs.getString(2), rs.getString(3),
                    rs.getString(4), rs.getString(5), rs.getString(6),
                    rs.getString(7), rs.getString(8));
        }

        return tabla;
    }
}