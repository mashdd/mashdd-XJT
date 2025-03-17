<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>新车备案</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css">
</head>
<body class="bg-light">
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="#">交通管理系统</a>
    </div>
</nav>

<div class="container mt-4">
    <h3>新车备案</h3>

    <form action="${pageContext.request.contextPath}/registerVehicle" method="post">
        <div class="mb-3">
            <label for="vehicleName" class="form-label">车辆名称</label>
            <input type="text" class="form-control" id="vehicleName" name="vehicleName" required>
        </div>
        <div class="mb-3">
            <label for="vehicleType" class="form-label">车辆类型</label>
            <input type="text" class="form-control" id="vehicleType" name="vehicleType" required>
        </div>
        <div class="mb-3">
            <label for="registrationDate" class="form-label">注册日期</label>
            <input type="date" class="form-control" id="registrationDate" name="registrationDate" required>
        </div>
        <button type="submit" class="btn btn-primary">备案车辆</button>
    </form>
</div>

<script src="${pageContext.request.contextPath}/static/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>
