//// TrafficEventServlet.java
//package com.trafficmanagement.service;
//import com.trafficmanagement.dao.TrafficEventDAO;
//import com.trafficmanagement.model.TrafficEvent;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.List;
//
//public class TrafficEventServlet extends HttpServlet {
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String action = req.getParameter("action");
//        if (action.equals("add")) {
//            // 添加交通事件逻辑
//        } else if (action.equals("delete")) {
//            // 删除交通事件逻辑
//        }
//        req.getRequestDispatcher("event.jsp").forward(req, resp);
//    }
//
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        TrafficEventDAO trafficEventDAO = new TrafficEventDAO();
//        List<TrafficEvent> trafficEvents = trafficEventDAO.getAllTrafficEvents();
//        req.setAttribute("trafficEvents", trafficEvents);
//        req.getRequestDispatcher("event.jsp").forward(req, resp);
//    }
//}