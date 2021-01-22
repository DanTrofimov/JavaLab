package ru.itis.application.repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SimpleJdbcTemplate {
    private DataSource dataSource;

    public SimpleJdbcTemplate(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // на update, insert и пр
    public <T> int execute(String sql, Object ... args) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                statement.setObject(i + 1, args[i]);
            }
            return statement.executeUpdate();
        } catch (SQLException ex) {
            throw new IllegalStateException(ex);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                }
            }
        }
    }

    // на select и пр.
    public <T> List<T> query(String sql, RowMapper<T> rowMapper, Object ... args) {
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement statement = null;
        List<T> resultEntities = new ArrayList<>();

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                statement.setObject(i + 1, args[i]);
            }
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                resultEntities.add(rowMapper.mapRow(resultSet));
            }
            return resultEntities;
        } catch (SQLException ex) {
            throw new IllegalStateException(ex);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                }
            }
        }
    }
}
