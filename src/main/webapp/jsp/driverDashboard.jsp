<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    // 从 Session 获取 driverIdCard
    String driverIdCard = (String) session.getAttribute("driverIdCard");

    // 如果 driverIdCard 为空，则重定向到登录页面
    if (driverIdCard == null) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
%>

<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>驾驶员主页</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css">
    <style>
        .card-hover:hover {
            transform: scale(1.05);
            transition: 0.3s ease-in-out;
        }
    </style>
</head>
<body class="bg-light">
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="#">交通管理系统</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav me-auto">
                <li class="nav-item"><a class="nav-link" href="register_vehicle.jsp">新车注册登记</a></li>
                <li class="nav-item"><a class="nav-link" href="select_vehicle_number.jsp">新车选号</a></li>
                <li class="nav-item"><a class="nav-link" href="violationQuery.jsp">违章查询</a></li>
            </ul>
            <ul class="navbar-nav ms-auto">
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/logout">退出登录</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container mt-4">
    <h3 class="text-center">欢迎，驾驶员 <%= driverIdCard %></h3>

    <!-- 机动车业务 -->
    <div class="row mt-4">
        <div class="col-md-12">
            <h4 class="mb-3">机动车业务</h4>
            <div class="row">
                <div class="col-md-6">
                    <div class="card card-hover shadow-sm">
                        <div class="card-body">
                            <h5 class="card-title">新车注册登记</h5>
                            <p class="card-text">登记新购车辆信息，获取临时车牌。</p>
                            <a href="register_vehicle.jsp" class="btn btn-primary">前往登记</a>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="card card-hover shadow-sm">
                        <div class="card-body">
                            <h5 class="card-title">新车选号</h5>
                            <p class="card-text">为已登记的车辆选择正式车牌号。</p>
                            <a href="select_vehicle_number.jsp" class="btn btn-primary">前往选号</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- 交通违法业务 -->
    <div class="row mt-4">
        <div class="col-md-12">
            <h4 class="mb-3">交通违法业务</h4>
            <div class="row">
                <div class="col-md-6">
                    <div class="card card-hover shadow-sm">
                        <div class="card-body">
                            <h5 class="card-title">违章查询</h5>
                            <p class="card-text">查询当前登记车辆的交通违章记录。</p>
                            <a href="violationQuery.jsp" class="btn btn-danger">查询违章</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/static/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>
