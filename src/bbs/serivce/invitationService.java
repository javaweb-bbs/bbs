package bbs.serivce;

import bbs.dao.InvitaionDao;
import bbs.model.Invitation;
import bbs.util.DbUtil;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Connection;

/**
 * Created by sjf on 5/24/17.
 */

@WebServlet("/invitation")
public class invitationService extends HttpServlet {
    Connection con = null;

    public static JSONObject getParams(HttpServletRequest req) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream) req.getInputStream(), "utf-8"));
        StringBuffer sb = new StringBuffer("");
        String temp;
        while ((temp = br.readLine()) != null) {
            sb.append(temp);
        }
        br.close();
        JSONObject params = new JSONObject(sb.toString());
        return params;
    }

    public void init() {
        try {
            con = new DbUtil().getCon();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("application/json; charset=utf-8");
        try {
            int pageNum = Integer.parseInt(req.getParameter("pageNum"));
            int pageSize = Integer.parseInt(req.getParameter("pageSize"));
            JSONArray invitationList = InvitaionDao.list(con, pageNum, pageSize);
            PrintWriter out = res.getWriter();
            out.print(invitationList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("application/json;charset=utf-8");
        try {
            JSONObject params = getParams(req);
            Boolean isEssence = params.getBoolean("isEssence");
            int invitationId = params.getInt("invitationId");
            JSONObject updateResult = InvitaionDao.updateEssence(con, isEssence, invitationId);
            PrintWriter out = res.getWriter();
            out.print(updateResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doPut(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("application/json");
        try {
            JSONObject params = getParams(req);
            Invitation newInvitation = new Invitation();
            newInvitation.setTitle(params.getString("title"));
            newInvitation.setContent(params.getString("content"));
            newInvitation.setAuthor(params.getInt("author"));
            newInvitation.setType(params.getString("type"));
            newInvitation.setEssence(params.getBoolean("isEssence"));
            JSONObject addResult = InvitaionDao.add(con, newInvitation);
            PrintWriter out = res.getWriter();
            out.print(addResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void destroy () {

    }
}
