package ru.itis.trofimoff.todoapp.repositories;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.itis.trofimoff.todoapp.models.Todo;
import ru.itis.trofimoff.todoapp.repositories.utils.RowMapper;
import ru.itis.trofimoff.todoapp.repositories.utils.SqlJDBCTemplate;

import javax.sql.DataSource;;
import java.util.HashMap;
import java.util.List;

@Repository
public class TodoRepository implements CrudRepository<Todo> {

    //language=SQL
    private final String SQL_INSERT_TODO = "INSERT INTO todos(text, todogroup) VALUES(?, ?)";
    //language=SQL
    private final String SQL_DELETE_TODO = "DELETE FROM todos WHERE id = ?";
    //language=SQL
    private final String SQL_SELECT_ALL_TODOS = "SELECT userstodo.userid, todos.text, todos.id, todos.todogroup FROM userstodo JOIN todos ON userstodo.todoid = todos.id WHERE userstodo.userid = ?";
    //language=SQL
    private final String  SQL_SELECT_ALL_TODOS_BY_GROUP = "SELECT userstodo.userid, todos.text, todos.id, todos.todogroup FROM userstodo JOIN todos ON userstodo.todoid = todos.id WHERE userstodo.userid = ? AND todos.todogroup = ?";
    //language=SQL
    private final String SQL_UPDATE_TODO = "UPDATE todos SET text = ? WHERE id = ?";
    //language=SQL
    private final String SQL_INCREMENT_USER_STAT_ALL = "UPDATE users SET alltodos = alltodos + 1 WHERE id = ?";
    //language=SQL
    private final String SQL_INCREMENT_USER_STAT_DONE = "UPDATE users SET donetodos = donetodos + 1 WHERE id = ?";
    //language=SQL
    private final String SQL_USERSTODO_INSERT = "INSERT INTO userstodo(userid, todoid) VALUES(?, ?)";
    private DataSource dataSource;
    private SqlJDBCTemplate sqlJDBCTemplate;

    private RowMapper<Todo> todoRowMapper = row -> Todo.builder()
            .text(row.getString("text").trim())
            .id(row.getInt("id"))
            .groupId(row.getInt("todogroup"))
            .build();

    public TodoRepository(DataSource dataSource) {
        this.dataSource = dataSource;
        this.sqlJDBCTemplate = new SqlJDBCTemplate(dataSource);
    }

    public void saveUserTodo(Todo todo, int userId) {
        HashMap keys = new HashMap();
        this.sqlJDBCTemplate.execute(SQL_INSERT_TODO, keys, todo.getText(), todo.getGroupId());
        int todoId = (int) keys.get("id");
        this.sqlJDBCTemplate.execute(SQL_USERSTODO_INSERT, userId, todoId);
        this.sqlJDBCTemplate.execute(SQL_INCREMENT_USER_STAT_ALL, userId);
    }

    public void saveTodo(Todo todo) {
        HashMap keys = new HashMap();
        this.sqlJDBCTemplate.execute(SQL_INSERT_TODO, keys, todo.getText(), todo.getGroupId());
        todo.setId((int) keys.get("id"));
    }

    public void bindUserWithTodo(int todoId, int userId) {
        this.sqlJDBCTemplate.execute(SQL_USERSTODO_INSERT, userId, todoId);
        this.sqlJDBCTemplate.execute(SQL_INCREMENT_USER_STAT_ALL, userId);
    }

     public List<Todo> getUserTodos(int userId) {
        return this.sqlJDBCTemplate.query(SQL_SELECT_ALL_TODOS, todoRowMapper, userId);
     }

     public List<Todo> getUserTodos(int userId, int todoGroup) {
        return this.sqlJDBCTemplate.query(SQL_SELECT_ALL_TODOS_BY_GROUP, todoRowMapper, userId, todoGroup);
     }

    public void update(Todo todo) {
        this.sqlJDBCTemplate.execute(SQL_UPDATE_TODO, todo.getText(), todo.getId());
    }


    public void deleteById(int todoId, int userId) {
        this.sqlJDBCTemplate.execute(SQL_DELETE_TODO, todoId);
        this.sqlJDBCTemplate.execute(SQL_INCREMENT_USER_STAT_DONE, userId);
    }


    @Override
    public void save(Todo entity) {}

    @Override
    public void delete(Todo entity) {}

    @Override
    public List<Todo> findAll() {return null;}
}
