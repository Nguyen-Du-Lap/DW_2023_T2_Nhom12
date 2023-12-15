package loadAggregate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
	public Connection getConnection(String db_name, String server_name, String port, String username, String password) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://" + server_name + ":" + port + "/" + db_name;
			Connection connect = DriverManager.getConnection(url, username, password);
			return connect;
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;

	}

	public boolean isConnect(String db_name, String server_name, String port, String username, String password) {
		Connection connection = getConnection(db_name, server_name, port, username, password);
		if (connection != null) {
			try {
				// Kiểm tra nếu kết nối hợp lệ trong vòng 5 giây
				return connection.isValid(5);
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
		return false;

	}

	public static void main(String[] args) {
		ConnectDB connect = new ConnectDB();
	    System.out.println(connect.getConnection("Database_Control", "127.0.0.1", "3306", "root", "123456789"));
		System.out.println(connect.isConnect("Database_Control", "127.0.0.1", "3306", "root", "123456789"));

	}

}