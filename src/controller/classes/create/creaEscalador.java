package controller.classes.create;

import model.classes.Escalador;
import model.dao.MySQLDAO.MySQLEscaladorDAO;
import static controller.functions.DemanaDades.*;

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
        String escola = demanaString(s, 50, "Introduce el nombre de la escuela de la via que conseguite tu maximo de dificultad.");
        String tipo_favorito = demanaString(s, 50,"Introduce el tipo de escalada favorita del escalador.", "Introduce una de la siguientes opciones: 'classica' 'esportiva' 'gel'.");
        if (!comprobaTipus(tipo_favorito)) throw new InputMismatchException("El tipo de via favorito introducido no existe.");
        String fita = demanaString(s, 100,"Introduce la fita del escalador.");
        e.create(new Escalador(nom,alies,edad,nombre_via_max,escola,tipo_favorito,fita));
    }
}