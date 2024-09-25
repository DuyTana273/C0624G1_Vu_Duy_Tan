
package com.example.demo.demo.repository.user;


import com.example.demo.demo.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserRepository implements IUserRepository {
    private static final String INSERT_USER_SQL = "INSERT INTO users ( account, password,email,name,address,phone_number,role) VALUES (?,?,?,?,?,?,?);";
    private static final String SELECT_USER = "SELECT * FROM users WHERE account = ? AND password = ?";
    private static final String SELECT_USER_SQL = "SELECT * FROM users ";
    private static final String UPDATE_USER_SQL = "UPDATE users SET name = ?, email = ?, phone_number = ?, address = ? WHERE account = ?";
    private static final String CHANGE_PASSWORD_SQL = "UPDATE users SET password = ? WHERE account = ?";
    private static final String DELETE_USER_BY_ACCOUNT_SQL = "DELETE FROM users WHERE account = ?";
    private static final String UPDATE1_USER_SQL = "UPDATE users SET account = ?, email = ?, name = ?, phone_number = ?, address = ?, role = ? WHERE account = ?";
    private static final String DELETE_USER_BY_ID_SQL = "DELETE FROM users WHERE user_id = ?";
    BaseRepository baseRepository = new BaseRepository();

    @Override
    public boolean updateUser1(User user) throws SQLException {
        try (Connection connection = baseRepository.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE1_USER_SQL)) {
            preparedStatement.setString(1, user.getAccount());
            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setString(4, user.getPhone());
            preparedStatement.setString(5, user.getAddress());
            preparedStatement.setString(6, user.getRole());
            preparedStatement.setString(7, user.getAccount());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public boolean deleteUserById(int userId) throws SQLException {
        try (Connection connection = baseRepository.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_BY_ID_SQL)) {
            preparedStatement.setInt(1, userId);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        }
    }

    @Override
    public boolean insertUser(User user) throws SQLException {
        try (Connection connection = baseRepository.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL)) {
            preparedStatement.setString(1, user.getAccount());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getName());
            preparedStatement.setString(5, user.getAddress());
            preparedStatement.setString(6, user.getPhone());
            preparedStatement.setString(7, user.getRole());
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    @Override
    public boolean deleteUserByAccount(String account) throws SQLException {
        try (Connection connection = baseRepository.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_BY_ACCOUNT_SQL)) {
            preparedStatement.setString(1, account);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        }
    }

    @Override
    public User authenticateUser(String account, String password) throws SQLException {
        try (Connection connection = baseRepository.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER)) {
            preparedStatement.setString(1, account);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("user_id"),
                        rs.getString("account"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("name"),
                        rs.getString("phone_number"),
                        rs.getString("address"),
                        rs.getString("role")
                );
            }
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        try (Connection connection = baseRepository.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_SQL)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("user_id");
                String account = rs.getString("account");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String phone = rs.getString("phone_number");
                String role = rs.getString("role");
                User user = new User(id,account, password, email, name, address, phone, role);
                users.add(user);
            }

        }
        return users;
    }


    @Override
    public boolean changePassword(String pass,User user) throws SQLException {
        try(Connection connection = baseRepository.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_PASSWORD_SQL)) {
            preparedStatement.setString(2, user.getAccount());
            preparedStatement.setString(1, pass);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        }
    }

    @Override
    public boolean updateUser(User user) throws SQLException {
        try (Connection connection = baseRepository.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_SQL)) {

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPhone());
            preparedStatement.setString(4, user.getAddress());
            preparedStatement.setString(5, user.getAccount());
            int result = preparedStatement.executeUpdate();
            return result > 0;
        }
    }
}
