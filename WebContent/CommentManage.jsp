<%@ page language="java" import="java.util.*" import="bbs.model.*" 
import="bbs.dao.*" pageEncoding="UTF-8"%>
<%
User u = (User)request.getSession().getAttribute("currentUser");
//添加下面两行代码用于分页显示
/* int curPage = (Integer)request.getAttribute("curPage");
int totalPages = (Integer)request.getAttribute("totalPages"); */
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
                    <li><a href="${pageContext.request.contextPath }/invitation?action=imanage"><i class="glyphicon glyphicon-cog"></i> 帖子管理</a></li>
                    <li class="divider"></li>
                    <li><a href="${pageContext.request.contextPath }/invitationtype?action=itmanage"><i class="glyphicon glyphicon-cog"></i> 分类管理</a></li>
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
  	
	<%-- 删除评论的提示消息 --%>
  	
	<% if (null != u) { %>
	<div class="container">	
		<div class="well">
			<table class="table">
				<thead>
					<tr>
						<th>评论内容</th>
						<th>评论人</th>
						<th>评论的文章标题</th>
						<th>评论时间</th>
						<th style="width: 50px;">操作</th>
					</tr>
				</thead>
				<tbody>
					
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td><a onClick="dele('')"><i
								class="glyphicon glyphicon-remove"></i></a></td>
					</tr>
					
				</tbody>
			</table>
		</div>
		<div>
		 <!-- pager -->
          <ul class="pager">
            <li class="previous"><a href="">&larr; 上一页</a></li>
            <li class="next"><a href="">下一页  &rarr;</a></li>
          </ul>
		</div>
		
	</div>
	<%} %>
	
<jsp:include page="frame/Footer.jsp"></jsp:include>

<script type="text/javascript">
function dele(deleUrl) {
	
	if (confirm("你确定要删除该评论吗？")) {
		location.href = deleUrl;
	}
}
</script>