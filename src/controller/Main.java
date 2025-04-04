package controller;

import view.View;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static Scanner scan = new Scanner(System.in);
    public static void main(String[] args){
        boolean seguir = true;

        View.mostrarMsg("Bienvenido al gestionador de Vias de Escalada!");

        while (seguir){

            View.mostrarMenu("A","B","C","D");
            int opcion = aplicaOpcio(scan, 1, 4);

            switch (opcion){
                case 1:
                    View.mostrarMsg("1");
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
            if (n < min || n > max) throw new NumberFormatException();
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