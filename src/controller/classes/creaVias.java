package controller.classes;

import model.classes.Via;
import view.View;

import java.util.InputMismatchException;
import java.util.Scanner;

public class creaVias {
    public static Via creaVia(Scanner s){
        String sector = demanaString(s,"Introduce el nombre del sector de la via.");
        //Comprovar que el sector existe
        String tipus = demanaString(s,"Introduce el nombre del tipo de la via.");
        //Comprovar que el tipo de via existe
        String ancoratge = demanaString(s,"Introduce el nombre del ancorage de la via.");
        //Comprovar que el ancorage existe
        String tipus_roca = demanaString(s,"Introduce el tipo de roca de la via.");
        //Comprovar que el tipo de roca existe
        String escalador = demanaString(s,"Introduce el escalador que fundo la via.");
        //Comprovar que el escalador existe
        String dificultat = demanaString(s, "Introduce la dificultad de la via.");
        //Comprobar qeu la dificultat existe
        String nom = demanaString(s,"Introdueix el nom de la via.");
        //Hay que comprobar que en una escuela no exista el nombre de esta via
        int llargada = demanaInt("Introdueix la llargada de la via.", s, 0, 50);
        int numero_via = demanaInt("Introdueix el numero de la via.", s, 1, 1000000);
        //Hay que comprobar que el numero de via no exista en esa escuela
        String orientacio = demanaString(s,"Introdueix la orientacio de la via.");
        //Hay que comprobar que la orientacion sea entre las establecidas
        String estat = demanaString(s,"Introdueix l'estat de la via.");
        //Hay que comprobar que el estado sea entre los establecidos

        return new Via(sector,tipus,ancoratge,tipus_roca,escalador,dificultat,nom,llargada,numero_via,orientacio,estat);
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