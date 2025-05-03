package controller.classes.update;

import controller.Main;
import model.classes.Escola;
import model.dao.MySQLDAO.MySQLEscolaDAO;
import view.View;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;

import static controller.functions.DemanaDades.*;

public class actualizarEscola {
    public static void actualizarEscuela(Connection c) {
        boolean seguir = true;

        while (seguir) {

            View.mostrartitulo("ACTUALIZAR ESCUELA");
            View.mostrarMenu("Poblacion","Nombre","Aproximacion","Popularidad","Restricciones","Volver");
            int opcion = Main.aplicaOpcio(Main.scan, 1, 6);

            try {
                if (opcion == 6){
                    seguir = false;
                    View.mostrarMsg("Volviendo al menu de actualizaciones...");
                    return;
                }

                int id = demanaInt("Introduce la ID del sector que quieres actualizar.",Main.scan,1,2000000);
                MySQLEscolaDAO escolaDAO = new MySQLEscolaDAO(c);
                Escola escola = escolaDAO.read(id);
                Escola newEscola = new Escola(id,escola.getPoblacio(),escola.getNom(),escola.getAproximacio(),escola.getPopularitat(),escola.getRestriccions());
                String atributo;

                switch (opcion) {
                    case 1:
                        atributo = demanaString(Main.scan,50,"Introduce la nueva poblacion de la escuela.");
                        newEscola.setPoblacio(atributo);
                        escolaDAO.update(newEscola);
                        break;
                    case 2:
                        atributo = demanaString(Main.scan,50,"Introduce el nuevo nombre de la escuela.");
                        newEscola.setNom(atributo);
                        escolaDAO.update(newEscola);
                        break;
                    case 3:
                        atributo = demanaString(Main.scan,100,"Introduce la nueva aproximacion de la escuela.");
                        newEscola.setAproximacio(atributo);
                        escolaDAO.update(newEscola);
                        break;
                    case 4:
                        atributo = demanaString(Main.scan,50,"Introduce la nueva popularidad de la escuela.");
                        if (!comprobaPopularitat(atributo)) throw new SQLException("La popularitat del sector es incorrecte.");
                        newEscola.setPopularitat(atributo);
                        escolaDAO.update(newEscola);
                        break;
                    case 5:
                        atributo = demanaString(Main.scan,50,"Introduce la nueva restriccion de la escuela.");
                        newEscola.setRestriccions(atributo);
                        escolaDAO.update(newEscola);
                        break;
                }
            } catch (SQLException | InputMismatchException e) {
                View.mostrarMsg(e.getMessage());
            }
        }
    }
}