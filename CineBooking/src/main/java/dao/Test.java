//package com.example.demo_jdbc_mysql.dao;
//
//import com.example.demo_jdbc_mysql.model.User;
//import java.sql.CallableStatement;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//
//public class CustomerRepository implements ICustomerRepository{
//
//
//    private static final String INSERT_USERS_SQL = "INSERT INTO users" + "  (name, email, country) VALUES " +
//            " (?, ?, ?);";
//    private static final String SELECT_USER_BY_ID = "select id,name,email,country from users where id =?";
//    private static final String SELECT_ALL_USERS = "select * from users";
//    private static final String DELETE_USERS_SQL = "delete from users where id = ?;";
//    private static final String UPDATE_USERS_SQL = "update users set name = ?,email= ?, country =? where id = ?;";
//    private static final String SEARCH_BY_COUNTRY = "select * from users where country like (?);";
//    private static final String SORT_BY_NAME = "select * from users order by name;";
//
//    private final String INSERT_SP = "call add_user(?,?,?);";
//    private final String SELECT_SP = "call get_all_user();";
//    private final String UPDATE_SP = "call edit_user(?,?,?,?);";
//
//    private final String DELETE_SP = "call delete_user(?);";
//
//
//
//    public void  insertUser(User user) throws SQLException {
////   INSERT_USERS_SQL = "INSERT INTO users" + "  (name, email, country) VALUES " +
////        " (?, ?, ?);";
//        Connection connection = BaseRepository.getConnection();
//        try {
//            CallableStatement callableStatement = connection.prepareCall(INSERT_SP);
//            callableStatement.setString(1, user.getName());
//            callableStatement.setString(2, user.getEmail());
//            callableStatement.setString(3, user.getCountry());
//            callableStatement.executeUpdate();
//        } catch (SQLException e) {
//            printSQLException(e);
//        }
//    }
//
//    public User selectUser(int id) {
//        User user = null;
//        Connection connection = BaseRepository.getConnection();
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);
//            preparedStatement.setInt(1, id);
//
//            ResultSet rs = preparedStatement.executeQuery();
//            while (rs.next()) {
//                String name = rs.getString("name");
//                String email = rs.getString("email");
//                String country = rs.getString("country");
//                user = new User(id, name, email, country);
//            }
//        } catch (SQLException e) {
//            printSQLException(e);
//        }
//        return user;
//    }
//
//    @Override
//    public List<User> searchByCountry(String country) {
//        List<User> users = new ArrayList<>();
//        int id;
//        String name,email;
//        Connection connection = BaseRepository.getConnection();
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_BY_COUNTRY);
//            preparedStatement.setString(1,"%"+ country + "%");
//            ResultSet rs = preparedStatement.executeQuery();
//            while (rs.next()){
//                id = rs.getInt("id");
//                name = rs.getString("name");
//                email = rs.getString("email");
//                country = rs.getString("country");
//                users.add(new User(id, name, email, country));
//            }
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return users;
//    }
//    @Override
//    public List<User> sortByName() {
//        List<User> userList = new ArrayList<>();
//        Connection connection = BaseRepository.getConnection();
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(SORT_BY_NAME);
//            ResultSet rs = preparedStatement.executeQuery();
//            while (rs.next()) {
//                int id = rs.getInt("id");
//                String name = rs.getString("name");
//                String email = rs.getString("email");
//                String country = rs.getString("country");
//                userList.add(new User(id, name, email, country));
//            }
//        } catch (SQLException e) {
//            printSQLException(e);
//        }
//        return userList;
//    }
//
//    @Override
//    public User getUserByIdStore(int id) {
//        User user = null;
//        String query = "{Call get_user_by_id(?)}";
//        Connection connection = BaseRepository.getConnection();
//        System.out.println(" prostore id");
//
//        try {
//            CallableStatement callableStatement = connection.prepareCall(query);
//            callableStatement.setInt(1,id);
//            ResultSet resultSet = callableStatement.executeQuery();
//            String name,email,country;
//
//            while (resultSet.next()){
//                name = resultSet.getString("name");
//                email = resultSet.getString("email");
//                country = resultSet.getString("country");
//                user = new User(id,name,email,country);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//        return user;
//    }
//
//    @Override
//    public void insertUserByIdStore(User user) {
//        String query = "{Call insert_user(?,?,?)}";
//        System.out.println("insert prostore");
//
//        Connection connection = BaseRepository.getConnection();
//        try {
//            CallableStatement callableStatement = connection.prepareCall(query);
//            callableStatement.setString(1,user.getName());
//            callableStatement.setString(2,user.getEmail());
//            callableStatement.setString(3,user.getCountry());
//            callableStatement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
//
//
//    public List<User> selectAllUsers() {
//        List<User> users = new ArrayList<>();
//        Connection connection = BaseRepository.getConnection();
//        try  {
//            CallableStatement callableStatement = connection.prepareCall(SELECT_SP);
//            ResultSet resultSet = callableStatement.executeQuery();
//            ResultSet rs = callableStatement.executeQuery();
//            while (rs.next()) {
//                int id = rs.getInt("id");
//                String name = rs.getString("name");
//                String email = rs.getString("email");
//                String country = rs.getString("country");
//                users.add(new User(id, name, email, country));
//            }
//        } catch (SQLException e) {
//            printSQLException(e);
//        }
//        return users;
//    }
//
//    public boolean deleteUser(int id) throws SQLException {
//        boolean rowDeleted;
//        //  DELETE_USERS_SQL = "delete from users where id = ?;";
//        Connection connection = BaseRepository.getConnection();
//        CallableStatement callableStatement = connection.prepareCall(DELETE_SP);
//        callableStatement.setInt(1, id);
//        rowDeleted = callableStatement.executeUpdate() > 0;
//        return rowDeleted;
//    }
//
//    public boolean updateUser(User user) throws SQLException {
//        boolean rowUpdated;
//        // UPDATE_USERS_SQL = "update users set name = ?,email= ?, country =? where id = ?;";
//        Connection connection = BaseRepository.getConnection();
//        try  {
//            CallableStatement callableStatement = connection.prepareCall(UPDATE_SP);
//
//            callableStatement.setString(1, user.getName());
//            callableStatement.setString(2, user.getEmail());
//            callableStatement.setString(3, user.getCountry());
//            callableStatement.setInt(4, user.getId());
//
//            rowUpdated = callableStatement.executeUpdate() > 0;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return rowUpdated;
//    }
//    @Override
//    public void addUserTransaction(User user) {
//        Connection connection = BaseRepository.getConnection();
//        try {
//            CallableStatement callableStatement = connection.prepareCall(INSERT_SP);
//            callableStatement.setString(1, user.getName());
//            callableStatement.setString(1, user.getEmail());
//            callableStatement.setString(1, user.getCountry());
//            System.out.println(callableStatement);
//            callableStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            System.out.println(e);
//        }
//    }
//
//
//    private void printSQLException(SQLException ex) {
//        for (Throwable e : ex) {
//            if (e instanceof SQLException) {
//                e.printStackTrace(System.err);
//                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
//                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
//                System.err.println("Message: " + e.getMessage());
//                Throwable t = ex.getCause();
//                while (t != null) {
//                    System.out.println("Cause: " + t);
//                    t = t.getCause();
//                }
//            }
//        }
//    }
//
//}