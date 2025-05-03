package model.dao;

import java.sql.SQLException;

public interface DAO <T,K> {
    //FUNCIONES GENERICAS PARA LOS OBJETOS
    void create(T o) throws SQLException;
    T read(K key) throws SQLException;
    void update(T o) throws SQLException;
    void delete(K key) throws SQLException;
}
