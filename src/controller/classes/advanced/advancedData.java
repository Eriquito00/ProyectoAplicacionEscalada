package controller.classes.advanced;

import controller.Main;
import model.dao.MySQLDAO.MySQLViaDAO;
import view.View;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class advancedData {
    public static String escolesDisponibles (Connection c) throws SQLException {
        MySQLViaDAO viaDAO = new MySQLViaDAO(c);
        String escola = demanaString(Main.scan,50, "Introduce el nombre de la escuela que quieres saber que vias estan activas.");
        ResultSet rs = viaDAO.viesEscola(escola);
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
        String dificultad = demanaString(Main.scan,3, "Introduce la dificultad de la que quieres obtener las vias.");
        ResultSet rs = viaDAO.viesDificultat(dificultad);
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

    private static String demanaString(Scanner s, int llargada, String ... msg){
        for (String str: msg) View.mostrarMsg(str);
        String str = s.nextLine();
        if (str.length() > llargada) throw new InputMismatchException("El maximo de caracteres permitidos son " + llargada + ".");
        return str;
    }
}