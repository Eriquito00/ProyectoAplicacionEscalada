package controller.classes.create;

import model.classes.Escola;
import model.dao.MySQLDAO.MySQLEscolaDAO;
import static controller.functions.DemanaDades.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class creaEscola {
    public static void creaEscola (Scanner s, Connection c) throws SQLException,InputMismatchException {
        MySQLEscolaDAO e = new MySQLEscolaDAO(c);
        String poblacio = demanaString(s,50,"Introduce la poblacion donde se encuentra la escuela.");
        String nom = demanaString(s,50,"Introduce el nombre de la escuela.");
        if (e.existeEscola(nom)) throw new InputMismatchException("Esta escuela ya existe.");
        String aproximacio = demanaString(s,100,"Introduce la aproximacion de la escuela.");
        String popularitat = demanaString(s,50,"Introduce la popularidad de la escuela.", "Valors valids: 'baixa', 'mitjana' o 'alta'.");
        if (!comprobaPopularitat(popularitat)) throw new InputMismatchException("El valor de popularitat introduit no es valid.");
        String restriccions = demanaString(s,100,"Introduce las restricciones de la escuela.");
        e.create(new Escola(poblacio,nom,aproximacio,popularitat,restriccions));
    }
}
