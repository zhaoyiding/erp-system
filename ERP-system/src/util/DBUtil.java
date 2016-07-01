package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {
	private static final String driver = "com.mysql.jdbc.Driver";// 驱动
	private static final String url = "jdbc:mysql://localhost:3306/erpsystem";// 数据库url
	private static final String user = "root";// 数据库用户
	private static final String password = "admin";// 数据库密码

	public static Connection getConnection() {
		Connection connection = null;
		try {
			// 加载驱动
			Class.forName(driver);
			// 获取数据库连接
			connection = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public static void closeConnection(ResultSet resultSet, PreparedStatement preparedStatement,
			Connection connection) {
		// 关闭结果集
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// 关闭预编译语句
		if (preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// 关闭数据库连接
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
