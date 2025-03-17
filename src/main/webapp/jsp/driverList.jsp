<%@ page import="com.trafficmanagement.model.Driver" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>驾驶人列表</title>
    <!-- 使用绝对路径确保资源正确加载 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h2 class="mt-5">驾驶人列表</h2>

    <!-- 使用JSTL替代Java脚本 -->
    <c:choose>
        <c:when test="${empty drivers}">
            <p>没有找到驾驶人信息。</p>
            <p>驾驶人数量：${drivers.size()}</p>
        </c:when>
        <c:otherwise>
            <!-- 添加响应式表格容器 -->
            <div class="table-responsive">
                <table class="table table-striped mt-3">
                    <thead>
                    <tr>
                        <th>身份证号</th>
                        <th>姓名</th>
                        <th>联系电话</th>
                        <th>住址</th>
                    </tr>
                    </thead>
                    <tbody>
                    <!-- 使用JSTL循环并转义输出 -->
                    <c:forEach items="${drivers}" var="driver">
                        <tr>
                            <!-- 防止XSS攻击，对动态内容进行转义 -->
                            <td style="word-break: break-all;"><c:out value="${driver.driverId}"/></td>
                            <td><c:out value="${driver.name}"/></td>
                            <td><c:out value="${driver.phone}"/></td>
                            <td><c:out value="${driver.address}"/></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <!-- 分页组件示例（需后端实现分页逻辑） -->
            <nav aria-label="Page navigation" class="mt-3">
                <ul class="pagination justify-content-center">
                    <li class="page-item"><a class="page-link" href="#">上一页</a></li>
                    <li class="page-item active"><a class="page-link" href="#">1</a></li>
                    <li class="page-item"><a class="page-link" href="#">2</a></li>
                    <li class="page-item"><a class="page-link" href="#">下一页</a></li>
                </ul>
            </nav>
        </c:otherwise>
    </c:choose>
</div>
<script src="${pageContext.request.contextPath}/static/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>