package controller.classes.create;

import model.classes.Sector;
import model.dao.MySQLDAO.MySQLSectorDAO;
import static controller.functions.DemanaDades.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class creaSector {
    public static void creaSector(Scanner s, Connection c) throws SQLException {
        MySQLSectorDAO sec = new MySQLSectorDAO(c);
        String escola = demanaString(s,50,"Introduce el nombre de la escuela de la via.");
        String nom = demanaString(s,50,"Introduce el nombre del sector.");
        String latitud = demanaString(s, 20,"Introduce la latitud del sector.", "Por ejemplo: '90º40'20\"N o S'.");
        if (!comprobaLatitud(latitud)) throw new InputMismatchException("El formato de la latitud no es correcto.");
        String longitud = demanaString(s, 20,"Introduce la longitud del sector.", "Por ejemplo: '180º40'20\"E o O'.");
        if (!comprobaLongitud(longitud)) throw new InputMismatchException("El formato de la longitud no es correcto.");
        String aproximacio = demanaString(s, 100,"Introduce la aproximacion del sector.");
        int num_vies = 0;
        String popularitat = demanaString(s,50,"Introduce la popularidad del sector.");
        if (!comprobaPopularitat(popularitat)) throw new InputMismatchException("El valor de popularitat introduit no es valid.");
        String restriccions = demanaString(s, 100,"Introduce la restriccio del sector.");
        sec.create(new Sector(escola,nom,latitud,longitud,aproximacio,popularitat,restriccions));
    }
}