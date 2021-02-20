package ru.itis.trofimoff.todoapp.repositories;

//import SemesterWork1.models.Todo;
//import SemesterWork1.services.DataBaseConnector;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class TodoRepository implements CrudRepository<Todo> {
//
//    private Connection conn;
//    private ResultSet result;
//    private PreparedStatement preparedStatement;
//    private String sqlInsertTodoFormat = "INSERT INTO todos(text, todogroup) VALUES('%s', %d)";
//    private String sqlDeleteTodoFormat = "DELETE FROM todos WHERE id = %d";
//    private String sqlSelectAllTodosFormat = "SELECT userstodo.userid, todos.text, todos.id, todos.todogroup FROM userstodo JOIN todos ON userstodo.todoid = todos.id WHERE userstodo.userid = %d";
//    private String sqlUpdateTodoFormat = "UPDATE todos SET text = '%s' WHERE id = %d";
//    private String sqlIncrementUserStatFormat = "UPDATE users SET %s = %s + 1 WHERE id = %d";
//    private String sqlInsertUserTodoFormat = "INSERT INTO userstodo(userid, todoid) VALUES(%d, %d)";
//
//    public void saveUserTodo(Todo todo, int userId) {
//        String text = todo.getText();
//        int todoGroup = todo.getGroupId();
//        try {
//            DataBaseConnector connector = new DataBaseConnector();
//            conn = connector.getConnection();
//            preparedStatement = conn.prepareStatement(String.format(sqlInsertTodoFormat, text, todoGroup), Statement.RETURN_GENERATED_KEYS);
//            preparedStatement.executeUpdate();
//            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
//            if (generatedKeys.next()) {
//                preparedStatement = conn.prepareStatement(String.format(sqlInsertUserTodoFormat, userId, generatedKeys.getInt("id")));
//                preparedStatement.executeUpdate();
//            } else {
//                throw new SQLException("Problem with retrieve id");
//            }
//            preparedStatement = conn.prepareStatement(String.format(sqlIncrementUserStatFormat, "alltodos", "alltodos", userId));
//            preparedStatement.executeUpdate();
//            preparedStatement.close();
//            conn.close();
//        } catch(SQLException se) {
//            System.out.println(se.getMessage());
//        } finally {
//            if (preparedStatement != null) {
//                try {
//                    preparedStatement.close();
//                } catch (SQLException ex) {
//                    System.out.println("Problems with a saving user. Can't close statement.");
//                }
//            }
//            if (conn != null) {
//                try {
//                    conn.close();
//                } catch (SQLException ex) {
//                    System.out.println("Problems with a saving user. Can't close connection.");
//                }
//            }
//        }
//    }
//
//    public void saveTodo(Todo todo) {
//        String text = todo.getText();
//        int todoGroup = todo.getGroupId();
//
//        try {
//            DataBaseConnector connector = new DataBaseConnector();
//            conn = connector.getConnection();
//            preparedStatement = conn.prepareStatement(String.format(sqlInsertTodoFormat, text, todoGroup), Statement.RETURN_GENERATED_KEYS);
//            preparedStatement.executeUpdate();
//            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
//            if (generatedKeys.next()) {
//                todo.setId(generatedKeys.getInt("id"));
//            } else {
//                throw new SQLException("Problem with retrieve id");
//            }
//            preparedStatement.close();
//            conn.close();
//        } catch(SQLException se) {
//            System.out.println(se.getMessage());
//        } finally {
//            if (preparedStatement != null) {
//                try {
//                    preparedStatement.close();
//                } catch (SQLException ex) {
//                    System.out.println("Problems with a saving user. Can't close statement.");
//                }
//            }
//            if (conn != null) {
//                try {
//                    conn.close();
//                } catch (SQLException ex) {
//                    System.out.println("Problems with a saving user. Can't close connection.");
//                }
//            }
//        }
//    };
//
//    public void bindUserWithTodo(int todoId, int userId) {
//        try {
//            DataBaseConnector connector = new DataBaseConnector();
//            conn = connector.getConnection();
//            preparedStatement = conn.prepareStatement(String.format(sqlInsertUserTodoFormat, userId, todoId));
//            preparedStatement.executeUpdate();
//            preparedStatement = conn.prepareStatement(String.format(sqlIncrementUserStatFormat, "alltodos", "alltodos", userId));
//            preparedStatement.executeUpdate();
//            preparedStatement.close();
//            conn.close();
//        } catch(SQLException se) {
//            System.out.println(se.getMessage());
//        } finally {
//            if (preparedStatement != null) {
//                try {
//                    preparedStatement.close();
//                } catch (SQLException ex) {
//                    System.out.println("Problems with a saving user. Can't close statement.");
//                }
//            }
//            if (conn != null) {
//                try {
//                    conn.close();
//                } catch (SQLException ex) {
//                    System.out.println("Problems with a saving user. Can't close connection.");
//                }
//            }
//        }
//    };
//
//    public List<Todo> getUserTodos(int userId) {
//        List<Todo> todos = new ArrayList<Todo>();
//        try {
//            DataBaseConnector connector = new DataBaseConnector();
//            conn = connector.getConnection();
//            preparedStatement = conn.prepareStatement(String.format(sqlSelectAllTodosFormat, userId));
//            result = preparedStatement.executeQuery();
//            while(result.next()){
//                String text = result.getString(2);
//                int todoId = result.getInt(3);
//                int todoGroup = result.getInt(4);
//                Todo todo = new Todo(todoId, text, todoGroup);
//                todos.add(todo);
//            }
//            preparedStatement.close();
//            conn.close();
//            return todos;
//        } catch(SQLException se) {
//            System.out.println(se.getMessage());
//        } finally {
//            if (preparedStatement != null) {
//                try {
//                    preparedStatement.close();
//                } catch (SQLException ex) {
//                    System.out.println("Problems with a saving user. Can't close statement.");
//                }
//            }
//            if (conn != null) {
//                try {
//                    conn.close();
//                } catch (SQLException ex) {
//                    System.out.println("Problems with a saving user. Can't close connection.");
//                }
//            }
//        }
//        return null;
//    };
//
//    public List<Todo> getUserTodos(int userId, int todoGroup) {
//        List<Todo> todos = new ArrayList<Todo>();
//        try {
//            DataBaseConnector connector = new DataBaseConnector();
//            conn = connector.getConnection();
//            String sqlSelectTodosByUserGroup = "SELECT userstodo.userid, todos.text, todos.id, todos.todogroup FROM userstodo JOIN todos ON userstodo.todoid = todos.id WHERE userstodo.userid = %d AND todos.todogroup = %d";
//            preparedStatement = conn.prepareStatement(String.format(sqlSelectTodosByUserGroup, userId, todoGroup));
//            result = preparedStatement.executeQuery();
//            while(result.next()){
//                String text = result.getString(2);
//                int todoId = result.getInt(3);
//                Todo todo = new Todo(todoId, text, todoGroup);
//                todos.add(todo);
//            }
//            preparedStatement.close();
//            conn.close();
//            return todos;
//        } catch(SQLException se) {
//            System.out.println(se.getMessage());
//        } finally {
//            if (preparedStatement != null) {
//                try {
//                    preparedStatement.close();
//                } catch (SQLException ex) {
//                    System.out.println("Problems with a saving user. Can't close statement.");
//                }
//            }
//            if (conn != null) {
//                try {
//                    conn.close();
//                } catch (SQLException ex) {
//                    System.out.println("Problems with a saving user. Can't close connection.");
//                }
//            }
//        }
//        return null;
//    };
//
//    @Override
//    public void update(Todo todo) {
//        String text = todo.getText();
//        int todoId = todo.getId();
//
//        try {
//            DataBaseConnector connector = new DataBaseConnector();
//            conn = connector.getConnection();
//            preparedStatement = conn.prepareStatement(String.format(sqlUpdateTodoFormat, text, todoId));
//            preparedStatement.executeUpdate();
//            preparedStatement.close();
//            conn.close();
//        } catch(SQLException se) {
//            System.out.println(se.getMessage());
//        } finally {
//            if (preparedStatement != null) {
//                try {
//                    preparedStatement.close();
//                } catch (SQLException ex) {
//                    System.out.println("Problems with a saving user. Can't close statement.");
//                }
//            }
//            if (conn != null) {
//                try {
//                    conn.close();
//                } catch (SQLException ex) {
//                    System.out.println("Problems with a saving user. Can't close connection.");
//                }
//            }
//        }
//    }
//
//    public void deleteById(int todoId, int userId) {
//        try {
//            DataBaseConnector connector = new DataBaseConnector();
//            conn = connector.getConnection();
//            preparedStatement = conn.prepareStatement(String.format(sqlDeleteTodoFormat, todoId));
//            preparedStatement.executeUpdate();
//            preparedStatement = conn.prepareStatement(String.format(sqlIncrementUserStatFormat, "donetodos", "donetodos", userId));
//            preparedStatement.executeUpdate();
//            preparedStatement.close();
//            conn.close();
//        } catch(SQLException se) {
//            System.out.println(se.getMessage());
//        } finally {
//            if (preparedStatement != null) {
//                try {
//                    preparedStatement.close();
//                } catch (SQLException ex) {
//                    System.out.println("Problems with a saving user. Can't close statement.");
//                }
//            }
//            if (conn != null) {
//                try {
//                    conn.close();
//                } catch (SQLException ex) {
//                    System.out.println("Problems with a saving user. Can't close connection.");
//                }
//            }
//        }
//    }
//
//
//    @Override
//    public void deleteById(int id) {};
//
//    @Override
//    public void save(Todo entity) {}
//
//    @Override
//    public List<Todo> findAll() {
//        return null;
//    }
//
//    @Override
//    public Todo findById(int id) {
//        return null;
//    }
//}
