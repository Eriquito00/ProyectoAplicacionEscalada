package controller;

import model.connection.MySQLConnection;
import view.View;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) throws SQLException {
        boolean seguir = true;

        View.mostrarMsg("Bienvenido al gestionador de Vias de Escalada!");

        while (seguir){

            View.mostrarMenu("A","B","C","D");
            int opcion = aplicaOpcio(scan, 1, 4);

            switch (opcion){
                case 1:
                    View.mostrarMsg("1");
                    Statement s = MySQLConnection.mysqlConnection();
                    ResultSet rs = s.executeQuery("Select * from vies");
                    ResultSetMetaData md = rs.getMetaData();

                    String col = String.format("%-30s %-5s %-5s %-5s %-10s",
                            md.getColumnName(7),
                            md.getColumnName(8),
                            md.getColumnName(9),
                            md.getColumnName(10),
                            md.getColumnName(11));
                    System.out.println(col + "\n");
                    while (rs.next()){
                        String str = String.format("%-30s %-5s %-5s %-5s %-10s",
                                rs.getString(7),
                                rs.getString(8),
                                rs.getString(9),
                                rs.getString(10),
                                rs.getString(11));
                        System.out.println(str + "\n");
                    }
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
                    break;
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