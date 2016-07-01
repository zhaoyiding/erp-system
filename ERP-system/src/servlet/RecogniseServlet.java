package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Employee;
import dao.EmployeeDAO;
import factory.DAOFactory;

/**
 * Servlet implementation class RecogniseServlet
 */
@WebServlet("/recognise")
public class RecogniseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置编码方式为utf-8
		request.setCharacterEncoding("utf8");
		// 设置转发地址
		String dispatcherUrl = "/jsp/recognise.jsp";
		// 设置重定向地址
		String redirectUrl = "jsp/index.jsp";
		// 取得提交的员工编号
		String employeeID = request.getParameter("employeeID");
		// 取得提交的密码
		String password = request.getParameter("password");

		// 输入编号为空
		if (employeeID == null || "".equals(employeeID)) {
			request.setAttribute("error", "必须输入编号");
			request.getRequestDispatcher(dispatcherUrl).forward(request, response);
		} else {
			// 输入密码为空
			if (password == null || "".equals(password)) {
				request.setAttribute("error", "必须输入密码");
				request.getRequestDispatcher(dispatcherUrl).forward(request, response);
			} else {
				// 取得employee工具
				EmployeeDAO employeeDAO = DAOFactory.getEmployeeDAO();
				// 将提交的员工编号从字符串转换为数字形式，然后用该编号查找该员工信息
				Employee employee = employeeDAO.getEmployeeByID(Integer.parseInt(employeeID));

				// 找不到该员工信息
				if (employee == null) {
					request.setAttribute("error", "该员工编号不存在");
					request.getRequestDispatcher(dispatcherUrl).forward(request, response);
				} else {
					// 员工编号正确，并且密码正确，可以通过验证！！
					if (password.equals(employee.getPassword())) {
						// 使用session，将employee相关信息存在sessin中
						request.getSession().setAttribute("employee", employee);
						// 重定向到首页
						response.sendRedirect(redirectUrl);
					} else {
						request.setAttribute("error", "密码不正确");
						request.getRequestDispatcher(dispatcherUrl).forward(request, response);
					}
				}
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
