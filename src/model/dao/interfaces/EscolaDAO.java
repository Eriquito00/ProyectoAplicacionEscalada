package model.dao.interfaces;

import model.classes.Escola;
import model.dao.DAO;

import java.sql.SQLException;

public interface EscolaDAO extends DAO<Escola,Integer> {
    boolean existeEscola(String escolaNom) throws SQLException;
    int getEscolaIdByNom(String escolaNom) throws SQLException;
}
