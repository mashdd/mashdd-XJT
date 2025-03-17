package com.trafficmanagement.controller;

import com.google.gson.Gson;
import com.trafficmanagement.service.VehicleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/generatePlateNumbers")
public class GeneratePlateNumbersController extends HttpServlet {
    private VehicleService vehicleService = new VehicleService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String province = request.getParameter("province");
        List<String> plateNumbers = vehicleService.generateVehicleNumbers(province);
        request.setAttribute("plateNumbers", plateNumbers);
        request.getRequestDispatcher("new_vehicle_registration.jsp").forward(request, response);
    }
}
