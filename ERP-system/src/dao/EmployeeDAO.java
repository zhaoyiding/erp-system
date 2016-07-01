package dao;

import bean.Employee;

public interface EmployeeDAO {
	//由编号查询员工信息
	public Employee getEmployeeByID(int employeeID);
}
