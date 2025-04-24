package model.dao.interfaces;

import model.classes.Via;
import model.dao.DAO;

import java.sql.SQLException;

public interface ViaDAO extends DAO<Via,Integer> {
    public int getNumVies(int idEscola) throws SQLException;
    public int getViaIdByNom(String nom) throws SQLException;
    public String getDificultatByNom(String nom, String escola) throws SQLException;
    public Boolean existeVia(String nom, String escola) throws SQLException;
}
