package ru.itis.trofimoff.crf.repositories;

import org.springframework.stereotype.Component;
import ru.itis.trofimoff.crf.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Optional;

@Component(value = "usersRepositoryJdbcImpl")
public class UsersRepositoryJdbcImpl implements UsersRepository {

    //language=SQL
    private static final String SQL_INSERT_USER = "insert into simple_user(email, password, confirm_code) " +
            "values (?, ?, ?)";

    //language=SQL
    private static final String SQL_UPDATE_USER = "update simple_user set email = ?, password = ?, confirm_code = ?, is_deleted = ? " +
            "where id = ?";

    //language=SQL
    private static final String SQL_FIND_USER_BY_ID = "select * from simple_user where id = ?";

    //language=SQL
    private static final String SQL_FIND_USER_BY_EMAIL = "select * from simple_user where email = ?";

    private DataSource dataSource;

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(User entity) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet generatedKeys = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQL_INSERT_USER, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, entity.getEmail());
            statement.setString(2, entity.getPassword());
            statement.setString(3, entity.getConfirmCode());
            int affectedRows = statement.executeUpdate();

            if (affectedRows != 1) {
                throw new SQLException("User save not executed");
            }

            generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next()) {
                Long id = generatedKeys.getLong("id");
                entity.setId(id);
            } else {
                throw new SQLException("Something wrong");
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ignored) {
                }
            }
            if (generatedKeys != null) {
                try {
                    generatedKeys.close();
                } catch (SQLException ignored) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ignored) {
                }
            }
        }
    }

    @Override
    public void update(User entity) {
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQL_UPDATE_USER);
            statement.setString(1, entity.getEmail());
            statement.setString(2, entity.getPassword());
            statement.setString(3, entity.getConfirmCode());
            statement.setBoolean(4, entity.getIsDeleted());
            statement.setLong(5, entity.getId());

            int affectedRows = statement.executeUpdate();

            if (affectedRows != 1) {
                throw new SQLException("User update not executed");
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ignored) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ignored) {
                }
            }
        }
    }

    @Override
    public Optional<User> findOne(Long id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQL_FIND_USER_BY_ID);
            statement.setLong(1, id);
            result = statement.executeQuery();
            if (result.next()) {
                User user = User.builder()
                        .email(result.getString("email"))
                        .id(result.getLong("id"))
                        .confirmCode(result.getString("confirm_code"))
                        .password(result.getString("password"))
                        .build();
                return Optional.of(user);
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            if (result != null) {
                try {
                    result.close();
                } catch (SQLException ignored) {
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ignored) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ignored) {
                }
            }
        }
    }

    @Override
    public Optional<User> findOneByEmail(String email) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQL_FIND_USER_BY_EMAIL);
            statement.setString(1, email);
            result = statement.executeQuery();
            if (result.next()) {
                User user = User.builder()
                        .email(result.getString("email"))
                        .id(result.getLong("id"))
                        .confirmCode(result.getString("confirm_code"))
                        .password(result.getString("password"))
                        .build();
                return Optional.of(user);
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            if (result != null) {
                try {
                    result.close();
                } catch (SQLException ignored) {
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ignored) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ignored) {
                }
            }
        }
    }
}
