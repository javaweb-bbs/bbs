package bbs.serivce;

import bbs.dao.InvitationDao;
import bbs.model.Invitation;
import bbs.util.DbUtil;
import bbs.util.getParams;
import org.json.JSONObject;

import javax.servlet.ServletException;
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
                if (req.getParameter("pageNum") != null) {
                    pageNum = Integer.parseInt(req.getParameter("pageNum"));
                }
                if (req.getParameter("pageSize") != null) {
                    pageSize = Integer.parseInt(req.getParameter("pageSize"));
                }
                JSONObject invitationList = InvitationDao.list(con, pageNum, pageSize);
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
            Boolean isEssence = params.getBoolean("isEssence");
            int invitationId = params.getInt("invitationId");
            JSONObject updateResult = InvitationDao.updateEssence(con, isEssence, invitationId);
            PrintWriter out = res.getWriter();
            out.print(updateResult);
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
