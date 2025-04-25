package controller.classes.update;

import controller.Main;
import view.View;

import java.sql.Connection;
import java.util.InputMismatchException;

public class actualizarEscalador {
    public static void actualizarEscalador(Connection c) {
        boolean seguir = true;

        while (seguir) {

            View.mostrartitulo("ACTUALIZAR ESCALADOR");
            View.mostrarMenu("Nombre","Alias","Edat","Nombre de la via maxima","Tipo favorito","Fita","Volver");
            int opcion = Main.aplicaOpcio(Main.scan, 1, 8);

            try {
                switch (opcion) {
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    case 7:
                        seguir = false;
                        View.mostrarMsg("Volviendo al menu de actualizaciones...");
                        break;
                }
            } catch (/*SQLException |*/ InputMismatchException e) {
                View.mostrarMsg(e.getMessage());
            }
        }
    }
}