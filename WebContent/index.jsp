<%@ page language="java" import="java.util.*" import="bbs.model.*" import="bbs.dao.*" pageEncoding="utf-8"%>
<jsp:include page="frame/Header.jsp"></jsp:include>
    <div class="container">
      <div class="row">
        <div id="blog" class="col-lg-9" >
          <h1><a href="index.jsp">BBS论坛——<small>基于JSP, Servlet技术构建</small></a></h1>
          <br>
          <div class="list">
          </div>
          <ul class="pager">
            <li class="previous"><a>&larr; 上一页</a></li>
            <li class="next"><a>下一页  &rarr;</a></li>
          </ul>
        </div>
        <div class="col-lg-3">
          <div class="well">
            <h4>搜索站内帖子</h4>
            <div class="input-group">
              <input type="text" class="form-control search-invitation" placeholder="帖子标题">
              <span class="input-group-btn search-btn">
                <button class="btn btn-default" type="submit"><span class="glyphicon glyphicon-search"></span></button>
              </span>
            </div>
          </div>
          <div class="well">
            <h4>帖子分类</h4>
              <div class="row">
                <div class="col-lg-6">
                  <ul class="list-unstyled type-list">
                  </ul>
                </div>
              </div>
          </div>
          
          <div class="well">
            <h4>精华TOP10</h4>
            <div class="row">
              <ul class="list-unstyled">
                <li><a href="" target="_blank">c</a></li>
                <li><a href="" target="_blank">c++</a></li>
              </ul>
            </div>
          </div>
          </div>
      </div>
    </div>

    <script src="js/ajax.js"></script>
    <script src="js/index.js"></script>

<jsp:include page="frame/Footer.jsp"></jsp:include>
