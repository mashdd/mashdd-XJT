<%--<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>--%>
<%--<%@ page import="java.util.List, com.trafficmanagement.model.Vehicle, com.trafficmanagement.model.DriverLicense" %>--%>
<%--<%@ page import="com.trafficmanagement.service.VehicleService, com.trafficmanagement.service.DriverLicenseService" %>--%>

<%--<%--%>
<%--    String driverId = (String) session.getAttribute("driverId");--%>

<%--    // 获取当前驾驶员的车辆列表--%>
<%--    VehicleService vehicleService = new VehicleService();--%>
<%--    List<Vehicle> vehicles = vehicleService.getVehiclesByDriverId(driverId);--%>

<%--    // 获取所有驾驶证--%>
<%--    DriverLicenseService driverLicenseService = new DriverLicenseService();--%>
<%--    List<DriverLicense> driverLicenses = driverLicenseService.getAllDriverLicenses();--%>
<%--%>--%>

<%--<!DOCTYPE html>--%>
<%--<html lang="zh">--%>
<%--<head>--%>
<%--    <meta charset="UTF-8">--%>
<%--    <title>我的车辆</title>--%>
<%--    <link rel="stylesheet" href="<%= request.getContextPath() %>/static/bootstrap/css/bootstrap.min.css">--%>
<%--</head>--%>
<%--<body>--%>
<%--<div class="container mt-4">--%>
<%--    <h2>我的车辆</h2>--%>

<%--    <!-- 车辆列表 -->--%>
<%--    <div class="card mt-3">--%>
<%--        <div class="card-header">--%>
<%--            <h4>已绑定车辆</h4>--%>
<%--        </div>--%>
<%--        <div class="card-body">--%>
<%--            <table class="table">--%>
<%--                <thead>--%>
<%--                <tr>--%>
<%--                    <th>车牌号</th>--%>
<%--                    <th>品牌</th>--%>
<%--                    <th>型号</th>--%>
<%--                    <th>操作</th>--%>
<%--                </tr>--%>
<%--                </thead>--%>
<%--                <tbody>--%>
<%--                <% for (Vehicle vehicle : vehicles) { %>--%>
<%--                <tr>--%>
<%--                    <td><%= vehicle.getPlateNumber() %></td>--%>
<%--                    <td><%= vehicle.getBrand() %></td>--%>
<%--                    <td><%= vehicle.getModel() %></td>--%>
<%--                    <td>--%>
<%--                        <!-- 绑定驾驶证的按钮 -->--%>
<%--                        <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#bindLicenseModal" data-vehicle="<%= vehicle.getPlateNumber() %>">绑定驾驶证</button>--%>
<%--                    </td>--%>
<%--                </tr>--%>
<%--                <% } %>--%>
<%--                </tbody>--%>
<%--            </table>--%>

<%--            <!-- 绑定车辆按钮 -->--%>
<%--            <div class="mt-3">--%>
<%--                <a href="#" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#bindVehicleModal">绑定新车辆</a>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>

<%--<!-- 绑定驾驶证模态框 -->--%>
<%--<div class="modal fade" id="bindLicenseModal" tabindex="-1" aria-labelledby="bindLicenseModalLabel" aria-hidden="true">--%>
<%--    <div class="modal-dialog">--%>
<%--        <div class="modal-content">--%>
<%--            <div class="modal-header">--%>
<%--                <h5 class="modal-title" id="bindLicenseModalLabel">绑定驾驶证</h5>--%>
<%--                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>--%>
<%--            </div>--%>
<%--            <div class="modal-body">--%>
<%--                <form action="<%= request.getContextPath() %>/bindLicense" method="post">--%>
<%--                    <input type="hidden" id="vehicleId" name="vehicleId">--%>
<%--                    <div class="mb-3">--%>
<%--                        <label for="driverLicense" class="form-label">选择驾驶证</label>--%>
<%--                        <select class="form-select" id="driverLicense" name="driverLicense" required>--%>
<%--                            <option value="">请选择驾驶证</option>--%>
<%--                            <% for (DriverLicense license : driverLicenses) { %>--%>
<%--                            <option value="<%= license.getLicenseNumber() %>"><%= license.getLicenseNumber() %></option>--%>
<%--                            <% } %>--%>
<%--                        </select>--%>
<%--                    </div>--%>
<%--                    <button type="submit" class="btn btn-primary">绑定</button>--%>
<%--                </form>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>

<%--<!-- 绑定车辆模态框 -->--%>
<%--<div class="modal fade" id="bindVehicleModal" tabindex="-1" aria-labelledby="bindVehicleModalLabel" aria-hidden="true">--%>
<%--    <div class="modal-dialog">--%>
<%--        <div class="modal-content">--%>
<%--            <div class="modal-header">--%>
<%--                <h5 class="modal-title" id="bindVehicleModalLabel">绑定新车辆</h5>--%>
<%--                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>--%>
<%--            </div>--%>
<%--            <div class="modal-body">--%>
<%--                <form action="<%= request.getContextPath() %>/bindVehicle" method="post">--%>
<%--                    <div class="mb-3">--%>
<%--                        <label for="plateNumber" class="form-label">车牌号</label>--%>
<%--                        <input type="text" class="form-control" id="plateNumber" name="plateNumber" required>--%>
<%--                    </div>--%>
<%--                    <div class="mb-3">--%>
<%--                        <label for="brand" class="form-label">品牌</label>--%>
<%--                        <input type="text" class="form-control" id="brand" name="brand" required>--%>
<%--                    </div>--%>
<%--                    <div class="mb-3">--%>
<%--                        <label for="model" class="form-label">型号</label>--%>
<%--                        <input type="text" class="form-control" id="model" name="model" required>--%>
<%--                    </div>--%>
<%--                    <button type="submit" class="btn btn-success">绑定</button>--%>
<%--                </form>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>

<%--<script src="<%= request.getContextPath() %>/static/bootstrap/js/bootstrap.bundle.min.js"></script>--%>
<%--<script>--%>
<%--    // 设置模态框中的 vehicleId--%>
<%--    $('#bindLicenseModal').on('show.bs.modal', function (event) {--%>
<%--        var button = $(event.relatedTarget);--%>
<%--        var vehicleId = button.data('vehicle');--%>
<%--        var modal = $(this);--%>
<%--        modal.find('#vehicleId').val(vehicleId);--%>
<%--    });--%>
<%--</script>--%>
<%--</body>--%>
<%--</html>--%>
