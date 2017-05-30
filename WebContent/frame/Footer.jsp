<%@ page language="java" import="java.util.*" import="cap.bean.*" import="cap.dao.impl.*" pageEncoding="utf-8"%>
    <div class="container">
      <hr>
      <footer>
        <div class="row">
          <div class="col-lg-12">
            <p >
            Copyright &copy; 2017 &middot; UI based on Bootstrap 3 
                                  &middot; <a href="${pageContext.request.contextPath}/AdminLogin.jsp" target="_blank">admin</a>
                                  &middot;访问人数：<%=(Integer)session.getAttribute("num") %>                                                 
            </p>
          </div>
        </div>
      </footer>
    </div>
    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
  </body>
</html>
