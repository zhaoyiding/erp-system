package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Message;
import dao.MessageDAO;
import factory.DAOFactory;
import util.Page;
import util.PageUtil;

/**
 * Servlet implementation class MessageListServlet
 */
@WebServlet("/messageList")
public class MessageListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置转发地址
		String dispatcherUrl = "/jsp/messageList.jsp";
		// 默认设置当前页为1
		int currentPage = 1;
		// 获取当前页参数
		String currentPageStr = request.getParameter("currentPage");
		// 如果存在该参数，则设置为当前页
		if (currentPageStr != null) {
			currentPage = Integer.parseInt(currentPageStr);
		}

		// 获取工具
		MessageDAO messageDAO = DAOFactory.getMessageDAO();
		// 创建页面信息
		Page page = PageUtil.getPage(5, messageDAO.getMessagesCount(), currentPage);
		// 根据页面信息取得一定数量的消息
		List<Message> messageList = messageDAO.getMessageList(page);

		/*
		 * 存储消息列表和页面信息，转发到消息列表
		 */
		request.setAttribute("list", messageList);
		request.setAttribute("page", page);
		request.getRequestDispatcher(dispatcherUrl).forward(request, response);
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
