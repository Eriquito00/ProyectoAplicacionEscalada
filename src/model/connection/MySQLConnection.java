package model.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
    public static Connection mysqlConnection() throws SQLException{
        String url = "jdbc:mysql://localhost:3306/escaladadb", user = "permatrago", passw = "bloste_escalada";
        return DriverManager.getConnection(url,user,passw);
    }
}