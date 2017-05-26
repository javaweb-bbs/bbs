package bbs.serivce;

import bbs.dao.UserDao;
import bbs.model.User;
import bbs.util.DbUtil;
import bbs.util.getParams;
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
 * Created by sjf on 5/26/17.
 */
@WebServlet("/user")
public class userService extends HttpServlet {
    protected DbUtil util = new DbUtil();

    // 获取用户信息
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            Connection con = util.getCon();
            if (req.getParameter("userId") != null) {
                int userId = Integer.parseInt(req.getParameter("userId"));
                JSONObject result = UserDao.detail(con, userId);
                PrintWriter out = res.getWriter();
                out.print(result);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    // 注册
    public void doPut(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            Connection con = util.getCon();
            JSONObject params = getParams.get(req);
            User user = new User();
            user.setUserName(params.getString("username"));
            user.setPassWord(params.getString("password"));
            user.setEmail(params.getString("email"));
            JSONObject result = UserDao.register(con, user);
            PrintWriter out = res.getWriter();
            out.print(result);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    // 登录和更改信息
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            Connection con = util.getCon();
            JSONObject params = getParams.get(req);
            JSONObject result = new JSONObject();
            User user = new User();
            if (params.getString("action").equals("login")) {
                user.setUserName(params.getString("username"));
                user.setPassWord(params.getString("password"));
                result = UserDao.login(con, user);
            } else if (params.getString("action").equals("update")) {
                user.setSex(params.getInt("sex"));
                user.setEmail(params.getString("email"));
                user.setUserId(params.getInt("userId"));
                result = UserDao.updateUser(con, user);
            } else {
                user.setPassWord(params.getString("password"));
                user.setUserId(params.getInt("userId"));
                result = UserDao.updatePassword(con, user);
            }
            PrintWriter out = res.getWriter();
            out.print(result);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
