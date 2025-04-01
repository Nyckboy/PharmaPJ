package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.DataBaseManager;
import models.User;

public class UserController {
    public static void addUserToDB(String name,String username,String password,String role){
        String sqlQuerry = "INSERT INTO user(name, username, password, role) values (?,?,?,?)";
        try(Connection conn = DataBaseManager.connect();
            PreparedStatement pstmt = conn.prepareStatement(sqlQuerry)) {
            pstmt.setString(1, name);
            pstmt.setString(2, username);
            pstmt.setString(3, password);
            pstmt.setString(4, role);
            pstmt.executeQuery();
            System.out.println("User added successfully.");
        } catch (SQLException e) {
            System.out.println("Failed to add medecine user: " + e.getMessage());
        }
    }

    public static List<User> getAllUsersFromDB(){
        List<User> users = new ArrayList<>();
        String sqlQuerry = "SELECT * FROM user";

        try(Connection conn = DataBaseManager.connect();
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(sqlQuerry)) {
            while (result.next()){
                users.add(new User( result.getInt("id"),
                                    result.getString("name"),
                                    result.getString("username"),
                                    result.getString("password"),
                                    result.getString("role")));
                                }
        } catch (SQLException e) {
            System.out.println("Failed to fetch users: " + e.getMessage());
        }
        return users;
    }

    public static void updateUserToDB(User toModidy){
        String sqlQuerry = "UPDATE user set name = ?, username = ?, password = ?, role = ? where id = ?";

        try(Connection conn = DataBaseManager.connect();
            PreparedStatement pstmt = conn.prepareStatement(sqlQuerry)) {
                pstmt.setString(1, toModidy.getName());            
                pstmt.setString(2, toModidy.getUsername());            
                pstmt.setString(3, toModidy.getpassword());            
                pstmt.setString(4, toModidy.getrole());            
                pstmt.setInt(5, toModidy.getId());            
        } catch (SQLException e) {
            System.out.println("Failed to update user: " + e.getMessage());
        }
    }
}
