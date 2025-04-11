package controller.classes;

import model.classes.Escola;
import model.dao.MySQLDAO.MySQLEscolaDAO;
import view.View;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

public class creaEscola {
    public static void creaEscola (Scanner s, Connection c) throws SQLException,InputMismatchException {
        MySQLEscolaDAO e = new MySQLEscolaDAO(c);
        String poblacio = demanaString(s,"Introduce la poblacion donde se encuentra la escuela.");
        String nom = demanaString(s,"Introduce el nombre de la escuela.");
        if (e.existeEscola(nom)) throw new InputMismatchException("Esta escuela ya existe.");
        String aproximacio = demanaString(s,"Introduce la aproximacion de la escuela.");
        String popularitat = demanaString(s,"Introduce la popularidad de la escuela.", "Valors valids: 'baixa', 'mitjana' o 'alta'.");
        if (!comprobaPopularitat(popularitat)) throw new InputMismatchException("El valor de popularitat introduit no es valid.");
        String restriccions = demanaString(s,"Introduce las restricciones de la escuela.");
         e.create(new Escola(poblacio,nom,aproximacio,popularitat,restriccions));
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
}
