package ru.itis.trofimoff;

import ru.itis.trofimoff.models.User;

import javax.sql.DataSource;
import java.util.UUID;

public class Main {

    public static void main(String[] args) {
        DataSource dataSource = SimpleDataSource.getDataSource();
        EntityManager entityManager = new EntityManager(dataSource);
        entityManager.dropTable("users");
        entityManager.createTable(User.class);
        UUID id = UUID.randomUUID();
        User user = new User(id, "Bob", "Martin", 69, "solid@gmail.com");
        entityManager.save( user);
        System.out.println(entityManager.findById(User.class, id));
    }
}