package com.trafficmanagement.controller;

import com.trafficmanagement.service.VehicleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/generatePlates")
public class VehicleController extends HttpServlet {
    private VehicleService vehicleService = new VehicleService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();

        // 获取用户身份证所在省份（前端传递）
        String province = request.getParameter("province");

        List<String> plates = vehicleService.generateVehicleNumbers(province);

        // 返回 JSON 数据
        out.print("[\"" + String.join("\", \"", plates) + "\"]");
        out.flush();
    }
}
