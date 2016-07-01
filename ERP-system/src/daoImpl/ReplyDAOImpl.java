package daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Reply;
import dao.ReplyDAO;
import util.DBUtil;
import util.Page;

public class ReplyDAOImpl implements ReplyDAO {

	@Override
	public void addReply(Reply reply) {
		// 获取数据库连接
		Connection connection = DBUtil.getConnection();
		// 声明sql语句
		String sql = "insert into reply(replyContent,employeeID,messageID,replyTime) " + "values(?,?,?,?)";
		// 声明预编译语句
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			/*
			 * 设置各个查询变量
			 */
			preparedStatement.setString(1, reply.getReplyContent());
			preparedStatement.setInt(2, reply.getEmployeeID());
			preparedStatement.setInt(3, reply.getMessageID());
			// Date的形式转换
			preparedStatement.setDate(4, new Date(reply.getReplyTime().getTime()));

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
	public List<Reply> getReplyList(int messageID, Page page) {
		// 获取数据库连接
		Connection connection = DBUtil.getConnection();
		// 声明sql语句
		String sql = "select * from reply where messageID=? limit ?,?";
		/*
		 * 声明预编译语句，查询结果集
		 */
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Reply> list = new ArrayList<Reply>();

		try {
			preparedStatement = connection.prepareStatement(sql);
			/*
			 * 设置查询变量
			 */
			preparedStatement.setInt(1, messageID);
			preparedStatement.setInt(2, page.getBeginIndex());
			preparedStatement.setInt(3, page.getEveryPage());
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Reply reply = new Reply();
				/*
				 * 取得查询结果的参数
				 */
				reply.setReplyID(resultSet.getInt(1));
				reply.setReplyContent(resultSet.getString(2));
				reply.setEmployeeID(resultSet.getInt(3));
				reply.setMessageID(resultSet.getInt(4));
				reply.setReplyTime(resultSet.getDate(5));
				// 将结果插入列表
				list.add(reply);
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
	public int getRepliesCount(int messageID) {
		// 获取数据库连接
		Connection connection = DBUtil.getConnection();
		// 声明sql语句
		String sql = "select count(*) from reply where messageID=?";
		/*
		 * 声明预编译语句，查询结果集
		 */
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int repliesCount = 0;

		try {
			preparedStatement = connection.prepareStatement(sql);
			// 设置查找参数
			preparedStatement.setInt(1, messageID);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				/*
				 * 取得查询结果的参数
				 */
				repliesCount = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(resultSet, preparedStatement, connection);
		}
		// 返回查询列表
		return repliesCount;
	}

}
