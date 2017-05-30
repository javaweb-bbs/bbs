<%@ page language="java" import="java.util.*" import="bbs.model.*" import="bbs.dao.*" pageEncoding="UTF-8"%>

<%
	User admin = (User) request.getSession().getAttribute("admin");
	int totalUserCount = (Integer)request.getAttribute("totalUserCount");
	int totalInvitationCount = (Integer)request.getAttribute("totalInvitationCount");
	int totalCommentCount = (Integer)request.getAttribute("totalCommentCount");
%>
<jsp:include page="frame/Header.jsp"></jsp:include>
<%
	if(admin!=null){
%>
      <div class="container">
      <div class="col-lg-12">
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
     </div>
     
	<div id="page-wrapper">

        <div class="row">
          <div class="col-lg-12">
            <h1>BBS论坛系统 <small>基于Jsp和servlet技术构建</small></h1>
            <ol class="breadcrumb">
              <li class="active"><i class="glyphicon glyphicon-dashboard"></i> 控制面板</li>
            </ol>
            
          <div class="row">
	        <!-- <div class="col-lg-3">
	            <div class="panel panel-info">
	              <div class="panel-heading">
	                <div class="row">
	                  <div class="col-xs-6">
	                    <i class="glyphicon glyphicon-search"></i>
	                  </div>
	                  <div class="col-xs-6 text-right">
	                    <p class="announcement-heading"></p>
	                    <p class="announcement-text">网站来访人数</p>
	                  </div>
	                </div>
	              </div>
	            </div>
            </div> -->
            
            <div class="col-lg-3" >
	            <div class="panel panel-success">
	              <div class="panel-heading">
	                <div class="row">
	                  <div class="col-xs-6">
	                    <i class="glyphicon glyphicon-edit"></i>
	                  </div>
	                  <div class="col-xs-6 text-right">
	                    <p class="announcement-heading"><%=totalInvitationCount %></p>
	                    <p class="announcement-text">帖子总数</p>
	                  </div>
	                </div>
	              </div>
	            </div>
            </div>
            
            <div class="col-lg-3">
            <div class="panel panel-warning">
              <div class="panel-heading">
                <div class="row">
                  <div class="col-xs-6">
                    <i class="glyphicon glyphicon-envelope"></i>
                  </div>
                  <div class="col-xs-6 text-right">
                    <p class="announcement-heading"><%=totalCommentCount %></p>
                    <p class="announcement-text">评论总数</p>
                  </div>
                </div>
              </div>
            </div>
          </div>
            
            
            <div class="col-lg-3">
            <div class="panel panel-danger">
              <div class="panel-heading">
                <div class="row">
                  <div class="col-xs-6">
             		  <i class="glyphicon glyphicon-user"></i>
                  </div>
                  <div class="col-xs-6 text-right">
                    <p class="announcement-heading"><%=totalUserCount %></p>
                    <p class="announcement-text">注册用户数</p>
                  </div>
                </div>
              </div>
            </div>
          </div>
                      
           </div>
         </div>
        </div>
    </div>
    </div>
<%
	}
%>
<jsp:include page="frame/Footer.jsp"></jsp:include>