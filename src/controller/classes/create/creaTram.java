package controller.classes.create;

import controller.Main;
import model.classes.Tram;

import static controller.functions.DemanaDades.*;

public class creaTram {
    public static Tram creaTram(int numero_tram){
        String dificultat = demanaString(Main.scan, 3, "Introduce la dificultad del tramo.");
        int llargada = demanaInt("Introduce la longitud del tramo.", Main.scan,1,50);
        return new Tram(llargada,dificultat,numero_tram);
    }
}