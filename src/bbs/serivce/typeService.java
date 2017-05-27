package bbs.serivce;

import bbs.dao.typeDao;
import bbs.util.DbUtil;
import bbs.util.getParams;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

/**
 * Created by sjf on 5/25/17.
 */
@WebServlet("/type")
public class typeService extends HttpServlet {
    protected DbUtil util = new DbUtil();

    public typeService() throws Exception {
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        try {
            Connection con = util.getCon();
            JSONArray types = typeDao.list(con);
            PrintWriter out = res.getWriter();
            out.print(types);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void doPut(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        try {
            Connection con = util.getCon();
            JSONObject params = getParams.get(req);
            String name = params.getString("name");
            JSONObject addResult = typeDao.add(con, name);
            PrintWriter out = res.getWriter();
            out.print(addResult);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        try {
            Connection con = util.getCon();
            JSONObject params = getParams.get(req);
            String name = params.getString("name");
            JSONObject addResult = typeDao.delete(con, name);
            PrintWriter out = res.getWriter();
            out.print(addResult);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
