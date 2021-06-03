package ru.itis.trofimoff.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.trofimoff.task.models.Todo;

import java.util.List;

@Repository
@Transactional
public interface TodoRepository extends JpaRepository<Todo, Integer> {
    List<Todo> findAll();

    Todo findById(int id);

    Todo save(Todo todo);

    Todo deleteById(int id);

    @Modifying
    @Query(value = "UPDATE todos SET text = :text WHERE id = :id", nativeQuery = true)
    void update(@Param("text") String text, @Param("id") int id);
}
