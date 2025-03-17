package com.trafficmanagement.service;

import com.trafficmanagement.dao.PublicSecurityDAO;
import com.trafficmanagement.dao.UserDAO;
import com.trafficmanagement.dao.VehicleDAO;
import com.trafficmanagement.dao.VehicleDriverDAO;
import com.trafficmanagement.model.User;
import com.trafficmanagement.model.Vehicle;
import org.json.JSONObject;

import java.util.*;

public class VehicleService {
    private VehicleDAO vehicleDAO = new VehicleDAO();
    private VehicleDriverDAO vehicleDriverDAO = new VehicleDriverDAO();
    private PublicSecurityDAO publicSecurityDAO = new PublicSecurityDAO();


    // 新车备案：生成临时车牌并存入交通库
    public boolean registerVehicle(String vehicleName, String vehicleType, String registrationDate, String driverIdCard) {
        String tempVehicleNumber = generateTempVehicleNumber(); // 生成临时车牌
        return vehicleDAO.registerVehicle(vehicleName, vehicleType, registrationDate, tempVehicleNumber, driverIdCard);
    }

    // 生成唯一的临时车牌
    private String generateTempVehicleNumber() {
        Random random = new Random();
        char letter = (char) ('A' + random.nextInt(26)); // 生成随机字母
        int number = random.nextInt(90000) + 10000; // 生成5位随机数
        return "TEMP" + letter + number; // 例如 "TEMPX12345"
    }

    public boolean upgradeToFormalPlate(String tempVehicleNumber, String formalVehicleNumber) {
        // 先获取车辆信息（临时车牌）
        Vehicle vehicle = vehicleDAO.getVehicleByNumber(tempVehicleNumber);
        if (vehicle == null) return false;

        // 更新车牌（把临时车牌替换为正式车牌）
        boolean success = vehicleDAO.updateVehicleNumber(tempVehicleNumber, formalVehicleNumber);
        if (success) {
            // 只有成功更新车牌后，才将数据同步到公安库
            return publicSecurityDAO.uploadVehicleToPublicSecurity(
                    formalVehicleNumber,
                    vehicle.getVehicleName(),
                    vehicle.getVehicleType(),
                    vehicle.getRegistrationDate().toString(),
                    vehicle.getDrivingLicenseNumber()
            );
        }
        return false;
    }


    // 生成20个未被使用的车牌号
    public List<String> generateVehicleNumbers(String province) {
        List<String> allUsedNumbers = vehicleDAO.getAllUsedVehicleNumbers(); // 获取已使用的车牌号
        List<String> availableNumbers = new ArrayList<>();

        Random random = new Random();
        while (availableNumbers.size() < 20) {
            String plateNumber = generatePlateNumber(province, random); // 生成车牌号
            // 如果该车牌号没有被使用过，则将其加入到可用车牌号列表
            if (!allUsedNumbers.contains(plateNumber)) {
                availableNumbers.add(plateNumber);
            }
        }
        return availableNumbers;
    }

    // 生成车牌号的辅助方法，包含省份、省内字母和数字部分
    private String generatePlateNumber(String province, Random random) {
        char letter = (char) ('A' + random.nextInt(26)); // 随机字母
        int number = random.nextInt(90000) + 10000; // 随机5位数字，范围从10000到99999
        return province + letter + number;
    }

    /**
     * 绑定车辆到用户，并备案到公安数据库
     * @param plateNumber 车牌号
     * @param driverIdCard 车主身份证号
     * @return 是否绑定成功
     */
    public boolean bindVehicleToDriver(String plateNumber, String driverIdCard) {
        // 检查该车辆是否已被绑定
        if (vehicleDriverDAO.isVehicleBound(plateNumber)) {
            return false; // 车辆已被绑定，无法重复绑定
        }

        // 插入车辆绑定信息
        boolean isBound = vehicleDriverDAO.bindVehicleToDriver(plateNumber, driverIdCard);

        // 如果数据库绑定成功，则调用公安库 API 备案
        if (isBound) {
            // 调用虚拟公安库的API
            return publicSecurityDAO.bindVehicleToPublicSecurity(plateNumber, driverIdCard);
        }
        return false;
    }

    // 删除车辆，先解绑用户，再删除本地和公安库的备案
    public boolean deleteVehicle(String vehicleNumber, String driverIdCard) {
        // 删除车辆绑定关系
        boolean unbindSuccess = vehicleDriverDAO.unbindVehicleFromDriver(vehicleNumber, driverIdCard);
        if (!unbindSuccess) {
            return false;
        }

        // 删除公安库中的车辆备案
        boolean deleteFromPublicSecurity = publicSecurityDAO.deleteVehicle(vehicleNumber);
        if (!deleteFromPublicSecurity) {
            return false;
        }

        // 删除本地数据库中的车辆信息
        return vehicleDAO.deleteVehicle(vehicleNumber);
    }

    // 用户登录服务
    public class UserService {
        public User loginUser(String idCard, String password) {
            UserDAO userDAO = new UserDAO();
            return userDAO.loginUser(idCard, password); // 确保 User 对象不为空
        }
    }
}
