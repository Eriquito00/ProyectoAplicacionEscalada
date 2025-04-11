package model.dao.interfaces;

import model.classes.Escola;
import model.dao.DAO;

import java.sql.SQLException;

public interface EscolaDAO extends DAO<Escola,Integer> {
    public boolean existeEscola(String escolaNom) throws SQLException;
    public int getIdEscola(String escolaNom) throws SQLException;
}
