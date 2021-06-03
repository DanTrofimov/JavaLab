package ru.itis.trofimoff.task.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.itis.trofimoff.task.models.Todo;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class TodoJdbcRepositoryImpl implements TodoJdbcRepository {
    private final JdbcTemplate jdbcTemplate;
    //language=SQL
    private static final String SQL_SELECT_ALL_TODOS = "select * from todos";

    @Autowired
    public TodoJdbcRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Todo> findAllTodos() {
        List<Todo> todos = jdbcTemplate.query(
                SQL_SELECT_ALL_TODOS,
                (rs, rowNum) -> new Todo(rs.getInt("id"),
                        rs.getString("text")));

        return todos;
    }
}
