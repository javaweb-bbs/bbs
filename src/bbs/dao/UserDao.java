package bbs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import bbs.model.User;
import bbs.util.DbUtil;

import org.json.JSONObject;

public class UserDao {

	// 获取用户信息
	public static JSONObject detail (Connection con, int userId) throws Exception {
		JSONObject result = new JSONObject();
		String search = "select * from user where user_id = ?";
		PreparedStatement ps = null;
		if (userId != 0) {
			try {
				ps = con.prepareStatement(search);
				ps.setInt((int) 1, userId);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					User user = new User();
					user.setUserId(rs.getInt("user_id"));
					user.setUserName(rs.getString("username"));
					user.setIsAdmin(rs.getBoolean("is_admin"));
					user.setEmail(rs.getString("email"));
					user.setSex(rs.getShort("sex"));
					result = new JSONObject(user);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				ps.close();
				new DbUtil().closeCon(con);
			}
		} else {
			result.put("msg", "the user is not existed");
		}
		return result;
	}

	// 登录
	public static JSONObject login(Connection con,User user) throws Exception {
		JSONObject result = new JSONObject();
		String search = "select * from user where username=? and password=?";
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(search);
			ps.setString((int) 1, user.getUserName());
			ps.setString((int) 2, user.getPassWord());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				User info = new User();
				info.setUserId(rs.getInt("user_id"));
				info.setUserName(user.getUserName());
				info.setEmail(rs.getString("email"));
				info.setSex(rs.getInt("sex"));
				info.setIsAdmin(rs.getBoolean("is_admin"));
				result = new JSONObject(info);
			} else {
				result.put("status", "user is not existed");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ps.close();
			new DbUtil().closeCon(con);
		}
		return result;
	}
	
	// 注册
	public static JSONObject register(Connection con,User user) throws Exception {
		JSONObject result = new JSONObject();
		String insertMes = "insert into user (username, password, email) values (?, ?, ?)";
		PreparedStatement ps = null;

		try {
			ps = con.prepareStatement(insertMes);
			ps.setString((int) 1, user.getUserName());
			ps.setString((int) 2, user.getPassWord());
			ps.setString((int) 3, user.getEmail());
			int num = ps.executeUpdate();
			if (num == 0) {
				result.put("status", "register fail");
			} else {
				User userInfo = newUser(con, user.getUserName(), user.getPassWord());
				result = new JSONObject(userInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ps.close();
			new DbUtil().closeCon(con);
		}
		return result;
	}

	// 获取注册用户信息
	public static User newUser(Connection con, String username, String password) throws Exception {
		User result = new User();
		String search = "select * from user where username = ? and password = ?";
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(search);
			ps.setString((int) 1, username);
			ps.setString((int) 2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				result.setUserId(rs.getInt("user_id"));
				result.setSex(rs.getShort("sex"));
				result.setEmail(rs.getString("email"));
				result.setUserName(username);
				result.setIsAdmin(rs.getBoolean("is_admin"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ps.close();
			new DbUtil().closeCon(con);
		}
		return result;
	}
	
	// 更改用户信息
	public static JSONObject updateUser(Connection con,User user) throws Exception {
		JSONObject result = new JSONObject();
		String updateMes = "update user set email = ?, sex = ? where user_id = ?";
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(updateMes);
			ps.setString((int) 1, user.getEmail());
			ps.setInt((int) 2, user.getSex());
			ps.setInt((int) 3, user.getUserId());
			int num = ps.executeUpdate();
			if (num == 0) {
				result.put("status", "update fail");
			} else {
				result.put("status", "update success");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ps.close();
			new DbUtil().closeCon(con);
		}
		return result;
	}

	// 更改密码
	public static JSONObject updatePassword(Connection con,User user) throws Exception {
		JSONObject result = new JSONObject();
		String updateMes = "update user set password = ? where user_id = ?";
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(updateMes);
			ps.setString((int) 1, user.getPassWord());
			ps.setInt((int) 2, user.getUserId());
			int num = ps.executeUpdate();
			if (num == 0) {
				result.put("status", "update password fail");
			} else {
				result.put("status", "update password success");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ps.close();
			new DbUtil().closeCon(con);
		}
		return result;
	}
	
}
