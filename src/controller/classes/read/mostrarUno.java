package controller.classes.read;

import controller.Main;
import model.classes.*;
import model.dao.MySQLDAO.*;
import view.View;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class mostrarUno {
    public static Escola mostrarEscoles(Connection c) throws SQLException {
        MySQLEscolaDAO escolaDAO = new MySQLEscolaDAO(c);
        int id = demanaInt("Introduce la ID de la escuela que quieres consultar.",Main.scan,1,2000000);
        return escolaDAO.read(id);
    }

    public static Via mostrarVies(Connection c) throws SQLException {
        MySQLViaDAO viaDAO = new MySQLViaDAO(c);
        int id = demanaInt("Introduce la ID de la via que quieres consultar.",Main.scan,1,2000000);
        return viaDAO.read(id);
    }

    public static Sector mostrarSectores(Connection c) throws SQLException {
        MySQLSectorDAO sectorDAO = new MySQLSectorDAO(c);
        int id = demanaInt("Introduce la ID del sector que quieres consultar.",Main.scan,1,2000000);
        return sectorDAO.read(id);
    }

    public static Escalador mostrarEscaladores(Connection c) throws SQLException {
        MySQLEscaladorDAO escaladorDAO = new MySQLEscaladorDAO(c);
        int id = demanaInt("Introduce la ID del escalador que quieres consultar.",Main.scan,1,2000000);
        return escaladorDAO.read(id);
    }

    private static int demanaInt(String msg, Scanner s, int min, int max){
        View.mostrarMsg(msg);
        int n = Integer.parseInt(s.nextLine());
        if (n < min || n > max) throw new InputMismatchException("El valor introducido no existe.");
        return n;
    }
}