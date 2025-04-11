package model.dao.interfaces;

import model.classes.Sector;
import model.dao.DAO;

import java.sql.SQLException;

public interface SectorDAO extends DAO<Sector,Integer> {
    public int getNumVies(int idEscola) throws SQLException;
    public int getSectorIdByNom(String nom) throws SQLException;
}
