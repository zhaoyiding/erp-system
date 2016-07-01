package bean;

import java.util.Date;

/*
 * 员工信息类
 */
public class Employee {
	private int employeeID;// 编号
	private String employeeName;// 名字
	private boolean employeeGender;// 性别
	private Date employeeBirth;// 生日
	private String employeePhone;// 手机号
	private String password;// 密码

	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public boolean isEmployeeGender() {
		return employeeGender;
	}

	public void setEmployeeGender(boolean employeeGender) {
		this.employeeGender = employeeGender;
	}

	public Date getEmployeeBirth() {
		return employeeBirth;
	}

	public void setEmployeeBirth(Date employeeBirth) {
		this.employeeBirth = employeeBirth;
	}

	public String getEmployeePhone() {
		return employeePhone;
	}

	public void setEmployeePhone(String employeePhone) {
		this.employeePhone = employeePhone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
