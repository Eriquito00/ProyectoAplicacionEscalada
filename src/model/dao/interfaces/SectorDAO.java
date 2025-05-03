package model.dao.interfaces;

import model.classes.Sector;
import model.dao.DAO;

import java.sql.SQLException;

public interface SectorDAO extends DAO<Sector,Integer> {
    int getNumVies(int idEscola) throws SQLException;
    int getSectorIdByNom(String nom) throws SQLException;
    String getEscola(String sector) throws SQLException;
    Boolean sectorExists(String nom, String escola) throws SQLException;
}
