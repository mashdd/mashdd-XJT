package com.trafficmanagement.api;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * PublicSecurityAPI 类用于与公安系统进行交互，包括上传车辆信息、获取已备案车牌号、注册车辆和删除车辆信息等操作。
 */
public class PublicSecurityAPI {
    private static final Logger LOGGER = Logger.getLogger(PublicSecurityAPI.class.getName());
    private static final String API_BASE_URL = "http://localhost:8080/public_security/api/";
    private static final String API_URL = "http://public-security-api.com/registerVehicle"; // 公安 API 地址

    /**
     * 创建 HttpURLConnection 对象并设置请求方法和请求头。
     *
     * @param urlString      请求的 URL 字符串
     * @param requestMethod  请求方法（如 GET、POST）
     * @return HttpURLConnection 对象
     * @throws Exception 如果在创建连接时发生错误
     */
    private static HttpURLConnection createConnection(String urlString, String requestMethod) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(requestMethod);
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);
        return conn;
    }

    /**
     * 上传车辆信息到公安系统。
     *
     * @param vehicleNumber          车辆正式车牌号
     * @param vehicleName            车辆名称
     * @param vehicleType            车辆类型
     * @param registrationDate       登记日期
     * @param drivingLicenseNumber   绑定的驾驶证号
     * @return 是否上传成功
     */
    public static boolean uploadVehicleToPublicSecurity(String vehicleNumber, String vehicleName, String vehicleType, String registrationDate, String drivingLicenseNumber) {
        HttpURLConnection conn = null;
        try {
            conn = createConnection(API_BASE_URL + "uploadVehicle", "POST");
            // 构造 JSON 请求体
            JSONObject requestBody = new JSONObject();
            requestBody.put("vehicle_number", vehicleNumber);
            requestBody.put("vehicle_name", vehicleName);
            requestBody.put("vehicle_type", vehicleType);
            requestBody.put("registration_date", registrationDate);
            requestBody.put("driving_license_number", drivingLicenseNumber);

            // 发送请求
            try (OutputStream os = conn.getOutputStream()) {
                os.write(requestBody.toString().getBytes("UTF-8"));
            }

            return conn.getResponseCode() == 200;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error uploading vehicle to public security", e);
            return false;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    /**
     * 获取所有已备案的车牌号。
     *
     * @return 已备案车牌号列表
     */
    public static List<String> getUsedVehicleNumbers() {
        List<String> usedNumbers = new ArrayList<>();
        HttpURLConnection conn = null;
        try {
            conn = createConnection(API_BASE_URL + "getUsedVehicleNumbers", "GET");
            conn.setRequestProperty("Accept", "application/json");

            // 读取响应
            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    response.append(line);
                }

                JSONArray jsonArray = new JSONArray(response.toString());
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject obj = jsonArray.getJSONObject(i);
                    usedNumbers.add(obj.getString("vehicle_number"));
                }
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error fetching used vehicle numbers", e);
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return usedNumbers;
    }

    /**
     * 注册车辆信息到公安系统。
     *
     * @param vehicleNumber          车辆正式车牌号
     * @param vehicleName            车辆名称
     * @param vehicleType            车辆类型
     * @param registrationDate       登记日期
     * @param drivingLicenseNumber   绑定的驾驶证号
     * @return 是否注册成功
     */
    public static boolean registerVehicle(String vehicleNumber, String vehicleName, String vehicleType, String registrationDate, String drivingLicenseNumber) {
        HttpURLConnection conn = null;
        try {
            conn = createConnection(API_URL, "POST");
            // 构造 JSON 请求体
            JSONObject json = new JSONObject();
            json.put("vehicleNumber", vehicleNumber);
            json.put("vehicleName", vehicleName);
            json.put("vehicleType", vehicleType);
            json.put("registrationDate", registrationDate);
            json.put("drivingLicenseNumber", drivingLicenseNumber);

            // 发送请求
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = json.toString().getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            return conn.getResponseCode() == 200;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error registering vehicle", e);
            return false;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    /**
     * 删除公安库中的车辆备案信息。
     *
     * @param vehicleNumber 车辆正式车牌号
     * @return 是否删除成功
     */
    public static boolean deleteVehicle(String vehicleNumber) {
        HttpURLConnection conn = null;
        try {
            conn = createConnection(API_BASE_URL + "deleteVehicle?vehicleNumber=" + vehicleNumber, "DELETE");
            return conn.getResponseCode() == 200;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error deleting vehicle", e);
            return false;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }
}