package ru.itis.trofimoff.crf.repositories;

import java.util.Optional;

public interface CrudRepository<T> {
    void save(T entity);
    void update(T entity);
    Optional<T> findOne(Long id);
}
