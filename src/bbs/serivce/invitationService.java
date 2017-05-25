package bbs.serivce;

import bbs.dao.InvitaionDao;
import bbs.util.DbUtil;
import org.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Map;

/**
 * Created by sjf on 5/24/17.
 */

@WebServlet("/invitation")
public class invitationService extends HttpServlet {
    protected void service (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        this.doGet(req, res);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("application/json; charset=utf-8");
        try {
            Connection con = new DbUtil().getCon();
            System.out.println("pageSize is " + req.getParameter("pageSize"));
            int pageNum = Integer.parseInt(req.getParameter("pageNum"));
            int pageSize = Integer.parseInt(req.getParameter("pageSize"));
            JSONArray invitationList = InvitaionDao.list(con, pageNum, pageSize);
            PrintWriter out = res.getWriter();
            out.print(invitationList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void destroy () {

    }
}
