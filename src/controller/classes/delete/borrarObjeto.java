package controller.classes.delete;

import controller.Main;
import controller.classes.read.mostrarTodo;
import model.dao.MySQLDAO.*;
import view.View;
import static controller.functions.DemanaDades.*;

import java.sql.Connection;
import java.sql.SQLException;

public class borrarObjeto {
    public static void deleteEscoles(Connection c) throws SQLException {
        MySQLEscolaDAO escolaDAO = new MySQLEscolaDAO(c);
        View.mostrarMsg(mostrarTodo.mostrarEscoles(c));
        int id = demanaInt("Introduce la ID de la escuela que quieres eliminar.",Main.scan,1,2000000);
        escolaDAO.delete(id);
    }

    public static void deleteVies(Connection c) throws SQLException {
        MySQLViaDAO viaDAO = new MySQLViaDAO(c);
        View.mostrarMsg(mostrarTodo.mostrarVies(c));
        int id = demanaInt("Introduce la ID de la via que quieres eliminar.",Main.scan,1,2000000);
        viaDAO.delete(id);
    }

    public static void deleteSectores(Connection c) throws SQLException {
        MySQLSectorDAO sectorDAO = new MySQLSectorDAO(c);
        View.mostrarMsg(mostrarTodo.mostrarSectores(c));
        int id = demanaInt("Introduce la ID del sector que quieres eliminar.",Main.scan,1,2000000);
        sectorDAO.delete(id);
    }

    public static void deleteEscaladores(Connection c) throws SQLException {
        MySQLEscaladorDAO escaladorDAO = new MySQLEscaladorDAO(c);
        View.mostrarMsg(mostrarTodo.mostrarEscaladores(c));
        int id = demanaInt("Introduce la ID del escalador que quieres eliminar.",Main.scan,1,2000000);
        escaladorDAO.delete(id);
    }
}