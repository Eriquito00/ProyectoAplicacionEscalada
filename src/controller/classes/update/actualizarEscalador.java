package controller.classes.update;

import controller.Main;
import static controller.functions.DemanaDades.*;

import model.classes.Escalador;
import model.dao.MySQLDAO.MySQLEscaladorDAO;
import view.View;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;

public class actualizarEscalador {
    public static void actualizarEscalador(Connection c) {
        boolean seguir = true;

        while (seguir) {

            View.mostrartitulo("ACTUALIZAR ESCALADOR");
            View.mostrarMenu("Nombre","Alias","Edat","Nombre de la via maxima","Tipo favorito","Fita","Volver");
            int opcion = Main.aplicaOpcio(Main.scan, 1, 7);

            try {
                if (opcion == 7){
                    seguir = false;
                    View.mostrarMsg("Volviendo al menu de actualizaciones...");
                    return;
                }

                int id = demanaInt("Introduce la ID del sector que quieres actualizar.",Main.scan,1,2000000);
                MySQLEscaladorDAO escaladorDAO = new MySQLEscaladorDAO(c);
                Escalador escalador = escaladorDAO.read(id);
                String escola = demanaString(Main.scan, 50, "Introduce el nombre de la escuela donde el escalador consiguio su dificultad maxima.");
                Escalador newEscalador = new Escalador(id,escalador.getNom(), escalador.getAlies(),escalador.getEdad(),escalador.getNombre_via_max(),escola,escalador.getTipo_favorito(),escalador.getFita());
                String atributo;

                switch (opcion) {
                    case 1:
                        atributo = demanaString(Main.scan,50,"Introduce el nuevo nombre del escalador.");
                        newEscalador.setNom(atributo);
                        escaladorDAO.update(newEscalador);
                        break;
                    case 2:
                        atributo = demanaString(Main.scan,50,"Introduce el nuevo alias del escalador.");
                        newEscalador.setAlies(atributo);
                        escaladorDAO.update(newEscalador);
                        break;
                    case 3:
                        int edad = demanaInt("Introduce la nueva edad del escalador. \nEl escalador tiene que ser mayor de 18 años.",Main.scan,18,150);
                        newEscalador.setEdad(edad);
                        escaladorDAO.update(newEscalador);
                        break;
                    case 4:
                        atributo = demanaString(Main.scan,50,"Introduce el nuevo nombre de la via com mas dificultad del escalador.");
                        newEscalador.setNombre_via_max(atributo);
                        escaladorDAO.update(newEscalador);
                        break;
                    case 5:
                        atributo = demanaString(Main.scan,50,"Introduce el nuevo tipo favorito de escalada del escalador.", "Introduce 'classica', 'esportiva' o 'gel'.");
                        if (!comprobaTipus(atributo)) throw new SQLException("El tipo de escalada favorito del escalador no existe en la base de datos.");
                        newEscalador.setTipo_favorito(atributo);
                        escaladorDAO.update(newEscalador);
                        break;
                    case 6:
                        atributo = demanaString(Main.scan,100,"Introduce la nueva fita del escalador.");
                        newEscalador.setFita(atributo);
                        escaladorDAO.update(newEscalador);
                        break;
                }
            } catch (SQLException | InputMismatchException e) {
                View.mostrarMsg(e.getMessage());
            }
        }
    }
}