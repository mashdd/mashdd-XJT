package com.trafficmanagement.dao;

import com.trafficmanagement.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VehicleDriverDAO {
    /**
     * 检查车辆是否已被绑定
     */
    public boolean isVehicleBound(String vehicleNumber) {
        String sql = "SELECT COUNT(*) FROM vehicle_driver WHERE vehicle_number = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, vehicleNumber);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // 若查询结果大于 0，则车辆已被绑定
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 绑定车辆到驾驶人
     */
    public boolean bindVehicleToDriver(String vehicleNumber, String driverIdCard) {
        String sql = "INSERT INTO vehicle_driver (vehicle_number, driver_id_card, authorized_date) VALUES (?, ?, NOW())";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, vehicleNumber);
            pstmt.setString(2, driverIdCard);
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 解绑车辆与驾驶员的绑定关系
     * @param vehicleNumber 车牌号
     * @param driverIdCard  驾驶员身份证号
     * @return 是否解绑成功
     */
    public boolean unbindVehicleFromDriver(String vehicleNumber, String driverIdCard) {
        String sql = "DELETE FROM vehicle_driver WHERE vehicle_number = ? AND driver_id_card = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, vehicleNumber);
            pstmt.setString(2, driverIdCard);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0; // 如果删除成功，则返回 true
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
