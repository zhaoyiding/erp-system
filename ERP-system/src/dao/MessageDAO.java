package dao;

import java.util.List;

import bean.Message;
import util.Page;

public interface MessageDAO {
	// 添加消息
	public void addMessage(Message message);

	// 按页获取消息列表
	public List<Message> getMessageList(Page page);

	// 按编号获取消息
	public Message getMessage(int messageID);

	// 获取消息总数
	public int getMessagesCount();
}
