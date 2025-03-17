//// TrafficInfoServlet.java
//package com.trafficmanagement.service;
//import com.trafficmanagement.dao.TrafficInfoDAO;
//import com.trafficmanagement.model.TrafficInfo;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.List;
//
//public class TrafficInfoServlet extends HttpServlet {
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String action = req.getParameter("action");
//        if (action.equals("add")) {
//            // 添加交通信息逻辑
//        } else if (action.equals("delete")) {
//            // 删除交通信息逻辑
//        }
//        req.getRequestDispatcher("traffic.jsp").forward(req, resp);
//    }
//
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        TrafficInfoDAO trafficInfoDAO = new TrafficInfoDAO();
//        List<TrafficInfo> trafficInfos = trafficInfoDAO.getAllTrafficInfo();
//        req.setAttribute("trafficInfos", trafficInfos);
//        req.getRequestDispatcher("traffic.jsp").forward(req, resp);
//    }
//}