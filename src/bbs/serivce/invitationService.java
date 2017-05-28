package bbs.serivce;

import bbs.dao.InvitationDao;
import bbs.model.Invitation;
import bbs.util.DbUtil;
import bbs.util.Stringutil;
import bbs.util.getParams;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Connection;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by sjf on 5/24/17.
 */

@WebServlet("/invitation")
public class invitationService extends HttpServlet {
    /**
	 * 
	 */
	protected DbUtil util = new DbUtil();

    public invitationService() throws Exception {
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        try {
            Connection con = util.getCon();
            PrintWriter out = res.getWriter();
            if (req.getParameter("invitationId") != null) {
                int invitationId = Integer.parseInt(req.getParameter("invitationId"));
                JSONObject invitation = InvitationDao.detail(con, invitationId);
                out.print(invitation);
            } else {
                int pageNum = 1;
                int pageSize = 10;
                JSONObject filter = new JSONObject();
                Map<String, String[]> params = req.getParameterMap();
                Iterator<Map.Entry<String, String[]>> entries = params.entrySet().iterator();
                while (entries.hasNext()) {
                    Map.Entry<String, String[]> entry = entries.next();
                    System.out.println("Key = " + entry.getKey() + ", Value = " + Stringutil.arrToString(entry.getValue()));
                    filter.put(entry.getKey(), Stringutil.arrToString(entry.getValue()));
                }
                if (filter.isNull("pageNum")) {
                    filter.put("pageNum", String.valueOf(pageNum));
                }
                if (filter.isNull("pageSize")) {
                    filter.put("pageSize", String.valueOf(pageSize));
                }
                JSONObject invitationList = InvitationDao.list(con, filter);
                out.print(invitationList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        try {
            Connection con = util.getCon();
            JSONObject params = getParams.get(req);
            if (params.getString("action").equals("search")) {
                String title = params.getString("title");
                JSONObject result = InvitationDao.search(con, title);
                PrintWriter out = res.getWriter();
                out.print(result);
            } else {
                Boolean isEssence = params.getBoolean("isEssence");
                int invitationId = params.getInt("invitationId");
                JSONObject updateResult = InvitationDao.updateEssence(con, isEssence, invitationId);
                PrintWriter out = res.getWriter();
                out.print(updateResult);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doPut(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        try {
            Connection con = util.getCon();
            JSONObject params = getParams.get(req);
            Invitation newInvitation = new Invitation();
            newInvitation.setTitle(params.getString("title"));
            newInvitation.setContent(params.getString("content"));
            newInvitation.setAuthor(params.getInt("author"));
            newInvitation.setType(params.getString("type"));
            newInvitation.setEssence(params.getBoolean("isEssence"));
            JSONObject addResult = InvitationDao.add(con, newInvitation);
            PrintWriter out = res.getWriter();
            out.print(addResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        try {
            Connection con = util.getCon();
            JSONObject params = getParams.get(req);
            int invitationId = params.getInt("invitationId");
            JSONObject deleteResult = InvitationDao.delete(con, invitationId);
            PrintWriter out = res.getWriter();
            out.print(deleteResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
