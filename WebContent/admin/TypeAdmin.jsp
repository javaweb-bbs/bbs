<%@ page language="java" import="java.util.*" import="bbs.model.*" pageEncoding="UTF-8"%>

<%
	User admin = (User)request.getSession().getAttribute("admin");
	List<Type> tList = (List<Type>)request.getAttribute("type");
	
	String msg = (String)request.getAttribute("msg");	//删除
	String deleSuccMsg = (String)request.getAttribute("deleteSuccMsg");
	String deleErrorMsg = (String)request.getAttribute("deleteErrorMsg");
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
          <br>
            <ol class="breadcrumb">
            <li><a href="${pageContent.request.contentPath }admin.html?action=index"><i class="glyphicon glyphicon-dashboard"></i> 控制面板</a></li>
              <li class="active"><i class="glyphicon glyphicon-cog"></i> 分类管理</li>
            </ol>
           </div>
          </div>
          
          <%
       			if (null != msg) {
       		%>
	  	<div class="row">
         	<div class="col-lg-12">
	  		<div class="alert alert-success"><%=msg%></div>
	  		</div>
	  	</div>
          <%
       			}
       			if (null != deleSuccMsg) {
       		%>
	  	<div class="row">
         	<div class="col-lg-12">
	  		<div class="alert alert-success"><%=deleSuccMsg%></div>
	  		</div>
	  	</div>
	  	<%
	  		  	   }
	  		  		if (null != deleErrorMsg) {
	  		  	%>
	  	<div class="row">
         	<div class="col-lg-12">
	  		<div class="alert alert-error"><%=deleErrorMsg%></div>
	  		</div>
	  	</div>
	  	<%
	  		  	   }
	  	%>
            <p><a class="btn btn-primary" href="${pageContent.request.contentPath }admin.html?action=AddTypeAdmin">新建分类</a><p>
		    
		    
		    
          <div class="row">
          	<div class="col-lg-12">
            	<div class="table-responsive">
              	<table class="table table-hover tablesorter">
					<thead>
						<tr>
							<th>分类名</th>
		          			<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<%
					for (int i = 0; i < tList.size(); i++) {
						Type t = tList.get(i);
				%>
				<tr>
					<td><%=t.getName()%></td>
					<td>
						<a href="${pageContext.request.contextPath }/admin.html?action=updateType&name=<%=t.getName()%>"><i class="glyphicon glyphicon-pencil"></i></a>&nbsp;&nbsp;
						<a href="${pageContext.request.contextPath }/admin.html?action=deleteType&name=<%=t.getName()%>"><i class="glyphicon glyphicon-floppy-remove"></i></a>
					</td>
				</tr>
				<%
					} 
				%>
					</tbody>
					</table>
					</div>
				</div>
			</div>
				<div>
					<!-- pager -->
					
				</div>
			</div>
        </div>
<% } else { %>
<%-- 404 page --%>
<% } %>
<jsp:include page="frame/Footer.jsp"></jsp:include>