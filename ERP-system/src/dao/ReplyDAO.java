package dao;

import java.util.List;

import bean.Reply;
import util.Page;

public interface ReplyDAO {
	//添加回复
	public void addReply(Reply reply);
	
	//根据消息编号、页面信息获取消息列表
	public List<Reply> getReplyList(int messageID, Page page);

	//获取对应同一消息的回复
	public int getRepliesCount(int messageID);
}
