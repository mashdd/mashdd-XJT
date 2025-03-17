package com.trafficmanagement.controller;

import com.trafficmanagement.service.VehicleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registerVehicle")
public class RegisterVehicleController extends HttpServlet {
    private VehicleService vehicleService = new VehicleService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String vehicleName = request.getParameter("vehicleName");
        String vehicleType = request.getParameter("vehicleType");
        String registrationDate = request.getParameter("registrationDate");
        String driverIdCard = (String) request.getSession().getAttribute("driverIdCard");

        boolean success = vehicleService.registerVehicle(vehicleName, vehicleType, registrationDate, driverIdCard);
        if (success) {
            response.sendRedirect("register_vehicle.jsp?status=success");
        } else {
            response.sendRedirect("register_vehicle.jsp?status=failed");
        }
    }
}
