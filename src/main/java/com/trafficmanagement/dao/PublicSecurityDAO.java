package com.trafficmanagement.dao;

import org.json.JSONObject;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PublicSecurityDAO {

    private static final String BASE_API_URL = "http://localhost:8080/public_security/api";
    private static final String BIND_VEHICLE_API = BASE_API_URL + "/bindVehicle";  // 绑定车辆 API
    private static final String DELETE_VEHICLE_API = BASE_API_URL + "/deleteVehicle"; // 删除备案 API
    private static final String UPLOAD_VEHICLE_API = BASE_API_URL + "/uploadVehicle"; // 上传正式车牌 API

    /**
     * 绑定车辆到公安库
     * @param vehicleNumber 车牌号（临时或正式）
     * @param driverIdCard 驾驶人身份证号
     * @return 是否绑定成功
     */
    public boolean bindVehicleToPublicSecurity(String vehicleNumber, String driverIdCard) {
        JSONObject json = new JSONObject();
        json.put("vehicle_number", vehicleNumber);
        json.put("driver_id_card", driverIdCard);
        return sendPostRequest(BIND_VEHICLE_API, json);
    }

    /**
     * 上传正式车牌到公安库
     * @param vehicleNumber 车牌号（正式）
     * @param vehicleName 车辆名称
     * @param vehicleType 车辆类型
     * @param registrationDate 登记日期
     * @param drivingLicenseNumber 绑定的驾驶证号
     * @return 是否上传成功
     */
    public boolean uploadVehicleToPublicSecurity(String vehicleNumber, String vehicleName, String vehicleType, String registrationDate, String drivingLicenseNumber) {
        JSONObject json = new JSONObject();
        json.put("vehicle_number", vehicleNumber);
        json.put("vehicle_name", vehicleName);
        json.put("vehicle_type", vehicleType);
        json.put("registration_date", registrationDate);
        json.put("driving_license_number", drivingLicenseNumber);
        return sendPostRequest(UPLOAD_VEHICLE_API, json);
    }

    /**
     * 删除公安库中的车辆备案
     * @param vehicleNumber 车牌号
     * @return 删除是否成功
     */
    public boolean deleteVehicle(String vehicleNumber) {
        JSONObject json = new JSONObject();
        json.put("vehicle_number", vehicleNumber);
        return sendPostRequest(DELETE_VEHICLE_API, json, "DELETE");
    }

    /**
     * 发送 HTTP POST 请求（默认 POST 请求）
     * @param apiUrl API 地址
     * @param jsonData 请求 JSON 数据
     * @return 请求是否成功
     */
    private boolean sendPostRequest(String apiUrl, JSONObject jsonData) {
        return sendPostRequest(apiUrl, jsonData, "POST");
    }

    /**
     * 发送 HTTP 请求（支持 POST / DELETE）
     * @param apiUrl API 地址
     * @param jsonData 请求 JSON 数据
     * @param method 请求方法（POST / DELETE）
     * @return 请求是否成功
     */
    private boolean sendPostRequest(String apiUrl, JSONObject jsonData, String method) {
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(method);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            // 发送 JSON 数据
            try (OutputStream os = conn.getOutputStream()) {
                os.write(jsonData.toString().getBytes("UTF-8"));
                os.flush();
            }

            // 获取响应状态码
            int responseCode = conn.getResponseCode();
            return responseCode == 200; // 200 代表请求成功
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
