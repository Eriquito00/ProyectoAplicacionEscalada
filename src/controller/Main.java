package controller;

import model.connection.MySQLConnection;
import view.View;
import utils.Test;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) throws SQLException {
        boolean seguir = true;

        View.mostrarMsg("Bienvenido al gestionador de Vias de Escalada!");

        while (seguir){

            View.mostrarMenu("A","B","C","D");
            int opcion = aplicaOpcio(scan, 1, 4);

            try {
                Connection c = MySQLConnection.mysqlConnection();

                switch (opcion){
                    case 1:
                        View.mostrarMsg("1");
                        Test.pruebaConexion(c);
                        break;
                    case 2:
                        View.mostrarMsg("2");
                        break;
                    case 3:
                        View.mostrarMsg("3");
                        break;
                    case 4:
                        View.mostrarMsg("salir");
                        seguir = false;
                        c.close();
                        break;
                }
            }
            catch (RuntimeException | SQLException e) {
                View.mostrarMsg(e.getMessage());
            }
        }
    }

    public static int aplicaOpcio(Scanner s, int min, int max){
        int n = 0;

        try {
            n = Integer.parseInt(s.nextLine());
            if (n < min || n > max) throw new InputMismatchException();
        }
        catch (NumberFormatException e){
            View.mostrarMsg("El valor introducido no es valido.");
        }
        catch (InputMismatchException e){
            View.mostrarMsg("El valor introducido esta fuera de las opciones.");
        }

        return n;
    }
}