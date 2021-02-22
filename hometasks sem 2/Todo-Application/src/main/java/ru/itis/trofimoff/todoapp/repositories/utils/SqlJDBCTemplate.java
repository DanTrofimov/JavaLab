package ru.itis.trofimoff.todoapp.repositories.utils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SqlJDBCTemplate<T> {
    private DataSource dataSource;

    public SqlJDBCTemplate(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // на update, insert и пр
    public <T> int execute(String sql, Object ... args) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            return preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new IllegalStateException(ex);
        }
    }

    // на update, insert и пр + generated keys
    public <T> int execute(String sql, Map<String, Object> generatedKeys, Object ... args) {
        try (
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {;
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            int result = preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                // uses only for todos, but need to replace by more generic version
                generatedKeys.put("id", resultSet.getInt("id"));
            }
            return result;
        } catch (SQLException ex) {
            throw new IllegalStateException(ex);
        }
    }

    public T queryForObject(String sql, RowMapper<T> rowMapper, Object ... args) {
        ResultSet resultSet = null;
        try (
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {;
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return rowMapper.mapRow(resultSet);
            }
        } catch (SQLException ex) {
            throw new IllegalStateException(ex);
        }
        return null;
    }

    // на select и пр.
    public <T> List<T> query(String sql, RowMapper<T> rowMapper, Object ... args) {
        ResultSet resultSet = null;
        List<T> resultEntities = new ArrayList<>();
        try (
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){;
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                resultEntities.add(rowMapper.mapRow(resultSet));
            }
            return resultEntities;
        } catch (SQLException ex) {
            throw new IllegalStateException(ex);
        }
    }
}