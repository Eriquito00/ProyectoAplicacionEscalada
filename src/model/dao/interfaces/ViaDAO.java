package model.dao.interfaces;

import model.classes.Via;
import model.dao.DAO;

import java.sql.SQLException;

public interface ViaDAO extends DAO<Via,Integer> {
    int getNumVies(int idEscola) throws SQLException;
    int getViaIdByNom(String nom, String escola) throws SQLException;
    String getDificultatByNom(String nom, String escola) throws SQLException;
    Boolean existeVia(String nom, String escola) throws SQLException;
}
