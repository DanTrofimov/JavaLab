package ru.itis.trofimoff;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.HashMap;

public class EntityManager {
    private final DataSource dataSource;

    public EntityManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void save(String tableName, Object entity) {
        Connection connection = null;
        PreparedStatement statement = null;

        Class<?> entityClass = entity.getClass();
        Field[] fields = entityClass.getDeclaredFields();

        QueryConstructor.init();
        StringBuilder sql = QueryConstructor.save(tableName, fields);

        // adding entity into the db
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql.toString());
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                statement.setObject(i + 1, fields[i].get(entity));
            }

            statement.executeUpdate();
        } catch (SQLException | IllegalAccessException ex) {
            throw new IllegalArgumentException(ex);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    // ignore
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    // ignore
                }
            }
        }
    }

    public <T> void createTable(String tableName, Class<T> entityClass) {

        Field[] fields = entityClass.getDeclaredFields();

        QueryConstructor.init();
        StringBuilder sql = QueryConstructor.createTable(tableName, fields);

        Connection connection = null;
        Statement statement = null;

        // adding table into the db
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate(sql.toString());
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    // ignore
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    // ignore
                }
            }
        }
    }

    public <T, ID> T findById(String tableName, Class<T> resultType, ID idValue) {

        T result;

        QueryConstructor.init();
        StringBuilder sql = QueryConstructor.findById(tableName);

        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement statement = null;

        // finding entity in the db by id
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql.toString());
            Field id = resultType.getDeclaredField("id");
            id.setAccessible(true);
            statement.setObject(1, idValue);
            resultSet = statement.executeQuery();
            result = resultType.newInstance();
            if (resultSet.next()) {
                for (Field field : resultType.getDeclaredFields()) {
                    field.setAccessible(true);
                    field.set(result, resultSet.getObject(field.getName()));
                }
            } else {
                result = null;
            }
            return result;
        } catch (SQLException | NoSuchFieldException | IllegalAccessException | InstantiationException e) {
            throw new IllegalArgumentException(e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException throwables) {
                    // ignore
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    // ignore
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    // ignore
                }
            }
        }
    }


    public void dropTable(String tableName) {

        // constructing sql query
        QueryConstructor.init();
        StringBuilder sql = QueryConstructor.dropTable(tableName);

        Connection connection = null;
        PreparedStatement statement = null;

        // removing table from the db
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql.toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    // ignore
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    // ignore
                }
            }
        }
    }
}
