<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>管理员登录</title>
<script type="text/javascript">
	function resetValue(){
		document.getElementById("userName").value="";
		document.getElementById("password").value="";
	}
</script>
</head>
<body>
	<form action="login" method="post">
		<table>
			<caption>用户登录</caption>
			<tr>
				<td>用户名：</td>
				<td><input type="text" name="userName" id="userName" value="${userName }"/></td>
			</tr>
			<tr>
				<td>密码：</td>
				<td><input type="password" name="password" id="password" value="${password }"/></td>
			</tr>
			<tr>
				<td><input type="submit" value="登录"/></td>
				<td><input type="button" onclick="resetValue()" value="重置"/></td>
				<td><font color="red">${error }</font></td>
			</tr>
		</table>
	</form>
</body>
</html>