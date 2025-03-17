package com.trafficmanagement.dao;

import com.trafficmanagement.model.Vehicle;
import com.trafficmanagement.util.DBUtil;
import com.trafficmanagement.api.PublicSecurityAPI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 车辆管理 DAO（数据库访问对象）
 * 负责管理车辆的增删改查及与公安库的交互
 */
public class VehicleDAO {

    /**
     * 获取所有已使用的车牌号（本地数据库 + 公安备案）
     * @return 已使用的车牌号列表
     */
    public List<String> getAllUsedVehicleNumbers() {
        List<String> usedNumbers = new ArrayList<>();

        // 查询本地数据库中的车牌号
        String sql = "SELECT vehicle_number FROM vehicle";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                usedNumbers.add(rs.getString("vehicle_number"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 查询公安库中的已备案车牌号
        List<String> publicSecurityNumbers = PublicSecurityAPI.getUsedVehicleNumbers();
        usedNumbers.addAll(publicSecurityNumbers);

        return usedNumbers;
    }

    /**
     * 备案新车（仅存入交通管理数据库，不上传公安库）
     * @param vehicleName 车辆名称
     * @param vehicleType 车辆类型
     * @param registrationDate 车辆登记日期
     * @param tempVehicleNumber 生成的临时车牌号
     * @param driverIdCard 绑定的驾驶人身份证号
     * @return 备案是否成功
     */
    public boolean registerVehicle(String vehicleName, String vehicleType, String registrationDate, String tempVehicleNumber, String driverIdCard) {
        String sql = "INSERT INTO vehicle (vehicle_number, vehicle_name, vehicle_type, registration_date, driving_license_number) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tempVehicleNumber);
            stmt.setString(2, vehicleName);
            stmt.setString(3, vehicleType);
            stmt.setString(4, registrationDate);
            stmt.setString(5, driverIdCard);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 正式上牌（更换车牌号）
     * @param tempVehicleNumber 备案时生成的临时车牌号
     * @param formalVehicleNumber 正式车牌号
     * @return 是否成功更新
     */
    public boolean updateVehicleNumber(String tempVehicleNumber, String formalVehicleNumber) {
        String sql = "UPDATE vehicle SET vehicle_number = ? WHERE vehicle_number = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, formalVehicleNumber);
            stmt.setString(2, tempVehicleNumber);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 绑定驾驶人和车辆（存入 vehicle_driver 绑定表）
     * @param vehicleNumber 车牌号
     * @param driverIdCard 驾驶人身份证号
     * @return 绑定是否成功
     */
    public boolean bindVehicleToDriver(String vehicleNumber, String driverIdCard) {
        String sql = "INSERT INTO vehicle_driver (vehicle_number, driver_id_card) VALUES (?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, vehicleNumber);
            stmt.setString(2, driverIdCard);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 解绑驾驶人和车辆（删除 vehicle_driver 绑定关系）
     * @param vehicleNumber 车牌号
     * @param driverIdCard 驾驶人身份证号
     * @return 是否解绑成功
     */
    public boolean unbindVehicleFromDriver(String vehicleNumber, String driverIdCard) {
        String sql = "DELETE FROM vehicle_driver WHERE vehicle_number = ? AND driver_id_card = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, vehicleNumber);
            ps.setString(2, driverIdCard);
            return ps.executeUpdate() > 0; // 如果有记录被删除，返回 true
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 删除本地数据库中的车辆信息
     * @param vehicleNumber 车牌号
     * @return 删除是否成功
     */
    public boolean deleteVehicle(String vehicleNumber) {
        String sql = "DELETE FROM vehicle WHERE vehicle_number = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, vehicleNumber);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 删除公安库中的车辆备案信息（通过公安 API）
     * @param vehicleNumber 车牌号
     * @return 删除是否成功
     */
    public boolean deleteVehicleFromPublicSecurity(String vehicleNumber) {
        try {
            return PublicSecurityAPI.deleteVehicle(vehicleNumber);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据车牌号查询车辆信息
     * @param vehicleNumber 车牌号
     * @return 车辆对象（如果存在）
     */
    public Vehicle getVehicleByNumber(String vehicleNumber) {
        String sql = "SELECT * FROM vehicle WHERE vehicle_number = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, vehicleNumber);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Vehicle(
                        rs.getString("vehicle_number"),
                        rs.getString("vehicle_name"),
                        rs.getString("vehicle_type"),
                        rs.getDate("registration_date"),
                        rs.getString("driving_license_number")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
