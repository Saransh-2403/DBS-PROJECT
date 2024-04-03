/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project.dao;

/**
 *
 * @author harshit_nagpal
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import project.database.DatabaseConnection;

public class UserDAO {
    public void createUser(String username, String password, String email) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT * from patient";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();
        }
        
    }
    public static void main(String args[]) throws SQLException {
        UserDAO a = new UserDAO();
        a.createUser("abc","abc","abc@gmail.com");
    }

    // Add more methods for reading, updating, and deleting users
}