package controller.classes.create;

import controller.Main;
import model.classes.Tram;
import model.classes.Via;
import model.dao.MySQLDAO.MySQLViaDAO;
import view.View;

import static controller.functions.DemanaDades.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class creaVia {
    public static void creaVia(Scanner s, Connection c) throws SQLException {
        boolean tram = true;
        MySQLViaDAO v = new MySQLViaDAO(c);
        String sector = demanaString(s,50,"Introduce el nombre del sector de la via.");
        String tipus = demanaString(s,20,"Introduce el nombre del tipo de la via.", "La via puede ser de los siguientes tipos: 'esportiva', 'classica' o 'gel'.");
        if (!comprobaTipus(tipus)) throw new InputMismatchException("El valor introducido no es valido.");
        if (tipus.matches("^esportiva$")) tram = false;
        String ancoratge = demanaString(s,20,"Introduce el nombre del ancorage de la via.");
        String tipus_roca = demanaString(s,20,"Introduce el tipo de roca de la via.");
        String escalador = demanaString(s,50,"Introduce el escalador que fundo la via.");
        String dificultat = demanaString(s, 3,"Introduce la dificultad de la via.");
        if (!dificultatAdequada(dificultat,tipus)) throw new InputMismatchException("El valor de dificultad no es el correcto para esta via, ten en cuenta que la de tipo 'gel' el grado de dificultad comienza con 'WI'.");
        String nom = demanaString(s,50,"Introdueix el nom de la via.");
        int llargada = demanaInt("Introdueix la llargada de la via.", s, 0, 50);
        int numero_via = demanaInt("Introdueix el numero de la via.", s, 1, 1000000);
        String orientacio = demanaString(s,2,"Introdueix la orientacio de la via.");
        if (!comprobaOrientacio(orientacio)) throw new InputMismatchException("La orientacio introduida no es valida.");
        String estat = demanaString(s,15,"Introdueix l'estat de la via.");
        if (!comprobaEstat(estat)) throw new InputMismatchException("El estado introducido no es valido.");

        // HAY UN PROBLEMA QUE SI INTRODUCE LOS DATOS Y TODO LO DE LOS TRAMOS SI HAY ALGUNA COSA QUE NO ESTA BIEN TODA LA CREACION DE LOS TRAMOS I DE LA VIA NO SE GUARDARA

        ArrayList<Tram> trams = new ArrayList<>();
        while (tram){
            trams.add(creaTram.creaTram());
            String crearTramos = demanaString(Main.scan,2,"Quieres añadir otro tramo a la via " + nom + "? (si / no).");
            if (crearTramos.matches("no")) tram = false;
        }

        v.create(new Via(sector,tipus,ancoratge,tipus_roca,escalador,dificultat,nom,llargada,numero_via,orientacio,estat));
    }
}