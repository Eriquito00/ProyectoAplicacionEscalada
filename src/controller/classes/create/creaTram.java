package controller.classes.create;

import controller.Main;
import model.classes.Tram;

import static controller.functions.DemanaDades.*;

public class creaTram {
    public static Tram creaTram(){
        String via = demanaString(Main.scan, 50, "Introduce el nombre de la via que pertenece este tramo.");
        String dificultat = demanaString(Main.scan, 3, "Introduce la dificultad del tramo.");
        int llargada = demanaInt("Introduce la longitud del tramo.", Main.scan,1,50);
        int numero_tram = demanaInt("Introduce el numero del tramo.", Main.scan, 1,10);
        return new Tram(llargada,dificultat);
    }
}