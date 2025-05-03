package controller.functions;

import view.View;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DemanaDades {
    /**
     * Demana una dada de tipus string i la retorna despres d'uns tests
     * @param s Scanner
     * @param llargada Llargada maxima del text
     * @param msg Missatge de guia per l'usuari per la dada que demanem
     * @return String amb els tests pasats.
     */
    public static String demanaString(Scanner s, int llargada, String ... msg){
        for (String str: msg) View.mostrarMsg(str);
        String str = s.nextLine();
        if (str.length() > llargada) throw new InputMismatchException("El maximo de caracteres permitidos son " + llargada + ".");
        return str;
    }

    /**
     * Demana una dada de tipus integer
     * @param msg Missatge de guia per l'usuari
     * @param s Scanner
     * @param min Numero minim valid
     * @param max Numero maxim valid
     * @return Integer dins dels limits
     */
    public static int demanaInt(String msg, Scanner s, int min, int max){
        View.mostrarMsg(msg);
        int n = Integer.parseInt(s.nextLine());
        if (n < min || n > max) throw new InputMismatchException("El valor introducido esta fuera de las opciones.");
        return n;
    }

    /**
     * Comprova que el tipus de escalada
     * @param est String a comprobar
     * @return True si es una de les comprobades False si no
     */
    public static boolean comprobaTipus(String est){
        return (est.toLowerCase().trim().equals("classica")
                || est.toLowerCase().trim().equals("esportiva")
                || est.toLowerCase().trim().equals("gel"));
    }

    /**
     * Comprova que el tipus de Popularitat
     * @param pop String amb la popularitat
     * @return True si es una de les popularitats valides False si no
     */
    public static boolean comprobaPopularitat(String pop){
        return (pop.toLowerCase().trim().equals("baixa")
                || pop.toLowerCase().trim().equals("mitjana")
                || pop.toLowerCase().trim().equals("alta"));
    }

    /**
     * Comproba el format de la latitud
     * @param latitud Latitud
     * @return True si es valida False si no
     */
    public static boolean comprobaLatitud(String latitud){
        return latitud.matches("^(90|[1-8]?[0-9])º([0-5]?[0-9])\'([0-5]?[0-9])\"[NS]$");
    }

    /**
     * Comproba el format de la longitud
     * @param longitud Longitud
     * @return True si es valida False si no
     */
    public static boolean comprobaLongitud(String longitud){
        return longitud.matches("^(180|[1-9]?[0-9])º([0-5]?[0-9])\'([0-5]?[0-9])\"[OE]$");
    }

    /**
     * Comprova el estat de la via sigui valid
     * @param est String amb l'estat de la via
     * @return True si es un dels estats False si no
     */
    public static boolean comprobaEstat(String est){
        return (est.toLowerCase().trim().equals("apte")
                || est.toLowerCase().trim().equals("construccio")
                || est.toLowerCase().trim().equals("tancada"));
    }

    /**
     * Comproba si la via es de tipus gel i si la dificultat es la que han de tenir les de tipus gel
     * @param dificultat Dificultat de la via
     * @param tipus Tipus de la via
     * @return True si es tipus gel i la dificultat comença per WI False si no
     */
    public static boolean dificultatAdequada(String dificultat, String tipus){
        if(tipus.equals("gel") && !dificultat.startsWith("WI")) return false;
        return true;
    }

    /**
     * Comproba que la orientacio sigui valida
     * @param orient Orientacio
     * @return True si es valida False si no
     */
    public static boolean comprobaOrientacio(String orient){
        return orient.matches("\\b(N|S|E|O|NE|NO|SE|SO)\\b");
    }
}