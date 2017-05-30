<%@ page language="java" import="java.util.*" import="bbs.model.*" 
import="bbs.dao.*" pageEncoding="UTF-8"%>

<jsp:include page="frame/Header.jsp"></jsp:include>
	<div class="container">
		
		<div class="row col-md-12">
			<div class="col-md-12">
			   <div class="col-md-12">
					<ol class="breadcrumb">
		              <li><a href="invitationManage.jsp">帖子管理</a></li>
		              <li class="active">新建帖子</li>
		            </ol>
	            </div>
	                <div class="col-md-6">
	                    <div class="form-group">
	                    <label for="title">标题</label>
	                    <input class="form-control" id="name" name="title" type="text" >
	                    </div>
	                    <div class="form-group">
	                    <label for="sys_category">分类</label>
	                        <select class="form-control"  id="subject" name="sys_category">
	                    		<!--  遍历出来   -->
	                    		<%--<option value="" selected=""></option>--%>
                            </select>
                         </div> 
	                
	                <div class="form-group">
	                    <textarea class="form-control" id="message" name="content"  placeholder="文章内容" rows="5"></textarea>
	                </div>
	                  
	                <div class="form-group">
	                    <button id="contact-submit" type="submit" class="btn btn-primary input-medium pull-right">保存</button>
	                </div>
	                </div>
	        </div>
		</div>
	</div>
    <script src="js/ajax.js"></script>
    <script src="js/addInvitation.js"></script>

<jsp:include page="frame/Footer.jsp"></jsp:include>