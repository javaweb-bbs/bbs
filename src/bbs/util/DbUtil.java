package bbs.util;
import java.sql.Connection;
import java.sql.DriverManager;

public class DbUtil {
	private String dbUrl="jdbc:mysql://localhost:3306/bbs";
	private String dbUserName="root";
	private String dbPasssword="1972886479";
	private String jdbcName="com.mysql.jdbc.Driver";

	//	链接数据库
	public Connection getCon()throws Exception{
		Class.forName(jdbcName);
		Connection con=DriverManager.getConnection(dbUrl,dbUserName,dbPasssword);
		return con;
	}

	// 关闭数据库链接
	public void closeCon(Connection con)throws Exception{
		if(con!=null){
			con.close();
		}
	}
	public static void main(String[] args) {
		DbUtil dbutil = new DbUtil();
		try {
			dbutil.getCon();
			System.out.println("链接数据库成功");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("链接数据库失败");
		}
	}
}
