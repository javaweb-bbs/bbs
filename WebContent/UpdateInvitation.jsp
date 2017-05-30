<%@ page language="java" import="java.util.*" import="bbs.model.*" 
import="bbs.dao.*" pageEncoding="UTF-8"%>

<jsp:include page="frame/Header.jsp"></jsp:include>

<body>
	<div class="container">
		<div class="row col-md-12">
			<div class="col-md-12">
			   <div class="col-md-12">					
					<ol class="breadcrumb">
		              <li><a href="">帖子管理</a></li>
		              <li class="active">编辑帖子</li>
		            </ol>
	            </div>
	            <form class="form-horizontal" name="add_artical_form" action="" method="post">
	                <div class="col-md-6">
	                    <div class="form-group">
	                    <label for="title">标题</label>
	                    <input class="form-control" id="name" name="title" type="text" value="">
	                    </div>
	                    <div class="form-group">
	                    <label for="sys_category">分类</label>
	                        <select class="form-control" id="subject" name="sys_category" class="span3">
								<!-- 更改类别只能改成已存在的，这里遍历出来，开始显示原来的分类 -->		
                             	<option value=""></option>
                            </select>
                        </div>
	                <div class="form-group">
	                    <textarea class="form-control" id="message" name="content" class="span6" placeholder="帖子内容" rows="5"></textarea>
	                </div>
	                <div class="form-group">
	                	<!-- 要有提示是否保存成功 -->
	                    <button id="contact-submit" type="submit" class="btn btn-primary input-medium pull-right">保存</button>
	                </div>
	                </div>
	            </form>
	        </div>
		</div>
	</div>
	
<jsp:include page="frame/Footer.jsp"></jsp:include>