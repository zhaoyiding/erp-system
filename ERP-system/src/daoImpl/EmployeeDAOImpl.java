package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.Employee;
import dao.EmployeeDAO;
import util.DBUtil;

public class EmployeeDAOImpl implements EmployeeDAO {

	@Override
	public Employee getEmployeeByID(int employeeID) {
		// 获取数据库连接
		Connection connection = DBUtil.getConnection();
		// 查询语句
		String sql = "select * from employee where employeeID=?";
		/*
		 * 分别声明预编译语句，结果集，查询对象
		 */
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Employee employee = null;

		try {
			preparedStatement = connection.prepareStatement(sql);
			// 设置查询参数位员工编号
			preparedStatement.setInt(1, employeeID);
			resultSet = preparedStatement.executeQuery();

			// 得到查询结果
			if (resultSet.next()) {
				// 创建查询对象
				employee = new Employee();
				/*
				 * 用得到的查询结果，分别设置查询对象的各个属性
				 */
				employee.setEmployeeID(resultSet.getInt(1));
				employee.setEmployeeName(resultSet.getString(2));
				employee.setEmployeeGender(resultSet.getBoolean(3));
				employee.setEmployeeBirth(resultSet.getDate(4));
				employee.setEmployeePhone(resultSet.getString(5));
				employee.setPassword(resultSet.getString(6));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 关闭三个对象
			DBUtil.closeConnection(resultSet, preparedStatement, connection);
		}
		// 返回查询对象
		return employee;
	}

}
