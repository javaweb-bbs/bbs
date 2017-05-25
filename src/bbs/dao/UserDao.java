package bbs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bbs.model.User;
import bbs.util.DbUtil;

public class UserDao {

	DbUtil dbUtil = new DbUtil();
	
	/**
	 * 前台登录
	 * @param con
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public User login(Connection con,User user)throws Exception{
		User resultUser=null;
		String sql="select * from user where username=? and password=? and is_admin=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,user.getUserName());
		pstmt.setString(2,user.getPassWord());
		pstmt.setInt(3, user.getIsAdmin());
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			resultUser=new User();
			resultUser.setUserId(rs.getInt("user_id"));
			resultUser.setUserName(rs.getString("userName"));
			resultUser.setPassWord(rs.getString("password"));
			resultUser.setSex(rs.getInt("sex"));
			resultUser.setEmail(rs.getString("email"));
			resultUser.setIsAdmin(1);
		}
		return resultUser;
	}
	
	/**
	 * 注册
	 * @param user
	 * @return
	 */
	public int insertUser(Connection con,User user)throws Exception{
		String insertsql="insert into user values(null,?,?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(insertsql);
		pstmt=con.prepareStatement(insertsql);
		pstmt.setString(1, user.getUserName());
		pstmt.setString(2, user.getPassWord());
		pstmt.setInt(3, user.getSex());
		pstmt.setString(4, user.getEmail());
		pstmt.setInt(5, 1);
		return pstmt.executeUpdate();
	}
	
	/**
	 * 编辑个人信息
	 * @param id
	 * @return
	 */
	public User getUserById(Connection con,int id) {
		User user=null;
		String sql="select * from user where user_id in("+id+")";
		System.out.println(id);
		try {
			con = dbUtil.getCon();
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(sql);
			if(rs.next())
			{
				user = new User();
				user.setUserName(rs.getString("username"));
				user.setPassWord(rs.getString("password"));
				user.setSex(rs.getInt("sex"));
				user.setEmail(rs.getString("email"));
				user.setIsAdmin(rs.getInt("is_admin"));
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
		return user;
	}
	
	/**
	 * 更改个人信息
	 * @param con
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int updateUser(Connection con,User user)throws Exception{
		String sql="UPDATE user SET email=?,sex=? WHERE user_id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,user.getEmail());
		pstmt.setInt(2, user.getSex());
		pstmt.setInt(3, user.getUserId());
		return pstmt.executeUpdate();
	}
	
	public int updatePassword(Connection con,User user)throws Exception{
		String sql="UPDATE user SET password=? WHERE user_id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,user.getPassWord());
		pstmt.setInt(2, user.getUserId());
		return pstmt.executeUpdate();
	}
}
