package ru.itis.trofimoff;

import ru.itis.trofimoff.models.User;
import javax.sql.DataSource;
import java.util.UUID;

public class Main {

    public static void main(String[] args) {
        DataSource dataSource = SimpleDataSource.getDataSource();
        EntityManager entityManager = new EntityManager(dataSource);
        entityManager.dropTable("users");
        entityManager.createTable("users", User.class);
        UUID id = UUID.randomUUID();
        entityManager.save("users", User.builder()
                .id(id)
                .email("hello@gmail.com")
                .firstName("Dan")
                .lastName("Abramov")
                .hash(false).build());
        System.out.println(entityManager.findById("users", User.class, id));
    }
}