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

public class PublicSecurityAPI {
    private static final String API_BASE_URL = "http://localhost:8080/public_security/api/";
    private static final String API_URL = "http://public-security-api.com/registerVehicle"; // 公安 API 地址

    /**
     * 上传车辆信息到公安系统
     * @param vehicleNumber 车辆正式车牌号
     * @param vehicleName 车辆名称
     * @param vehicleType 车辆类型
     * @param registrationDate 登记日期
     * @param drivingLicenseNumber 绑定的驾驶证号
     * @return 是否上传成功
     */
    public static boolean uploadVehicleToPublicSecurity(String vehicleNumber, String vehicleName, String vehicleType, String registrationDate, String drivingLicenseNumber) {
        try {
            URL url = new URL(API_BASE_URL + "/uploadVehicle");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            // 构造 JSON 请求体
            JSONObject requestBody = new JSONObject();
            requestBody.put("vehicle_number", vehicleNumber);
            requestBody.put("vehicle_name", vehicleName);
            requestBody.put("vehicle_type", vehicleType);
            requestBody.put("registration_date", registrationDate);
            requestBody.put("driving_license_number", drivingLicenseNumber);

            // 发送请求
            OutputStream os = conn.getOutputStream();
            os.write(requestBody.toString().getBytes("UTF-8"));
            os.close();

            int responseCode = conn.getResponseCode();
            return responseCode == 200; // 假设 200 代表成功
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 获取所有已备案的车牌号
     */
    public static List<String> getUsedVehicleNumbers() {
        List<String> usedNumbers = new ArrayList<>();
        try {
            URL url = new URL(API_BASE_URL + "getUsedVehicleNumbers");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            br.close();

            JSONArray jsonArray = new JSONArray(response.toString());
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                usedNumbers.add(obj.getString("vehicle_number"));
            }

            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usedNumbers;
    }

    public static boolean registerVehicle(String vehicleNumber, String vehicleName, String vehicleType, String registrationDate, String drivingLicenseNumber) {
        try {
            URL url = new URL(API_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

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

            // 检查响应状态码
            int responseCode = conn.getResponseCode();
            return responseCode == 200;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除公安库中的车辆备案信息
     */
    public static boolean deleteVehicle(String vehicleNumber) {
        try {
            URL url = new URL(API_BASE_URL + "deleteVehicle?vehicleNumber=" + vehicleNumber);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("DELETE");

            int responseCode = conn.getResponseCode();
            conn.disconnect();
            return responseCode == 200;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
