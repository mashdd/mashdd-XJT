<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>主页</title>
  <link href="<c:url value="/static/bootstrap/css/bootstrap.min.css"/>" rel="stylesheet">
</head>
<body class="bg-light">
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container">
    <a class="navbar-brand" href="#">交通管理系统</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item"><a class="nav-link" href="traffic.jsp">交通信息</a></li>
        <li class="nav-item"><a class="nav-link" href="event.jsp">交通事件</a></li>
      </ul>
      <ul class="navbar-nav ml-auto">
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown">
            ${user.username} <span class="caret"></span>
          </a>
          <ul class="dropdown-menu dropdown-menu-end">
            <li><a class="dropdown-item" href="profile.jsp">个人信息</a></li>
            <li><a class="dropdown-item" href="logout.jsp">退出登录</a></li>
          </ul>
        </li>
      </ul>
    </div>
  </div>
</nav>

<div class="container mt-4">
  <div class="row justify-content-center">
    <div class="col-md-8">
      <div class="card shadow">
        <div class="card-body">
          <h3 class="card-title text-center mb-4">欢迎，${user.username}</h3>
          <p>这是道路交通信息管理系统的主页。</p>

          <!-- 新车上牌按钮 -->
          <div class="text-center mt-4">
            <a href="register_vehicle.jsp" class="btn btn-primary btn-lg">新车上牌</a>
          </div>

        </div>
      </div>
    </div>
  </div>
</div>


<script src="<c:url value="/static/bootstrap/js/bootstrap.bundle.min.js"/>"></script>
</body>
</html>