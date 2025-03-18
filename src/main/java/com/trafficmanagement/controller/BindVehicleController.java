//package com.trafficmanagement.controller;
//
//import com.trafficmanagement.service.VehicleService;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.util.List;
//
//@WebServlet("/BindVehicleController")
//public class BindVehicleController extends HttpServlet {
//    private VehicleService vehicleService = new VehicleService();
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        String driverIdCard = (String) session.getAttribute("driverIdCard");
//
//        if (driverIdCard == null) {
//            response.sendRedirect("login.jsp");
//            return;
//        }
//
//        // 默认省份（可根据实际情况改成从数据库查询）
//        String province = "豫";
//
//        // 获取随机生成的车牌号
//        List<String> plateList = vehicleService.generateVehicleNumbers(province);
//        request.setAttribute("plates", plateList);
//
//        // 跳转到 JSP
//        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/register_vehicle.jsp");
//        dispatcher.forward(request, response);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        String driverIdCard = (String) session.getAttribute("driverIdCard");
//        String selectedPlate = request.getParameter("selectedPlate");
//
//        if (driverIdCard == null) {
//            response.sendRedirect("login.jsp");
//            return;
//        }
//
//        if (selectedPlate == null || selectedPlate.isEmpty()) {
//            request.setAttribute("error", "请选择一个车牌号！");
//            RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/register_vehicle.jsp");
//            dispatcher.forward(request, response);
//            return;
//        }
//
//        // 绑定车辆到驾驶人
//        boolean success = vehicleService.bindVehicleToDriver(selectedPlate, driverIdCard);
//        if (success) {
//            request.setAttribute("success", "上牌成功！");
//        } else {
//            request.setAttribute("error", "上牌失败，该车牌可能已被绑定或系统错误！");
//        }
//
//        // 跳转回 JSP，显示成功或失败信息
//        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/register_vehicle.jsp");
//        dispatcher.forward(request, response);
//    }
//}
package com.trafficmanagement.controller;

import com.trafficmanagement.service.PublicSecurityService;
import com.trafficmanagement.service.VehicleService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@WebServlet("/BindVehicleController")
public class BindVehicleController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(BindVehicleController.class.getName());
    private VehicleService vehicleService = new VehicleService();
    private PublicSecurityService publicSecurityService = new PublicSecurityService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Received GET request for binding vehicle.");
        HttpSession session = request.getSession();
        String driverIdCard = (String) session.getAttribute("driverIdCard");
        LOGGER.info("Driver ID Card: " + driverIdCard);

        if (driverIdCard == null) {
            LOGGER.warning("Driver ID Card is null. Redirecting to login page.");
            response.sendRedirect("login.jsp");
            return;
        }

        // 默认省份（可根据实际情况改成从数据库查询）
        String province = "豫";

        // 获取随机生成的车牌号
        List<String> plateList = vehicleService.generateVehicleNumbers(province);
        request.setAttribute("plates", plateList);

        // 获取公安库API地址
        String apiAddress = publicSecurityService.getAPIAddress();
        request.setAttribute("apiAddress", apiAddress);

        // 跳转到 JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/select_vehicle_number.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Received POST request for binding vehicle.");
        HttpSession session = request.getSession();
        String driverIdCard = (String) session.getAttribute("driverIdCard");
        String selectedPlate = request.getParameter("selectedPlate");
        LOGGER.info("Driver ID Card: " + driverIdCard);
        LOGGER.info("Selected Plate: " + selectedPlate);

        if (driverIdCard == null) {
            LOGGER.warning("Driver ID Card is null. Redirecting to login page.");
            response.sendRedirect("login.jsp");
            return;
        }

        if (selectedPlate == null || selectedPlate.isEmpty()) {
            LOGGER.warning("Selected plate is null or empty.");
            request.setAttribute("error", "请选择一个车牌号！");
            RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/select_vehicle_number.jsp");
            dispatcher.forward(request, response);
            return;
        }

        // 绑定车辆到驾驶人
        boolean success = vehicleService.bindVehicleToDriver(selectedPlate, driverIdCard);
        if (success) {
            LOGGER.info("Vehicle binding successful.");
            request.setAttribute("success", "上牌成功！");
        } else {
            LOGGER.warning("Vehicle binding failed. The plate number may already be bound or there is a system error.");
            request.setAttribute("error", "上牌失败，该车牌可能已被绑定或系统错误！");
        }

        // 跳转回 JSP，显示成功或失败信息
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/select_vehicle_number.jsp");
        dispatcher.forward(request, response);
    }
}