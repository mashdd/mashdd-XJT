<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>河南省交通安全综合服务管理平台</title>
  <link href="https://cdn.bootcdn.net/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
  <style>
    .department-header {
      background: #004b87;
      color: white;
      padding: 8px 15px;
    }
    .platform-title {
      background: #f5f5f5;
      padding: 15px;
      border-bottom: 2px solid #004b87;
    }
    .side-section {
      border-right: 1px solid #ddd;
      padding-right: 25px;
    }
    .news-date {
      color: #666;
      font-size: 0.9em;
    }
    .service-icon {
      font-size: 2.5rem;
      color: #004b87;
    }
    .contact-list {
      list-style: none;
      padding-left: 0;
    }
    .footer-links {
      background: #f8f9fa;
      padding: 20px 0;
    }
  </style>
</head>
<body>
<!-- 部门切换 -->
<div class="department-header d-flex justify-content-between">
  <span>切换公安交通管理部门 河南省</span>
  <span>📧 0556</span>
</div>

<!-- 平台标题 -->
<div class="platform-title container-fluid">
  <h3>交通安全综合服务管理平台</h3>
  <p class="mb-0">河南省公安厅交通管理局</p>
</div>

<!-- 主导航 -->
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container">
    <div class="collapse navbar-collapse">
      <ul class="navbar-nav">
        <li class="nav-item"><a class="nav-link active" href="#">首页</a></li>
        <li class="nav-item"><a class="nav-link" href="#">业务办理</a></li>
        <li class="nav-item"><a class="nav-link" href="#">服务导航</a></li>
        <li class="nav-item"><a class="nav-link" href="#">公告公布</a></li>
        <li class="nav-item"><a class="nav-link" href="#">APP下载</a></li>
        <li class="nav-item"><a class="nav-link" href="#">办事指南</a></li>
      </ul>
    </div>
  </div>
</nav>

<div class="container mt-4">
  <div class="row">
    <!-- 左侧边栏 -->
    <div class="col-md-3 side-section">
      <div class="card mb-4">
        <div class="card-body">
          <h5 class="card-title">业务咨询</h5>
          <p>请拨打 📞 0556-12123</p>

          <h5 class="mt-4">夏季平安行</h5>
          <div class="btn-group">
            <button class="btn btn-outline-primary">凌晨1点~</button>
            <button class="btn btn-outline-primary">1</button>
            <button class="btn btn-outline-primary">2</button>
            <button class="btn btn-outline-primary">3</button>
          </div>

          <h5 class="mt-4">业务热点</h5>
          <ul class="list-unstyled">
            <li><a href="#">驾驶员考试预约计划公布</a></li>
            <li><a href="#">驾驶人考试预约结果公布</a></li>
            <li><a href="#">互联网预选号牌导出公布</a></li>
          </ul>
        </div>
      </div>
    </div>

    <!-- 中间内容 -->
    <div class="col-md-6">
      <div class="row">
        <!-- 交管资讯 -->
        <div class="col-md-4">
          <h5>交管资讯</h5>
          <ul class="list-unstyled">
            <li class="mb-2">
              <div class="d-flex justify-content-between">
                <a href="#">“交管12123” APP上...</a>
                <span class="news-date">08-16</span>
              </div>
            </li>
            <!-- 更多资讯条目... -->
          </ul>
        </div>

        <!-- 警示教育 -->
        <div class="col-md-4">
          <h5>警示教育</h5>
          <ul class="list-unstyled">
            <li class="mb-2">
              <div class="d-flex justify-content-between">
                <a href="#">正反案例 | 天再热，这...</a>
                <span class="news-date">08-16</span>
              </div>
            </li>
            <!-- 更多教育条目... -->
          </ul>
        </div>

        <!-- 公示公告 -->
        <div class="col-md-4">
          <h5>公示公告</h5>
          <ul class="list-unstyled">
            <li class="mb-2">
              <div class="d-flex justify-content-between">
                <a href="#">注意！这些高风险企业...</a>
                <span class="news-date">08-12</span>
              </div>
            </li>
            <!-- 更多公告条目... -->
          </ul>
        </div>
      </div>
    </div>

    <!-- 右侧边栏 -->
    <div class="col-md-3">
      <div class="card">
        <div class="card-body">
          <h5 class="card-title">信息查询</h5>
          <div class="mb-3">
            <input type="text" class="form-control" placeholder="机动车违法查询">
          </div>
          <div class="mb-3">
            <input type="text" class="form-control" placeholder="驾驶证记分查询">
          </div>

          <h5 class="mt-4">便民工具</h5>
          <button class="btn btn-outline-primary w-100">车检计算器</button>

          <h5 class="mt-4">友情链接</h5>
          <ul class="list-unstyled">
            <li><a href="#">中华人民共和国公安部</a></li>
            <li><a href="#">公安部交通管理科学研究所</a></li>
            <li><a href="#">交通安全宣传网</a></li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</div>

<!-- 底部功能区 -->
<div class="footer-links mt-5">
  <div class="container">
    <div class="row">
      <div class="col-md-4">
        <h5>联系方式</h5>
        <ul class="contact-list">
          <li>合肥车辆管理所: 0551-12123</li>
          <li>芜湖车辆管理所: 0553-12123</li>
          <!-- 更多联系方式... -->
        </ul>
      </div>

      <div class="col-md-8">
        <div class="row">
          <div class="col-3 text-center">
            <i class="bi bi-camera-video service-icon"></i>
            <div>电子监控违法处理</div>
          </div>
          <div class="col-3 text-center">
            <i class="bi bi-123 service-icon"></i>
            <div>预选机动车号牌</div>
          </div>
          <!-- 更多服务图标... -->
        </div>
      </div>
    </div>
  </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.bootcdn.net/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
</body>
</html>