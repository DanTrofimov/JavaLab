package ru.itis.trofimoff;

import javax.sql.DataSource;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.Properties;

public class EntityManager {
    private final DataSource dataSource;

    private final static Properties typeCast = new Properties();
    private static final String SQL_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS ";
    private static final String SQL_DROP_TABLE = "DROP TABLE IF EXISTS ";
    private static final String SQL_SAVE = "INSERT INTO ";
    private static final String SQL_FIND = "SELECT * FROM ";

    public EntityManager(DataSource dataSource) {
        this.dataSource = dataSource;
        try {
            typeCast.load(ClassLoader.getSystemResourceAsStream("db_types.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void save(String tableName, Object entity) {
        Class<?> entityClass = entity.getClass();
        StringBuilder sql = new StringBuilder(SQL_SAVE);
        sql.append(tableName.trim()).append(" VALUES(");
        Field[] fields = entityClass.getDeclaredFields();

        for (int i = 0; i < fields.length; i++) {
            sql.append("?");
            if (i + 1 != fields.length) {
                sql.append(", ");
            }
        }

        sql.append(");");

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql.toString())) {

            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                statement.setObject(i + 1, fields[i].get(entity));
            }

            statement.executeUpdate();
        } catch (SQLException | IllegalAccessException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public <T> void createTable(String tableName, Class<T> entityClass) {
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
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql.toString());
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public <T, ID> T findById(String tableName, Class<T> resultType, ID idValue) {
        StringBuilder sql = new StringBuilder(SQL_FIND);
        T result;
        sql.append(tableName.trim()).append(" WHERE id=?");

        ResultSet resultSet = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql.toString())) {

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
                    // empty
                }
            }
        }
    }

    private String getDbType(String fieldType) {
        String type = typeCast.getProperty(fieldType);
        return type != null ? type : fieldType;
    }

    public void dropTable(String tableName) {
        StringBuilder sql = new StringBuilder(SQL_DROP_TABLE);
        sql.append(tableName);

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql.toString());
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
