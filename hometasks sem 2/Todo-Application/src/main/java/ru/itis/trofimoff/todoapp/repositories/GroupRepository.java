//package ru.itis.trofimoff.todoapp.repositories;
//
//import SemesterWork1.models.Group;
//import SemesterWork1.services.DataBaseConnector;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class GroupRepository implements CrudRepository {
//    private Connection conn;
//    private ResultSet result;
//    private PreparedStatement preparedStatement;
//    private String sqlSelectAllGroups = "SELECT groups.id, groups.name FROM groups";
//
//    @Override
//    public List<Group> findAll() {
//        List<Group> groups = new ArrayList<Group>();
//        try {
//            DataBaseConnector connector = new DataBaseConnector();
//            conn = connector.getConnection();
//            preparedStatement = conn.prepareStatement(sqlSelectAllGroups);
//            result = preparedStatement.executeQuery();
//            while(result.next()){
//                int groupId = result.getInt(1);
//                String groupName = result.getString(2);
//                Group group = new Group(groupName, groupId);
//                groups.add(group);
//            }
//            preparedStatement.close();
//            conn.close();
//            return groups;
//        }  catch(SQLException se) {
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
//    }
//
//    @Override
//    public Object findById(int id) {
//        return null;
//    }
//
//    @Override
//    public void save(Object entity) {
//
//    }
//
//    @Override
//    public void update(Object entity) {
//
//    }
//
//    @Override
//    public void deleteById(int id) {
//
//    }
//}
