package model.dao.interfaces;

import model.classes.Escalador;
import model.dao.DAO;

import java.sql.SQLException;

public interface EscaladorDAO extends DAO<Escalador,Integer> {
    public int getEscaladorIdByNom(String nom) throws SQLException;
    public boolean existeEscalador(String alies);
}
