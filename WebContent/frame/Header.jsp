<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>JSP BBS</title>
   	<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
   	<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-theme.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/bootstrap/css/blog-home.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/bootstrap/js/jquery-2.1.1.js"></script>
  </head>
  <body>
  <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
      <div class="navbar-header">
        <a class="navbar-brand" href="index.jsp">BBS</a>
      </div>

      <div class="collapse navbar-collapse navbar-ex1-collapse">
        <ul class="nav navbar-nav manage-invitation">
          <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">板块管理<b class="caret"></b></a>
            <ul class="dropdown-menu">
              <li><a href="invitationManage.jsp"><i class="glyphicon glyphicon-cog"></i> 帖子管理</a></li>
              <li class="divider"></li>
              <li><a href="TypeManage.jsp"><i class="glyphicon glyphicon-cog"></i> 分类管理</a></li>
              <li class="divider"></li>
              <!-- <li><a href="CommentManage.jsp"><i class="glyphicon glyphicon-cog"></i> 评论管理</a></li> -->
            </ul>
        </ul>
        <ul class="nav navbar-nav navbar-right no-login">
          <li><a href="Login.jsp">登录</a></li>
          <li><a href="Register.jsp" target="_blank">注册</a></li>
        </ul>
        <div class="pull-right login-success">
          <ul class="nav pull-right">
            <li class="dropdown"><a href="#" class="dropdown-toggle username" data-toggle="dropdown"></a>
              <ul class="dropdown-menu">
                <li><a href="Profile.jsp"><i class="glyphicon glyphicon-cog"></i> 编辑个人信息</a></li>
                <li class="divider"></li>
                <li class="login-out"><a><i class="glyphicon glyphicon-off"></i> 登出</a></li>
              </ul>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </nav>
  <script src="${pageContext.request.contextPath}/js/judge.js"></script>
