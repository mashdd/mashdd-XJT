package com.trafficmanagement.controller;

import com.trafficmanagement.service.VehicleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/registerVehicle")
public class RegisterVehicleController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(RegisterVehicleController.class.getName());
    private VehicleService vehicleService = new VehicleService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String vehicleName = request.getParameter("vehicleName");
        String vehicleType = request.getParameter("vehicleType");
        String registrationDate = request.getParameter("registrationDate");
        String driverIdCard = (String) request.getSession().getAttribute("driverIdCard");

        if (driverIdCard == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            boolean success = vehicleService.registerVehicle(vehicleName, vehicleType, registrationDate, driverIdCard);
            if (success) {
                response.sendRedirect("jsp/register_vehicle.jsp?status=success");
            } else {
                response.sendRedirect("jsp/register_vehicle.jsp?status=failed");
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error registering vehicle", e);
            response.sendRedirect("jsp/register_vehicle.jsp?status=failed");
        }
    }
}