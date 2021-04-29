package ru.itis.trofimoff;

import ru.itis.trofimoff.criteria.Criteria;
import ru.itis.trofimoff.criteria.Expression;
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
        User bob = new User(id, "Bob", "Martin", 69, "solid@gmail.com");
        User barbara = new User(id, "Barbara", "Liskov", 81, "barbara@gmail.com");
        User linus = new User(id, "Linus", "Torvalds", 51, "linux@gmail.com");

        entityManager.save(bob);
        entityManager.save(barbara);
        entityManager.save(linus);

        Expression expressionAge = new Expression("users.age", "=", new String[] {"69"});
        Expression expressionName = new Expression("users.firstName", "=", new String[] {"'Bob'"});
        Expression expressionSoname = new Expression("users.lastName", "=",new String[] {"'Liskov'"});
        Expression expressionArray = new Expression("users.firstName", "in",new String[] {"'Bob'", "'Barbara'"});

        Criteria criteria = new Criteria.Builder()
                .single(expressionName)
                .and(expressionAge)
                .or(expressionSoname)
                .build();

        Criteria criteriaArray = new Criteria.Builder()
                .single(expressionArray)
                .build();

        // findAll
        Criteria criteriaAll = new Criteria.Builder()
                .clear()
                .build();

        System.out.println(entityManager.findBy(User.class, criteria));
        System.out.println(entityManager.findBy(User.class, criteriaArray));
        System.out.println(entityManager.findBy(User.class, criteriaAll));
    }
}