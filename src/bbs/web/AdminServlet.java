package bbs.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import bbs.model.User;
import bbs.util.DbUtil;

@WebServlet("/admin.html")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Connection con=null;
    DbUtil dbUtil =new DbUtil();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	
	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action=request.getParameter("action");
		if(action.equals("login")){
			String userName = request.getParameter("username");
			String passWord = request.getParameter("password");
			User admin=new User(userName,passWord,true);
			if (admin == null) {
				request.getSession().setAttribute("msg", "用户名或密码为空！");
				response.sendRedirect("AdminLogin.jsp");
			} else {
				request.getSession().setAttribute("admin", admin);
				request.getRequestDispatcher("/admin/UserIndex.jsp").forward(request, response);
			}
		}else if(action.equals("logout")){
			HttpSession session = request.getSession(false);
			
			if (null != session) {
				session.removeAttribute("admin");
			}
			request.getSession().setAttribute("logoutMsg", "已退出登录！");
			response.sendRedirect("AdminLogin.jsp");
			
		}else if(action.equals("useradmin")){	
			
			request.getRequestDispatcher("admin/UserAdmin.jsp").forward(request, response);
		}else if(action.equals("InvitationAdmin")){	
			
			request.getRequestDispatcher("admin/InvitationAdmin.jsp").forward(request, response);
		}
	}

	
}
