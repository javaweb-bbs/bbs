package bbs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bbs.model.Invitation;
import bbs.model.User;
import bbs.util.DbUtil;

public class AdminDao {
	
	Connection con=null;
	
	//管理员登录
	public User adminLogin(Connection con, User user) throws SQLException{
		User resultUser=null;
		String sql="select * from user where userName=? and password=? and id_admin=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,user.getUserName());
		pstmt.setString(2,user.getPassWord());
		pstmt.setBoolean(3, true);
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			resultUser=new User();
			resultUser.setUserId(rs.getInt("user_id"));
			resultUser.setUserName(rs.getString("username"));
			resultUser.setPassWord(rs.getString("password"));
			resultUser.setIsAdmin(rs.getBoolean("is_admin"));
		}
		return resultUser;
	}
	
	//帖子总数
	public int getTotalRecords() {
		int count=0;
		DbUtil dbc=null;
		try {
			dbc=new DbUtil();
			String sql = "select count(*) as t from invitation";
			con = dbc.getCon();
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(sql);
			if(rs.next()){
				//count=rs.getInt(1);对应于没有as t
				count=rs.getInt("t");
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;

	}

	// 显示帖子详情
	public List<Invitation> getInvitationByPage(int curPage, int size) throws Exception {
		List<Invitation> inList = null;
		int start = (curPage - 1) * size;	//limit从0开始
		DbUtil dbc=new DbUtil();
		try {
			String sql="select *, username, (select count(*) from invitation) as total from invitation,user where" +
                    " user.user_id = invitation.author  limit " + start
					+ "," + size;
			inList = new ArrayList<Invitation>();			
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(sql);
			while (rs.next()) {
				Invitation in = new Invitation();
				in.setInvitationId(rs.getInt("invitation_id"));
				in.setAuthor(rs.getInt("author"));
				in.setAuthorName(rs.getString("username"));
				in.setTitle(rs.getString("title"));
				in.setContent(rs.getString("content"));
				in.setEssence(rs.getBoolean("is_essence"));
				in.setType(rs.getString("type"));
				in.setDateCreate(rs.getDate("date_create"));
				inList.add(in);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbc.closeCon(con);
		}
		return inList;
	}
	
	//用户总数
	public int getUserTotalRecords() {
		int count=0;
		DbUtil dbc=null;
		try {
			dbc=new DbUtil();
			String sql = "select count(*) as t from user";
			con = dbc.getCon();
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(sql);
			if(rs.next()){
				//count=rs.getInt(1);对应于没有as t
				count=rs.getInt("t");
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;

	}

	// 显示用户详情
	public List<User> getUserByPage(int curPage, int size) throws Exception {
		List<User> uList = null;
		int start = (curPage - 1) * size;	//limit从0开始
		DbUtil dbc=new DbUtil();
		try {
			String sql="select *, (select count(*) from user) as total from user limit " + start
					+ "," + size;
			uList = new ArrayList<User>();			
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(sql);
			while (rs.next()) {
				User u = new User();
				u.setUserId(rs.getInt("user_id"));
				u.setUserName(rs.getString("username"));
				u.setEmail(rs.getString("email"));
				u.setSex(rs.getInt("sex"));
				uList.add(u);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbc.closeCon(con);
		}
		return uList;
	}
	
	//评论总数
	public int getCommentTotalRecords() {
		int count=0;
		DbUtil dbc=null;
		try {
			dbc=new DbUtil();
			String sql = "select count(*) as t from comment";
			con = dbc.getCon();
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(sql);
			if(rs.next()){
				//count=rs.getInt(1);对应于没有as t
				count=rs.getInt("t");
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	//删除贴子
	public int deleteInvitation(Connection con,String id)throws Exception{
		String sql="delete from invitation where invitation_id ='"+id+"'";
		PreparedStatement pstmt=con.prepareStatement(sql);
		return pstmt.executeUpdate();
	}
	
	//删除用户
	public int deleteUser(Connection con,String id)throws Exception{
		String sql="delete from user where user_id ='"+id+"'";
		PreparedStatement pstmt=con.prepareStatement(sql);
		return pstmt.executeUpdate();
	}
	
	//设置精华
	public int updateGood(Connection con,String id)throws Exception{
		String sql="UPDATE invitation SET is_essence=? WHERE invitation_id='"+id+"'";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setBoolean(1,true);
		return pstmt.executeUpdate();
	}
}
