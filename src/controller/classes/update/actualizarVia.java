package controller.classes.update;

import controller.Main;
import view.View;

import java.sql.Connection;
import java.util.InputMismatchException;

import static controller.functions.DemanaDades.demanaInt;
import static controller.functions.DemanaDades.demanaString;

public class actualizarVia {
    public static void actualizaVia(Connection c) {
        boolean seguir = true;

        while (seguir) {
            // TODO: Hace falta que me pases tambien la ID de la via en el objeto via cuando te traigas la via de la bbdd, si no no la podremos actualizar!!!!!!!!
            View.mostrartitulo("ACTUALIZAR VIAS");
            View.mostrarMenu("Sector", "Tipo", "Ancorage", "Tipo de roca", "Escalador","Dificultad","Nombre","Longitud","Latitud","Numero de via","Orientacion","Estado","Volver");
            int opcion = Main.aplicaOpcio(Main.scan, 1, 12);

            try {
                int id = demanaInt("Introduce la ID del escalador que quieres actualizar.",Main.scan,1,2000000);
                String atributo;

                switch (opcion) {
                    case 1:
                        atributo = demanaString(Main.scan,50,"Introduce el nuevo sector de la via.");
                        //HACE FALTA QUE EL SECTOR EXISTA
                        break;
                    case 2:
                        atributo = demanaString(Main.scan,20,"Introduce el nuevo tipo de la via.");
                        //HACE FALTA QUE EL TIPO EXISTA
                        break;
                    case 3:
                        atributo = demanaString(Main.scan,20,"Introduce el nuevo ancorage de la via.");
                        //HACE FALTA QUE EL ANCORAGE EXISTA
                        break;
                    case 4:
                        atributo = demanaString(Main.scan,20,"Introduce el nuevo tipo de roca de la via.");
                        //HACE FALTA QUE EL TIPO DE ROCA EXISTA
                        break;
                    case 5:
                        atributo = demanaString(Main.scan,50,"Introduce el nuevo escalador de la via.");
                        //HACE FALTA QUE EL ESCALADOR EXISTA
                        break;
                    case 6:
                        atributo = demanaString(Main.scan,3,"Introduce la nueva dificultad de la via.");
                        //HACE FALTA QUE LA DIFICULTAD EXISTA
                        break;
                    case 7:
                        atributo = demanaString(Main.scan,50,"Introduce el nuevo tipo de roca de la via.");
                        break;
                    case 8:
                        atributo = demanaString(Main.scan,20,"Introduce la nueva longitud de la via.");
                        break;
                    case 9:
                        atributo = demanaString(Main.scan,20,"Introduce la nueva latitud de la via.");
                        break;
                    case 10:
                        atributo = demanaString(Main.scan,15,"Introduce el nuevo numero de la via.");
                        break;
                    case 11:
                        atributo = demanaString(Main.scan,10,"Introduce la nueva orientacion de la via.");
                        break;
                    case 12:
                        atributo = demanaString(Main.scan,15,"Introduce el nuevo estado de la via.");
                        break;
                    case 13:
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