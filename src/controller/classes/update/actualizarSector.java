package controller.classes.update;

import controller.Main;
import view.View;

import java.sql.Connection;
import java.util.InputMismatchException;

import static controller.functions.DemanaDades.demanaInt;
import static controller.functions.DemanaDades.demanaString;

public class actualizarSector {
    public static void actualizarSector(Connection c) {
        boolean seguir = true;

        while (seguir) {

            View.mostrartitulo("ACTUALIZAR SECTOR");
            View.mostrarMenu("Escuela","Nombre","Latitud","Longitud","Aproximacion","Popularidad","Restricciones","Volver");
            int opcion = Main.aplicaOpcio(Main.scan, 1, 8);

            try {
                int id = demanaInt("Introduce la ID del escalador que quieres actualizar.",Main.scan,1,2000000);
                String atributo;

                switch (opcion) {
                    case 1:
                        atributo = demanaString(Main.scan,50,"Introduce la nueva escuela del sector.");
                        //HACE FALTA COMRPOBAR QUE LA ESCUELA EXISTA
                        break;
                    case 2:
                        atributo = demanaString(Main.scan,50,"Introduce el nuevo nombre del sector.");
                        //HACE FALTA QUE EL SECTOR NO EXISTA
                        break;
                    case 3:
                        atributo = demanaString(Main.scan,20,"Introduce la nueva latitud del sector.");
                        //HACE FALTA COMPROVAR QUE LA LATITUD SEA VALIDA
                        break;
                    case 4:
                        atributo = demanaString(Main.scan,20,"Introduce la nueva longitud del sector.");
                        //HACE FALTA COMPROVAR QUE LA LONGITUD SEA VALIDA
                        break;
                    case 5:
                        atributo = demanaString(Main.scan,100,"Introduce la nueva aproximacion del sector.");
                        break;
                    case 6:
                        atributo = demanaString(Main.scan,20,"Introduce la nueva popularidad del sector.");
                        //HACE FALTA QUE LA POPULARIDAD EXISTA
                        break;
                    case 7:
                        atributo = demanaString(Main.scan,100,"Introduce la nueva restriccion del sector.");
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