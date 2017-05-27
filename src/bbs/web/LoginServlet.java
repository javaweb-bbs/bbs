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
		request.setCharacterEncoding("utf-8");
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
		}else if(action.equals("register")){
			request.setCharacterEncoding("utf-8");	
			String username = request.getParameter("username");
			String sex = request.getParameter("sex");
			int sex_i=0;
			if(sex.equals("男"))
				sex_i=1;
			else if(sex.equals("女"))
				sex_i=2;
			else if(sex.equals("")||sex==null)
				sex_i=1;
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			DbUtil dbUtil = new DbUtil();
			Connection con = null;
			User user =new User(username,password,sex_i,email,1);
			try {
				con = dbUtil.getCon();
				int insertNums = userDao.insertUser(con, user);
				if (insertNums > 0) {
					request.getSession().setAttribute("succMsg", "注册成功！");
					response.sendRedirect("Register.jsp");
				} else {
					request.getSession().setAttribute("errorMsg", "注册失败！");
					response.sendRedirect("Register.jsp");
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					dbUtil.closeCon(con);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}else if(action.equals("profile")){
			int id = Integer.parseInt(request.getParameter("id"));
			Connection con=null;
			User user=new User();
			try{
				con=dbUtil.getCon();
				user=userDao.getUserById(con, id);
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				try {
					dbUtil.closeCon(con);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}		
			request.getSession().setAttribute("user", user);
			response.sendRedirect("Profile.jsp");
		}else if(action.equals("updateprofile")){
			int userId = Integer.parseInt(request.getParameter("id"));
			String email=request.getParameter("email");
			String sex = request.getParameter("gender");
			System.out.println(sex);
			int sex_i=0;
			if(sex.equals("男"))
				sex_i=1;
			else if(sex.equals("女"))
				sex_i=2;
			else if(sex.equals("")||sex==null)
				sex_i=1;
			
			Connection con = null;
			User user=new User(userId,sex_i,email);
			try {
				con = dbUtil.getCon();
				int delNums = userDao.updateUser(con, user);
				if (delNums > 0) {
					request.getSession().setAttribute("succMsg", "保存成功！");
				} else {
					request.getSession().setAttribute("errorMsg", "保存失败！");
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					dbUtil.closeCon(con);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			response.sendRedirect("user?action=profile&id="+userId);
		}else if(action.equals("updatepass")){
			int userId = Integer.parseInt(request.getParameter("id"));
			String password = request.getParameter("new_pwd");
			
			Connection con = null;
			User user=new User(userId,password);
			try {
				con = dbUtil.getCon();
				int delNums = userDao.updatePassword(con, user);
				if (delNums > 0) {
					request.getSession().setAttribute("succMsg", "保存成功！");
				} else {
					request.getSession().setAttribute("errorMsg", "保存失败！");
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					dbUtil.closeCon(con);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			response.sendRedirect("user?action=profile&id="+userId);
		}else if(action.equals("bloginfo")){
			
			response.sendRedirect("BlogInfo.jsp");
		}
	}
}

