package model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public interface DAO <T,K> {
    public abstract void create(T o) throws SQLException;
    public abstract T read(K key) throws SQLException;
    public abstract void update(T o) throws SQLException;
    public abstract void delete(K key) throws SQLException;
}
