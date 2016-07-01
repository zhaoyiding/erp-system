package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Message;
import bean.Reply;
import dao.MessageDAO;
import dao.ReplyDAO;
import factory.DAOFactory;
import util.Page;
import util.PageUtil;

/**
 * Servlet implementation class MessageDetailServlet
 */
@WebServlet("/messageDetail")
public class MessageDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置转发地址
		String dispatcherUrl = "/jsp/messageDetail.jsp";
		// 默认设置当前页为1
		int currentPage = 1;
		// 获取当前页参数
		String currentPageStr = request.getParameter("currentPage");
		// 如果存在该参数，则设置为当前页
		if (currentPageStr != null) {
			currentPage = Integer.parseInt(currentPageStr);
		}
		/*
		 * 取得消息编号，并进行类型转换
		 */
		String messageIDStr = request.getParameter("messageID");
		int messageID = Integer.parseInt(messageIDStr);
		
		// 取得消息工具
		MessageDAO messageDAO = DAOFactory.getMessageDAO();
		// 根据编号查找消息
		Message message = messageDAO.getMessage(messageID);
		// 存储消息
		request.setAttribute("message", message);
		
		//取得回复工具
		ReplyDAO replyDAO=DAOFactory.getReplyDAO();
		//创建页面信息
		Page page=PageUtil.getPage(5, replyDAO.getRepliesCount(messageID), currentPage);
		//根据消息编号、页面信息取得一定数量的回复
		List<Reply> replyList=replyDAO.getReplyList(messageID, page);
		/*
		 * 存储参数
		 */
		request.setAttribute("page", page);
		request.setAttribute("replyList", replyList);
		
		//转发到消息详情页面
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
