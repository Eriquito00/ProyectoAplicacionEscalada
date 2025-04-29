package controller.functions;

import view.View;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DemanaDades {
    public static String demanaString(Scanner s, int llargada, String ... msg){
        for (String str: msg) View.mostrarMsg(str);
        String str = s.nextLine();
        if (str.length() > llargada) throw new InputMismatchException("El maximo de caracteres permitidos son " + llargada + ".");
        return str;
    }

    public static int demanaInt(String msg, Scanner s, int min, int max){
        View.mostrarMsg(msg);
        int n = Integer.parseInt(s.nextLine());
        if (n < min || n > max) throw new InputMismatchException("El valor introducido esta fuera de las opciones.");
        return n;
    }

    public static boolean comprobaTipus(String est){
        return (est.toLowerCase().trim().equals("classica")
                || est.toLowerCase().trim().equals("esportiva")
                || est.toLowerCase().trim().equals("gel"));
    }

    public static boolean comprobaPopularitat(String pop){
        return (pop.toLowerCase().trim().equals("baixa")
                || pop.toLowerCase().trim().equals("mitjana")
                || pop.toLowerCase().trim().equals("alta"));
    }

    public static boolean comprobaLatitud(String latitud){
        return latitud.matches("^(90|[1-8]?[0-9])º([0-5]?[0-9])\'([0-5]?[0-9])\"[NS]$");
    }

    public static boolean comprobaLongitud(String longitud){
        return longitud.matches("^(180|[1-9]?[0-9])º([0-5]?[0-9])\'([0-5]?[0-9])\"[OE]$");
    }

    public static boolean comprobaEstat(String est){
        return (est.toLowerCase().trim().equals("apte")
                || est.toLowerCase().trim().equals("construccio")
                || est.toLowerCase().trim().equals("tancada"));
    }

    public static boolean dificultatAdequada(String dificultat, String tipus){
        if(tipus.equals("gel") && !dificultat.startsWith("WI")) return false;
        return true;
    }

    public static boolean comprobaOrientacio(String orient){
        return orient.matches("\\b(N|S|E|O|NE|NO|SE|SO)\\b");
    }
}