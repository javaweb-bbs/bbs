<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<body>
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
     <div class="container">
       <div class="navbar-header">
         <a class="navbar-brand" href="${pageContext.request.contextPath }/index.jsp">BBS</a>
       </div>

       <div class="collapse navbar-collapse navbar-ex1-collapse">
         <ul class="nav navbar-nav">
           <li><a href="${pageContext.request.contextPath}/index.jsp">BBS首页</a></li>
         </ul>
       
         <ul class="nav navbar-nav navbar-right">
         	<li><a href="Login.jsp" target="_blank">登录</a></li>
         	<li><a href="Register.jsp" target="_blank">注册</a></li>
         </ul>        
       </div>
     </div>
   </nav>

	<div class="container">
		<div class="row col-md-6">
			<form name="login_form" role="form" action="user?action=login"
				method="POST" onsubmit="return isValidate(login_form)">
				<fieldset>
					<div id="legend">
						<legend class="caption">登录</legend>
					</div>
					<div class="form-group">
						<label for="username">用户名</label> 
						<input type="text"
							class="form-control " value="${username }" name="username" id="username"
							placeholder="Username">
					</div>
					<div class="form-group">
						<label for="password">密码</label> <input type="password"
							class="form-control" value="${password }" name="password" id="password"
							placeholder="Password">
					</div>
					
					<div class="form-group">
					<button type="submit" class="btn btn-success">登录</button>
					</div>
					<div class="form-group">
						<font color="red">${error }</font>
					</div>
				</fieldset>
			</form>
		</div>
	</div>