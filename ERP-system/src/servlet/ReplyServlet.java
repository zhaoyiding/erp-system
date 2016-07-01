package servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Employee;
import bean.Reply;
import dao.ReplyDAO;
import factory.DAOFactory;

/**
 * Servlet implementation class ReplyServlet
 */
@WebServlet("/reply")
public class ReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置编码方式
		request.setCharacterEncoding("utf8");
		// 获取消息编号，并进行格式转换
		int messageID = Integer.parseInt(request.getParameter("messageID"));
		// 获取回复内容
		String replyContent = request.getParameter("replyContent");
		// 得到此时是哪一个员工在回复
		Employee employee = (Employee) request.getSession().getAttribute("employee");
		// 如果员工没有登录
		if (employee == null) {
			request.setAttribute("error", "回复前必须先登录");
		} else {
			if (replyContent == null || "".equals(replyContent)) {
				request.setAttribute("error", "必须输入回复内容");
			} else {
				// 获取工具
				ReplyDAO replyDAO = DAOFactory.getReplyDAO();
				//
				Reply reply = new Reply();
				/*
				 * 设置该回复的属性
				 */
				reply.setReplyContent(replyContent);
				reply.setEmployeeID(employee.getEmployeeID());
				reply.setMessageID(messageID);
				reply.setReplyTime(new Date());
				// 添加回复
				replyDAO.addReply(reply);
			}
		}
		// 携带消息编号参数，转发到
		request.getRequestDispatcher("/messageDetail?messageID=" + messageID).forward(request, response);
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
