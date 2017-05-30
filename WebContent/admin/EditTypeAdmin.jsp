<%@ page language="java" import="java.util.*" import="bbs.model.*" pageEncoding="UTF-8"%>
<%
User admin = (User)request.getSession().getAttribute("admin");
Type t = (Type)request.getAttribute("t");
String scgExist = (String)request.getAttribute("msg");
String Errormsg = (String)request.getAttribute("Errormsg");
%>

<jsp:include page="frame/Header.jsp"></jsp:include>

	<% if (null != admin) { %>
	<div class="container">
      <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="navbar-header">
          <a class="navbar-brand" href="">BBS管理系统</a>
        </div>

        <div class="collapse navbar-collapse navbar-ex1-collapse">
          <ul class="nav navbar-nav side-nav">
            <li class="active"><a href="${pageContent.request.contentPath }admin.html?action=index"><i class="glyphicon glyphicon-dashboard"></i> 控制面板</a></li>
            <li><a href="${pageContent.request.contentPath }admin.html?action=useradmin"><i class="glyphicon glyphicon-cog"></i> 用户管理</a></li>
            <li><a href="${pageContent.request.contentPath }admin.html?action=InvitationAdmin"><i class="glyphicon glyphicon-cog"></i> 帖子管理</a></li>
            <li><a href="${pageContent.request.contentPath }admin.html?action=TypeAdmin"><i class="glyphicon glyphicon-edit"></i> 分类管理</a></li>
          </ul>

          <ul class="nav navbar-nav navbar-right navbar-user">
            <li class="dropdown user-dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="glyphicon glyphicon-user"></i> <%=admin.getUserName() %> <b class="caret"></b></a>
              <ul class="dropdown-menu">
                <li><a href="#"><i class="glyphicon glyphicon-cog"></i> 设置</a></li>
                <li class="divider"></li>
                <li><a href="admin?action=logout"><i class="glyphicon glyphicon-off"></i> 登出</a></li>
              </ul>
            </li>
          </ul>
        </div>
      </nav>
      
     
	<div id="page-wrapper">
        <div class="row">
          <div class="col-lg-12">
              <h1>BBS论坛系统 <small>基于JSP和Servlet技术构建</small></h1>
              <ol class="breadcrumb">
	              <li><a href="${pageContent.request.contentPath }admin.html?action=index"><i class="glyphicon glyphicon-dashboard"></i> 控制面板</a></li>
	              <li><a href="${pageContent.request.contentPath }admin.html?action=TypeAdmin"><i class="glyphicon glyphicon-cog"></i> 分类管理</a></li>
	              <li class="active"><i class="glyphicon glyphicon-cog"></i> 编辑分类</li>
              </ol>
             </div>
            </div>
			<%
       			if (null != scgExist) {
       		%>
	  	<div class="row">
         	<div class="col-lg-12">
	  		<div class="alert alert-success"><%=scgExist%></div>
	  		</div>
	  	</div>
          <%
       			}
       			if (null != Errormsg) {
       		%>
	  	<div class="row">
         	<div class="col-lg-12">
	  		<div class="alert alert-success"><%=Errormsg%></div>
	  		</div>
	  	</div>
          <%
       			}
       		%>
       		
	          <div class="row">
	          	<div class="col-lg-6">
	          		<form action='${pageContent.request.contentPath }admin.html?action=SaveEditTypeAdmin&name=<%=t.getName() %>' method="post">
	            		<label>分类名：</label><input name="scgName" class="form-control" type="text" value="<%=t.getName() %>"/><br>
	            		<input type="submit" class="btn btn-primary" value="修改">
	            	</form>
				</div>
			  </div>
		  </div>
		 </div>
<% } else { %>
<%-- 404 page --%>
<% } %>
<jsp:include page="frame/Footer.jsp"></jsp:include>

