package repository;

import java.util.List;

public interface Repository<K, T> {
    void save(T entity);
    List<T> getAll();
    T getByKey(K key);
    void delete(K key);
}
