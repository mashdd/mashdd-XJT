package com.trafficmanagement.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/traffic_management?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root"; // 你的数据库用户名
    private static final String PASSWORD = "jasl123."; // 你的数据库密码
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    static {
        try {
            Class.forName(DRIVER); // 加载 MySQL 驱动
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("无法加载 MySQL 驱动", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
