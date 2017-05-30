<%@ page language="java" import="java.util.*" import="bbs.model.*" import="bbs.dao.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	User admin = (User)request.getSession().getAttribute("admin");
	List<Invitation> inList = (List<Invitation>)request.getAttribute("invitation");

	String deleSuccMsg = (String)request.getAttribute("deleSuccMsg");	//删除帖子消息
	String deleErrorMsg = (String)request.getAttribute("deleErrorMsg");
	
	String updateSuccMsg = (String)request.getAttribute("updateSuccMsg");	//设置精华帖子
	String updateErrorMsg = (String)request.getAttribute("updateErrorMsg");

	int curPage = (Integer)request.getAttribute("currentP");
	int totalPages = (Integer)request.getAttribute("pageCount");
%>


<jsp:include page="frame/Header.jsp"></jsp:include>

<%
	if (null != admin) {
%>
      <div class="container">
	  <!-- Sidebar -->
      <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
          <a class="navbar-brand" href="">BBS管理系统</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse navbar-ex1-collapse">
          <ul class="nav navbar-nav side-nav">
            <li class="active"><a href="${pageContent.request.contentPath }admin.html?action=index"><i class="glyphicon glyphicon-dashboard"></i> 控制面板</a></li>
            <li><a href="${pageContent.request.contentPath }admin.html?action=useradmin"><i class="glyphicon glyphicon-cog"></i> 用户管理</a></li>
            <li><a href="${pageContent.request.contentPath }admin.html?action=InvitationAdmin"><i class="glyphicon glyphicon-cog"></i> 帖子管理</a></li>
            <li><a href="${pageContent.request.contentPath }admin.html?action=TypeAdmin"><i class="glyphicon glyphicon-edit"></i> 分类管理</a></li>
          </ul>
          <ul class="nav navbar-nav navbar-right navbar-user">
            <li class="dropdown user-dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="glyphicon glyphicon-user"></i> <%=admin.getUserName()%> <b class="caret"></b></a>
              <ul class="dropdown-menu">
                <li><a href="#"><i class="glyphicon glyphicon-cog"></i> 设置</a></li>
                <li class="divider"></li>
                <li><a href="admin.html?action=logout"><i class="glyphicon glyphicon-off"></i> 登出</a></li>
              </ul>
            </li>
          </ul>
        </div><!-- /.navbar-collapse -->
      </nav>
    

	<div id="page-wrapper">

       <div class="row">
         <div class="col-lg-12">
           <br>
           <ol class="breadcrumb">
           <li><a href="${pageContent.request.contentPath }admin.html?action=index"><i class="glyphicon glyphicon-dashboard"></i> 控制面板</a></li>
             <li class="active"><i class="glyphicon glyphicon-edit"></i> 帖子管理</li>
           </ol>
         </div>  
       </div>
       
		<%
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
	  	<%
       			if (null != updateSuccMsg) {
       		%>
	  	<div class="row">
         	<div class="col-lg-12">
	  		<div class="alert alert-success"><%=updateSuccMsg%>返回<a href="${pageContent.request.contentPath }index.jsp">首页</a>查看</div>
	  		</div>
	  	</div>
	  	<%
	  		  	   }
	  		  		if (null != updateErrorMsg) {
	  		  	%>
	  	<div class="row">
         	<div class="col-lg-12">
	  		<div class="alert alert-error"><%=updateErrorMsg%></div>
	  		</div>
	  	</div>
	  	<%
	  		  	   }
	  	%>
			

        <div class="row">
          <div class="col-lg-12">
            <div class="table-responsive">
              <table class="table table-hover tablesorter">
                <thead>
                  <tr>
                    <th>文章标题 </th>
                    <th>作者</th>
                    <th>发布时间</th>
                    <th style="width: 50px;">精华</th>
                    <th style="width: 50px;">操作 </th>
                  </tr>
                </thead>
           
                <tbody>
                <%
					for (int i = 0; i < inList.size(); i++) {
						Invitation inv = inList.get(i);
				%>
				<tr>
					<td><%=inv.getTitle()%></td>
					<td><%=inv.getAuthorName()%></td>
					<td><%=inv.getDateCreate()%></td>
					<td><a href="${pageContext.request.contextPath }/admin.html?action=goodInvitation&id=<%=inv.getInvitationId()%>">精华</a></td>
					<td><a href="${pageContext.request.contextPath }/admin.html?action=deleteInvitation&id=<%=inv.getInvitationId()%>"><i class="glyphicon glyphicon-floppy-remove"></i></a></td>
				</tr>
				<%
					} 
				%>
				
                </tbody>
              </table>
            </div>
          </div>
        </div>
        
        <!-- pager -->
          <ul class="pager">
						<%
							if (curPage > 1) {
						%>
						<li class="previous"><a
							href="${pageContext.request.contextPath }/admin.html?action=InvitationAdmin&curPage=<%=(curPage - 1)%>">&larr;
								上一页</a></li>
						<%
							}
						%>

						<%
							if (curPage < totalPages) {
						%>
						<li class="next"><a
							href="${pageContext.request.contextPath }/admin.html?action=InvitationAdmin&curPage=<%=(curPage + 1)%>">下一页
								&rarr;</a></li>
						<%
							}
						%>
					</ul>
        
		</div>
    </div>
    
<% } else { %>
<%-- 404 page --%>
<% } %>
<jsp:include page="frame/Footer.jsp"></jsp:include>

<script type="text/javascript">
function dele(deleUrl) {
	
	if (confirm("你确定要删除该分类吗？")) {
		location.href = deleUrl;
	
	}
}
</script>