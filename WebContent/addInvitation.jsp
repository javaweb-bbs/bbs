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
        
    <% if (null != u) { %>
	<div class="container">
		
		<div class="row col-md-12">
			<div class="col-md-12">
			   <div class="col-md-12">
					<ol class="breadcrumb">
		              <li><a href="">帖子管理</a></li>
		              <li class="active">新建帖子</li>
		            </ol>
	            </div>
	            
	            <form class="form-horizontal" name="add_artical_form" action="" method="post">
	            	
	                <div class="col-md-6">
	                    <div class="form-group">
	                    <label for="title">标题</label>
	                    <input class="form-control" id="name" name="title" type="text" >
	                    </div>
	                    <div class="form-group">
	                    <label for="sys_category">分类</label>
	                        <select class="form-control"  id="subject" name="sys_category">
	                    		<!--  遍历出来   -->
	                    		<option value="" selected=""></option>
                            </select>
                         </div> 
	                
	                <div class="form-group">
	                    <textarea class="form-control" id="message" name="summary"  placeholder="摘要" rows="5"></textarea>
	                </div>
	                
	                <div class="form-group">
	                    <textarea class="form-control" id="message" name="content"  placeholder="文章内容" rows="5"></textarea>
	                </div>
	                  
	                <div class="form-group">
	                    <button id="contact-submit" type="submit" class="btn btn-primary input-medium pull-right">保存</button>
	                </div>
	                </div>
	            </form>
	        </div>
		</div>
	</div>
	<% } %>
	
<jsp:include page="frame/Footer.jsp"></jsp:include>