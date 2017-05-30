<%@ page language="java" import="java.util.*" import="bbs.model.*" 
import="bbs.dao.*" pageEncoding="UTF-8"%>

<jsp:include page="frame/Header.jsp"></jsp:include>
	<div class="container">
		<div class="btn-toolbar">
		    <a class="btn btn-primary" href="addType.jsp">新建分类</a>
		</div>
		<div class="well">
		    <table class="table">
		      <thead>
		        <tr>
		          <th>分类名称</th>
		          <th>包含文章数量</th>
		          <th style="width: 50px;">操作</th>
		        </tr>
		      </thead>
		      <tbody class="type-list">
		        <tr>
		        </tr>
		        
		      </tbody>
		    </table>
		</div>
	</div>
<jsp:include page="frame/Footer.jsp"></jsp:include>
<script src="js/ajax.js"></script>
<script src="js/typeManage.js"></script>
