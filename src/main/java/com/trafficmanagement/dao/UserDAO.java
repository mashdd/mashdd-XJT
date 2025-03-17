package com.trafficmanagement.dao;

import com.trafficmanagement.model.User;
import com.trafficmanagement.util.DBUtil;
import java.sql.*;

public class UserDAO {

    // 用户注册
    public boolean registerUser(User user) {
        String sql = "INSERT INTO user (id_card, password) VALUES (?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getIdCard());
            stmt.setString(2, user.getPassword());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 用户登录验证
    public User loginUser(String idCard, String password) {
        String sql = "SELECT * FROM user WHERE id_card = ? AND password = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, idCard);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // 如果找到匹配的用户，返回该用户信息
                User user = new User();
                user.setIdCard(rs.getString("id_card"));
                user.setPassword(rs.getString("password"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // 如果未找到该用户，返回 null
    }

}
