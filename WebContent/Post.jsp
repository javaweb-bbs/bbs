<%@ page language="java" import="java.util.*" import="bbs.model.*"
	import="bbs.dao.*" pageEncoding="UTF-8"%>
<jsp:include page="frame/Header.jsp"></jsp:include>

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
<script src="js/ajax.js"></script>
<script src="js/getDetail.js"></script>