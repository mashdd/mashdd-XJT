package com.trafficmanagement.controller;

import com.trafficmanagement.service.VehicleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteVehicle")
public class DeleteVehicleController extends HttpServlet {
    private VehicleService vehicleService = new VehicleService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String vehicleNumber = request.getParameter("vehicleNumber");
        String driverIdCard = request.getParameter("driverIdCard");

        boolean success = vehicleService.deleteVehicle(vehicleNumber, driverIdCard);

        if (success) {
            response.sendRedirect("vehicleDashboard.jsp?message=车辆删除成功！");
        } else {
            response.sendRedirect("vehicleDashboard.jsp?error=车辆删除失败，请重试！");
        }
    }
}
