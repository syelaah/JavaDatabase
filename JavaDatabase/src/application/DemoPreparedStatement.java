package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.ConnectionUtil;

/**
 *
 * @author sye
 */
public class DemoPreparedStatement {
    static Connection connection;
    static PreparedStatement preparedStatement;
    static ResultSet rs;
    
    public static void main(String[] args) {
        try {
            connection = ConnectionUtil.getConnection();

            String username = "admin";
            String password = "password";
            
            String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";
            
            // statement = connection.createrStatement();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            System.out.println(sql);
 
            // execute the query
            rs = preparedStatement.executeQuery();
 
            if (rs.next()) {
                System.out.println("Login success");
            } else {
                System.out.println("Login FAILED");
            }
            
            String newUser = "superuser";
            String newUserPass = "it is a password";
            
            sql = "INSERT INTO admin (username, password) VALUE (?, ?)";
            
            // statement = connection.createStatement();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, newUser);
            preparedStatement.setString(2, newUserPass);
            
            int affectedRows = preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
