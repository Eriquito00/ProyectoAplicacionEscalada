package controller.classes.create;

import controller.Main;
import model.classes.Tram;
import view.View;

import java.sql.SQLException;

import static controller.functions.DemanaDades.*;

public class creaTram {
    public static Tram creaTram(int numero_tram, String type) throws SQLException{
        View.mostrarMsg("Ahora introduzca los datos del tramo numero " + numero_tram + " de la via:");
        String dificultat = demanaString(Main.scan, 3, "Introduce la dificultad del tramo.");
        int llargada = demanaInt("Introduce la longitud del tramo.", Main.scan,1,50);
        if (type.equals("gel")){
            if (!dificultatAdequada(dificultat,type)) throw new SQLException("La dificultad introducida no es valida. Esta via es de tipo 'gel'.");
        }
        return new Tram(llargada,dificultat,numero_tram);
    }
}