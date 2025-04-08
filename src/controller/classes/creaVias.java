package controller.classes;

import model.classes.Via;
import view.View;

import java.util.InputMismatchException;
import java.util.Scanner;

public class creaVias {
    public static Via creaVia(Scanner s){
        String sector = "";
        String tipus = "";
        String ancoratge = "";
        String tipus_roca = "";
        String escalador = "";
        String nom = demanaString("Introdueix el nom de la via.", s);
        //Hay que comprobar que en una escuela no exista el nombre de esta via
        int llargada = demanaInt("Introdueix la llargada de la via.", s, 0, 50);
        int numero_via = demanaInt("Introdueix el numero de la via.", s, 1, 1000000);
        //Hay que comprobar que el numero de via no exista en esa escuela
        String orientacio = demanaString("Introdueix la orientacio de la via.", s);
        //Hay que comprobar que la orientacion sea entre las establecidas
        String estat = demanaString("Introdueix l'estat de la via.", s);
        //Hay que comprobar que el estado sea entre los establecidos

        return new Via(sector,tipus,ancoratge,tipus_roca,escalador,nom,llargada,numero_via,orientacio,estat);
    }

    private static String demanaString(String msg, Scanner s){
        View.mostrarMsg(msg);
        return s.nextLine();
    }

    private static int demanaInt(String msg, Scanner s, int min, int max){
        View.mostrarMsg(msg);
        int n = Integer.parseInt(s.nextLine());
        if (n > min || n < max) throw new InputMismatchException("El valor introducido esta fuera de las opciones.");
        return n;
    }
}