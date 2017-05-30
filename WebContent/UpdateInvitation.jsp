<%@ page language="java" import="java.util.*" import="bbs.model.*" 
import="bbs.dao.*" pageEncoding="UTF-8"%>

<jsp:include page="frame/Header.jsp"></jsp:include>
<div class="container">
	<div class="row col-md-12">
		<div class="col-md-12">
			<ol class="breadcrumb">
				<li><a href="invitationManage.jsp">帖子管理</a></li>
				<li class="active">编辑帖子</li>
			</ol>
		</div>
		<div class="col-md-6">
			<div class="form-group">
				<label for="title">标题</label>
				<input class="form-control" id="title" name="title" type="text" value="">
			</div>
			<div class="form-group">
				<label for="sys_category">分类</label>
				<select class="form-control" id="sys_category" name="sys_category" class="span3">
				</select>
			</div>
			<div class="form-group">
				<textarea class="form-control" id="message" name="content" class="span6" placeholder="帖子内容" rows="5"></textarea>
			</div>
			<div class="form-group">
				<button id="update-btn" type="submit" class="btn btn-primary input-medium pull-right">保存</button>
			</div>
		</div>
	</div>
</div>
<script src="js/ajax.js"></script>
<script src="js/updateInvitation.js"></script>
<jsp:include page="frame/Footer.jsp"></jsp:include>
</body>
