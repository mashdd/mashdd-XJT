<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>用户登录</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h2 class="mb-4">登录</h2>

    <!-- 修改 action 为 /login，保证和 Servlet 对应 -->
    <form action="${pageContext.request.contextPath}/login" method="post">
        <div class="mb-3">
            <label for="idCard" class="form-label">身份证号</label>
            <input type="text" id="idCard" name="idCard" class="form-control" required>
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">密码</label>
            <input type="password" id="password" name="password" class="form-control" required>
        </div>
        <button type="submit" class="btn btn-success">登录</button>
    </form>

    <!-- 错误提示信息 -->
    <c:if test="${param.error == 'invalid'}">
        <div class="alert alert-danger mt-3">账号或密码错误，请重试！</div>
    </c:if>
    <c:if test="${param.error == 'empty'}">
        <div class="alert alert-warning mt-3">请填写所有字段！</div>
    </c:if>

    <p class="mt-3"><a href="register.jsp">没有账号？去注册</a></p>
</div>

<script src="${pageContext.request.contextPath}/static/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>
