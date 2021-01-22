package ru.itis.trofimoff;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.HashMap;

public class EntityManager {
    private final DataSource dataSource;

    private final HashMap<String, String> types = new HashMap<>();
    //language=SQL
    private static final String SQL_CREATE_TABLE = "create table ";
    //language=SQL
    private static final String SQL_DROP_TABLE = "drop table ";
    //language=SQL
    private static final String SQL_SAVE = "insert into ";
    //language=SQL
    private static final String SQL_FIND = "select * from ";

    public EntityManager(DataSource dataSource) {
        this.dataSource = dataSource;
        this.types.put("string", "varchar");
        this.types.put("long", "bigint");
        this.types.put("integer", "int");
    }

    private String getDbType(String fieldType) {
        String type = types.get(fieldType);
        return type != null ? type : fieldType;
    }

    public void save(String tableName, Object entity) {
        Connection connection = null;
        PreparedStatement statement = null;
        Class<?> entityClass = entity.getClass();

        // constructing full sql query
        StringBuilder sql = new StringBuilder(SQL_SAVE);
        sql.append(tableName.trim()).append(" values(");
        Field[] fields = entityClass.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            sql.append("?");
            if (i + 1 != fields.length) {
                sql.append(", ");
            }
        }
        sql.append(");");

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

        // constructing full sql query
        StringBuilder sql = new StringBuilder(SQL_CREATE_TABLE);
        sql.append(tableName.trim()).append("(");
        Field[] fields = entityClass.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            sql.append(fields[i].getName()).append(" ");
            sql.append(getDbType(fields[i].getType().getSimpleName().toLowerCase()));
            if (i + 1 != fields.length) {
                sql.append(",");
            }
        }
        sql.append(");");

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

        // constructing sql query
        StringBuilder sql = new StringBuilder(SQL_FIND);
        T result;
        sql.append(tableName.trim()).append(" WHERE id=?");

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
        StringBuilder sql = new StringBuilder(SQL_DROP_TABLE);
        sql.append(tableName);

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
