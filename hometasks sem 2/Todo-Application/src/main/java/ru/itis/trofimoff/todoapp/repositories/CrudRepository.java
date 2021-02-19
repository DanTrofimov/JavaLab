package ru.itis.trofimoff.todoapp.repositories;

import java.util.List;

public interface CrudRepository<T> {

    void save(T entity);
    void update(T entity);
    void delete(T entity);

    List<T> findAll();
}