package ru.itis.trofimoff.todoapp.repositories.utils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SqlJDBCTemplate<T> {
    private DataSource dataSource;

    public SqlJDBCTemplate(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // на update, insert и пр
    public <T> int execute(String sql, Object ... args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            return preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new IllegalStateException(ex);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
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
        PreparedStatement preparedStatement = null;
        List<T> resultEntities = new ArrayList<>();
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(sql);
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
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
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