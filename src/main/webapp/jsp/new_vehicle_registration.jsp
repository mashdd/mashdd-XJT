<%--
  Created by IntelliJ IDEA.
  User: 19939
  Date: 2025/3/16
  Time: 11:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>新车上牌</title>
    <link rel="stylesheet" href="<c:url value='../static/bootstrap/css/bootstrap.min.css' />">
</head>
<body>
<div class="container mt-4">
    <h3>新车上牌结果</h3>
    <div class="alert alert-<c:choose><c:when test="${param.status == 'success'}">success</c:when><c:otherwise>danger</c:otherwise></c:choose>">
        <c:choose>
            <c:when test="${param.status == 'success'}">
                <strong>成功！</strong> 车辆已成功上牌并备案到公安数据库。
            </c:when>
            <c:otherwise>
                <strong>失败！</strong> 车辆上牌失败，请检查输入信息。
            </c:otherwise>
        </c:choose>
    </div>
</div>
<script src="<c:url value='../static/bootstrap/js/bootstrap.bundle.min.js' />"></script>
</body>
</html>

