<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<link rel="stylesheet" href="css/login.css">
<body>
<div class="container">
	<div class="login-container">
		<div class="title">注册</div>
		<input type="text" class="username" name="username" placeholder="用户名">
		<input type="text" class="email" name="email" placeholder="邮箱">
		<input type="password" class="password" name="password" placeholder="密码">
		<input type="password" class="ensure-pwd" name="password" placeholder="确认密码">
		<button class="submit">注册</button>
	</div>
</div>
<script src="js/ajax.js"></script>
<script src="js/register.js"></script>
</body>