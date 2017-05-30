package bbs.dao;

import bbs.model.Invitation;
import bbs.util.DbUtil;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by sjf on 5/24/17.
 */
public class InvitationDao {
    // 获取帖子列表
    public static JSONObject list (Connection con, JSONObject filter) throws Exception {
        JSONObject result = new JSONObject();
        JSONArray invitations = new JSONArray();
        String search = "select *, username, (select count(*) from invitation) as total from invitation,user where" +
                        " user.user_id = invitation.author ";
        ArrayList<String> filterValues = new ArrayList<>();
        int count = 0;
        int filterCount = 0;
        int pageNum = 1;
        int pageSize = 10;
        if (filter != null) {
            pageNum = Integer.parseInt(filter.getString("pageNum"));
            pageSize = Integer.parseInt(filter.getString("pageSize"));
            filter.remove("pageSize");
            filter.remove("pageNum");
            Iterator<String> iterator = filter.keys();
            while (iterator.hasNext()) {
                filterCount++;
                String key = iterator.next();
                String value = filter.getString(key);
                search += "and " + key + "= ?";
                filterValues.add(value);
            }
        }
        search += "limit ?,?";
        System.out.println("search is " + search);
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(search);
            for (int i = 0; i < filterCount; i++) {
                ps.setString((int) i + 1, filterValues.get(i));
            }
            ps.setInt((int) filterCount + 1, (pageNum - 1) * pageSize);
            ps.setInt((int) filterCount + 2, pageSize);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (count == 0) {
                    result.put("total", rs.getInt("total"));
                }
                Invitation resultInvitation = new Invitation();
                resultInvitation.setAuthor(rs.getInt("author"));
                resultInvitation.setInvitationId(rs.getInt("invitation_id"));
                resultInvitation.setTitle(rs.getString("title"));
                resultInvitation.setContent(rs.getString("content"));
                resultInvitation.setType(rs.getString("type"));
                resultInvitation.setEssence(rs.getBoolean("is_essence"));
                resultInvitation.setDateCreate(rs.getDate("date_create"));
                resultInvitation.setAuthorName(rs.getString("username"));
                invitations.put(new JSONObject(resultInvitation));
                count++;
            }
            result.put("invitations", invitations);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ps.close();
            new DbUtil().closeCon(con);
        }
        return result;
    }
    // 获取帖子详情
    public static JSONObject detail(Connection con, int invitationId) throws Exception {
        JSONObject result = new JSONObject();
        String search = "select *, username from invitation, user where invitation.author = user.user_id " +
                        "and invitation.invitation_id = ?";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(search);
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
                invitation.setDateCreate(rs.getDate("date_create"));
                invitation.setAuthorName(rs.getString("username"));
                result = new JSONObject(invitation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ps.close();
            new DbUtil().closeCon(con);
        }
        return result;
    }

    // 添加帖子
    public static JSONObject add(Connection con, Invitation invitation) throws Exception {
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
        } finally {
            ps.close();
            new DbUtil().closeCon(con);
        }
        return result;
    }

    // 更改帖子信息
    public static JSONObject updateInfo(Connection con, Invitation invitation) throws Exception {
        JSONObject result = new JSONObject();
        String updateMes = "update invitation set title = ?, type = ?, content = ? where invitation_id = ?";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(updateMes);
            ps.setString((int) 1, invitation.getTitle());
            ps.setString((int) 2, invitation.getType());
            ps.setString((int) 3, invitation.getContent());
            ps.setInt((int) 4, invitation.getInvitationId());
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

    // 设置精华帖
    public static JSONObject updateEssence(Connection con, Boolean isEssence, int invitationId) throws Exception {
        JSONObject result = new JSONObject();
        String message = "update invitation set is_essence = ? where invitation_id = ?";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(message);
            ps.setBoolean((int) 1, isEssence);
            ps.setInt((int) 2, invitationId);
            int num = ps.executeUpdate();
            if (num == 0) {
                result.put("status", "Fail");
            } else {
                result.put("status", "OK");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ps.close();
            new DbUtil().closeCon(con);
        }
        return result;
    }

    // 删除帖子
    public static JSONObject delete(Connection con, int invitationId) throws Exception {
        JSONObject result = new JSONObject();
        String deleteInvitation = "delete from invitation where invitation_id = ?";

        PreparedStatement invitationPs = null;
        try {
            invitationPs = con.prepareStatement(deleteInvitation);
            invitationPs.setInt((int) 1, invitationId);
            int num = invitationPs.executeUpdate();
            if (num == 0) {
                result.put("status", "delete fail");
            } else {
                JSONObject deleteComment =  commentDao.delete(con, 0, invitationId);
                result.put("status", "delete success");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            invitationPs.close();
            new DbUtil().closeCon(con);
        }
        return result;
    }

    public static JSONObject search(Connection con, String title) throws Exception {
        JSONObject result = new JSONObject();
        String search = "select *, username,(select count(*) from invitation where title like ?) as total from invitation, user " +
                "where user.user_id = invitation.author and invitation.title like ?";
        PreparedStatement ps = null;
        int count = 0;
        try {
            ps = con.prepareStatement(search);
            ps.setString((int) 1, "%" + title + "%");
            ps.setString((int) 2, "%" + title + "%");
            ResultSet rs = ps.executeQuery();
            JSONArray invitations = new JSONArray();
            result.put("total", 0);
            while (rs.next()) {
                Invitation searchInvitation = new Invitation();
                searchInvitation.setAuthor(rs.getInt("author"));
                searchInvitation.setInvitationId(rs.getInt("invitation_id"));
                searchInvitation.setTitle(rs.getString("title"));
                searchInvitation.setContent(rs.getString("content"));
                searchInvitation.setType(rs.getString("type"));
                searchInvitation.setEssence(rs.getBoolean("is_essence"));
                searchInvitation.setDateCreate(rs.getDate("date_create"));
                searchInvitation.setAuthorName(rs.getString("username"));
                invitations.put(new JSONObject(searchInvitation));
                if (count == 0) {
                    result.put("total", rs.getInt("total"));
                }
                count++;
            }
            result.put("invitations", invitations);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ps.close();
            new DbUtil().closeCon(con);
        }
        return result;
    }
}
