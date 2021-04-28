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

        Expression expressionAge = new Expression("users.age", "=", "69");
        Expression expressionName = new Expression("users.firstName", "=", "'Bob'");
        Expression expressionSoname = new Expression("users.lastName", "=", "'Martin'");

        Criteria criteria = new Criteria.Builder()
                .single(expressionName)
                .and(expressionAge)
                .or(expressionSoname)
                .build();

        System.out.println(entityManager.findBy(User.class, criteria));
    }
}

/* todo:
    1) setAccessible(true) -> ... -> setAccessible(false)
    2) add setters
 */
