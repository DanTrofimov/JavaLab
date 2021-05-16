package ru.itis.trofimoff.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.trofimoff.model.Method;

public interface LogRepository extends JpaRepository<Method, Integer> {
}
