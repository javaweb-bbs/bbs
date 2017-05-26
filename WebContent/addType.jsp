<%@ page language="java" import="java.util.*" import="bbs.model.*" 
import="bbs.dao.*" pageEncoding="UTF-8"%>

<%
User u = (User)request.getSession().getAttribute("currentUser");
 %>

<jsp:include page="frame/Header.jsp"></jsp:include>

<body>
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
   
          <a class="navbar-brand" href="index.jsp">BBS</a>
        </div>

        <div class="collapse navbar-collapse navbar-ex1-collapse">
          <ul class="nav navbar-nav">
            <li><a href="${pageContext.request.contextPath}/index.jsp">BBS首页</a></li>
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
                    <li><a href="invitationManage.jsp"><i class="glyphicon glyphicon-cog"></i> 帖子管理</a></li>
                    <li class="divider"></li>
                    <li><a href="TypeManage.jsp"><i class="glyphicon glyphicon-cog"></i> 分类管理</a></li>
                    <li class="divider"></li>
                    <li><a href="CommentManage.jsp"><i class="glyphicon glyphicon-cog"></i> 评论管理</a></li>
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
                         <li><a href="${pageContext.request.contextPath }/user?action=profile&id=<%=u.getUserId() %>"><i class="glyphicon glyphicon-cog"></i> 编辑个人信息</a></li>
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
    
    <div class="container "> 
		<ol class="breadcrumb">
             <li><a href="">分类管理</a></li>
             <li class="active">新建分类</li>
        </ol>  
         
    	<form class="form-horizontal" name="add_category_form" action="">
    	<div class="col-md-6">
    	<div class="from-group">
    		<label for="category_name">分类名：</label>
    		<input class="form-control" id="category_name" name="category_name" type="text">
    	</div>
    	
    	<div class="from-group">
    		<button id="add_category_submit" type="submit" class="btn btn-primary">保存</button>
    	</div>
    	</div>
    	</form>
    </div>
<jsp:include page="frame/Footer.jsp"></jsp:include>

<script type="text/javascript">
function isValidate(form) {
	var category_name = form.category_name.value;
	
	if (category_name == "") {
		alert("请填写分类名！");	
		
		return false;
	}
	return true;
}
</script>