<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>用户注册</title>
    <link rel="stylesheet" href="static/bootstrap/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h2>注册</h2>
    <form action="UserController" method="post">
        <input type="hidden" name="action" value="register">
        <div class="mb-3">
            <label>身份证号</label>
            <input type="text" name="id_card" class="form-control" required>
        </div>
        <div class="mb-3">
            <label>密码</label>
            <input type="password" name="password" class="form-control" required>
        </div>
        <button type="submit" class="btn btn-primary">注册</button>
    </form>
    <p class="mt-3"><a href="login.jsp">已有账号？去登录</a></p>
</div>
</body>
</html>
