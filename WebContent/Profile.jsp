<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
import = "bbs.model.*"
%>

<jsp:include page="frame/Header.jsp"></jsp:include>
	<div class="container">
		<div class="well row col-md-6">
	    <ul class="nav nav-tabs">
	      <li class="active"><a href="#home" data-toggle="tab">个人资料</a></li>
	      <li><a href="#profile" data-toggle="tab">修改密码</a></li>
	    </ul>
	    <div id="myTabContent" class="tab-content">
	      <div class="tab-pane active in" id="home">
	            <div class="form-group">
	            <label for="username">用户名</label>
	            <input type="text" id="username" class="form-control" name="username" disabled>
	            </div>
	            <div class="form-group">
	            <label for="email">邮箱</label>
	            <input type="text" id="email" class="form-control" name="email">
	            </div>
	            <div class="form-group">
	            <label for="gender">性别</label>
	            <select class="form-control" name="gender" id="gender">
	            	<option value="男">男</option>
	            	<option value="女" selected>女</option>
	            </select>
	            </div>
	          	<div class="form-group" >
	        	    <button class="btn btn-primary update-info" type="submit">保存</button>
	        	</div>
	      </div>
	      <div class="tab-pane fade" id="profile">
	        	<div class="form-group">
	        	<label for="old-pwd">旧密码</label>
	        	<input class="form-control" id="old-pwd" type="password" name="old_pwd" >
	        	</div>
	        	<div class="form-group">
	        	<label for="new-pwd" >新密码</label>
	        	<input class="form-control" id="new-pwd" type="password" name="new_pwd" >
	        	</div>
	        	<div class="form-group">
	        	<label for="ensure-pwd">新密码（确认）</label>
	        	<input class="form-control" id="ensure-pwd" type="password" name="submit_new_pwd">
	        	</div>
	        	<div class="form-group">
	        	   <button class="btn btn-primary update-pwd">保存</button>
	        	</div>
	      </div>
	  </div>
	</div>
	</div>
<jsp:include page="frame/Footer.jsp"></jsp:include>
<script src="js/ajax.js"></script>
<script src="js/profile.js"></script>