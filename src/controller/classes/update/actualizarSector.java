package controller.classes.update;

import controller.Main;
import view.View;

import java.sql.Connection;
import java.util.InputMismatchException;

public class actualizarSector {
    public static void actualizarSector(Connection c) {
        boolean seguir = true;

        while (seguir) {

            View.mostrartitulo("ACTUALIZAR SECTOR");
            View.mostrarMenu("Escuela","Nombre","Latitud","Longitud","Aproximacion","Popularidad","Restricciones","Volver");
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
                        break;
                    case 8:
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