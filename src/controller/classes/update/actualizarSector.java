package controller.classes.update;

import controller.Main;
import model.classes.Sector;
import model.dao.MySQLDAO.MySQLSectorDAO;
import view.View;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;

import static controller.functions.DemanaDades.*;

public class actualizarSector {
    public static void actualizarSector(Connection c) {
        boolean seguir = true;

        while (seguir) {

            View.mostrartitulo("ACTUALIZAR SECTOR");
            View.mostrarMenu("Nombre","Latitud","Longitud","Aproximacion","Popularidad","Restricciones","Volver");
            int opcion = Main.aplicaOpcio(Main.scan, 1, 7);

            try {
                if (opcion == 7){
                    seguir = false;
                    View.mostrarMsg("Volviendo al menu de actualizaciones...");
                    return;
                }

                int id = demanaInt("Introduce la ID del sector que quieres actualizar.",Main.scan,1,2000000);
                MySQLSectorDAO sectorDAO = new MySQLSectorDAO(c);
                Sector sector = sectorDAO.read(id);
                Sector newSector = new Sector(id,sector.getEscola(),sector.getNom(),sector.getLatitud(),sector.getLongitud(),sector.getAproximacio(),sector.getPopularitat(),sector.getRestriccions());
                String atributo;

                switch (opcion) {
                    case 1:
                        atributo = demanaString(Main.scan,50,"Introduce el nuevo nombre del sector.");
                        newSector.setNom(atributo);
                        sectorDAO.update(newSector);
                        break;
                    case 2:
                        atributo = demanaString(Main.scan,20,"Introduce la nueva latitud del sector.", "Por ejemplo: '90º40'20\"N o S'.");
                        if (!comprobaLatitud(atributo)) throw new SQLException("La latitud del sector es incorrecte.");
                        newSector.setLatitud(atributo);
                        sectorDAO.update(newSector);
                        break;
                    case 3:
                        atributo = demanaString(Main.scan,20,"Introduce la nueva longitud del sector.", "Por ejemplo: '180º40'20\"E o O'.");
                        if (!comprobaLongitud(atributo)) throw new SQLException("La longitud del sector es incorrecte.");
                        newSector.setLongitud(atributo);
                        sectorDAO.update(newSector);
                        break;
                    case 4:
                        atributo = demanaString(Main.scan,100,"Introduce la nueva aproximacion del sector.");
                        newSector.setAproximacio(atributo);
                        sectorDAO.update(newSector);
                        break;
                    case 5:
                        atributo = demanaString(Main.scan,20,"Introduce la nueva popularidad del sector.");
                        if (!comprobaPopularitat(atributo)) throw new SQLException("La popularitat del sector es incorrecte.");
                        newSector.setPopularitat(atributo);
                        sectorDAO.update(newSector);
                        break;
                    case 6:
                        atributo = demanaString(Main.scan,100,"Introduce la nueva restriccion del sector.");
                        newSector.setRestriccions(atributo);
                        sectorDAO.update(newSector);
                        break;
                }
            } catch (SQLException | InputMismatchException e) {
                View.mostrarMsg(e.getMessage());
            }
        }
    }
}