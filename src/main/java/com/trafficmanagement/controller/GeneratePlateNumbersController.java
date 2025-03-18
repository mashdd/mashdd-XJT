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
import java.util.logging.Logger;

@WebServlet("/generatePlateNumbers")
public class GeneratePlateNumbersController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(GeneratePlateNumbersController.class.getName());
    private VehicleService vehicleService = new VehicleService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Received request to generate plate numbers.");
        String province = request.getParameter("province");
        LOGGER.info("Province: " + province);
        List<String> plateNumbers = vehicleService.generateVehicleNumbers(province);
        LOGGER.info("Generated plate numbers: " + plateNumbers);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String json = new Gson().toJson(plateNumbers);
        response.getWriter().write("{\"plateNumbers\":" + json + "}");
    }
}