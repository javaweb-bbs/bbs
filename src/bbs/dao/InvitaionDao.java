package bbs.dao;

import bbs.model.Invitation;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
}
