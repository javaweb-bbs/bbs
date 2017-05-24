package bbs.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mysql.jdbc.Connection;

import bbs.model.User;

public class UserDao {

	//登录
	public User login(Connection con,User user)throws Exception{
		User resultUser=null;
		String sql="select * from user where username=? and password=? and is_admin=?";
		//传入sql并返回
		PreparedStatement pstmt=con.prepareStatement(sql);
		//设置问号的值
		pstmt.setString(1,user.getUserName());
		pstmt.setString(2,user.getPassWord());
		pstmt.setInt(3, user.getIsAdmin());
		//执行
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
