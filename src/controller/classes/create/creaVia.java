package controller.classes.create;

import controller.Main;
import model.classes.Tram;
import model.classes.Via;
import model.dao.MySQLDAO.MySQLViaDAO;

import static controller.functions.DemanaDades.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class creaVia {
    public static void creaVia(Scanner s, Connection c) throws SQLException {
        MySQLViaDAO v = new MySQLViaDAO(c);
        String sector = demanaString(s,50,"Introduce el nombre del sector de la via.");
        String tipus = demanaString(s,20,"Introduce el nombre del tipo de la via.", "La via puede ser de los siguientes tipos: 'esportiva', 'classica' o 'gel'.");
        if (!comprobaTipus(tipus)) throw new InputMismatchException("El valor introducido no es valido.");
        String ancoratge = demanaString(s,20,"Introduce el nombre del ancorage de la via.");
        String tipus_roca = demanaString(s,20,"Introduce el tipo de roca de la via.");
        String escalador = demanaString(s,50,"Introduce el escalador que fundo la via.");
        String nom = demanaString(s,50,"Introdueix el nom de la via.");
        int numero_via = demanaInt("Introdueix el numero de la via.", s, 1, 1000000);
        String orientacio = demanaString(s,2,"Introdueix la orientacio de la via.");
        if (!comprobaOrientacio(orientacio)) throw new InputMismatchException("La orientacio introduida no es valida.");
        String estat = demanaString(s,15,"Introdueix l'estat de la via. ('apte','construccio','tancada')");
        if (!comprobaEstat(estat)) throw new InputMismatchException("El estado introducido no es valido.");

        if (tipus.equals("esportiva")){
            String dificultat = demanaString(s, 3,"Introduce la dificultad de la via.");
            if (!dificultatAdequada(dificultat,tipus)) throw new InputMismatchException("El valor de dificultad no es el correcto para esta via, ten en cuenta que la de tipo 'gel' el grado de dificultad comienza con 'WI'.");
            int llargada = demanaInt("Introdueix la llargada de la via.", s, 0, 50);
            v.create(new Via(sector,tipus,ancoratge,tipus_roca,escalador,dificultat,nom,llargada,numero_via,orientacio,estat));
            return;
        }

        ArrayList<Tram> trams = new ArrayList<>();
        String crearTramos = "";
        for (int i = 1; !crearTramos.matches("no") ; i++) {
            trams.add(creaTram.creaTram(i, tipus));
            if (trams.size() >= 2) crearTramos = demanaString(Main.scan,2,"Tramo introducido con exito." + "\n" + "Introduce 'no' para parar de introducir tramos, qualquier otra cosa para seguir añadiendo tramos.").trim().toLowerCase();
        }
        v.create(new Via(sector,tipus,ancoratge,tipus_roca,escalador,nom,numero_via,orientacio,estat,trams.toArray(Tram[]::new)));
    }
}