package ru.itis.trofimoff;

import ru.itis.trofimoff.annotations.ModelTable;
import ru.itis.trofimoff.criteria.Criteria;
import ru.itis.trofimoff.exceptions.*;
import ru.itis.trofimoff.helpers.EntityHelper;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
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

    public void setEntityValue(Class<?> entityClass, Object instance, String fieldName, Object value) {
        StringBuilder setter = EntityHelper.getSetterName(fieldName);
        try {
            entityClass.getDeclaredMethod(setter.toString(), value.getClass()).invoke(instance, value);
        } catch (NoSuchMethodException ex) {
            throw new CantFindEntityMethodException();
        } catch (InvocationTargetException ex) {
            throw new UnknownSetterException();
        } catch (IllegalAccessException ex) {
            throw new NoAccessToEntityMethodException();
        }
    }

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
        }  catch (IllegalAccessException ex) {
            throw new NoAccessToEntityMethodException();
        } catch (SQLException ex) {
            throw new ConnectDatabaseException();
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
            throw new CantFindEntityMethodException();
        }
    }

    public <T, ID> List<T> findBy(Class<T> resultType, Criteria criteria) {

        T result;

        QueryConstructor.init();
        StringBuilder sql = QueryConstructor.findBy(getModelTableName(resultType), criteria.query);

        System.out.println(sql.toString());

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
                    // Class<?> entityClass, Object instance, String field, Object value
                    setEntityValue(resultType, result, field.getName(), resultSet.getObject(field.getName()));
                    field.setAccessible(false);
                }
                resultList.add(result);
            }
            return resultList;
        } catch (InstantiationException ex) {
            throw new CreateNewEntityInstanseException();
        } catch (SQLException ex) {
            throw new ConnectDatabaseException();
        } catch (IllegalAccessException ex) {
            throw new NoAccessToEntityMethodException();
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
            throw new ConnectDatabaseException();
        }
    }
}
