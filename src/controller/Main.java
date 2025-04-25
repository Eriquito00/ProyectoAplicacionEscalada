package controller;

import controller.menus.MenuAvanzados;
import controller.menus.MenuClasses;
import model.connection.MySQLConnection;
import view.View;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        boolean seguir = true;

        View.mostrarMsg("Bienvenido al gestionador de Vias de Escalada!");

        while (seguir){

            View.mostrartitulo("APLICACION DE GESTION DE ESCALADA");
            View.mostrarMenu("Crear","Borrar","Actualizar","Consultar","Consultas Avanzadas","Salir");
            int opcion = aplicaOpcio(scan, 1, 6);

            try {
                Connection c = MySQLConnection.mysqlConnection();

                switch (opcion){
                    case 1:
                        MenuClasses.menuClasses(opcion, "CREAR", c);
                        break;
                    case 2:
                        MenuClasses.menuClasses(opcion, "BORRAR", c);
                        break;
                    case 3:
                        MenuClasses.menuClasses(opcion, "ACTUALIZAR", c);
                        break;
                    case 4:
                        MenuClasses.menuClasses(opcion, "CONSULTAS", c);
                        break;
                    case 5:
                        MenuAvanzados.menuConsultasAvanzadas("CONSULTAS AVANZADAS");
                        //HACE FALTA PASARLE LA CONEXION
                        break;
                    case 6:
                        seguir = false;
                        c.close();
                        View.mostrarMsg("Gracias por usar la aplicacion. Nos vemos pronto...");
                        break;
                }
            }
            catch (SQLException e){
                seguir = false;
                View.mostrarMsg("Error en la conexion con la base de datos.");
                //BORRAR LUEGO
                View.mostrarMsg(e.getMessage());
            }
        }
    }

    /**
     * Obtiene un valor y comprueva que sea INT y que este entre los limites establecidos
     * @param s Scanner para obtener el valor
     * @param min Minimo valor aceptado
     * @param max Maximo valor aceptado
     * @return El numero introducido por el usuario si pasa los tests
     */
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