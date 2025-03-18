<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="zh">
<head>
  <meta charset="UTF-8">
  <title>新车选号</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css">
  <!-- 确保jQuery在Bootstrap之前加载 -->
  <script src="${pageContext.request.contextPath}/static/jquery/jquery.min.js"></script>
  <script src="${pageContext.request.contextPath}/static/bootstrap/js/bootstrap.bundle.min.js"></script>
</head>
<body class="bg-light">
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container">
    <a class="navbar-brand" href="#">交通管理系统</a>
  </div>
</nav>

<div class="container mt-4">
  <h3>新车选号</h3>

  <button type="button" class="btn btn-primary" onclick="generatePlateNumbers()">生成车牌号</button>

  <!-- 生成车牌号的模态框 -->
  <div class="modal fade" id="plateNumberModal" tabindex="-1" aria-labelledby="plateNumberModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="plateNumberModalLabel">选择车牌号</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <ul id="plateNumberList" class="list-group">
            <!-- 动态生成车牌号列表 -->
          </ul>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">关闭</button>
          <button type="button" class="btn btn-primary" id="confirmSelection" disabled>确定选择</button>
        </div>
      </div>
    </div>
  </div>
</div>

<script>
  function generatePlateNumbers() {
    $('#plateNumberModal').modal('show');
    $('#plateNumberList').empty();
    $('#confirmSelection').prop('disabled', true);

    $.ajax({
      url: '${pageContext.request.contextPath}/generatePlateNumbers',
      type: 'POST',
      data: { province: '豫' },
      success: function(response) {
        let plateNumbers = response.plateNumbers;
        for (let i = 0; i < plateNumbers.length; i++) {
          let plateNumber = plateNumbers[i];
          let listItem = `<li class="list-group-item" onclick="selectPlateNumber('${plateNumber}')">${plateNumber}</li>`;
          $('#plateNumberList').append(listItem);
        }
      }
    });
  }

  function selectPlateNumber(plateNumber) {
    $('#plateNumberList .list-group-item').removeClass('active');
    $(`#plateNumberList .list-group-item:contains(${plateNumber})`).addClass('active');
    $('#confirmSelection').prop('disabled', false).off('click').on('click', function() {
      confirmPlateNumber(plateNumber);
    });
  }

  function confirmPlateNumber(plateNumber) {
    // 提交选择的车牌号
    $.ajax({
      url: '${pageContext.request.contextPath}/BindVehicleController',
      type: 'POST',
      data: { selectedPlate: plateNumber },
      success: function(response) {
        alert('车牌号绑定成功');
        $('#plateNumberModal').modal('hide');
      },
      error: function() {
        alert('车牌号绑定失败');
      }
    });
  }
</script>
</body>
</html>