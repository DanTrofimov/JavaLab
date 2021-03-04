//package ru.itis.trofimoff.todoapp.repositories.todo;
//
//import org.springframework.stereotype.Repository;
//import ru.itis.trofimoff.todoapp.models.Todo;
//import ru.itis.trofimoff.todoapp.repositories.utils.RowMapper;
//import ru.itis.trofimoff.todoapp.repositories.utils.SqlJDBCTemplate;
//
//import javax.sql.DataSource;;
//import java.util.HashMap;
//import java.util.List;
//
//@Repository
//public class TodoRepositoryImpl implements TodoRepository {
//
//    //language=SQL
//    private final String SQL_INSERT_TODO = "INSERT INTO todos(text, todogroup) VALUES(?, ?)";
//    //language=SQL
//    private final String SQL_DELETE_TODO = "DELETE FROM todos WHERE id = ?";
//    //language=SQL
//    private final String SQL_SELECT_ALL_TODOS = "SELECT userstodo.userid, todos.text, todos.id, todos.todogroup FROM userstodo JOIN todos ON userstodo.todoid = todos.id WHERE userstodo.userid = ?";
//    //language=SQL
//    private final String  SQL_SELECT_ALL_TODOS_BY_GROUP = "SELECT userstodo.userid, todos.text, todos.id, todos.todogroup FROM userstodo JOIN todos ON userstodo.todoid = todos.id WHERE userstodo.userid = ? AND todos.todogroup = ?";
//    //language=SQL
//    private final String SQL_UPDATE_TODO = "UPDATE todos SET text = ? WHERE id = ?";
//    //language=SQL
//    private final String SQL_INCREMENT_USER_STAT_ALL = "UPDATE users SET alltodos = alltodos + 1 WHERE id = ?";
//    //language=SQL
//    private final String SQL_INCREMENT_USER_STAT_DONE = "UPDATE users SET donetodos = donetodos + 1 WHERE id = ?";
//    //language=SQL
//    private final String SQL_USERSTODO_INSERT = "INSERT INTO userstodo(userid, todoid) VALUES(?, ?)";
//    private DataSource dataSource;
//    private SqlJDBCTemplate sqlJDBCTemplate;
//
//    private RowMapper<Todo> todoRowMapper = row -> Todo.builder()
//            .text(row.getString("text").trim())
//            .id(row.getInt("id"))
//            .group.getId(row.getInt("todogroup"))
//            .build();
//
//    public TodoRepositoryImpl(DataSource dataSource) {
//        this.dataSource = dataSource;
//        this.sqlJDBCTemplate = new SqlJDBCTemplate(dataSource);
//    }
//
//
//
//
//    public List<Todo> getUserTodos(int userId) {
//        return this.sqlJDBCTemplate.query(SQL_SELECT_ALL_TODOS, todoRowMapper, userId);
//    }
//
//    public List<Todo> getUserTodos(int userId, int todoGroup) {
//        return this.sqlJDBCTemplate.query(SQL_SELECT_ALL_TODOS_BY_GROUP, todoRowMapper, userId, todoGroup);
//    }
//
//    public void update(Todo todo) { // done
//        this.sqlJDBCTemplate.execute(SQL_UPDATE_TODO, todo.getText(), todo.getId());
//    }
//
//
//
//
//
//    // по сути можно перенести по отдельности в сервисе вызывать
//    public void deleteByIdAndChangeUserStat(int todoId, int userId) {
//        this.deleteTodo(todoId);
//        this.incrementUserStatDone(userId);
//    }
//
//    //fixme: for deleteByIdAndChangeUserStat
//    public void deleteTodo(int todoId) { // done
//        this.sqlJDBCTemplate.execute(SQL_DELETE_TODO, todoId);
//    }
//    public void incrementUserStatDone(int userId) { // done
//        this.sqlJDBCTemplate.execute(SQL_INCREMENT_USER_STAT_DONE, userId);
//    }
//
//    public void bindUserWithTodo(int todoId, int userId) {
//        this.insertTodoIntoUsersTodo(todoId, userId);
//        this.incrementUserStatAll(userId);
//    }
//
//    // fixme: for bindUserWithTodo
//    public void insertTodoIntoUsersTodo(int todoId, int userId) { // done
//        this.sqlJDBCTemplate.execute(SQL_USERSTODO_INSERT, userId, todoId);
//    }
//    public void incrementUserStatAll(int userId) { // done
//        this.sqlJDBCTemplate.execute(SQL_INCREMENT_USER_STAT_ALL, userId);
//    }
//
//
//    @Override
//    public void save(Todo entity) {}
//
//    @Override
//    public void delete(Todo entity) {}
//
//    @Override
//    public List<Todo> findAll() {return null;}
//}
