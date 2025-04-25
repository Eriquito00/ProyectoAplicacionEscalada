package controller.menus;

import controller.Main;
import controller.classes.create.*;
import controller.classes.delete.*;
import controller.classes.update.*;
import controller.classes.read.*;
import view.View;

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
                            case 1: creaVia.creaVia(Main.scan, c); break;
                            case 2: borrarObjeto.deleteVies(c); break;
                            case 3: actualizarVia.actualizaVia(c); break;
                            case 4:
                                View.mostrartitulo("TIPOS DE CONSULTA");
                                View.mostrarMenu("Mostrar todos", "Mostrar uno");
                                int v = Main.aplicaOpcio(Main.scan, 1,2);
                                switch (v){
                                    case 1: View.mostrarMsg(mostrarTodo.mostrarVies(c)); break;
                                    case 2: View.mostrarMsg(mostrarUno.mostrarVies(c).toString()); break;
                                }
                                break;
                        }
                        break;
                    case 2:
                        switch (opt){
                            case 1: creaSector.creaSector(Main.scan, c); break;
                            case 2: borrarObjeto.deleteSectores(c); break;
                            case 3: actualizarSector.actualizarSector(c); break;
                            case 4:
                                View.mostrartitulo("TIPOS DE CONSULTA");
                                View.mostrarMenu("Mostrar todos", "Mostrar uno");
                                int s = Main.aplicaOpcio(Main.scan, 1,2);
                                switch (s){
                                    case 1: View.mostrarMsg(mostrarTodo.mostrarSectores(c)); break;
                                    case 2: mostrarUno.mostrarSectores(c); break;
                                }
                                break;
                        }
                        break;
                    case 3:
                        switch (opt){
                            case 1: creaEscola.creaEscola(Main.scan, c); break;
                            case 2: borrarObjeto.deleteEscoles(c); break;
                            case 3: actualizarEscola.actualizarEscuela(c); break;
                            case 4:
                                View.mostrartitulo("TIPOS DE CONSULTA");
                                View.mostrarMenu("Mostrar todos", "Mostrar uno");
                                int e = Main.aplicaOpcio(Main.scan, 1,2);
                                switch (e){
                                    case 1: View.mostrarMsg(mostrarTodo.mostrarEscoles(c)); break;
                                    case 2: mostrarUno.mostrarEscoles(c); break;
                                }
                                break;
                        }
                        break;
                    case 4:
                        switch (opt){
                            case 1: creaEscalador.creaEscalador(Main.scan,c); break;
                            case 2: borrarObjeto.deleteEscaladores(c); break;
                            case 3: actualizarEscola.actualizarEscuela(c); break;
                            case 4:
                                View.mostrartitulo("TIPOS DE CONSULTA");
                                View.mostrarMenu("Mostrar todos", "Mostrar uno");
                                int esc = Main.aplicaOpcio(Main.scan, 1,2);
                                switch (esc){
                                    case 1: View.mostrarMsg(mostrarTodo.mostrarEscaladores(c)); break;
                                    case 2: mostrarUno.mostrarEscaladores(c); break;
                                }
                                break;
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
}