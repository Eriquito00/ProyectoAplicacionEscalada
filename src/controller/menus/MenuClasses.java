package controller.menus;

import controller.Main;
import view.View;
import controller.classes.*;

import java.beans.Visibility;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;

public class MenuClasses {
    public static void menuClasses(int opt, String titulo, Connection c){
        boolean seguir = true;

        while (seguir){

            View.mostrartitulo(titulo);
            View.mostrarMenu("Vias","Sectores","Escuelas","Escaladores","Salir");
            int opcion = Main.aplicaOpcio(Main.scan, 1, 5);

            try {
                switch (opcion){
                    case 1:
                        switch (opt){
                            case 1: creaVias.creaVia(Main.scan); break;
                            case 2:  break;
                            case 3:  break;
                            case 4:  break;
                        }
                        break;
                    case 2:
                        switch (opt){
                            case 1: creaSector.creaSector(Main.scan); break;
                            case 2:  break;
                            case 3:  break;
                            case 4:  break;
                        }
                        break;
                    case 3:
                        switch (opt){
                            case 1: creaEscola.creaEscola(Main.scan, c); break;
                            case 2:  break;
                            case 3:  break;
                            case 4:  break;
                        }
                        break;
                    case 4:
                        switch (opt){
                            case 1:  break;
                            case 2:  break;
                            case 3:  break;
                            case 4:  break;
                        }
                        break;
                    case 5:
                        seguir = false;
                        View.mostrarMsg("Volviendo al menu principal...");
                        break;
                }
            }
            catch (SQLException | InputMismatchException e){
                View.mostrarMsg(e.getMessage());
            }
        }
    }

    public static void menuConsultasAvanzadas(String titulo){
        boolean seguir = true;

        while (seguir){

            View.mostrartitulo(titulo);
            View.mostrarMenu("Consulta1","Consulta2","Consulta3","Consulta4","Consulta5","Salir");
            int opcion = Main.aplicaOpcio(Main.scan, 1, 6);

            try {
                switch (opcion){
                    case 1:
                        //Consulta avanzada 1
                        break;
                    case 2:
                        //Consulta avanzada 2
                        break;
                    case 3:
                        //Consulta avanzada 3
                        break;
                    case 4:
                        //Consulta avanzada 4
                        break;
                    case 5:
                        //Consulta avanzada 5
                        break;
                    case 6:
                        seguir = false;
                        View.mostrarMsg("Volviendo al menu principal...");
                        break;
                }
            }
            catch (RuntimeException e){
                //Hay que cambiar que en vez de runtime sea SQLExeption
                System.out.println(e.getMessage());
            }
        }
    }
}