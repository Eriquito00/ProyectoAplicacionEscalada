package controller.classes.create;

import model.classes.Via;
import model.dao.MySQLDAO.MySQLViaDAO;
import view.View;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class creaVia {
    public static void creaVia(Scanner s, Connection c) throws SQLException {
        MySQLViaDAO v = new MySQLViaDAO(c);
        String sector = demanaString(s,"Introduce el nombre del sector de la via.");
        //Comprovar que el sector existe
        String tipus = demanaString(s,"Introduce el nombre del tipo de la via.", "La via puede ser de los siguientes tipos: 'esportiva', 'classica' o 'gel'.");
        if (!comprobaTipus(tipus)) throw new InputMismatchException("El valor introducido no es valido.");
        //Comprovar que el tipo de via existe
        String ancoratge = demanaString(s,"Introduce el nombre del ancorage de la via.");
        //Comprovar que el ancorage existe
        String tipus_roca = demanaString(s,"Introduce el tipo de roca de la via.");
        //Comprovar que el tipo de roca existe
        String escalador = demanaString(s,"Introduce el escalador que fundo la via.");
        //Comprovar que el escalador existe
        String dificultat = demanaString(s, "Introduce la dificultad de la via.");
        if (!dificultatAdequada(dificultat,tipus)) throw new InputMismatchException("El valor de dificultad no es el correcto para esta via, ten en cuenta que la de tipo 'gel' el grado de dificultad comienza con 'WI'.");
        //Comprobar qeu la dificultat existe
        String nom = demanaString(s,"Introdueix el nom de la via.");
        //Hay que comprobar que en una escuela no exista el nombre de esta via
        int llargada = demanaInt("Introdueix la llargada de la via.", s, 0, 50);
        int numero_via = demanaInt("Introdueix el numero de la via.", s, 1, 1000000);
        //Hay que comprobar que el numero de via no exista en esa escuela
        String orientacio = demanaString(s,"Introdueix la orientacio de la via.");
        if (!comprobaOrientacio(orientacio)) throw new InputMismatchException("La orientacio introduida no es valida.");
        String estat = demanaString(s,"Introdueix l'estat de la via.");
        if (!comprobaEstat(estat)) throw new InputMismatchException("El estado introducido no es valido.");
        v.create(new Via(sector,tipus,ancoratge,tipus_roca,escalador,dificultat,nom,llargada,numero_via,orientacio,estat));
    }

    public static boolean comprobaEstat(String est){
        return (est.toLowerCase().trim().equals("apte")
                || est.toLowerCase().trim().equals("construccio")
                || est.toLowerCase().trim().equals("tancada"));
    }

    public static boolean comprobaTipus(String est){
        return (est.toLowerCase().trim().equals("esportiva")
                || est.toLowerCase().trim().equals("classica")
                || est.toLowerCase().trim().equals("gel"));
    }

    public static boolean dificultatAdequada(String dificultat, String tipus){
        return tipus.equals("gel") && dificultat.startsWith("WI");
    }

    public static boolean comprobaOrientacio(String orient){
        return orient.matches("\\b(N|S|E|O|NE|NO|SE|SO)\\b");
    }

    private static String demanaString(Scanner s, String ... msg){
        for (String str: msg) View.mostrarMsg(str);
        return s.nextLine();
    }

    private static int demanaInt(String msg, Scanner s, int min, int max){
        View.mostrarMsg(msg);
        int n = Integer.parseInt(s.nextLine());
        if (n < min || n > max) throw new InputMismatchException("El valor introducido esta fuera de las opciones.");
        return n;
    }
}