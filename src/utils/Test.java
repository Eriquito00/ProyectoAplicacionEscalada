package utils;

import java.sql.*;

public class Test {
    public static void pruebaConexion(Connection c) throws SQLException {
        Statement s = c.createStatement();
        ResultSet rs = s.executeQuery("Select * from vies");
        ResultSetMetaData md = rs.getMetaData();

        String col = String.format("%-30s %-5s %-5s %-5s %-10s",
                md.getColumnName(7),
                md.getColumnName(8),
                md.getColumnName(9),
                md.getColumnName(10),
                md.getColumnName(11));
        System.out.println(col + "\n");
        while (rs.next()){
            String str = String.format("%-30s %-5s %-5s %-5s %-10s",
                    rs.getString(7),
                    rs.getString(8),
                    rs.getString(9),
                    rs.getString(10),
                    rs.getString(11));
            System.out.println(str + "\n");
        }
    }
}