package by.verdeth.dao;

import java.util.List;
import java.util.Optional;

//basic interface for the models
//CRUD - create, read, update, delete
public interface CrudDao<T> {
    Optional<T> find(Integer id);
    void save(T model);
    void update(T model);
    void delete(Integer id);

    List<T> findAll();

}
