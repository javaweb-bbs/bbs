package bbs.dao;

import bbs.model.Invitation;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by sjf on 5/24/17.
 */
public class InvitaionDao {
    public static JSONArray list (Connection con, int pageNum, int pageSize) {
        JSONArray result = new JSONArray();
        String search = "select *, (select count(*) from invitation) as total from invitation limit ?,?";
        try {
            PreparedStatement ps = con.prepareStatement(search);
            ps.setInt((int) 1, (pageNum - 1) * pageSize);
            ps.setInt((int) 2, pageSize);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Invitation resultInvitation = new Invitation();
                resultInvitation.setAuthor(rs.getInt("author"));
                resultInvitation.setInvitationId(rs.getInt("invitation_id"));
                resultInvitation.setTitle(rs.getString("title"));
                resultInvitation.setContent(rs.getString("content"));
                resultInvitation.setType(rs.getString("type"));
                resultInvitation.setEssence(rs.getBoolean("is_essence"));
                result.put(new JSONObject(resultInvitation));
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static JSONObject detail(Connection con, int invitationId) {
        JSONObject result = new JSONObject();
        String search = "select * from invitation where invitation_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(search);
            ps.setInt((int)1, invitationId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Invitation invitation = new Invitation();
                invitation.setAuthor(rs.getInt("author"));
                invitation.setInvitationId(rs.getInt("invitation_id"));
                invitation.setTitle(rs.getString("title"));
                invitation.setContent(rs.getString("content"));
                invitation.setType(rs.getString("type"));
                invitation.setEssence(rs.getBoolean("is_essence"));
                result = new JSONObject(invitation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static JSONObject add(Connection con, Invitation invitation) {
        JSONObject result = new JSONObject();
        String message = "insert into invitation (author, title, is_essence, type, content) values (?, ?, ?, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(message);
            ps.setInt((int) 1, invitation.getAuthor());
            ps.setString((int) 2, invitation.getTitle());
            ps.setBoolean((int) 3, invitation.getEssence());
            ps.setString((int) 4, invitation.getType());
            ps.setString((int) 5, invitation.getContent());
            int num = ps.executeUpdate();
            if (num == 0) {
                result.put("status", "Fail");
            } else {
                result.put("status", "OK");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

//    public static JSONObject updateEssence(Connection con, Boolean isEssence) {
//        JSONObject result = new JSONObject();
//        String message = "update "
//    }
}
