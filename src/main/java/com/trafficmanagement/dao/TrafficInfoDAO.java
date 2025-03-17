//// TrafficInfoDAO.java
//package com.trafficmanagement.dao;
//import com.trafficmanagement.model.TrafficInfo;
//import com.trafficmanagement.util.DBUtil;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class TrafficInfoDAO {
//    public List<TrafficInfo> getAllTrafficInfo() {
//        List<TrafficInfo> trafficInfos = new ArrayList<>();
//        Connection conn = DBUtil.getConnection();
//        String sql = "SELECT * FROM traffic_info";
//        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
//            ResultSet rs = stmt.executeQuery();
//            while (rs.next()) {
//                TrafficInfo trafficInfo = new TrafficInfo();
//                trafficInfo.setId(rs.getInt("id"));
//                trafficInfo.setRoadName(rs.getString("road_name"));
//                trafficInfo.setTrafficStatus(rs.getString("traffic_status"));
//                trafficInfo.setCreateTime(rs.getTimestamp("create_time"));
//                trafficInfos.add(trafficInfo);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            DBUtil.closeConnection(conn);
//        }
//        return trafficInfos;
//    }
//}