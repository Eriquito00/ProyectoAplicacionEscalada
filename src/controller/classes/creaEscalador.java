package controller.classes;

import model.classes.Escalador;
import model.dao.MySQLDAO.MySQLEscaladorDAO;
import view.View;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class creaEscalador {
    public static void creaEscalador (Scanner s, Connection c) throws SQLException {
        MySQLEscaladorDAO e = new MySQLEscaladorDAO(c);
        String nom = demanaString(s, 50, "Introduce el nombre del escalador.");
        String alies = demanaString(s, 50,"Introduce el alias del escalador.");
        int edad = demanaInt("Introduce la edad del escalador.",s,18,150);
        String nombre_via_max = demanaString(s,50,"Introduce el nombre de la via que conseguiste tu maximo de dificultad.");
        //HAY QUE COMRPOBAR 100% QUE LA VIA EXISTA I SI ESO TAMBIEN HABRIA QUE COMPROBAR QUE LA DIFICULTAD QUE HA INTRODUCIDO SEA REALMENTE LA QUE TIENE LA VIA
        String escola = demanaString(s, 50, "Introduce el nombre de la escuela de la via que conseguite tu maximo de dificultad.");
        String tipo_favorito = demanaString(s, 50,"Introduce el tipo de escalada favorita del escalador.", "Introduce una de la siguientes opciones: 'clasica' 'deportiva' 'hielo'.");
        if (!comprobaTipus(tipo_favorito)) throw new InputMismatchException("El tipo de via favorito introducido no existe.");
        String fita = demanaString(s, 100,"Introduce la fita del escalador.");
        e.create(new Escalador(nom,alies,edad,nombre_via_max,escola,tipo_favorito,fita));
    }

    public static boolean comprobaTipus(String est){
        return (est.toLowerCase().trim().equals("clasica")
                || est.toLowerCase().trim().equals("deportiva")
                || est.toLowerCase().trim().equals("hielo"));
    }

    private static String demanaString(Scanner s, int llargada, String ... msg){
        for (String str: msg) View.mostrarMsg(str);
        String str = s.nextLine();
        if (str.length() > llargada) throw new InputMismatchException("El maximo de caracteres permitidos son " + llargada + ".");
        return str;
    }

    private static int demanaInt(String msg, Scanner s, int min, int max){
        View.mostrarMsg(msg);
        int n = Integer.parseInt(s.nextLine());
        if (n < min || n > max) throw new InputMismatchException("El valor introducido esta fuera de las opciones.");
        return n;
    }
}