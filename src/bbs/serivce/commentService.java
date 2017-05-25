package bbs.serivce;

import bbs.dao.commentDao;
import bbs.model.Comment;
import bbs.util.DbUtil;
import bbs.util.getParams;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

/**
 * Created by sjf on 5/25/17.
 */
@WebServlet("/comment")
public class commentService extends HttpServlet {
    protected Connection con = new DbUtil().getCon();

    public commentService() throws Exception {
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        try {
            int invitationId = Integer.parseInt(req.getParameter("invitationId"));
            JSONArray comments = commentDao.list(con, invitationId);
            PrintWriter out = res.getWriter();
            out.print(comments);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void doPut(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        try {
            JSONObject params = getParams.get(req);
            Comment comment = new Comment();
            comment.setAnswerUser(params.getInt("answerUser"));
            comment.setInvitation(params.getInt("invitation"));
            comment.setContent(params.getString("content"));
            comment.setAnswerUser(params.getInt("answerUser"));
            JSONObject addComment = commentDao.add(con, comment);
            PrintWriter out = res.getWriter();
            out.print(addComment);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setCharacterEncoding("UTF-8");
        res.setContentType("application/json");
        try {
            JSONObject params = getParams.get(req);
            int commentId = params.getInt("commentId");
            JSONObject deleteMes = commentDao.delete(con, commentId, 0);
            PrintWriter out = res.getWriter();
            out.print(deleteMes);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
