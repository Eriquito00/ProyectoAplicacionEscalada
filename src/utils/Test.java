package utils;

import java.sql.*;

public class Test {
    public static void pruebaConexion(Connection c) throws SQLException {
        Statement s = c.createStatement();
        ResultSet rs = s.executeQuery("Select * from escoles");
        ResultSetMetaData md = rs.getMetaData();

        String col = String.format("%-30s %-5s %-5s %-5s %-10s",
                md.getColumnName(3),
                md.getColumnName(4),
                md.getColumnName(5),
                md.getColumnName(6),
                md.getColumnName(7));
        System.out.println(col + "\n");
        while (rs.next()){
            String str = String.format("%-30s %-5s %-5s %-5s %-10s",
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7));
            System.out.println(str + "\n");
        }
    }
}