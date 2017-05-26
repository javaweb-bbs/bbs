package bbs.serivce;

import bbs.util.DbUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

/**
 * Created by sjf on 5/26/17.
 */
@WebServlet("/user")
public class userService extends HttpServlet {
    protected DbUtil util = new DbUtil();

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            util.getCon();
            if (req.getParameter("userId") != null) {

            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void doPut(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
