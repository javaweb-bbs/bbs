import java.sql.*;

/**
 * Created by sjf on 5/23/17.
 */

public class connectSql {
    private static Statement stmt;
    private static Connection conn;

    public connectSql() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            System.out.println("成功加载到mysql驱动");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String url = "jdbc:mysql://localhost:3306/bbs?allowMultiQueries=true";

        try {
            conn = DriverManager.getConnection(url, "root", "sjf978977");
            stmt = conn.createStatement();
            System.out.println("成功连接到数据库");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Statement getStat() {
        return stmt;
    }

    public static Connection getConn() {
        return conn;
    }
}