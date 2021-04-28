package ru.itis.trofimoff;

import ru.itis.trofimoff.annotations.ModelTable;
import ru.itis.trofimoff.criteria.Criteria;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EntityManager {
    private final DataSource dataSource;

    public EntityManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String getModelTableName(Class<?> entityClass) {
        ModelTable annotation = entityClass.getAnnotation(ModelTable.class);
        return annotation.tableName();
    }

//    public void setEntityValue(Class<?> entity, String field, Object value) throws NoSuchMethodException {
//        entity.getDeclaredMethod(field);
//        System.out.println();
//    }

    public void save(Object entity) {

        Class<?> entityClass = entity.getClass();
        Field[] fields = entityClass.getDeclaredFields();

        QueryConstructor.init();
        StringBuilder sql = QueryConstructor.save(getModelTableName(entityClass), fields);

        // adding entity into the db
        try (
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql.toString())
        ) {
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                statement.setObject(i + 1, fields[i].get(entity));
                fields[i].setAccessible(false);
            }
            statement.executeUpdate();
        }  catch (SQLException | IllegalAccessException ex) {
            throw new IllegalArgumentException(ex);
        }
    }

    public <T> void createTable(Class<T> entityClass) {

        Field[] fields = entityClass.getDeclaredFields();

        QueryConstructor.init();
        StringBuilder sql = QueryConstructor.createTable(getModelTableName(entityClass), fields);

        // adding table into the db
        try (
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        ) {
            statement.executeUpdate(sql.toString());
        } catch (SQLException ex) {
            throw new IllegalArgumentException(ex);
        }
    }

    public <T, ID> List<T> findBy(Class<T> resultType, Criteria criteria) {

        T result;

        QueryConstructor.init();
        StringBuilder sql = QueryConstructor.findBy(getModelTableName(resultType), criteria.query);

        ResultSet resultSet = null;

        List<T> resultList = new ArrayList<>();

        // finding entity in the db
        try (
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql.toString())
        ) {
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result = resultType.newInstance();
                for (Field field : resultType.getDeclaredFields()) {
                    field.setAccessible(true);
                    field.set(result, resultSet.getObject(field.getName()));
                    field.setAccessible(false);
                }
                resultList.add(result);
            }
            return resultList;
        } catch (SQLException | IllegalAccessException | InstantiationException ex) {
            throw new IllegalArgumentException(ex);
        }
    }


    public void dropTable(String tableName) {

        // constructing sql query
        QueryConstructor.init();
        StringBuilder sql = QueryConstructor.dropTable(tableName);

        // removing table from the db
        try (
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql.toString());
        ) {
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new IllegalArgumentException(ex);
        }
    }
}
