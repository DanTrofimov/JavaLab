package ru.itis.trofimoff.todoapp.repositories;

import ru.itis.trofimoff.todoapp.models.User;
import ru.itis.trofimoff.todoapp.repositories.utils.RowMapper;
import ru.itis.trofimoff.todoapp.repositories.utils.SqlJDBCTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class UserRepository implements CrudRepository<User> {
    //language=SQL
    private String SQL_INSERT_USER = "INSERT INTO users(name, email, password, role) VALUES(?, ?, ?, ?)";
    //language=SQL
    private String SQL_SELECT_USER_BY_EMAIL = "SELECT * FROM users WHERE email = ? ";
    //language=SQL
    private String sqlDeleteFormat = "DELETE FROM users WHERE id = ?";
    //language=SQL
    private String SQL_SELECT_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
    //language=SQL
    private String SQL_SELECT_ALL_USERS = "SELECT * FROM users WHERE role = 'user'";
    private DataSource dataSource;
    private SqlJDBCTemplate sqlJDBCTemplate;

    private RowMapper<User> userRowMapper = row -> User.builder()
            .email(row.getString("email").trim())
            .password(row.getString("password").trim())
            .name(row.getString("name").trim())
            .id(row.getInt("id"))
            .role(row.getString("role").trim())
            .allTodos(row.getInt("alltodos"))
            .doneTodos(row.getInt("donetodos"))
            .build();

    public UserRepository(DataSource dataSource) {
        this.dataSource = dataSource;
        this.sqlJDBCTemplate = new SqlJDBCTemplate(dataSource);
    }

    // adding user into the DB
    @Override
    public void save(User user){
        sqlJDBCTemplate.execute(SQL_INSERT_USER, user.getName(), user.getEmail(), user.getPassword(), user.getRole());
    }

    // finding user in the DB
    // user need to have password & email
    // fixme: findByEmail, use only email
    public Optional<User> checkUser(User user) {
        User userResult;
        try {
            userResult = (User) sqlJDBCTemplate.query(SQL_SELECT_USER_BY_EMAIL, userRowMapper, user.getEmail()).get(0);
        } catch (Exception e) { // fixme
            userResult = null;
        }
        return Optional.ofNullable(userResult);
    }

    public Optional<User> findById(int id) {
        User userResult;
        try {
            userResult =  (User) this.sqlJDBCTemplate.query(SQL_SELECT_USER_BY_ID, userRowMapper, id).get(0);
        } catch (Exception ex) { // fixme
            userResult = null;
        }
        return Optional.ofNullable(userResult);
    }

    @Override
    public List<User> findAll() {
        return this.sqlJDBCTemplate.query(SQL_SELECT_ALL_USERS, userRowMapper);
    }

    @Override
    public void update(User entity) {}

    @Override
    public void delete(User entity) {}
}
