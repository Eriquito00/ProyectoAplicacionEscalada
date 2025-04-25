package controller.classes.update;

import controller.Main;
import view.View;

import java.sql.Connection;
import java.util.InputMismatchException;

public class actualizarVia {
    public static void actualizaVia(Connection c) {
        boolean seguir = true;

        while (seguir) {

            View.mostrartitulo("ACTUALIZAR VIAS");
            View.mostrarMenu("Sector", "Tipo", "Ancorage", "Tipo de roca", "Escalador","Dificultad","Nombre","Longitud","Numero de via","Orientacion","Estado","Volver");
            int opcion = Main.aplicaOpcio(Main.scan, 1, 12);

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
                        break;
                    case 8:
                        break;
                    case 9:
                        break;
                    case 10:
                        break;
                    case 11:
                        break;
                    case 12:
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