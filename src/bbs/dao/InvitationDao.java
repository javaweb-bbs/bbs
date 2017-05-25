package bbs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bbs.model.Invitation;
import bbs.model.User;
import bbs.util.DbUtil;


public class InvitationDao {

	DbUtil dbUtil = new DbUtil();
	Connection con = null;
	
	/**
	 * 根据用户id获取该用户帖子总数（用于分页）
	 * @return
	 */
	public int getTotalRecordsByUserId(int userId) {
		int count=0;
		try {
			String sql = "select count(*) as t from invitation where author=";
			con = dbUtil.getCon();
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
}
