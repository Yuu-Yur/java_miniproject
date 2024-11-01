package signinpage;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {
	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String userid = "scott";
	private static final String passwd = "tiger";

	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
}
