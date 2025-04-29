package controller.classes.update;

import controller.Main;
import view.View;

import java.sql.Connection;
import java.util.InputMismatchException;

import static controller.functions.DemanaDades.demanaInt;
import static controller.functions.DemanaDades.demanaString;

public class actualizarEscola {
    public static void actualizarEscuela(Connection c) {
        boolean seguir = true;

        while (seguir) {

            View.mostrartitulo("ACTUALIZAR ESCUELA");
            View.mostrarMenu("Poblacion","Nombre","Aproximacion","Popularidad","Restricciones","Volver");
            int opcion = Main.aplicaOpcio(Main.scan, 1, 6);

            try {
                int id = demanaInt("Introduce la ID del escalador que quieres actualizar.",Main.scan,1,2000000);
                String atributo;

                switch (opcion) {
                    case 1:
                        atributo = demanaString(Main.scan,50,"Introduce la nueva poblacion de la escuela.");
                        break;
                    case 2:
                        atributo = demanaString(Main.scan,50,"Introduce el nuevo nombre de la escuela.");
                        //HACE FALTA QUE LA ESCUELA NO EXISTA
                        break;
                    case 3:
                        atributo = demanaString(Main.scan,100,"Introduce la nueva aproximacion de la escuela.");
                        break;
                    case 4:
                        atributo = demanaString(Main.scan,50,"Introduce la nueva popularidad de la escuela.");
                        //HACE FALTA COMPROBAR QUE LA POPULARIDAD EXISTA
                        break;
                    case 5:
                        atributo = demanaString(Main.scan,50,"Introduce la nueva restriccion de la escuela.");
                        break;
                    case 6:
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