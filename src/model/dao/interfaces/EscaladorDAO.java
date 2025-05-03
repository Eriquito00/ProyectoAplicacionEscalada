package model.dao.interfaces;

import model.classes.Escalador;
import model.dao.DAO;

import java.sql.SQLException;

public interface EscaladorDAO extends DAO<Escalador,Integer> {
    int getEscaladorIdByNom(String nom) throws SQLException;
    boolean existeEscalador(String alies);
}
