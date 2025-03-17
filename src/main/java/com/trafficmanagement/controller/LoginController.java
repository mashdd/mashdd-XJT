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

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private UserService userService = new UserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取表单数据
        String idCard = request.getParameter("idCard");
        String password = request.getParameter("password");

        // **调试：打印输出，检查参数是否正确获取**
        System.out.println("收到登录请求：idCard=" + idCard + ", password=" + password);

        if (idCard == null || password == null || idCard.trim().isEmpty() || password.trim().isEmpty()) {
            response.sendRedirect("login.jsp?error=empty"); // 防止空数据
            return;
        }

        // 验证用户身份
        User user = userService.loginUser(idCard, password);
        if (user != null) {
            // 登录成功
            HttpSession session = request.getSession();
            session.setAttribute("driverIdCard", idCard);
            response.sendRedirect("jsp/driverDashboard.jsp");
        } else {
            // 登录失败，提示错误信息
            response.sendRedirect("login.jsp?error=invalid");
        }
    }
}
