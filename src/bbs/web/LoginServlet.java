package bbs.web;

import java.io.IOException;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import bbs.dao.UserDao;
import bbs.model.User;
import bbs.util.DbUtil;
import bbs.util.Stringutil;

@WebServlet("/user")
public class LoginServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DbUtil dbUtil=new DbUtil();
	UserDao userDao=new UserDao();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		String action=request.getParameter("action");
		if(action.equals("login")){
			String username=request.getParameter("username");
			String password=request.getParameter("password");
			request.setAttribute("username", username);
			request.setAttribute("password", password);
			if(Stringutil.isEmpty(username)||Stringutil.isEmpty(password)){
				request.setAttribute("error", "用户名或密码为空！");
				request.getRequestDispatcher("Login.jsp").forward(request, response);
				return;
			}
			User user=new User(username,password,0);
			Connection con=null;
			try {
				con=dbUtil.getCon();
				User currentUser=userDao.login(con, user);
				if(currentUser==null){
					request.setAttribute("error", "用户名或密码错误！");
					request.getRequestDispatcher("Login.jsp").forward(request, response);
				}else{
					HttpSession session=request.getSession();
					session.setAttribute("currentUser", currentUser);
					request.getRequestDispatcher("index.jsp").forward(request, response);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				try {
					dbUtil.closeCon(con);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}else if(action.equals("logout")){
			request.setCharacterEncoding("utf-8");		
			HttpSession session = request.getSession(false);
			if (session == null) {
				response.sendRedirect("index.jsp");
				return;
			}			
			session.removeAttribute("currentUser");
			response.sendRedirect("index.jsp");
		}
		
	}
}

