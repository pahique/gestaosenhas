package processoseletivo.gestaosenhas.dao;

import java.util.List;

public interface Dao<T> {
    
    T get(long id);
    
    List<T> getAll();
    
    void save(T obj);
    
    void update(T obj);
    
    void delete(T obj);
}
