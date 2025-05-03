package controller.classes.update;

import controller.Main;
import model.classes.Via;
import model.dao.MySQLDAO.MySQLViaDAO;
import view.View;

import java.sql.Connection;
import java.sql.SQLException;

import static controller.functions.DemanaDades.*;

public class actualizarVia {
    public static void actualizaVia(Connection c) {
        boolean seguir = true;

        while (seguir) {
            View.mostrartitulo("ACTUALIZAR VIAS");
            View.mostrarMenu("Ancorage", "Tipo de roca", "Escalador","Nombre","Numero de via","Orientacion","Estado","Volver");
            int opcion = Main.aplicaOpcio(Main.scan, 1, 8);

            try {

                if (opcion == 8){
                    seguir = false;
                    View.mostrarMsg("Volviendo al menu de actualizaciones...");
                    return;
                }

                int id = demanaInt("Introduce la ID de la via que quieres actualizar.",Main.scan,1,2000000);
                MySQLViaDAO viaDAO = new MySQLViaDAO(c);
                Via via = viaDAO.read(id);
                Via newVia = new Via(id,via.getAncoratge(), via.getTipus_roca(),via.getEscalador(),via.getNom(),via.getNumero_via(),via.getOrientacio(),via.getEstat());
                String atributo;

                switch (opcion) {
                    case 1:
                        atributo = demanaString(Main.scan,20,"Introduce el nuevo ancorage de la via.");
                        newVia.setAncoratge(atributo);
                        viaDAO.update(newVia);
                        break;
                    case 2:
                        atributo = demanaString(Main.scan,20,"Introduce el nuevo tipo de roca de la via.");
                        newVia.setTipus_roca(atributo);
                        viaDAO.update(newVia);
                        break;
                    case 3:
                        atributo = demanaString(Main.scan,50,"Introduce el nuevo escalador de la via.");
                        newVia.setEscalador(atributo);
                        viaDAO.update(newVia);
                        break;
                    case 4:
                        atributo = demanaString(Main.scan,50,"Introduce el nuevo nombre de la via.");
                        newVia.setNom(atributo);
                        viaDAO.update(newVia);
                        break;
                    case 5:
                        atributo = demanaString(Main.scan,15,"Introduce el nuevo numero de la via.");
                        newVia.setNumero_via(Integer.parseInt(atributo));
                        viaDAO.update(newVia);
                        break;
                    case 6:
                        atributo = demanaString(Main.scan,10,"Introduce la nueva orientacion de la via.");
                        if (!comprobaOrientacio(atributo)) throw new SQLException("La orientacion introducida no es valida.");
                        newVia.setOrientacio(atributo);
                        viaDAO.update(newVia);
                        break;
                    case 7:
                        atributo = demanaString(Main.scan,15,"Introduce el nuevo estado de la via.");
                        if (!comprobaEstat(atributo)) throw new SQLException("El estado introducido no es valido.");
                        newVia.setEstat(atributo);
                        viaDAO.update(newVia);
                        break;
                }
            } catch (SQLException | RuntimeException e) {
                View.mostrarMsg(e.getMessage());
            }
        }
    }
}