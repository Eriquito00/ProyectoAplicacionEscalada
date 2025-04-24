package controller.classes.read;

import java.sql.*;

public class mostrarTodo {
    // AUN FALTAN  DE HACER TODOS PORQUE FALTA LA FUNCION DE MYSQLDAO PARA OBTENER TODOS LOS DATOS
    public static String mostrarEscoles(Connection c) throws SQLException {
        // OBTENGO EL RS DE LA FUNCION DEL DAVID
        ResultSet rs = null;
        ResultSetMetaData md = rs.getMetaData();

        String tabla = String.format("%-15s %-15s %-60s %-110s %-15s %-20s %-110s",
                md.getColumnName(1),
                md.getColumnName(2),
                md.getColumnName(3),
                md.getColumnName(4),
                md.getColumnName(5),
                md.getColumnName(6),
                md.getColumnName(7));

        while (rs.next()){
            tabla += "\n" +  String.format("%-15s %-15s %-60s %-110s %-15s %-20s %-110s",
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
        // OBTENGO EL RS DE LA FUNCION DEL DAVID
        ResultSet rs = null;
        ResultSetMetaData md = rs.getMetaData();

        String tabla = String.format("%-15s %-15s %-15s %-15s %-15s %-15s %-15s %-60s %-15s %-15s %-15s %-15s",
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
                md.getColumnName(11),
                md.getColumnName(12));

        while (rs.next()){
            tabla += "\n" +  String.format("%-15s %-15s %-15s %-15s %-15s %-15s %-15s %-60s %-15s %-15s %-15s %-15s",
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
                    rs.getString(11),
                    rs.getString(12));
        }

        return tabla;
    }

    public static String mostrarSectores(Connection c) throws SQLException {
        // OBTENGO EL RS DE LA FUNCION DEL DAVID
        ResultSet rs = null;
        ResultSetMetaData md = rs.getMetaData();

        String tabla = String.format("%-15s %-15s %-60s %-25s %-25s %-110s %-15s %-15s %-110s",
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
            tabla += "\n" +  String.format("%-15s %-15s %-60s %-25s %-25s %-110s %-15s %-15s %-110s",
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
        // OBTENGO EL RS DE LA FUNCION DEL DAVID
        ResultSet rs = null;
        ResultSetMetaData md = rs.getMetaData();

        String tabla = String.format("%-15s %-60s %-60s %-15s %-10s %-60s %-60s %-110s",
                md.getColumnName(1),
                md.getColumnName(2),
                md.getColumnName(3),
                md.getColumnName(4),
                md.getColumnName(5),
                md.getColumnName(6),
                md.getColumnName(7),
                md.getColumnName(8));

        while (rs.next()){
            tabla += "\n" +  String.format("%-15s %-60s %-60s %-15s %-10s %-60s %-60s %-110s",
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