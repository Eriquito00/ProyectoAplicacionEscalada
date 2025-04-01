package model.dao;

public interface DAO <T,K> {
    public void create(T o);
    public T read(K key);
    public void update(Object o);
    public void delete(Object o);
}
