package bbs.dao;

import bbs.model.Comment;
import org.json.JSONArray;
import org.json.JSONObject;
import org.omg.CORBA.COMM_FAILURE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by sjf on 5/24/17.
 */
public class commentDao {
    // 获取评论列表
    public static JSONArray list(Connection con, int invitationId) {
        JSONArray result = new JSONArray();
        String search = "select *,username from comment, user where comment.answer_user = user.user_id and comment.invitation = ?";
        try {
            PreparedStatement ps = con.prepareStatement(search);
            ps.setInt((int) 1, invitationId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Comment comment = new Comment();
                comment.setCommentId(rs.getInt("comment_id"));
                comment.setCommentUser(rs.getInt("comment_user"));
                comment.setInvitation(invitationId);
                comment.setContent(rs.getString("content"));
                comment.setAnswerUser(rs.getInt("answer_user"));
                comment.setAuthorName(rs.getString("username"));
                result.put(new JSONObject(comment));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    // 添加评论
    public static JSONObject add(Connection con, Comment comment) {
        JSONObject result = new JSONObject();
        String message = "insert into comment (comment_user, invitation, answer_user, content) values (?, ?, ?, ?)";

        try {
            PreparedStatement ps = con.prepareStatement(message);
            ps.setInt((int) 1, comment.getCommentUser());
            ps.setInt((int) 2, comment.getInvitation());
            ps.setInt((int) 3, comment.getAnswerUser());
            ps.setString((int) 4, comment.getContent());
            int num = ps.executeUpdate();
            if(num == 0) {
                result.put("status", "add fail");
            } else {
                result.put("status", "add success");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // 删除评论
    public static JSONObject delete(Connection con, int commentId, int invitationId) throws SQLException {
        JSONObject result = new JSONObject();
        String deleteManyComment = "delete from comment where invitation = ?";
        String deleteSingleComment = "delete from comment where comment_id = ?";
        int num = 0;

        if (invitationId == 0) {
            PreparedStatement singlePs = con.prepareStatement(deleteSingleComment);
            singlePs.setInt((int) 1, commentId);
            num = singlePs.executeUpdate();
        } else {
            PreparedStatement manyPs = con.prepareStatement(deleteManyComment);
            manyPs.setInt((int) 1, invitationId);
            num = manyPs.executeUpdate();
        }

        if (num == 0) {
            result.put("status", "delete fail");
        } else {
            result.put("status", "delete success");
        }
        return result;
    }
}
