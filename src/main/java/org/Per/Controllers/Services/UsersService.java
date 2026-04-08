package org.Per.Controllers.Services;

import org.Per.Controllers.Entities.Users;
import org.Per.Controllers.Utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.security.MessageDigest;


public class UsersService implements UsersInterface<Users>{
    Connection con;

    public UsersService() {
        con = MyDataBase.getInstance().getConnection();
    }

    public static String md5Hash(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashBytes = md.digest(password.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void ajouter(Users users) throws SQLException {
        String sql = "INSERT INTO users (username, email, password, role, numtel, points, created_at) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, users.getUsername());
        ps.setString(2, users.getEmail());

        String hashedPassword = md5Hash(users.getPassword());
        ps.setString(3, hashedPassword);

        ps.setString(4, users.getRole());
        ps.setString(5, users.getNumtel());
        ps.setInt(6, users.getPoints());
        ps.setString(7, users.getCreated_at());

        ps.executeUpdate();

    }

    @Override
    public void supprimer(int id) throws SQLException {
        String sql = "DELETE FROM `users` WHERE `id`=?";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
        System.out.println("User Deleted");


    }

    @Override
    public List<Users> afficher() throws SQLException {
        List<Users> usersList = new ArrayList<>();
        String sql = "SELECT * FROM users";
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            Users users = new Users();
            users.setId(rs.getInt("id"));
            users.setPoints(rs.getInt("points"));
            users.setUsername(rs.getString("username"));
            users.setEmail(rs.getString("email"));
            users.setPassword(rs.getString("password"));
            users.setRole(rs.getString("role"));
            users.setNumtel(rs.getString("numtel"));
            users.setCreated_at(rs.getString("created_at"));
            usersList.add(users);
        }
        return usersList;


    }

    @Override
    public void modifier(Users users) throws SQLException {
        String sql = "UPDATE users SET username=?, email=?, password=?, role=?, numtel=?, points=?, created_at=? WHERE id=?";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, users.getUsername());
        ps.setString(2, users.getEmail());

        String hashedPassword = md5Hash(users.getPassword());
        ps.setString(3, hashedPassword);

        ps.setString(4, users.getRole());
        ps.setString(5, users.getNumtel());
        ps.setInt(6, users.getPoints());
        ps.setString(7, users.getCreated_at());
        ps.setInt(8, users.getId());

        ps.executeUpdate();
        System.out.println("User updated successfully!");
    }

}
