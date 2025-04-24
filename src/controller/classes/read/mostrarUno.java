package controller.classes.read;

import controller.Main;
import model.dao.MySQLDAO.*;

import java.sql.*;

public class mostrarUno {
    public static void mostrarEscoles(Connection c) throws SQLException {
        MySQLEscolaDAO escolaDAO = new MySQLEscolaDAO(c);
        int id = Main.aplicaOpcio(Main.scan,1,2000000);
        escolaDAO.read(id);
    }

    public static void mostrarVies(Connection c) throws SQLException {
        MySQLViaDAO viaDAO = new MySQLViaDAO(c);
        int id = Main.aplicaOpcio(Main.scan,1,2000000);
        viaDAO.read(id);
    }

    public static void mostrarSectores(Connection c) throws SQLException {
        MySQLSectorDAO sectorDAO = new MySQLSectorDAO(c);
        int id = Main.aplicaOpcio(Main.scan,1,2000000);
        sectorDAO.read(id);
    }

    public static void mostrarEscaladores(Connection c) throws SQLException {
        MySQLEscaladorDAO escaladorDAO = new MySQLEscaladorDAO(c);
        int id = Main.aplicaOpcio(Main.scan,1,2000000);
        escaladorDAO.read(id);
    }
}