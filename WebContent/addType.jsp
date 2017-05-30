<%@ page language="java" import="java.util.*" import="bbs.model.*" 
import="bbs.dao.*" pageEncoding="UTF-8"%>
<jsp:include page="frame/Header.jsp"></jsp:include>
    <div class="container "> 
		<ol class="breadcrumb">
             <li><a href="TypeManage.jsp">分类管理</a></li>
             <li class="active">新建分类</li>
        </ol>  
    	<div class="col-md-6">
    	<div class="from-group">
    		<label for="category_name">分类名：</label>
    		<input class="form-control" id="category_name" name="category_name" type="text">
    	</div>
    	
    	<div class="from-group">
    		<button id="add_category_submit" type="submit" class="add-type btn btn-primary">保存</button>
    	</div>
    	</div>
    </div>
<jsp:include page="frame/Footer.jsp"></jsp:include>
<script src="js/ajax.js"></script>
<script src="js/addType.js"></script>