package servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Employee;
import bean.Message;
import dao.MessageDAO;
import factory.DAOFactory;

/**
 * Servlet implementation class MessagePublishServlet
 */
@WebServlet("/messagePublish")
public class MessagePublishServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置编码方式为utf-8
		request.setCharacterEncoding("utf8");
		/*
		 * 设置消息提交成功和失败时的转发地址
		 */
		String successUrl = "/messageList";
		String failureUrl = "/jsp/messagePublish.jsp";
		/*
		 * 取得提交的标题和内容
		 */
		String messageTitle = request.getParameter("messageTitle");
		String messageContent = request.getParameter("messageContent");
		// 取得此时的是哪个员工在发布消息
		Employee employee = (Employee) request.getSession().getAttribute("employee");

		// 如果员工没有登录，返回
		if (employee == null) {
			request.setAttribute("error", "发布消息必须先登录");
			request.getRequestDispatcher(failureUrl).forward(request, response);
		} else {
			// 如果消息标题为空，返回
			if (messageTitle == null || "".equals(messageTitle)) {
				request.setAttribute("error", "必须输入标题");
				request.getRequestDispatcher(failureUrl).forward(request, response);
			} else {
				Message message = new Message();
				// 设置要发布的消息属性
				message.setMessageTitle(messageTitle);
				message.setMessageContent(messageContent);
				message.setEmployeeID(employee.getEmployeeID());
				message.setPublishTime(new Date());

				// 取得消息工具
				MessageDAO messageDAO = DAOFactory.getMessageDAO();
				// 添加消息
				messageDAO.addMessage(message);
				// 转发到消息列表界面
				request.getRequestDispatcher(successUrl).forward(request, response);
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
