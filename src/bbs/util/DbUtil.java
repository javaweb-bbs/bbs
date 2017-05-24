package bbs.util;
import java.sql.Connection;
import java.sql.DriverManager;

/*
 * 类的备注
 * 数据库工具类
 */
public class DbUtil {
	private String dbUrl="jdbc:mysql://localhost:3306/bbs";
	private String dbUserName="root";//用户名
	private String dbPasssword="1972886479";//密码
	private String jdbcName="com.mysql.jdbc.Driver";
	//获取数据库连接
	public Connection getCon()throws Exception{
		Class.forName(jdbcName);
		Connection con=DriverManager.getConnection(dbUrl,dbUserName,dbPasssword);
		return con;
	}
	//关闭数据库连接
	public void closeCon(Connection con)throws Exception{
		if(con!=null){
			con.close();
		}
	}
	public static void main(String[] args) {
		DbUtil dbutil = new DbUtil();
		try {
			dbutil.getCon();
			System.out.println("数据库连接成功");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("数据库连接失败");
		}
	}
}
