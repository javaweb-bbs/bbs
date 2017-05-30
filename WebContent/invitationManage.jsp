<%@ page language="java" import="java.util.*" import="bbs.model.*"
	import="bbs.dao.*" pageEncoding="UTF-8"%>
<jsp:include page="frame/Header.jsp"></jsp:include>
	<div class="container">
		<div class="btn-toolbar">
			<a class="btn btn-primary" href="addInvitation.jsp">新建帖子</a>
		</div>
		<div class="well">
			<table class="table">
				<thead>
					<tr>
						<th>标题</th>
						<th>分类</th>
						<th>内容</th>
						<th>修改时间</th>
						<th style="width: 50px;">操作</th>
					</tr>
				</thead>
				<tbody class="invitation-list">

				</tbody>
			</table>
		</div>
		<ul class="pager">
			<li class="previous"><a>&larr;上一页</a></li>
			<li class="next"><a>下一页&rarr;</a></li>
		</ul>
	</div>
	<script src="js/ajax.js"></script>
	<script src="js/invitationManage.js"></script>
<jsp:include page="frame/Footer.jsp"></jsp:include>
</body>
