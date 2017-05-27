<%@ page language="java" import="java.util.*" import="bbs.model.*" import="bbs.dao.*" pageEncoding="utf-8"%>
<jsp:include page="frame/Header.jsp"></jsp:include>
<%
	User u=(User)request.getSession().getAttribute("currentUser");
%>
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

    <div class="container">
      <div class="row">
        <div id="blog" class="col-lg-8" >
          <h1><a href="index.jsp">BBS论坛——<small>基于JSP, Servlet技术构建</small></a></h1>
          <br>
          
          <div class="list">
          	
          </div>
          <ul class="pager">
            <li class="previous"><a>&larr; 上一页</a></li>
            <li class="next"><a>下一页  &rarr;</a></li>
          </ul>
        </div>
        <div class="col-lg-4">
          <div class="well">
            <h4>搜索站内帖子</h4>
            <form name="search_form" action="" method="post">
            <div class="input-group">
              <input type="text" class="form-control" name="q">
              <span class="input-group-btn">
                <button class="btn btn-default" type="submit"><span class="glyphicon glyphicon-search"></span></button>
              </span>
            </div>
            </form>
          </div>
          <div class="well">
            <h4>帖子分类</h4>
              <div class="row">
                <div class="col-lg-6">
                  <ul class="list-unstyled type-list">
                  </ul>
                </div>
              </div>
          </div>
          
          <div class="well">
            <h4>精华TOP10</h4>
            <div class="row">
              <ul class="list-unstyled">
                <li><a href="" target="_blank">c</a></li>
                <li><a href="" target="_blank">c++</a></li>
              </ul>
            </div>
          </div>
          </div>
      </div>
    </div>

    <script src="js/ajax.js"></script>
    <script src="js/index.js"></script>

<jsp:include page="frame/Footer.jsp"></jsp:include>
