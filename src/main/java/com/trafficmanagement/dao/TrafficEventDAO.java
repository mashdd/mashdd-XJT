// TrafficEventDAO.java
package com.trafficmanagement.dao;

//public class TrafficEventDAO {
//    public List<TrafficEvent> getAllTrafficEvents() {
////        List<TrafficEvent> trafficEvents = new ArrayList<>();
//////        Connection conn = DBUtil.getConnection();
////        String sql = "SELECT * FROM traffic_event";
//////        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
////            ResultSet rs = stmt.executeQuery();
////            while (rs.next()) {
////                TrafficEvent trafficEvent = new TrafficEvent();
////                trafficEvent.setId(rs.getInt("id"));
////                trafficEvent.setEventType(rs.getString("event_type"));
////                trafficEvent.setLocation(rs.getString("location"));
//////                trafficEvent.setDescription(rs.getString("description"));
//////                trafficEvent.setCreateTime(rs.getTimestamp("create_time"));
//////                trafficEvents.add(trafficEvent);
//////            }
//////        } catch (SQLException e) {
//////            e.printStackTrace();
//////        } finally {
////////            DBUtil.closeConnection(conn);
//////        }
//////        return trafficEvents;
//    }
//}