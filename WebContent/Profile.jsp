<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
import = "bbs.model.*"
%>


<%
User u = (User)request.getSession().getAttribute("currentUser");
String succMsg = (String)request.getSession().getAttribute("succMsg");
String errorMsg = (String)request.getSession().getAttribute("errorMsg");

String succUpdateMsg = (String)request.getSession().getAttribute("succUpdateMsg");		//密码更新消息提示
String errorUpdateMsg = (String)request.getSession().getAttribute("errorUpdateMsg");

String validPwdMsg = (String)request.getSession().getAttribute("validPwdMsg");			//旧密码验证失败消息提示
 %>
 
<jsp:include page="frame/Header.jsp"></jsp:include>

<body>

	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          
          <a class="navbar-brand" href="index.html">JSP 博客</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse navbar-ex1-collapse">
          <ul class="nav navbar-nav">
            <li><a href="${pageContext.request.contextPath }/index.jsp">网站首页</a></li>
          </ul>
          
          <%
              if (null != u) {
          %>
          <ul class="nav navbar-nav">
            <li><a href="">我的BBS</a></li>
          </ul>
          <ul class="nav navbar-nav">
            <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">板块管理<b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li><a href=""><i class="glyphicon glyphicon-cog"></i> 帖子管理</a></li>
                    <li class="divider"></li>
                    <li><a href=""><i class="glyphicon glyphicon-cog"></i> 分类管理</a></li>
                    <li class="divider"></li>
                    <li><a href=""><i class="glyphicon glyphicon-cog"></i> 评论管理</a></li>
                </ul>
            </li>
          </ul>
          <%
          	}
          	if(u==null){
          %>
          <ul class="nav navbar-nav navbar-right">
          	<li><a href="Login.jsp">登录</a></li>
          	<li><a href="Register.jsp" target="_blank">注册</a></li>
          </ul>
      	<%
          	}else{
      	%>
         <div class="pull-right">
             <ul class="nav pull-right">
                 <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">欢迎，<%=u.getUserName() %><b class="caret"></b></a>
                     <ul class="dropdown-menu">
                         <li><a href="${pageContext.request.contextPath }user?action=profile&id=<%=u.getUserId() %>"><i class="glyphicon glyphicon-cog"></i> 编辑个人信息</a></li>
                         <li class="divider"></li>
                         <li><a href=""><i class="glyphicon glyphicon-cog"></i> 编辑BBS信息</a></li>
                         <li class="divider"></li>
                         <li><a href="user?action=logout"><i class="glyphicon glyphicon-off"></i> 登出</a></li>
                       </ul>
                   </li>
              </ul>
          </div>
          <%
          	}
          %>
          
        </div>
      </div>
    </nav>
    
  	<% if (null != succMsg) { %>
  	 <div class="container">
     	<div class="alert alert-success">
     	<%=succMsg %>
     	</div>
     </div>
     <%    request.getSession().removeAttribute("succMsg"); 
       } %>
     
     <% if (null != errorMsg) { %>
     <div class="container">
     	<div class="alert alert-error">
     	<%=errorMsg %>
     	</div>
     </div>
     <%    request.getSession().removeAttribute("errorMsg"); 
        } %>
     
     <% if (null != succUpdateMsg) { %>
  	 <div class="container">
     	<div class="alert alert-success">
     	<%=succUpdateMsg %>
     	</div>
     </div>
     <%     request.getSession().removeAttribute("succUpdateMsg"); 
        } %>
     
     <% if (null != errorUpdateMsg) { %>
     <div class="container">
     	<div class="alert alert-error">
     	<%=errorUpdateMsg %>
     	</div>
     </div>
     <%     request.getSession().removeAttribute("errorUpdateMsg"); 
        } %>
    
    <% if (null != validPwdMsg) { %>
     <div class="container">
     	<div class="alert alert-error">
     	<%=validPwdMsg %>
     	</div>
     </div>
     <%     request.getSession().removeAttribute("validPwdMsg"); 
        } %>
    
	<div class="container">
		<div class="well row col-md-6">
	    <ul class="nav nav-tabs">
	      <li class="active"><a href="#home" data-toggle="tab">个人资料</a></li>
	      <li><a href="#profile" data-toggle="tab">修改密码</a></li>
	    </ul>
	    <div id="myTabContent" class="tab-content">
	      <div class="tab-pane active in" id="home">
	        <form class="form-horizontal" name="profile_form" action="user?action=updateprofile&id=<%=u.getUserId() %>" role="form" id="tab" method="post">
	            <div class="form-group">
	            <label for="username">用户名</label>
	            <input type="text" value="<%=u.getUserName() %>" class="form-control" name="username" disabled>
	            </div>
	            <div class="form-group">
	            <label for="email">邮箱</label>
	            <input type="text" value="<%=u.getEmail() %>" class="form-control" name="email">
	            </div>
	            <div class="form-group">
	            <label for="gender">性别</label>
	            <select class="form-control" name="gender" id="gender">
	            
	            	<option value="男">男</option>
	            	<option value="女" selected>女</option>
	            	
	            </select>
	           
	            </div>
	          	<div class="form-group" >
	        	    <button class="btn btn-primary" type="submit">保存</button>
	        	</div>
	        </form>
	      </div>
	      <div class="tab-pane fade" id="profile">
	    	<form class="form-horizontal" id="tab2" name="updatePwd" onsubmit="return isPasswordValidate(updatePwd)" 
	    	      method="post" action="user?action=updatepass&id=<%=u.getUserId()%>">
	        	<div class="form-group">
	        	<label for="old_pwd">旧密码</label>
	        	<input class="form-control" type="password" name="old_pwd" >
	        	</div>
	        	<div class="form-group">
	        	<label for="new_pwd" >新密码</label>
	        	<input class="form-control" type="password" name="new_pwd" >
	        	</div>
	        	<div class="form-group">
	        	<label for="submit_new_pwd">新密码（确认）</label>
	        	<input class="form-control" type="password" name="submit_new_pwd">
	        	</div>
	        	<div class="form-group">
	        	   <button class="btn btn-primary">保存</button>
	        	</div>
	    	</form>
	      </div>
	  </div>
	</div>
	</div>
<jsp:include page="frame/Footer.jsp"></jsp:include>

<script type="text/javascript">
function isPasswordValidate(form) {
	var old_pwd = form.old_pwd.value;
	var new_pwd = form.new_pwd.value;
	var submit_new_pwd = form.submit_new_pwd.value;
	
	if (old_pwd == "" || new_pwd == "" || submit_new_pwd == "") {
		alert("新密码、旧密码、旧密码（确认）为必填项");	
		return false;
	} else if (old_pwd == new_pwd) {
		alert("新密码和旧密码不能相同");
		return false;
	} else if (submit_new_pwd != new_pwd) {
		alert("新密码和新密码（确认）必须相同");
		return false;
	} else {
		return true;
	}

}
</script>