package dao;

import model.Account;

import java.sql.*;

public class BaseRepository {
    private  static final String  jdbcURL = "jdbc:mysql://localhost:3306/cinebooking?useSSL=false&allowPublicKeyRetrieval=true";
    private static final String jdbcUsername = "root";
    private static final String jdbcPassword = "12345";
    private static final String CHECK_ACCOUNT = "Select * from accounts where user_name = ? and pass_word =?";
    private static final String CHECK_EMAIL = "Select * from accounts where email=?";
    private static final String CHECK_USERNAME = "Select * from accounts where user_name=?";
    private static final String INSERT_ACCOUNT = "Insert into accounts(user_name,pass_word,email) values (?,?,?)";
    private static final String CHANGE_PASSWORD = "Update accounts set pass_word =? Where email =?";

    private BaseRepository() {
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static boolean checkAccount(String username, String password) throws SQLException {
        Connection connection = getConnection();
        ResultSet rs = null;
        try {
            PreparedStatement statement = connection.prepareStatement(CHECK_ACCOUNT);
            statement.setString(1, username);
            statement.setString(2, password);
            rs = statement.executeQuery();

        }catch (SQLException e){
            e.printStackTrace();
        }
        return rs.next();
    }

    public static int checkEmail(String email) throws SQLException {
        Connection connection = getConnection();
        ResultSet rs = null;
        int count = 0;
        try {
            PreparedStatement statement = connection.prepareStatement(CHECK_EMAIL);
            statement.setString(1, email);
            rs = statement.executeQuery();
            while(rs.next()){
                count += 1;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return count;
    }

    public static int checkUsername(String username) throws SQLException {
        Connection connection = getConnection();
        ResultSet rs = null;
        int count = 0;
        try {
            PreparedStatement statement = connection.prepareStatement(CHECK_USERNAME);
            statement.setString(1, username);
            rs = statement.executeQuery();
            while(rs.next()){
                count += 1;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return count;
    }

    public static void changePassword(String password, String email){
        Connection connection = getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(CHANGE_PASSWORD);
            statement.setString(1,password);
            statement.setString(2,email);
            statement.executeUpdate();
        }catch (SQLException exception){
            exception.printStackTrace();
        }
    }

    public static void registerAccount(String username, String password , String email){
        Connection connection = getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(INSERT_ACCOUNT);
            statement.setString(1,username);
            statement.setString(2,password);
            statement.setString(3,email);
            statement.executeUpdate();
        }catch (SQLException exception){
            exception.printStackTrace();
        }
    }
}