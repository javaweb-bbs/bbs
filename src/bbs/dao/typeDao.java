package bbs.dao;

import bbs.util.DbUtil;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by sjf on 5/24/17.
 */
public class typeDao {
    public static JSONArray list(Connection con) throws Exception {
        JSONArray result = new JSONArray();
        String search = "select * from invitation_type";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(search);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.put(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ps.close();
            new DbUtil().closeCon(con);
        }
        return result;
    }

    public static JSONObject add(Connection con, String type) throws Exception {
        JSONObject result = new JSONObject();
        String message = "insert into invitation_type (name) values (?)";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(message);
            ps.setString((int) 1, type);
            int num = ps.executeUpdate();

            if (num == 0) {
                result.put("status", "add failer");
            } else {
                result.put("status", "add success");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ps.close();
            new DbUtil().closeCon(con);
        }
        return result;
    }

    public static JSONObject delete(Connection con, String type) throws Exception {
        JSONObject result = new JSONObject();
        String deleteType = "delete from invitation_type where name = ?";

        PreparedStatement typePs = null;
        try {
            typePs = con.prepareStatement(deleteType);
            typePs.setString((int) 1, type);
            int num = typePs.executeUpdate();
            if (num == 0) {
                result.put("status", "delete fail");
            } else {
                result.put("status", "delete success");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            typePs.close();
            new DbUtil().closeCon(con);
        }
        return result;
    }
}
