package bbs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import bbs.model.User;

public class UserDao {

	/**
	 * 登录
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
			resultUser.setUserName(rs.getString("userName"));
			resultUser.setPassWord(rs.getString("password"));
			resultUser.setIsAdmin(rs.getInt("is_admin"));
		}
		return resultUser;
	}
}
