package bbs.web;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bbs.dao.AdminDao;
import bbs.model.Invitation;
import bbs.model.Type;
import bbs.model.User;
import bbs.util.DbUtil;
import bbs.util.PageBean;

@WebServlet("/admin.html")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Connection con=null;
    DbUtil dbUtil =new DbUtil();
    AdminDao adminDao=new AdminDao();
	
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
				response.sendRedirect("admin.html?action=index");
			}
		}else if(action.equals("logout")){
			HttpSession session = request.getSession(false);
			
			if (null != session) {
				session.removeAttribute("admin");
			}
			request.getSession().setAttribute("logoutMsg", "已退出登录！");
			response.sendRedirect("AdminLogin.jsp");
			
		}else if(action.equals("index")){		
			int totalTypeCount = adminDao.getTypeTotalRecords();
			request.setAttribute("totalTypeCount", totalTypeCount);
			int totalUserCount = adminDao.getUserTotalRecords();
			request.setAttribute("totalUserCount", totalUserCount);
			int totalInvitationCount=adminDao.getTotalRecords();
			request.setAttribute("totalInvitationCount", totalInvitationCount);
			int totalCommentCount=adminDao.getCommentTotalRecords();
			request.setAttribute("totalCommentCount", totalCommentCount);
			request.getRequestDispatcher("admin/UserIndex.jsp").forward(request, response);
		}else if(action.equals("useradmin")){	
			request.setCharacterEncoding("utf-8");
			PageBean pageBean=new PageBean();
			int pageSize = 10;//每页显示的记录数
			int currentP = 1;//当前页面
			int totalCount = adminDao.getUserTotalRecords();//表中的记录数
			int pageCount = pageBean.getTotalPages(totalCount, pageSize);
			//获得分页条上的当前页码
			String pStr = request.getParameter("curPage");
			if (pStr == null)
				pStr = "1";
			currentP = Integer.parseInt(pStr);
			//如果当前页大于总的页面数，当前页面赋值为总的页面数
			if (currentP > pageCount)
				currentP = pageCount;
			//如果当前页小于0 重置为第一页
			if (currentP < 0)
				currentP = 1;
			//分页查询，mysql的分页查询关键字是limit,注意limit后面有空格
			List<User> uList = null;
			try {
				uList = adminDao.getUserByPage(currentP, pageSize);
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.setAttribute("uList", uList);
			request.setAttribute("currentP", currentP);
			request.setAttribute("pageCount", pageCount);
			request.getRequestDispatcher("admin/UserAdmin.jsp").forward(request, response);
		}else if(action.equals("InvitationAdmin")){	
			request.setCharacterEncoding("utf-8");
			PageBean pageBean=new PageBean();
			int pageSize = 10;//每页显示的记录数
			int currentP = 1;//当前页面
			int totalCount = adminDao.getTotalRecords();//表中的记录数
			int pageCount = pageBean.getTotalPages(totalCount, pageSize);
			//获得分页条上的当前页码
			String pStr = request.getParameter("curPage");
			if (pStr == null)
				pStr = "1";
			currentP = Integer.parseInt(pStr);
			//如果当前页大于总的页面数，当前页面赋值为总的页面数
			if (currentP > pageCount)
				currentP = pageCount;
			//如果当前页小于0 重置为第一页
			if (currentP < 0)
				currentP = 1;
			//分页查询，mysql的分页查询关键字是limit,注意limit后面有空格
			List<Invitation> invitation = null;
			try {
				invitation = adminDao.getInvitationByPage(currentP, pageSize);
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.setAttribute("invitation", invitation);
			request.setAttribute("currentP", currentP);
			request.setAttribute("pageCount", pageCount);
			request.getRequestDispatcher("admin/InvitationAdmin.jsp").forward(request, response);
		}else if(action.equals("deleteInvitation")){		
			String id = request.getParameter("id");
			System.out.println("id"+id);
			try{
				con=dbUtil.getCon();
				int delNums=adminDao.deleteInvitation(con, id);
				if(delNums>0){
					request.setAttribute("deleSuccMsg", "删除成功！");
					request.getRequestDispatcher("admin.html?action=InvitationAdmin").forward(request, response);
				}else{
					request.setAttribute("deleErrorMsg", "删除失败！");
					request.getRequestDispatcher("admin.html?action=InvitationAdmin").forward(request, response);
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				try {
					dbUtil.closeCon(con);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}else if(action.equals("deleteUser")){		
			String id = request.getParameter("id");
			System.out.println("id"+id);
			try{
				con=dbUtil.getCon();
				int delNums=adminDao.deleteUser(con, id);
				if(delNums>0){
					request.setAttribute("deleSuccMsg", "删除成功！");
					request.getRequestDispatcher("admin.html?action=useradmin").forward(request, response);
				}else{
					request.setAttribute("deleErrorMsg", "删除失败！");
					request.getRequestDispatcher("admin.html?action=useradmin").forward(request, response);
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				try {
					dbUtil.closeCon(con);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}else if(action.equals("goodInvitation")){		
			String id = request.getParameter("id");
			System.out.println("id"+id);
			try{
				con=dbUtil.getCon();
				int updateNums=adminDao.updateGood(con, id);
				if(updateNums>0){
					request.setAttribute("updateSuccMsg", "设置精华成功！");
					request.getRequestDispatcher("admin.html?action=InvitationAdmin").forward(request, response);
				}else{
					request.setAttribute("updateErrorMsg", "设置精华失败！");
					request.getRequestDispatcher("admin.html?action=InvitationAdmin").forward(request, response);
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				try {
					dbUtil.closeCon(con);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}else if(action.equals("TypeAdmin")){	
			request.setCharacterEncoding("utf-8");
			PageBean pageBean=new PageBean();
			int pageSize = 10;//每页显示的记录数
			int currentP = 1;//当前页面
			int totalCount = adminDao.getTypeTotalRecords();//表中的记录数
			int pageCount = pageBean.getTotalPages(totalCount, pageSize);
			//获得分页条上的当前页码
			String pStr = request.getParameter("curPage");
			if (pStr == null)
				pStr = "1";
			currentP = Integer.parseInt(pStr);
			//如果当前页大于总的页面数，当前页面赋值为总的页面数
			if (currentP > pageCount)
				currentP = pageCount;
			//如果当前页小于0 重置为第一页
			if (currentP < 0)
				currentP = 1;
			//分页查询，mysql的分页查询关键字是limit,注意limit后面有空格
			List<Type> type = null;
			try {
				type = adminDao.getTypeByPage(currentP, pageSize);
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.setAttribute("type", type);
			request.setAttribute("currentP", currentP);
			request.setAttribute("pageCount", pageCount);
			request.getRequestDispatcher("admin/TypeAdmin.jsp").forward(request, response);
		}else if(action.equals("deleteType")){		
			request.setCharacterEncoding("utf-8");
			String name = request.getParameter("name");
			System.out.println("id"+name);
			try{
				con=dbUtil.getCon();
				boolean delNums=adminDao.getTypeNameByTypeId(con, name);
				if(delNums){
					request.setAttribute("msg", "该类别下有帖子，不能删除！");
					request.getRequestDispatcher("admin.html?action=TypeAdmin").forward(request, response);
					return;
				}else{
					int del=adminDao.deleteType(con, name);
					if(del>0){
						request.setAttribute("deleteSuccMsg", "删除成功！");
						request.getRequestDispatcher("admin.html?action=TypeAdmin").forward(request, response);
					}else{
						request.setAttribute("deleteSuccMsg", "删除失败！");
						request.getRequestDispatcher("admin.html?action=TypeAdmin").forward(request, response);
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				try {
					dbUtil.closeCon(con);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}else if(action.equals("AddTypeAdmin")){	
			request.getRequestDispatcher("admin/AddTypeAdmin.jsp").forward(request, response);
		}else if(action.equals("SaveType")){	
			String type = request.getParameter("scgName");
			Type t=new Type(type);
			try {
				con = dbUtil.getCon();
				int addNums = adminDao.addType(con, t);
				System.out.println(addNums);
				if (addNums > 0) {
					request.setAttribute("msg", "添加成功！");
					request.getRequestDispatcher("admin.html?action=AddTypeAdmin").forward(request, response);
				} else {
					request.setAttribute("Errormsg", "添加失败！");
					request.getRequestDispatcher("admin.html?action=AddTypeAdmin").forward(request, response);
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
		}else if(action.equals("SaveEditTypeAdmin")){
			String newname = request.getParameter("scgName");
			String oldname = request.getParameter("name");
			try {
				con = dbUtil.getCon();
				int updateNums = adminDao.updateType(con, newname,oldname);
				if (updateNums > 0) {
					request.setAttribute("msg", "修改成功！");
					request.getRequestDispatcher("admin.html?action=TypeAdmin").forward(request, response);
				} else {
					request.setAttribute("Errormsg", "修改失败！");
					request.getRequestDispatcher("admin.html?action=TypeAdmin").forward(request, response);
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
		}else if(action.equals("updateType")){
			request.setCharacterEncoding("utf-8");
			String name=request.getParameter("name");
			Type t=new Type();
			try{
				con=dbUtil.getCon();
				t=adminDao.findTypeNameByName(con, name);
				request.setAttribute("t", t);
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				try {
					dbUtil.closeCon(con);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			request.getRequestDispatcher("admin/EditTypeAdmin.jsp").forward(request, response);
		}
	}

	
}
