package com.trafficmanagement.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PublicSecurityService {
    private static final String API_BASE_URL = "http://localhost:8080/";

    public String getAPIAddress() {
        String apiUrl = API_BASE_URL + "getAPI";
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // 返回API地址
                return response.toString();
            } else {
                System.out.println("GET请求失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}