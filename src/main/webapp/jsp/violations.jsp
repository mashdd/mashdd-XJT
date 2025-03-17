<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>违章记录</title>
    <link rel="stylesheet" href="../static/bootstrap/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h2 class="text-center">违章记录</h2>

    <c:if test="${not empty violations}">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>违章ID</th>
                <th>车牌号</th>
                <th>违章日期</th>
                <th>违章地点</th>
                <th>扣分</th>
                <th>罚款</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="violation" items="${violations}">
                <tr>
                    <td>${violation.violationId}</td>
                    <td>${violation.plateNumber}</td>
                    <td>${violation.violationDate}</td>
                    <td>${violation.location}</td>
                    <td>${violation.pointsDeducted}</td>
                    <td>${violation.fineAmount}</td>
                    <td>
                        <form action="processViolation" method="post">
                            <input type="hidden" name="violationId" value="${violation.violationId}" />
                            <input type="hidden" name="plateNumber" value="${violation.plateNumber}" />
                            <button type="submit" class="btn btn-primary">处理违章</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>

    <c:if test="${empty violations}">
        <p>没有违章记录。</p>
    </c:if>
</div>

<script src="../static/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>
