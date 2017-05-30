<%@ page language="java" import="java.util.*" import="bbs.model.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
	User admin = (User) request.getSession().getAttribute("admin");
	List<User> uList=(List<User>)request.getAttribute("uList");
%>

<jsp:include page="frame/Header.jsp"></jsp:include>

	
	  <div class="container">
	  <!-- Sidebar -->
      <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
          <a class="navbar-brand" href="">BBS管理系统</a>
        </div>

        <div class="collapse navbar-collapse navbar-ex1-collapse">
          <ul class="nav navbar-nav side-nav">
            <li class="active"><a href="${pageContent.request.contentPath }admin/UserIndex.jsp"><i class="glyphicon glyphicon-dashboard"></i> 控制面板</a></li>
            <li><a href="${pageContent.request.contentPath }admin?action=useradmin"><i class="glyphicon glyphicon-cog"></i> 用户管理</a></li>
            <li><a href=""><i class="glyphicon glyphicon-cog"></i> 文章管理</a></li>
            <li><a href=""><i class="glyphicon glyphicon-edit"></i> 分类管理</a></li>
            
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
          	<br>
            <ol class="breadcrumb">
            <li><a href="<%=basePath%>admin/index.jsp"><i class="glyphicon glyphicon-dashboard"></i> 控制面板</a></li>
              <li class="active"><i class="glyphicon glyphicon-cog"></i> 用户管理</li>
            </ol>
           
		    <%-- 禁用账户结果提示消息 --%>
		  	
		  	
		  	<%-- 激活账户结果提示消息 --%>
		  	
          <div class="row">
          	<div class="col-lg-12">
            	<div class="table-responsive">
              	<table class="table table-hover tablesorter">
					<thead>
						<tr>
							<th>用户名</th>
		          			<th>邮箱地址</th>
		          			<th>当前状态</th>
		          			<th>操作</th>
		          			<th>查看用户信息</th>
						</tr>
					</thead>
					<%-- <tbody>
					<%
						if(uList != null){
							for(User u : uList){
								
								String deleUrl = basePath + "admin.html?action=deleteuser&uId=" + u.getId(); //禁用链接
								String actUrl = basePath + "admin.html?action=activeuser&uId=" + u.getId(); //激活链接
								String detailUrl = basePath + "admin.html?action=userprofile&uId=" + u.getId() ;//详细信息
					 %>
						<tr>
		          			<td><%=u.getUserName() %></td>
		          			<td>
		          				<% if (u.getIsApplied() == 1) { %>  
		          				<span class="label label-success">已申请</span>
		          				<% } else { %>
		          				<span class="label label-danger">未申请</span>
		          				<% }  %>
		          			</td>
		          			<td><%=u.getEmail() %></td>
		          			<td>
		          				<% if (u.getIsDelete() == 0) { %>
		          				<span class="label label-success">可用</span>
		          				<% } else { %>		
		          				<span class="label label-danger">不可用</span>
		          				<% } %>
		          			</td>
		          			<td> 
		          				<% if (u.getIsDelete() == 1) { %>
		          				
		          				 <a onClick="act('<%=actUrl %>')" class="btn btn-success btn-xs"> 启用账号</a>	
		          				<% } else { %>
		          				<a onClick="dele('<%=deleUrl %>')" class="btn btn-danger btn-xs"> 禁用账号</a>	
		          				<% } %>
		           			</td>
		          			<td>
		          				<% if (u.getIsProfile() == 1) { %>
		          				<a href="<%=detailUrl %>" class="btn btn-primary btn-xs">详细信息</a>
		          				<% } else { %>
		          				<a class="btn btn-warning btn-xs">尚未更新资料</a>
		          				<% } %>
		          			</td>
		          		</tr>
		          	<%          
		          		}
		          		}else{%>
		          	 <%="获取用户资料失败"%>
		          	 <%} %>
					</tbody> --%>
					</table>
					</div>
				</div>
			</div>
			
			<!-- pager -->
          <ul class="pager">
          	
            <li class="previous"><a href="">&larr; 上一页</a></li>
           
            
            
            <li class="next"><a href="">下一页  &rarr;</a></li>
            
          </ul>
			
			</div>
        </div>
    </div>
</div>

<jsp:include page="frame/Footer.jsp"></jsp:include>

<script type="text/javascript">
function dele(deleUrl) {
	
	if (confirm("你确定要禁用该用户吗？")) {
		location.href = deleUrl;
	}

}

function act(actUrl) {
	
	if (confirm("你确定要激活该用户吗？")) {
		location.href = actUrl;
	}
}
</script>