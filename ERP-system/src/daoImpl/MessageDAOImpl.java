package daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Message;
import dao.MessageDAO;
import util.DBUtil;
import util.Page;

public class MessageDAOImpl implements MessageDAO {

	@Override
	public void addMessage(Message message) {
		// 获取数据库连接
		Connection connection = DBUtil.getConnection();
		// 声明sql语句
		String sql = "insert into message(messageTitle,messageContent,employeeID,publishTime) " + "values(?,?,?,?)";
		// 声明预编译语句
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			/*
			 * 设置各个查询变量
			 */
			preparedStatement.setString(1, message.getMessageTitle());
			preparedStatement.setString(2, message.getMessageContent());
			preparedStatement.setInt(3, message.getEmployeeID());
			// Date的形式转换
			preparedStatement.setDate(4, new Date(message.getPublishTime().getTime()));

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 关闭变量
			DBUtil.closeConnection(null, preparedStatement, connection);
		}

	}

	@Override
	public List<Message> getMessageList(Page page) {
		// 获取数据库连接
		Connection connection = DBUtil.getConnection();
		// 声明sql语句
		String sql = "select * from message order by publishTime desc limit ?,?";
		/*
		 * 声明预编译语句，查询结果集
		 */
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Message> list = new ArrayList<Message>();

		try {
			preparedStatement = connection.prepareStatement(sql);
			/*
			 * 设置查询变量
			 */
			preparedStatement.setInt(1, page.getBeginIndex());
			preparedStatement.setInt(2, page.getEveryPage());
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Message message = new Message();
				/*
				 * 取得查询结果的参数
				 */
				message.setMessageID(resultSet.getInt(1));
				message.setMessageTitle(resultSet.getString(2));
				message.setMessageContent(resultSet.getString(3));
				message.setEmployeeID(resultSet.getInt(4));
				message.setPublishTime(resultSet.getDate(5));
				// 将结果插入列表
				list.add(message);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(resultSet, preparedStatement, connection);
		}
		// 返回查询列表
		return list;
	}

	@Override
	public Message getMessage(int messageID) {
		// 获取数据库连接
		Connection connection = DBUtil.getConnection();
		// 声明sql语句
		String sql = "select * from message where messageID=?";
		/*
		 * 声明预编译语句，查询结果集
		 */
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Message message = null;

		try {
			preparedStatement = connection.prepareStatement(sql);
			/*
			 * 设置查询变量
			 */
			preparedStatement.setInt(1, messageID);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				message = new Message();
				/*
				 * 取得查询结果的参数
				 */
				message.setMessageID(resultSet.getInt(1));
				message.setMessageTitle(resultSet.getString(2));
				message.setMessageContent(resultSet.getString(3));
				message.setEmployeeID(resultSet.getInt(4));
				message.setPublishTime(resultSet.getDate(5));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(resultSet, preparedStatement, connection);
		}
		// 返回查询列表
		return message;
	}

	@Override
	public int getMessagesCount() {
		// 获取数据库连接
		Connection connection = DBUtil.getConnection();
		// 声明sql语句
		String sql = "select count(*) from message";
		/*
		 * 声明预编译语句，查询结果集
		 */
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int messagesCount = 0;

		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				/*
				 * 取得查询结果的参数
				 */
				messagesCount = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(resultSet, preparedStatement, connection);
		}
		// 返回查询列表
		return messagesCount;
	}

}
