package model.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLConnection {
    public static Statement mysqlConnection(){
        String url = "jdbc:mysql://localhost:3306/escaladadb", user = "permatrago", passw = "bloste_escalada";

        Connection con = null;
        Statement stmt = null;

        try {
            con = DriverManager.getConnection(url,user,passw);
            stmt = con.createStatement();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return stmt;
    }
}