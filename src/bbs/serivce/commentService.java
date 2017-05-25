package bbs.serivce;

import bbs.util.DbUtil;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;

/**
 * Created by sjf on 5/25/17.
 */
@WebServlet("/comment")
public class commentService {
    protected Connection con = new DbUtil().getCon();

    public commentService() throws Exception {
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    }
}
