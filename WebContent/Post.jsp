<%@ page language="java" import="java.util.*" import="bbs.model.*"
	import="bbs.dao.*" pageEncoding="UTF-8"%>
<%
int userId = 0;
User u = (User)request.getSession().getAttribute("currentUser");		//用户登录后的信息
if(u==null){
	request.getRequestDispatcher("Login.jsp").forward(request, response);
}
%>


<jsp:include page="frame/Header.jsp"></jsp:include>

<body>
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-ex1-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
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
						 <input type="hidden" id="user-id" value="<%=u.getUserId() %>">
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
			<div class="col-lg-9">
				<div class="invitation-detail">

				</div>
				<hr>
				<div class="comment-list">

				</div>
				<div class="well">
					<h4>评论：</h4>
					<%
						if (null != u) { userId = u.getUserId();}
					%>
					<div class="form-group">
						<textarea class="comment-content form-control" rows="3" name="comment_content"></textarea>
					</div>
					<button type="submit" id="add-comment" class="btn btn-primary">提交</button>
				</div>
			</div>
			<div class="col-lg-3">
				<div class="well">
					<h4>所属分类</h4>
					<div class="row">
						<div class="col-lg-6">
							<ul class="list-unstyled">
								<li class="get-type"></li>
							</ul>
						</div>
					</div>
				</div>
				<div class="well">
					<h4>相关文章列表：</h4>
					<ul class="relative-article">
						<li><a href="" target="_blank">java</a></li>
						<li><a href="" target="_blank">c</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
		<jsp:include page="frame/Footer.jsp"></jsp:include>
		<script type="text/javascript">
			function isValidate(comment) {
				var comment_content = comment.comment_content.value;

				if (comment_content == "") {
					alert("请填写评论内容");

					return false;
				}

				return true;
			}
		</script>
	<script src="js/ajax.js"></script>
	<script src="js/getDetail.js"></script>