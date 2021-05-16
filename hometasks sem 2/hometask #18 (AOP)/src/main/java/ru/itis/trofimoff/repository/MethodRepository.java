package ru.itis.trofimoff.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.trofimoff.model.Method;

@Repository
@Transactional
public interface MethodRepository extends JpaRepository<Method, Integer> {
    Method save(Method method);

    Method findByName(String name);

    @Modifying
    @Query(value = "UPDATE methods SET amount = amount + 1 WHERE name = ?1", nativeQuery = true)
    void incrementMethodAmount(String methodName);
}
