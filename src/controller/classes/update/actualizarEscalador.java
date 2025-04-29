package controller.classes.update;

import controller.Main;
import static controller.functions.DemanaDades.*;
import view.View;

import java.sql.Connection;
import java.util.InputMismatchException;

public class actualizarEscalador {
    public static void actualizarEscalador(Connection c) {
        boolean seguir = true;

        while (seguir) {

            View.mostrartitulo("ACTUALIZAR ESCALADOR");
            View.mostrarMenu("Nombre","Alias","Edat","Nombre de la via maxima","Tipo favorito","Fita","Volver");
            int opcion = Main.aplicaOpcio(Main.scan, 1, 7);

            try {
                int id = demanaInt("Introduce la ID del escalador que quieres actualizar.",Main.scan,1,2000000);
                String atributo;

                switch (opcion) {
                    case 1:
                        atributo = demanaString(Main.scan,50,"Introduce el nuevo nombre del escalador.");
                        //OBTENGO EL OBJETO DEL ESCALADOR
                        //CAMBIO AL OBJETO EL ATRIBUTO QUE TIENE POR EL NUEVO
                        //ENVIO A MODELO EL OBJETO CAMBIADO PARA PROCEDER AL UPDATE
                        break;
                    case 2:
                        atributo = demanaString(Main.scan,50,"Introduce el nuevo alias del escalador.");
                        //HACE FALTA QUE EL ALIAS NO EXISTA YA
                        break;
                    case 3:
                        int edad = demanaInt("Introduce la nueva edad del escalador.",Main.scan,18,150);
                        break;
                    case 4:
                        atributo = demanaString(Main.scan,50,"Introduce el nuevo nombre de la via maxima del escalador.");
                        //HACE FALTA COMPROVAR QUE LA VIA EXISTA
                        break;
                    case 5:
                        atributo = demanaString(Main.scan,50,"Introduce el nuevo tipo favorito de escalada del escalador.");
                        //HACE FALTA COMRPOBAR QUE EL TIPO EXISTA
                        break;
                    case 6:
                        atributo = demanaString(Main.scan,100,"Introduce la nueva fita del escalador.");
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