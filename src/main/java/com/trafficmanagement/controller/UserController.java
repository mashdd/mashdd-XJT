package com.trafficmanagement.controller;

import com.trafficmanagement.model.User;
import com.trafficmanagement.service.UserService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/UserController")
public class UserController extends HttpServlet {
    private UserService userService = new UserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("register".equals(action)) {
            register(request, response);
        } else if ("login".equals(action)) {
            login(request, response);
        }
    }

    private void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idCard = request.getParameter("idcard");
        String password = request.getParameter("password");

        if (userService.registerUser(idCard, password)) {
            response.sendRedirect("login.jsp?msg=register_success");
        } else {
            response.sendRedirect("register.jsp?msg=register_fail");
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idCard = request.getParameter("id_card");
        String password = request.getParameter("password");

        User user = userService.loginUser(idCard, password);
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect(request.getContextPath() + "/jsp/driverDashboard.jsp");
        } else {
            response.sendRedirect("login.jsp?msg=login_fail");
        }
    }
}
