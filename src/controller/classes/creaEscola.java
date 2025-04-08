package controller.classes;

import model.classes.Escola;
import view.View;

import java.util.InputMismatchException;
import java.util.Scanner;

public class creaEscola {
    public static Escola creaEscola (Scanner s){
        String poblacio = demanaString(s,"Introduce la poblacion donde se encuentra la escuela.");
        String nom = demanaString(s,"Introduce el nombre de la escuela.");
        //Comprobar que esta escuela no exista ya
        String aproximacio = demanaString(s,"Introduce la aproximacion de la escuela.");
        String popularitat = demanaString(s,"Introduce la popularidad de la escuela.", "Valors valids: 'baixa', 'mitjana' o 'alta'.");
        if (!comprobaPopularitat(popularitat)) throw new InputMismatchException("El valor de popularitat introduit no es valid.");
        String restriccions = demanaString(s,"Introduce las restricciones de la escuela.");

        return new Escola(poblacio,nom,aproximacio,popularitat,restriccions);
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
