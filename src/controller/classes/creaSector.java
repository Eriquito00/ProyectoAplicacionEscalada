package controller.classes;

import model.classes.Sector;
import model.dao.MySQLDAO.MySQLSectorDAO;
import view.View;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class creaSector {
    public static void creaSector(Scanner s, Connection c) throws SQLException {
        MySQLSectorDAO sec = new MySQLSectorDAO(c);
        String escola = demanaString(s,"Introduce el nombre de la escuela de la via.");
        //Comprovar que la escuela existe
        String nom = demanaString(s,"Introduce el nombre del sector.");
        //Comprobar que el nombre del sector no existe
        String latitud = demanaString(s, "Introduce la latitud del sector,");
        if (!comprobaLatitud(latitud)) throw new InputMismatchException("El formato de la latitud no es correcto.");
        String longitud = demanaString(s, "Introduce la longitud del sector.");
        if (!comprobaLongitud(longitud)) throw new InputMismatchException("El formato de la longitud no es correcto.");
        String aproximacio = demanaString(s, "Introduce la aproximacion del sector.");
        int num_vies = 0;
        String popularitat = demanaString(s,"Introduce la popularidad del sector.");
        if (!comprobaPopularitat(popularitat)) throw new InputMismatchException("El valor de popularitat introduit no es valid.");
        String restriccions = demanaString(s, "Introduce la restriccio del sector.");
        sec.create(new Sector(escola,nom,latitud,longitud,aproximacio,popularitat,restriccions));
    }

    public static boolean comprobaLatitud(String latitud){
        return latitud.matches("^(90|[1-8]?[0-9])º([0-5]?[0-9])\'([0-5]?[0-9])\"[NS]$");
    }

    public static boolean comprobaLongitud(String longitud){
        return longitud.matches("^(180|[1-9]?[0-9])º([0-5]?[0-9])\'([0-5]?[0-9])\"[OE]$");
    }

    public static boolean comprobaPopularitat(String pop){
        return (pop.toLowerCase().trim().equals("baixa")
                || pop.toLowerCase().trim().equals("mitjana")
                || pop.toLowerCase().trim().equals("alta"));
    }

    private static String demanaString(Scanner s, String ... msg){
        for (String str: msg) View.mostrarMsg(str);
        return s.nextLine();
    }

    private static int demanaInt(String msg, Scanner s, int min, int max){
        View.mostrarMsg(msg);
        int n = Integer.parseInt(s.nextLine());
        if (n > min || n < max) throw new InputMismatchException("El valor introducido esta fuera de las opciones.");
        return n;
    }
}