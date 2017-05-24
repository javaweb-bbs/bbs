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
    public static JSONArray list(Connection con, int invitationId) {
        JSONArray result = new JSONArray();
        String search = "select * from comment where invitation = ?";
        try {
            PreparedStatement ps = con.prepareStatement(search);
            ps.setInt((int) 1, invitationId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Comment comment = new Comment();
                comment.setCommentId(rs.getInt("comment_id"));
                comment.setCommentUser(rs.getInt("comment_user"));
                comment.setInvitation(invitationId);
                comment.setContent(rs.getString("content"));
                comment.setAnswerUser(rs.getInt("answerUser"));
                result.put(new JSONObject(comment));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

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
                result.put("status", "Failer");
            } else {
                result.put("status", "OK");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
